package com.verywell.iotp.admin.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.verywell.framework.commons.ResultInfo;
import com.verywell.framework.commons.Tree;
import com.verywell.framework.commons.TreeNode;
import com.verywell.framework.controller.BaseController;
import com.verywell.iotp.admin.constants.CommonConstants;
import com.verywell.iotp.admin.constants.RequestNameConstants;
import com.verywell.iotp.admin.constants.ResultConstants;
import com.verywell.iotp.admin.constants.UrlConstants;
import com.verywell.iotp.admin.entity.dev.DevClassGroupInfo;
import com.verywell.iotp.admin.entity.dev.DevInfo;
import com.verywell.iotp.admin.entity.map.MapRoomInfo;
import com.verywell.iotp.admin.entity.scene.SceneConfigInfo;
import com.verywell.iotp.admin.entity.scene.SceneInfo;
import com.verywell.iotp.admin.service.DevInfoService;
import com.verywell.iotp.admin.service.MapRoomService;
import com.verywell.iotp.admin.service.SceneService;

@Controller
@RequestMapping("/sceneMgr")
public class SceneMgrController extends BaseController
{

	private final String[] INFORMATION_PARAMAS = { "场景" };
	// 基础目录
	private final String BASE_DIR = "/config_mgr/scene_mgr/";

	private final String REDIRECT_PATH = "/sceneMgr/";

	@Autowired
	private MapRoomService roomService;

	@Autowired
	private SceneService sceneService;

	@Autowired
	private DevInfoService devInfoService;

	/** 列表查询 */
	@RequestMapping(value = "/{roomId}")
	public String index(HttpServletRequest request, Model model, @PathVariable("roomId") Long roomId) throws Exception
	{
		List<SceneInfo> sceneList = sceneService.findByRoomId(roomId);
		MapRoomInfo roomInfo = roomService.findById(roomId);
		Tree tree = this.getLoginToken().getRoomTree(request);
		for (TreeNode treeNode : tree)
		{
			if (!treeNode.getpId().equals(Tree.DEFAULT_ROOT_ID))
			{
				treeNode.setUrl(request.getContextPath() + "/sceneMgr/" + treeNode.getId());
				treeNode.setTarget("mainFrame");
			}
		}
		model.addAttribute("tree", tree);
		model.addAttribute("roomInfo", roomInfo);
		model.addAttribute(RequestNameConstants.RESULT_LIST, sceneList);
		return BASE_DIR + "list";
	}

	@RequestMapping("/add/{roomId}")
	public String add(HttpServletRequest request, Model model, @PathVariable("roomId") Long roomId) throws Exception
	{
		// 房间信息
		MapRoomInfo roomInfo = roomService.findById(roomId);
		List<DevInfo> devList = devInfoService.findByRoomIdAndExceptClassGroupId(roomId, DevClassGroupInfo.CLASS_GROUP_MUTIMEDIA,
				DevClassGroupInfo.CLASS_GROUP_SCREEN, DevClassGroupInfo.CLASS_GROUP_SENSOR);
		SceneInfo sceneInfo = new SceneInfo();
		sceneInfo.setRoomId(roomId);
		sceneInfo.setSceneStatus(CommonConstants.STATUS_VALID);
		sceneInfo.setSceneType(1);

		model.addAttribute("devList", devList);
		model.addAttribute(RequestNameConstants.RESULT_OBJECT, sceneInfo);
		model.addAttribute("roomInfo", roomInfo);
		return BASE_DIR + "add";
	}

	@RequestMapping("/save")
	public String save(HttpServletRequest request, Model model, SceneInfo sceneInfo) throws Exception
	{
		Enumeration paramNames = request.getParameterNames();
		List<SceneConfigInfo> sceneConfigInfos = new ArrayList<SceneConfigInfo>();
		Map<Long, String> enableDevs = new HashMap<Long, String>();
		// 遍历查找哪些设备需要在场景中应用
		while (paramNames.hasMoreElements())
		{
			String paramName = (String) paramNames.nextElement();
			if (paramName.startsWith("enable-"))
			{
				String attrValue = request.getParameter(paramName);
				Long devId = Long.valueOf(paramName.split("-")[1]);
				if (attrValue.equals("1"))
				{

					enableDevs.put(devId, null);
				}
			}
		}
		paramNames = request.getParameterNames();
		// 遍历配置参数信息
		while (paramNames.hasMoreElements())
		{
			String paramName = (String) paramNames.nextElement();
			if (paramName.startsWith("configs-"))
			{
				String attrValue = request.getParameter(paramName);
				String attrKey = paramName.split("-")[1];
				Long devId = Long.valueOf(paramName.split("-")[2]);
				if (enableDevs.containsKey(devId))
				{
					SceneConfigInfo sceneConfigInfo = new SceneConfigInfo();
					sceneConfigInfo.setAttrKey(attrKey);
					sceneConfigInfo.setAttrValue(attrValue);
					sceneConfigInfo.setDevId(devId);
					sceneConfigInfo.setSceneInfo(sceneInfo);
					sceneConfigInfos.add(sceneConfigInfo);
				}
			}
		}
		sceneInfo.setSceneConfigInfos(sceneConfigInfos);
		int resultTag = sceneService.save(sceneInfo);
		if (resultTag == ResultConstants.SAVE_SUCCEED)
		{
			ResultInfo.saveMessage(ResultConstants.getResultInfo(resultTag, INFORMATION_PARAMAS), request, REDIRECT_PATH + sceneInfo.getRoomId());
		}
		else
		{
			model.addAttribute(RequestNameConstants.RESULT_OBJECT, sceneInfo);
			ResultInfo.saveErrorMessage(ResultConstants.getResultInfo(resultTag, INFORMATION_PARAMAS), request);
		}

		return UrlConstants.INFORMATION_PAGE;
	}

