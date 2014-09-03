package com.verywell.iotp.client.facade;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.verywell.framework.controller.BaseController;
import com.verywell.framework.utils.DateTimeUtil;
import com.verywell.iotp.admin.constants.ResultConstants;
import com.verywell.iotp.admin.entity.map.MapRoomInfo;
import com.verywell.iotp.admin.entity.meeting.MeetingInfo;
import com.verywell.iotp.admin.entity.scene.SceneInfo;
import com.verywell.iotp.admin.entity.sys.SysDept;
import com.verywell.iotp.admin.entity.sys.SysLogin;
import com.verywell.iotp.admin.service.MapRoomService;
import com.verywell.iotp.admin.service.MeetingService;
import com.verywell.iotp.admin.service.SceneService;
import com.verywell.iotp.admin.service.SysDeptService;
import com.verywell.iotp.admin.service.SysLoginService;
import com.verywell.iotp.client.facade.result.ServiceResult;
import com.verywell.iotp.client.model.DepInfo;
import com.verywell.iotp.client.model.Meeting;
import com.verywell.iotp.client.model.RoomInfo;
import com.verywell.iotp.client.model.RoomSceneInfo;
import com.verywell.iotp.client.model.UserInfo;

/**
 * 
 * @title:会议室预订控制器
 * @description:
 * @author: dl
 * 
 */
@Controller
@RequestMapping("/clientService/meeting")
public class MeetingFacade extends BaseController
{
	protected Logger logger = LoggerFactory.getLogger(this.getClass());// 日志

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

	/**
	 * 根据用户名获得该用户的会议室预定信息列表
	 * 
	 * @param request
	 * @param model
	 * @param loginName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/meetingList/{loginName}")
	@ResponseBody
	public List<Meeting> meetingList(HttpServletRequest request, Model model, @PathVariable("loginName") String loginName) throws Exception
	{
		List<Meeting> result = Lists.newArrayList();
		List<MeetingInfo> meetingList = meetingService.findByLoginName(loginName);
		if (meetingList != null && meetingList.size() > 0)
		{
			for (MeetingInfo meetingInfo : meetingList)
			{
				Meeting meeting = new Meeting();
				BeanUtils.copyProperties(meetingInfo, meeting);
				meeting.setRoomId(meetingInfo.getMapRoomInfo().getRoomId());
				result.add(meeting);
			}
		}
		return result;
	}

	/**
	 * 获得所有会议室列表
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/roomList")
	@ResponseBody
	public List<RoomInfo> roomList(HttpServletRequest request, Model model) throws Exception
	{
		List<RoomInfo> meetingRoomList = Lists.newArrayList();
		List<MapRoomInfo> roomList = mapRoomService.findByRoomType(MapRoomInfo.ROOM_TYPE_METTING);
		if (roomList != null && roomList.size() > 0)
		{
			if (roomList != null && roomList.size() > 0)
			{
				for (MapRoomInfo room : roomList)
				{
					RoomInfo roomInfo = new RoomInfo();
					roomInfo.setRoomId(room.getRoomId());
					roomInfo.setRoomName(room.getRoomName());
					roomInfo.setRoomImage(room.getRoomImage());
					meetingRoomList.add(roomInfo);
				}
			}
		}
		return meetingRoomList;
	}

	/**
	 * 根据会议室ID获得场景列表
	 * 
	 * @param request
	 * @param model
	 * @param roomId
	 * @return
	 */
	@RequestMapping(value = "/sceneList/{roomId}")
	@ResponseBody
	public List<RoomSceneInfo> sceneList(HttpServletRequest request, Model model, @PathVariable("roomId") Long roomId)
	{
		List<RoomSceneInfo> result = Lists.newArrayList();
		List<SceneInfo> sceneList = sceneService.findByRoomIdAndSceneType(roomId, SceneInfo.SCENETYPE_MANUAL);
		if (sceneList != null && sceneList.size() > 0)
		{
			for (SceneInfo sceneInfo : sceneList)
			{
				RoomSceneInfo roomSceneInfo = new RoomSceneInfo();
				roomSceneInfo.setRoomId(sceneInfo.getRoomId());
				roomSceneInfo.setSceneName(sceneInfo.getSceneName());
				roomSceneInfo.setSceneId(sceneInfo.getSceneId());
				result.add(roomSceneInfo);
			}
		}
		return result;
	}

	/**
	 * 获得参会人员部门信息
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/depList")
	@ResponseBody
	public List<DepInfo> depList(HttpServletRequest request, Model model) throws Exception
	{
		List<DepInfo> result = Lists.newArrayList();
		List<SysDept> deptList = deptService.findAll();
		if (deptList != null && deptList.size() > 0)
		{
			for (SysDept sysDept : deptList)
			{
				DepInfo depInfo = new DepInfo();
				depInfo.setDepId(sysDept.getDeptId());
				depInfo.setDepName(sysDept.getDeptName());
				List<SysLogin> userList = loginService.findByDeptId(sysDept.getDeptId());
				if (userList != null && userList.size() > 0)
				{
					List<UserInfo> users = Lists.newArrayList();
					for (SysLogin sysLogin : userList)
					{
						UserInfo userInfo = new UserInfo();
						userInfo.setUserId(sysLogin.getLoginId());
						userInfo.setUserName(sysLogin.getUserName());
						users.add(userInfo);
					}
					depInfo.setUsers(users);
				}
				result.add(depInfo);
			}

		}
		return result;
	}

	/**
	 * save保存预订会议室信息
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public ServiceResult save(HttpServletRequest request, Model model, MeetingInfo meetingInfo) throws Exception
	{
		ServiceResult result = new ServiceResult();
		try
		{
			meetingInfo.setStartTime(meetingInfo.getStartTime().replaceAll("-", "").replaceAll(":", "").replaceAll(" ", ""));
			meetingInfo.setEndTime(meetingInfo.getEndTime().replaceAll("-", "").replaceAll(":", "").replaceAll(" ", ""));
			meetingInfo.setSubscribeBy(meetingInfo.getSubscribeBy()); // 预订人员ID
			meetingInfo.setMeetingStatus(new Integer(1));
			meetingInfo.setNoticeStatus(new Integer(0)); // 会议通知状态
			meetingInfo.setSubscribeTime(DateTimeUtil.getChar14());
			int resultTag = meetingService.save(meetingInfo);
			if (resultTag != ResultConstants.SAVE_SUCCEED)
			{
				result.setError(ServiceResult.MEETING_SAVE_FAILED, ServiceResult.MEETING_SAVE_FAILED_MESSAGE);
			}
		}
		catch (Exception e)
		{
			logger.error("", e);
			result.setDefaultError();
		}
		return result;
	}

	/**
	 * 会议取消
	 * 
	 * @param request
	 * @param model
	 * @param meetingId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cancel/{meetingId}")
	@ResponseBody
	public ServiceResult cancel(HttpServletRequest request, Model model, @PathVariable("meetingId") Long meetingId) throws Exception
	{
		ServiceResult result = new ServiceResult();
		try
		{
			int resultTag = meetingService.cancelMeeting(meetingId);
			if (resultTag != ResultConstants.UPDATE_SUCCEED)
			{
				result.setError(ServiceResult.MEETING_CANCEL_FAILED, ServiceResult.MEETING_CANCEL_FAILED_MESSAGE);
			}
		}
		catch (Exception e)
		{
			logger.error("", e);
			result.setDefaultError();
		}
		return result;
	}

}
