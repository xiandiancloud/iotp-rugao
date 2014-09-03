package com.verywell.iotp.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.verywell.framework.dao.Page;
import com.verywell.framework.utils.DateTimeUtil;
import com.verywell.framework.utils.mapper.JsonMapper;
import com.verywell.iotp.admin.constants.RequestNameConstants;
import com.verywell.iotp.admin.constants.ResultConstants;
import com.verywell.iotp.admin.dto.DevInfoDTO;
import com.verywell.iotp.admin.dto.MeetingInfoDTO;
import com.verywell.iotp.admin.entity.dev.DevClassGroupInfo;
import com.verywell.iotp.admin.entity.dev.DevInfo;
import com.verywell.iotp.admin.entity.map.MapRoomInfo;
import com.verywell.iotp.admin.entity.meeting.MeetingInfo;
import com.verywell.iotp.admin.entity.meeting.MeetingTargetInfo;
import com.verywell.iotp.admin.entity.scene.SceneInfo;
import com.verywell.iotp.admin.entity.sys.SysDept;
import com.verywell.iotp.admin.entity.sys.SysLogin;
import com.verywell.iotp.admin.service.DevInfoService;
import com.verywell.iotp.admin.service.MapRoomService;
import com.verywell.iotp.admin.service.MeetingService;
import com.verywell.iotp.admin.service.SceneService;
import com.verywell.iotp.admin.service.SysDeptService;
import com.verywell.iotp.admin.service.SysLoginService;

/**
 * 
 * @title:会议室预订控制器
 * @description:
 * @author: dl
 * 
 */
@Controller
@RequestMapping("/meetingMgr")
public class MeetingMgrController extends BaseController
{
	protected Logger logger = LoggerFactory.getLogger(this.getClass());// 日志

	private final String[] INFORMATION_PARAMAS = { "会议室预订" };
	// 基础目录
	private final String BASE_DIR = "/meeting_mgr/";

	private final String REDIRECT_PATH = "/meetingMgr";

	@Autowired
	private MapRoomService mapRoomService;

	@Autowired
	private MeetingService meetingService;

	@Autowired
	private SysLoginService loginService;

	@Autowired
	private SysDeptService deptService;

	@Autowired
	private SceneService sceneService;

	@Autowired
	private DevInfoService devInfoService;

	/**
	 * 根据当前日期,获得预订会议室信息
	 * 
	 * @param request
	 * @param date
	 * @return
	 * @throws Exception
	 */
	@RequestMapping
	public String index(HttpServletRequest request, String meetingDate, Model model) throws Exception
	{

		// 获得会议室列表并按会议室名称排序
		List<MapRoomInfo> roomList = mapRoomService.findByRoomType(MapRoomInfo.ROOM_TYPE_METTING);
		List<RoomInfoDTO> roomDtoList = new ArrayList<RoomInfoDTO>();
		if (roomList != null)
		{
			for (MapRoomInfo roomInfo : roomList)
			{
				roomDtoList.add(new RoomInfoDTO(roomInfo.getRoomId(), roomInfo.getRoomName()));
			}
		}
		model.addAttribute("roomJson", JsonMapper.nonEmptyMapper().toJson(roomDtoList));

		return BASE_DIR + "meeting_order/list";

	}

