package com.verywell.iotp.admin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.verywell.framework.commons.ResultInfo;
import com.verywell.framework.controller.BaseController;
import com.verywell.iotp.admin.constants.CommonConstants;
import com.verywell.iotp.admin.constants.RequestNameConstants;
import com.verywell.iotp.admin.dto.DevInfoDTO;
import com.verywell.iotp.admin.dto.ScreenInfo;
import com.verywell.iotp.admin.entity.dev.DevClassGroupInfo;
import com.verywell.iotp.admin.entity.dev.DevInfo;
import com.verywell.iotp.admin.entity.map.MapRoomInfo;
import com.verywell.iotp.admin.service.DevClassGroupInfoService;
import com.verywell.iotp.admin.service.DevInfoService;
import com.verywell.iotp.admin.service.MapRoomService;
import com.verywell.iotp.admin.service.ScreenService;

/**
 * 房间监控控制器
 * 
 * @author yao
 * 
 */
@Controller
@RequestMapping("/roomMonitor")
public class RoomMonitorController extends BaseController
{
	@Autowired
	private DevInfoService devInfoService;

	@Autowired
	private DevClassGroupInfoService devClassGroupInfoService;

	@Autowired
	private ScreenService screenService;

	@Autowired
	private MapRoomService roomService;

	// 基础目录
	private final String BASE_DIR = "/iot_mgr/";

