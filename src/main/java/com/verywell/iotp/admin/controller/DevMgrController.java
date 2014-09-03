package com.verywell.iotp.admin.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.verywell.framework.commons.ResultInfo;
import com.verywell.framework.commons.Tree;
import com.verywell.framework.commons.TreeNode;
import com.verywell.framework.controller.BaseController;
import com.verywell.iotp.admin.commons.LoginToken;
import com.verywell.iotp.admin.constants.RequestNameConstants;
import com.verywell.iotp.admin.constants.ResultConstants;
import com.verywell.iotp.admin.entity.dev.DevAttrInfo;
import com.verywell.iotp.admin.entity.dev.DevAttributes;
import com.verywell.iotp.admin.entity.dev.DevClassGroupInfo;
import com.verywell.iotp.admin.entity.dev.DevClassInfo;
import com.verywell.iotp.admin.entity.dev.DevInfo;
import com.verywell.iotp.admin.entity.map.MapRoomInfo;
import com.verywell.iotp.admin.service.DevAttributesService;
import com.verywell.iotp.admin.service.DevClassGroupInfoService;
import com.verywell.iotp.admin.service.DevClassInfoService;
import com.verywell.iotp.admin.service.DevInfoService;
import com.verywell.iotp.admin.service.MapBuildingService;
import com.verywell.iotp.admin.service.MapRoomService;

/**
 * 房间监控控制器
 * 
 * @author yao
 * 
 */
@Controller
@RequestMapping("/devMgr")
public class DevMgrController extends BaseController
{
	private final String[] INFORMATION_PARAMAS = { "设备" };
	@Autowired
	private MapRoomService roomService;
	@Autowired
	private DevInfoService devInfoService;
	@Autowired
	private DevClassInfoService devClassInfoService;
	@Autowired
	private MapBuildingService buildingService;

	@Autowired
	private DevClassGroupInfoService devClassGroupInfoService;

	// 基础目录
	private final String BASE_DIR = "/dev_mgr/dev_mgr/";

	@RequestMapping("/roomDetail")
	public String roomDetail(HttpServletRequest request, Model model, Long roomId) throws Exception
	{
		LoginToken loginToken = this.getLoginToken();
		// 当前用户拥有房间权限
		Map<Long, MapRoomInfo> roomPermissions = loginToken.getRoomPermissions();
		if (roomId == null)
		{
			for (Long key : roomPermissions.keySet())
			{
				roomId = key;
				break;
			}
		}
		// 房间树形菜单
		Tree tree = this.getLoginToken().getRoomTree(request);
		for (TreeNode treeNode : tree)
		{
			if (!treeNode.getpId().equals(Tree.DEFAULT_ROOT_ID))
			{
				treeNode.setUrl(request.getContextPath() + "/devMgr/roomDetail?roomId=" + treeNode.getId());
				treeNode.setTarget("mainFrame");
			}
		}

		// 房间信息
		MapRoomInfo roomInfo = roomService.findById(roomId);
		// 设备信息
		List<DevInfo> devList = devInfoService.findByRoomId(roomId);

		model.addAttribute("mapTree", tree);
		model.addAttribute("roomInfo", roomInfo);
		model.addAttribute("devList", devList);
		return BASE_DIR + "index";
	}