	/** 进入添加 */
	@RequestMapping(value = "/meetingList/{viewDate}/{viewMode}")
	@ResponseBody
	public List<MeetingInfoDTO> meetingList(HttpServletRequest request, Model model, @PathVariable("viewDate") String viewDate,
			@PathVariable("viewMode") String viewMode) throws Exception
	{
		List<MeetingInfoDTO> meetingDtoList = new ArrayList<MeetingInfoDTO>();
		// 根据日期获得当天预订所有会议室的信息
		List<MeetingInfo> meetingList = meetingService.findByMettingDate(viewDate, viewMode);

		for (MeetingInfo meetingInfo : meetingList)
		{
			MeetingInfoDTO dto = new MeetingInfoDTO();
			BeanUtils.copyProperties(meetingInfo, dto);
			String startDate = meetingInfo.getStartTime() + "00";
			String endDate = meetingInfo.getEndTime() + "00";
			dto.setText(meetingInfo.getMeetingName());
			dto.setStart_date(DateTimeUtil.formatDate(startDate, "yyyy-MM-dd HH:mm"));
			dto.setEnd_date(DateTimeUtil.formatDate(endDate, "yyyy-MM-dd HH:mm"));
			dto.setRoomId(meetingInfo.getMapRoomInfo().getRoomId());
			dto.setRoomName(meetingInfo.getMapRoomInfo().getRoomName());

			SysLogin sysLogin = loginService.findByLoginName(meetingInfo.getSubscribeBy());
			if (sysLogin != null)
				dto.setSubscribeByName(sysLogin.getUserName());
			List<MeetingTargetInfo> targetInfoList = meetingInfo.getMeetingTargetInfos();
			StringBuffer targetIds = new StringBuffer("");
			if (targetInfoList != null && targetInfoList.size() > 0)
			{
				for (int i = 0; i < targetInfoList.size(); i++)
				{
					MeetingTargetInfo targetInfo = targetInfoList.get(i);
					if (i == 0)
						targetIds.append(targetInfo.getLoginId());
					else
						targetIds.append(",").append(targetInfo.getLoginId());
				}
			}
			dto.setTargetIds(targetIds.toString());
			dto.setSubscribeBy(meetingInfo.getSubscribeBy());
			dto.setPrepareBy(meetingInfo.getPrepareBy());
			dto.setPrepareEarlyTime(meetingInfo.getPrepareEarlyTime());
			if (meetingInfo.getSubscribeBy().equals(this.getLoginToken().getSysLogin().getLoginName()))
			{
				dto.setColor("#ffe763");
				dto.setTextColor("##dbcf8c");

			}
			else
			{
				dto.setColor("#fffbb6");
				dto.setTextColor("#000000");
			}
			meetingDtoList.add(dto);
		}
		return meetingDtoList;
	}

	@RequestMapping(value = "/order")
	public String order(HttpServletRequest request, Model model, String viewDate, String viewMode) throws Exception
	{

		// 获得会议室列表并按会议室名称排序
		List<MapRoomInfo> roomList = mapRoomService.findByRoomType(MapRoomInfo.ROOM_TYPE_METTING);
		model.addAttribute("roomList", roomList);
		MeetingInfo meetingInfo = new MeetingInfo();
		meetingInfo.setStartDate(viewDate);
		meetingInfo.setEndDate(viewDate);
		SysLogin sysLogin = this.getLoginToken().getSysLogin();
		if (sysLogin != null)
			meetingInfo.setSubscribeByName(sysLogin.getUserName());
		meetingInfo.setSceneFlag(0);
		meetingInfo.setScreenFlag(0);
		meetingInfo.setNoticeFlag(0);
		model.addAttribute(RequestNameConstants.RESULT_OBJECT, meetingInfo);

		List<RoomInfoDTO> roomDtoList = new ArrayList<RoomInfoDTO>();
		Map<Long, List<SceneInfo>> sceneMap = new HashMap<Long, List<SceneInfo>>();
		if (roomList != null)
		{
			for (MapRoomInfo roomInfo : roomList)
			{
				roomDtoList.add(new RoomInfoDTO(roomInfo.getRoomId(), roomInfo.getRoomName()));
			}

			// 加载所有场景信息
			List<SceneInfo> sceneList = sceneService.findBySceneType(SceneInfo.SCENETYPE_MANUAL);

			if (sceneList != null && sceneList.size() > 0)
			{
				// 根据房间在Map中分类存放
				for (SceneInfo sceneInfo : sceneList)
				{
					// 只加载会议室的场景数据
					for (MapRoomInfo roomInfo : roomList)
					{
						Long roomId = sceneInfo.getRoomId();
						if (roomInfo.getRoomId().equals(roomId))
						{
							List<SceneInfo> list = null;
							if (sceneMap.containsKey(roomId))
							{
								list = sceneMap.get(roomId);
								list.add(sceneInfo);
							}
							else
							{
								list = new ArrayList<SceneInfo>();
								list.add(sceneInfo);
								sceneMap.put(roomId, list);
							}
						}
					}
				}
			}
		}
		model.addAttribute("sceneMap", sceneMap);

		List<DevInfo> allScreenDev = devInfoService.findByClassGroupId(DevClassGroupInfo.CLASS_GROUP_SCREEN);
		List<DevInfoDTO> screenList = new ArrayList<DevInfoDTO>();
		if (allScreenDev != null && !allScreenDev.isEmpty())
		{
			for (DevInfo devInfo : allScreenDev)
			{
				DevInfoDTO devDto = new DevInfoDTO();
				devDto.setMacAddr(devInfo.getMacAddr());
				devDto.setRoomName(mapRoomService.findById(devInfo.getRoomId()).getRoomName());
				screenList.add(devDto);
			}
		}
		model.addAttribute("screenList", screenList);
		return BASE_DIR + "meeting_order/order";
	}