	/**
	 * 查看房间设备详细信息（Ajax）
	 * 
	 * 
	 * @param request
	 * @param model
	 * @param devId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/devDetail/{devId}")
	@ResponseBody
	public DevInfoDTO devDetail(HttpServletRequest request, Model model, @PathVariable("devId") Long devId) throws Exception
	{
		DevInfo devInfo = devInfoService.findById(devId);
		DevInfoDTO dto = new DevInfoDTO(devInfo);
		return dto;
	}

	/**
	 * 根据房间获得设备信息（Ajax）
	 * 
	 * 主界面定时刷新设备状态使用
	 * 
	 * @param request
	 * @param model
	 * @param roomId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/devDetailList/{roomId}")
	@ResponseBody
	public List<DevInfoDTO> getDevList(HttpServletRequest request, Model model, @PathVariable("roomId") Long roomId) throws Exception
	{
		List<DevInfoDTO> resultList = new ArrayList<DevInfoDTO>();
		List<DevInfo> devList = devInfoService.findByRoomId(roomId);
		if (devList != null && !devList.isEmpty())
		{
			for (DevInfo devInfo : devList)
			{
				DevInfoDTO devDto = new DevInfoDTO();
				devDto.setDevId(devInfo.getDevId());
				devDto.setDevStatus(devInfo.getDevStatus());
				devDto.setAlarmStatus(devInfo.getAlarmStatus());
				devDto.setCurrentClass(devInfo.getCurrentClass());
				resultList.add(devDto);
			}
		}
		return resultList;
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
		// 该类型下的所有设备的id，逗号分隔，用于做批量控制
		String devIds = "";
		DevInfo devInfo = null; // 单个设备（显示屏、会议中控）
		if (devList != null && devList.size() > 0)
		{
			devInfo = devList.get(0);
			for (DevInfo dev : devList)
			{
				if (devIds.equals(""))
					devIds += dev.getDevId();
				else
					devIds += ("," + dev.getDevId());
			}
		}
		model.addAttribute("devIds", devIds);
		model.addAttribute("devInfo", devInfo);

		// 灯光
		if (DevClassGroupInfo.CLASS_GROUP_LIGHT.equals(classGroupId))
			return BASE_DIR + "controll_light";
		//门禁
		else if (DevClassGroupInfo.CLASS_GROUP_DOOR.equals(classGroupId))
			return BASE_DIR + "controll_door";
		// 空调
		else if (DevClassGroupInfo.CLASS_GROUP_AIR.equals(classGroupId))
			return BASE_DIR + "controll_air";
		// 传感器
		else if (DevClassGroupInfo.CLASS_GROUP_SENSOR.equals(classGroupId))
			return BASE_DIR + "controll_sensor";
		// 窗帘
		else if (DevClassGroupInfo.CLASS_GROUP_CURTAIN.equals(classGroupId))
			return BASE_DIR + "controll_curtain";
		// 会议中控
		// else if
		// (DevClassGroupInfo.CLASS_GROUP_MUTIMEDIA.equals(classGroupId))
		// return BASE_DIR + "controll_mutimedia";
		// // 显示屏
		// else if (DevClassGroupInfo.CLASS_GROUP_SCREEN.equals(classGroupId))
		// {
		// if (devInfo != null)
		// {
		// // 查询当前显示屏配置情况
		// model.addAttribute("screenInfo",
		// screenService.getScreenInfo(devInfo.getMacAddr()));
		// }
		// return BASE_DIR + "controll_screen";
		// }
		else
			return BASE_DIR + "dev_list";
	}

	/**
	 * 进入设备控制面板
	 * 
	 * @param request
	 * @param model
	 * @param devId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/devControll/{devId}")
	public String devControll(HttpServletRequest request, Model model, @PathVariable("devId") String devId) throws Exception
	{
		// 空调多路控制
		if (devId.indexOf(",") >= 0)
		{
			model.addAttribute("devIds", devId);
			return BASE_DIR + "panel_air_muti";
		}
		else
		{
			DevInfo devInfo = devInfoService.findById(Long.valueOf(devId));
			model.addAttribute("devInfo", new DevInfoDTO(devInfo));
			if (devInfo.getDevClassInfo().getDevClassGroupInfo().getGroupId().equals(DevClassGroupInfo.CLASS_GROUP_AIR))
				return BASE_DIR + "panel_air";
			else if (devInfo.getDevClassInfo().getDevClassGroupInfo().getGroupId().equals(DevClassGroupInfo.CLASS_GROUP_LIGHT))
				return BASE_DIR + "panel_light";
			else
				return "";
		}
	}

	/**
	 * 设备开关
	 * 
	 * @param request
	 * @param model
	 * @param devId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/switchDev/{devIds}/{switchFlag}")
	@ResponseBody
	public String switchDev(HttpServletRequest request, Model model, @PathVariable("devIds") String devIds, @PathVariable("switchFlag") String switchFlag)
			throws Exception
	{
		return devInfoService.controllDev(devIds, CommonConstants.CMD_SWITCH, switchFlag);
	}

	/**
	 * 设备控制
	 * 
	 * @param request
	 * @param model
	 * @param devId
	 * @param cmd
	 * @param value
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/controllDev/{devIds}/{cmd}/{value}")
	@ResponseBody
	public String controllDev(HttpServletRequest request, Model model, @PathVariable("devIds") String devIds, @PathVariable("cmd") String cmd,
			@PathVariable("value") String value) throws Exception
	{
		return devInfoService.controllDev(devIds, cmd, value);
	}

	/**
	 * 场景设置
	 * 
	 * @param request
	 * @param model
	 * @param sceneId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/setScene/{sceneId}")
	@ResponseBody
	public String setScene(HttpServletRequest request, Model model, @PathVariable("sceneId") Long sceneId) throws Exception
	{
		return devInfoService.setScene(sceneId);
	}

	/**
	 * 趋势图
	 * 
	 * @param request
	 * @param model
	 * @param roomId
	 * @param devId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/trendChart/{roomId}")
	public String trendChart(HttpServletRequest request, Model model, @PathVariable("roomId") Long roomId, Long devId) throws Exception
	{
		Long currentDevId = devId;
		List<DevInfo> devList = devInfoService.findByClassGroupId(roomId, DevClassGroupInfo.CLASS_GROUP_SENSOR);
		if (devId == null && devList != null && devList.size() > 0)
		{
			currentDevId = devList.get(0).getDevId();
		}
		model.addAttribute("devList", devList);
		return BASE_DIR + "trend_chart";
	}

	/**
	 * 显示屏信息
	 * 
	 * @param request
	 * @param model
	 * @param roomId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/viewScreen/{roomId}")
	public String viewScreen(HttpServletRequest request, Model model, @PathVariable("roomId") Long roomId) throws Exception
	{
		List<DevInfo> devList = devInfoService.findByClassGroupId(roomId, DevClassGroupInfo.CLASS_GROUP_SCREEN);

		DevInfo devInfo = null;
		if (devList != null && devList.size() > 0)
		{
			String fileName = request.getParameter("fileName");
			devInfo = devList.get(0);
			model.addAttribute("devInfo", devInfo);
			// 查询当前显示屏配置情况
			ScreenInfo screenInfo = null;
			// 如果是会议室预定调用该页面，则从会议目录加载配置文件
			if (fileName != null && !fileName.equals(""))
			{
				screenInfo = screenService.getScreenInfo(CommonConstants.MEETING_SCREEN_FILE_DIR, fileName);
				model.addAttribute("fileName", fileName);
				model.addAttribute("fromMeeting", "1");
			}
			// 房间监控页面调用则从显示屏服务器目录加载配置文件
			else
			{
				screenInfo = screenService.getScreenInfo(CommonConstants.SCREEN_FILE_DIR, devInfo.getMacAddr() + ".txt");
				model.addAttribute("fileName", devInfo.getMacAddr() + ".txt");
				model.addAttribute("fromMeeting", "0");
			}
			model.addAttribute("screenInfo", screenInfo);
		}
		return BASE_DIR + "controll_screen";
	}

	/**
	 * 显示屏信息发布
	 * 
	 * @param request
	 * @param model
	 * @param screenInfo
	 * @param screenId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveScreen")
	@ResponseBody
	public ResultInfo saveScreen(HttpServletRequest request, Model model, ScreenInfo screenInfo, @RequestParam(value = "fileName") String fileName)
			throws Exception
	{
		try
		{
			Integer screenNum = Integer.valueOf(request.getParameter("screenNum"));
			for (int i = 1; i <= screenNum * 7; i++)
			{
				String message = (String) request.getParameter("message_" + i);
				screenInfo.addMessage(message);
			}

			String fromMeeting = request.getParameter("fromMeeting");
			if (fromMeeting.equals("0"))
			{
				screenService.saveScreenInfo(screenInfo, CommonConstants.SCREEN_FILE_DIR, fileName);
				return ResultInfo.saveMessage("显示屏信息发布成功！", null);
			}
			else
			{
				screenService.saveScreenInfo(screenInfo, CommonConstants.MEETING_SCREEN_FILE_DIR, fileName);
				return ResultInfo.saveMessage("配置文件已保存！", null);
			}

		}
		catch (Exception e)
		{
			logger.error("", e);
			return ResultInfo.saveErrorMessage("显示屏信息发布失败！");
		}
	}

	/**
	 * 会议中控
	 * 
	 * @param request
	 * @param model
	 * @param roomId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/viewMutiMedia/{roomId}")
	public String viewMutiMedia(HttpServletRequest request, Model model, @PathVariable("roomId") Long roomId) throws Exception
	{
		List<DevInfo> devList = devInfoService.findByClassGroupId(roomId, DevClassGroupInfo.CLASS_GROUP_MUTIMEDIA);
		MapRoomInfo roomInfo = roomService.findById(roomId);
		String roomName = roomInfo.getRoomName();
		DevInfo devInfo = null;
		if (devList != null && devList.size() > 0)
		{
			devInfo = devList.get(0);
			model.addAttribute("devInfo", devInfo);
		}
		if (roomName.equals("小会议室"))
			return BASE_DIR + "controll_mutimedia_smallMeeting";
		else if (roomName.equals("大会议室"))
			return BASE_DIR + "controll_mutimedia_bigMeeting";
		else if (roomName.equals("校史馆"))
			return BASE_DIR + "controll_mutimedia_schoolHistory";
		else
			return BASE_DIR + "controll_mutimedia_classRoom";
	}
}