	@RequestMapping("/edit/{sceneId}")
	public String edit(HttpServletRequest request, Model model, @PathVariable("sceneId") Long sceneId) throws Exception
	{
		// 房间信息

		SceneInfo sceneInfo = sceneService.findById(sceneId);
		MapRoomInfo roomInfo = roomService.findById(sceneInfo.getRoomId());
		List<DevInfo> devList = devInfoService.findByRoomIdAndExceptClassGroupId(sceneInfo.getRoomId(), DevClassGroupInfo.CLASS_GROUP_MUTIMEDIA,
				DevClassGroupInfo.CLASS_GROUP_SCREEN, DevClassGroupInfo.CLASS_GROUP_SENSOR);

		model.addAttribute("devList", devList);
		model.addAttribute(RequestNameConstants.RESULT_OBJECT, sceneInfo);
		model.addAttribute("roomInfo", roomInfo);
		return BASE_DIR + "edit";
	}

	@RequestMapping("/update/{sceneId}")
	public String update(HttpServletRequest request, Model model, SceneInfo sceneInfo) throws Exception
	{
		Enumeration paramNames = request.getParameterNames();
		List<SceneConfigInfo> sceneConfigInfos = new ArrayList<SceneConfigInfo>();
		// 遍历配置参数信息
		while (paramNames.hasMoreElements())
		{
			String paramName = (String) paramNames.nextElement();
			if (paramName.startsWith("configs-"))
			{
				String attrValue = request.getParameter(paramName);
				String attrKey = paramName.split("-")[1];
				Long devId = Long.valueOf(paramName.split("-")[2]);
				SceneConfigInfo sceneConfigInfo = new SceneConfigInfo();
				sceneConfigInfo.setAttrKey(attrKey);
				sceneConfigInfo.setAttrValue(attrValue);
				sceneConfigInfo.setDevId(devId);
				sceneConfigInfo.setSceneInfo(sceneInfo);
				sceneConfigInfos.add(sceneConfigInfo);
			}
		}
		sceneInfo.setSceneConfigInfos(sceneConfigInfos);
		int resultTag = sceneService.update(sceneInfo);
		if (resultTag == ResultConstants.UPDATE_SUCCEED)
		{
			ResultInfo.saveMessage(ResultConstants.getResultInfo(resultTag, INFORMATION_PARAMAS), request, REDIRECT_PATH + sceneInfo.getRoomId());
		}
		else
		{
			model.addAttribute(RequestNameConstants.RESULT_OBJECT, sceneInfo);
			ResultInfo.saveErrorMessage(ResultConstants.getResultInfo(resultTag, INFORMATION_PARAMAS), request);
		}

		return UrlConstants.INFORMATION_PAGE;
	}

	/** 删除 */
	@RequestMapping(value = "/delete/{id}")
	public String delete(HttpServletRequest request, Model model, @PathVariable("id") Long id) throws Exception
	{

		SceneInfo sceneInfo = sceneService.findById(id);
		int resultTag = sceneService.delete(id);
		if (resultTag == ResultConstants.DELETE_SUCCEED)
		{
			ResultInfo.saveMessage(ResultConstants.getResultInfo(resultTag, INFORMATION_PARAMAS), request, REDIRECT_PATH + sceneInfo.getRoomId());
		}
		else
		{
			ResultInfo.saveErrorMessage(ResultConstants.getResultInfo(resultTag, INFORMATION_PARAMAS), request);
		}

		return UrlConstants.INFORMATION_PAGE;
	}

	@RequestMapping("/viewDev/{roomId}/{devId}")
	public String viewDev(HttpServletRequest request, Model model, @PathVariable("roomId") Long roomId, @PathVariable("devId") Long devId) throws Exception
	{
		// 房间信息
		DevInfo devInfo = devInfoService.findById(devId);
		MapRoomInfo roomInfo = roomService.findById(roomId);
		List<DevInfo> devList = new ArrayList<DevInfo>();
		devList.add(devInfo);

		model.addAttribute("devList", devList);
		model.addAttribute("roomInfo", roomInfo);
		return BASE_DIR + "dev_view";
	}
}