	@RequestMapping(value = "/orderEdit/{meetingId}")
	public String orderEdit(HttpServletRequest request, Model model, String viewDate, String viewMode, @PathVariable("meetingId") Long meetingId)
			throws Exception
	{

		// 获得会议室列表并按会议室名称排序
		List<MapRoomInfo> roomList = mapRoomService.findByRoomType(MapRoomInfo.ROOM_TYPE_METTING);
		model.addAttribute("roomList", roomList);
		MeetingInfo meetingInfo = meetingService.findById(meetingId);
		meetingInfo.setRoomId(meetingInfo.getMapRoomInfo().getRoomId());
		SysLogin sysLogin = loginService.findById(meetingInfo.getSubscribeId());

		meetingInfo.setSubscribeByName(sysLogin.getUserName());
		List<MeetingTargetInfo> targetInfoList = meetingInfo.getMeetingTargetInfos();

		if (targetInfoList != null && targetInfoList.size() > 0)
		{
			StringBuffer targetIds = new StringBuffer("");
			StringBuffer targetNames = new StringBuffer("");
			for (int i = 0; i < targetInfoList.size(); i++)
			{
				MeetingTargetInfo targetInfo = targetInfoList.get(i);
				if (i == 0)
					targetIds.append(targetInfo.getLoginId());
				else
					targetIds.append(",").append(targetInfo.getLoginId());
			}
			meetingInfo.setTargetUserIds(targetIds.toString());
			List<SysLogin> users = loginService.findByIds(targetIds.toString());
			for (int i = 0; i < users.size(); i++)
			{
				SysLogin user = users.get(i);
				if (i == 0)
					targetNames.append(user.getUserName());
				else
					targetNames.append(",").append(user.getUserName());
			}
			meetingInfo.setTargetUserNames(targetNames.toString());
		}
		String prepareBy = meetingInfo.getPrepareBy();
		if (prepareBy != null && !prepareBy.equals(""))
		{
			StringBuffer prepareByNames = new StringBuffer("");
			List<SysLogin> users = loginService.findByIds(prepareBy);
			for (int i = 0; i < users.size(); i++)
			{
				SysLogin user = users.get(i);
				if (i == 0)
					prepareByNames.append(user.getUserName());
				else
					prepareByNames.append(",").append(user.getUserName());
			}
			meetingInfo.setPrepareByNames(prepareByNames.toString());
		}
		String startTime = DateTimeUtil.formatChar12(meetingInfo.getStartTime());
		String endTime = DateTimeUtil.formatChar12(meetingInfo.getEndTime());

		meetingInfo.setStartDate(startTime.substring(0, 10));
		meetingInfo.setStartTimeHour(startTime.substring(11, 13));
		meetingInfo.setStartTimeMin(startTime.substring(14, 16));
		meetingInfo.setEndTimeHour(endTime.substring(11, 13));
		meetingInfo.setEndTimeMin(endTime.substring(14, 16));

		model.addAttribute(RequestNameConstants.RESULT_OBJECT, meetingInfo);

		List<RoomInfoDTO> roomDtoList = new ArrayList<RoomInfoDTO>();
		Map<Long, List<SceneInfo>> sceneMap = new HashMap<Long, List<SceneInfo>>();
		if (roomList != null)
		{
			for (MapRoomInfo roomInfo : roomList)
			{
				roomDtoList.add(new RoomInfoDTO(roomInfo.getRoomId(), roomInfo.getRoomName()));
			}

			// 加载所有场景信息
			List<SceneInfo> sceneList = sceneService.findBySceneType(SceneInfo.SCENETYPE_MANUAL);

			if (sceneList != null && sceneList.size() > 0)
			{
				// 根据房间在Map中分类存放
				for (SceneInfo sceneInfo : sceneList)
				{
					// 只加载会议室的场景数据
					for (MapRoomInfo roomInfo : roomList)
					{
						Long roomId = sceneInfo.getRoomId();
						if (roomInfo.getRoomId().equals(roomId))
						{
							List<SceneInfo> list = null;
							if (sceneMap.containsKey(roomId))
							{
								list = sceneMap.get(roomId);
								list.add(sceneInfo);
							}
							else
							{
								list = new ArrayList<SceneInfo>();
								list.add(sceneInfo);
								sceneMap.put(roomId, list);
							}
						}
					}
				}
			}
		}
		model.addAttribute("sceneMap", sceneMap);
		List<DevInfo> allScreenDev = devInfoService.findByClassGroupId(DevClassGroupInfo.CLASS_GROUP_SCREEN);
		List<DevInfoDTO> screenList = new ArrayList<DevInfoDTO>();
		if (allScreenDev != null && !allScreenDev.isEmpty())
		{
			for (DevInfo devInfo : allScreenDev)
			{
				DevInfoDTO devDto = new DevInfoDTO();
				devDto.setMacAddr(devInfo.getMacAddr());
				devDto.setRoomName(mapRoomService.findById(devInfo.getRoomId()).getRoomName());
				screenList.add(devDto);
			}
		}
		model.addAttribute("screenList", screenList);
		return BASE_DIR + "meeting_order/order";
	}