	/**
	 * 根据房间和设备类型分组查询设备列表
	 * 
	 * @param request
	 * @param model
	 * @param roomId
	 * @param classGroupId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/devList/{roomId}/{classGroupId}")
	public String devList(HttpServletRequest request, Model model, @PathVariable("roomId") Long roomId, @PathVariable("classGroupId") Long classGroupId)
			throws Exception
	{
		List<DevInfo> devList = devInfoService.findByClassGroupId(roomId, classGroupId);
		DevClassGroupInfo classGroupInfo = devClassGroupInfoService.findById(classGroupId);
		model.addAttribute(RequestNameConstants.RESULT_LIST, devList);
		model.addAttribute("classGroupInfo", classGroupInfo);
		return BASE_DIR + "dev_list";
	}

	/**
	 * 进入设备新增页面
	 * 
	 * @param request
	 * @param model
	 * @param roomId
	 * @param posX
	 * @param posY
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/add/{roomId}/{posX}/{posY}")
	public String add(HttpServletRequest request, Model model, @PathVariable("roomId") Long roomId, @PathVariable("posX") Integer posX,
			@PathVariable("posY") Integer posY) throws Exception
	{
		// 房间信息
		MapRoomInfo roomInfo = roomService.findById(roomId);
		List<DevClassInfo> devClassList = devClassInfoService.findAll();
		model.addAttribute("devClassList", devClassList);
		model.addAttribute("roomInfo", roomInfo);
		DevInfo devInfo = new DevInfo();
		devInfo.setRoomId(roomId);
		devInfo.setPositionX(posX);
		devInfo.setPositionY(posY);
		model.addAttribute(RequestNameConstants.RESULT_OBJECT, devInfo);
		return BASE_DIR + "dev_add";
	}

	/**
	 * 设备新增保存
	 * 
	 * @param request
	 * @param model
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	@ResponseBody
	public ResultInfo save(HttpServletRequest request, Model model, DevInfo entity) throws Exception
	{
		entity.setDevStatus(DevInfo.DEV_STATUS_OPEN);
		entity.setAlarmStatus(DevInfo.ALARM_STATUS_NORMAL);
		entity.setAlaramSwitch(DevInfo.ALARM_SWITCH_ON);
		entity.setAlarmReceiver(this.getLoginToken().getSysLogin().getLoginName());
		int resultTag = devInfoService.save(entity);
		if (resultTag == ResultConstants.SAVE_SUCCEED)
		{
			return ResultInfo.saveMessage(ResultConstants.getResultInfo(resultTag, INFORMATION_PARAMAS), null);
		}
		else
		{
			return ResultInfo.saveErrorMessage(ResultConstants.getResultInfo(resultTag, INFORMATION_PARAMAS));
		}
	}

	/**
	 * 进入设备编辑
	 * 
	 * @param request
	 * @param model
	 * @param devId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/edit/{devId}")
	public String edit(HttpServletRequest request, Model model, @PathVariable("devId") Long devId) throws Exception
	{
		List<DevClassInfo> devClassList = devClassInfoService.findAll();
		model.addAttribute("devClassList", devClassList);
		DevInfo devInfo = devInfoService.findById(devId);
		model.addAttribute(RequestNameConstants.RESULT_OBJECT, devInfo);
		return BASE_DIR + "dev_edit";
	}

	/**
	 * 设备更新
	 * 
	 * @param request
	 * @param model
	 * @param devInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/update")
	@ResponseBody
	public ResultInfo update(HttpServletRequest request, Model model, DevInfo devInfo) throws Exception
	{
		DevInfo entity = devInfoService.findById(devInfo.getDevId());
		BeanUtils.copyProperties(devInfo, entity);
		int resultTag = devInfoService.update(entity);
		if (resultTag == ResultConstants.UPDATE_SUCCEED)
		{
			return ResultInfo.saveMessage(ResultConstants.getResultInfo(resultTag, INFORMATION_PARAMAS), null);
		}
		else
		{
			return ResultInfo.saveErrorMessage(ResultConstants.getResultInfo(resultTag, INFORMATION_PARAMAS));
		}
	}

	/**
	 * 设备位置更新
	 * 
	 * @param request
	 * @param model
	 * @param devId
	 * @param posX
	 * @param posY
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updatePosition/{devId}/{posX}/{posY}")
	@ResponseBody
	public ResultInfo updatePosition(HttpServletRequest request, Model model, @PathVariable("devId") Long devId, @PathVariable("posX") Integer posX,
			@PathVariable("posY") Integer posY) throws Exception
	{

		int resultTag = devInfoService.updatePosition(devId, posX, posY);
		if (resultTag == ResultConstants.UPDATE_SUCCEED)
		{
			return ResultInfo.saveMessage(ResultConstants.getResultInfo(resultTag, INFORMATION_PARAMAS), null);
		}
		else
		{
			return ResultInfo.saveErrorMessage(ResultConstants.getResultInfo(resultTag, INFORMATION_PARAMAS));
		}
	}

	@RequestMapping("/delete/{devId}")
	@ResponseBody
	public ResultInfo delete(HttpServletRequest request, Model model, @PathVariable("devId") Long devId) throws Exception
	{

		int resultTag = devInfoService.delete(devId);
		if (resultTag == ResultConstants.DELETE_SUCCEED)
		{
			return ResultInfo.saveMessage(ResultConstants.getResultInfo(resultTag, INFORMATION_PARAMAS), null);
		}
		else
		{
			return ResultInfo.saveErrorMessage(ResultConstants.getResultInfo(resultTag, INFORMATION_PARAMAS));
		}
	}
}
