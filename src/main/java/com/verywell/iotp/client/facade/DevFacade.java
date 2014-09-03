package com.verywell.iotp.client.facade;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.verywell.framework.controller.BaseController;
import com.verywell.iotp.admin.constants.CommonConstants;
import com.verywell.iotp.admin.dto.DevInfoDTO;
import com.verywell.iotp.admin.entity.dev.DevInfo;
import com.verywell.iotp.admin.service.DevInfoService;
import com.verywell.iotp.client.facade.result.ServiceResult;

/**
 * 房间监控控制器
 * 
 * @author yao
 * 
 */
@Controller
@RequestMapping("/clientService/dev")
public class DevFacade extends BaseController
{
	@Autowired
	private DevInfoService devInfoService;

	/**
	 * 根据房间获得设备信息
	 * 
	 * @param request
	 * @param model
	 * @param roomId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/devList/{roomId}")
	@ResponseBody
	public List<DevInfoDTO> devList(HttpServletRequest request, Model model, @PathVariable("roomId") Long roomId) throws Exception
	{
		List<DevInfoDTO> resultList = new ArrayList<DevInfoDTO>();
		List<DevInfo> devList = devInfoService.findByRoomId(roomId);
		if (devList != null && !devList.isEmpty())
		{
			for (DevInfo devInfo : devList)
			{
				DevInfoDTO devDto = new DevInfoDTO(devInfo);
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
	 * 设备详情
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
		DevInfoDTO devDto = new DevInfoDTO(devInfo);
		devDto.setDevId(devInfo.getDevId());
		devDto.setDevStatus(devInfo.getDevStatus());
		devDto.setAlarmStatus(devInfo.getAlarmStatus());
		devDto.setCurrentClass(devInfo.getCurrentClass());
		return new DevInfoDTO(devInfo);
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
	@RequestMapping("/switchDev/{devId}/{switchFlag}")
	@ResponseBody
	public ServiceResult switchDev(HttpServletRequest request, Model model, @PathVariable("devId") String devId, @PathVariable("switchFlag") String switchFlag)
			throws Exception
	{
		ServiceResult result = new ServiceResult();
		if (!devInfoService.controllDev(devId, CommonConstants.CMD_SWITCH, switchFlag).equals("1"))
		{
			result.setDefaultError();
		}
		return result;
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
	@RequestMapping("/controllDev/{devId}/{cmd}/{value}")
	@ResponseBody
	public ServiceResult controllDev(HttpServletRequest request, Model model, @PathVariable("devId") String devId, @PathVariable("cmd") String cmd,
			@PathVariable("cmd") String value) throws Exception
	{
		ServiceResult result = new ServiceResult();
		if (!devInfoService.controllDev(devId, cmd, value).equals("1"))
		{
			result.setDefaultError();
		}
		return result;
	}

}