	/**
	 * save保存预订会议室信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public ResultInfo saveOrder(HttpServletRequest request, Model model, MeetingInfo meetingInfo) throws Exception
	{
		try
		{
			meetingInfo.setStartTime(meetingInfo.getStartDate().replaceAll("-", "") + meetingInfo.getStartTimeHour() + meetingInfo.getStartTimeMin());
			meetingInfo.setEndTime(meetingInfo.getStartDate().replaceAll("-", "") + meetingInfo.getEndTimeHour() + meetingInfo.getEndTimeMin());
			meetingInfo.setSubscribeBy(String.valueOf(this.getLoginToken().getSysLogin().getLoginName())); // 预订人员ID
			meetingInfo.setSubscribeId(this.getLoginToken().getSysLogin().getLoginId());
			meetingInfo.setMeetingStatus(new Integer(1));
			meetingInfo.setNoticeStatus(new Integer(0)); // 会议通知状态
			meetingInfo.setSubscribeTime(DateTimeUtil.getChar14());
			meetingInfo.setSceneStatus(new Integer(0)); // 场景应用状态
			meetingInfo.setScreenStatus(new Integer(0)); // 显示屏应用状态
			meetingInfo.setPrepareStatus(new Integer(0));// 筹备人提前使用门禁卡
			if (meetingInfo.getScreenRoomIds() != null)
			{
				String screenIds = StringUtils.join(meetingInfo.getScreenRoomIds(), ",");
				meetingInfo.setScreenIds(screenIds);
			}
			if (meetingInfo.getMeetingId() != null)
			{
				int resultTag = meetingService.update(meetingInfo);
				if (resultTag == ResultConstants.UPDATE_SUCCEED)
				{
					return ResultInfo.saveMessage(ResultConstants.getResultInfo(resultTag, INFORMATION_PARAMAS), null);
				}
				else
				{
					return ResultInfo.saveErrorMessage("会议预定修改失败！该时间段已经有会议被预定，请更换其他时间！");
				}
			}
			else
			{
				int resultTag = meetingService.save(meetingInfo);
				if (resultTag == ResultConstants.SAVE_SUCCEED)
				{
					return ResultInfo.saveMessage(ResultConstants.getResultInfo(resultTag, INFORMATION_PARAMAS), null);
				}
				else
				{
					return ResultInfo.saveErrorMessage("会议预定保存失败！该时间段已经有会议被预定，请更换其他时间！");
				}
			}

		}
		catch (Exception e)
		{
			logger.error("", e);
			return ResultInfo.saveErrorMessage("会议预定保存失败！");
		}
	}

	/** 进入查询 */
	@RequestMapping(value = "/cancel/{meetingId}")
	@ResponseBody
	public ResultInfo cancel(HttpServletRequest request, Model model, @PathVariable("meetingId") Long meetingId) throws Exception
	{
		try
		{
			int resultTag = meetingService.cancelMeeting(meetingId);
			if (resultTag == ResultConstants.UPDATE_SUCCEED)
			{
				return ResultInfo.saveMessage("会议取消成功！", null);
			}
			else
			{
				return ResultInfo.saveErrorMessage("会议取消预定失败！");
			}
		}
		catch (Exception e)
		{
			logger.error("", e);
			return ResultInfo.saveErrorMessage("会议取消预定失败！");
		}
	}

	/** 弹出用户选择列表 */
	@RequestMapping(value = "/userSelect")
	public String userSelect(HttpServletRequest request, Page page, Model model) throws Exception
	{
		String context = request.getContextPath();
		String selectedIds = request.getParameter("selectedIds");
		// 部门用户树
		Tree selectedUserTree = new Tree();
		if (selectedIds != null && !selectedIds.equals(""))
		{
			List<SysLogin> selectedUsers = loginService.findByIds(selectedIds);

			for (SysLogin user : selectedUsers)
			{
				TreeNode treeNode = new TreeNode();
				treeNode.setId("" + user.getLoginId());
				treeNode.setpId("0");
				treeNode.setName(user.getUserName());
				treeNode.setIcon(context + "/images/icon_user.gif");
				selectedUserTree.addNode(treeNode);
			}
		}
		model.addAttribute("selectedUserTree", selectedUserTree);

		List<SysDept> deptList = deptService.findByCorpId(this.getLoginToken().getSysCorp().getCorpId());
		// 部门用户树
		Tree deptTree = new Tree();
		for (SysDept dept : deptList)
		{
			TreeNode treeNode = new TreeNode();
			treeNode.setId("" + dept.getDeptId());
			treeNode.setpId("" + dept.getParentDeptId());
			treeNode.setName(dept.getDeptName());
			if (dept.getParentDeptId() == null || dept.getParentDeptId().intValue() == 0)
			{
				treeNode.setIconOpen(context + "/plugins/zTree/css/zTreeStyle/img/diy/1_open.png");
				treeNode.setIconClose(context + "/plugins/zTree/css/zTreeStyle/img/diy/1_close.png");
			}
			else
				treeNode.setIcon(context + "/plugins/zTree/css/zTreeStyle/img/diy/8.png");
			deptTree.addNode(treeNode);
		}

		model.addAttribute("tree", deptTree);
		return BASE_DIR + "meeting_order/user_select";
	}

	/** 弹出用户选择列表 */
	@RequestMapping(value = "/getUserListByDeptId/{deptId}")
	@ResponseBody
	public List<TreeNode> getUserListByDeptId(HttpServletRequest request, @PathVariable("deptId") Long deptId, Model model) throws Exception
	{
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		List<SysLogin> userList = loginService.findByDeptId(deptId);
		if (userList != null)
		{
			for (SysLogin user : userList)
			{
				TreeNode node = new TreeNode();
				node.setId("" + user.getLoginId());
				node.setName(user.getUserName());
				node.setIcon(request.getContextPath() + "/images/icon_user.gif");
				nodes.add(node);
			}
		}
		return nodes;
	}

	class RoomInfoDTO
	{
		String key;
		String label;

		public RoomInfoDTO(Long roomId, String roomName)
		{
			key = String.valueOf(roomId);
			label = roomName;
		}

		public String getKey()
		{
			return key;
		}

		public void setKey(String key)
		{
			this.key = key;
		}

		public String getLabel()
		{
			return label;
		}

		public void setLabel(String label)
		{
			this.label = label;
		}
	}

}
