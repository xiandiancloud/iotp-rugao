package com.verywell.iotp.client.facade;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.verywell.framework.controller.BaseController;
import com.verywell.iotp.admin.commons.LoginToken;
import com.verywell.iotp.admin.entity.map.MapBuildingInfo;
import com.verywell.iotp.admin.entity.map.MapRoomInfo;
import com.verywell.iotp.admin.entity.sys.SysLogin;
import com.verywell.iotp.admin.service.MapBuildingService;
import com.verywell.iotp.admin.service.MapRoomService;
import com.verywell.iotp.admin.service.SysLoginService;
import com.verywell.iotp.client.model.BuildingInfo;
import com.verywell.iotp.client.model.RoomInfo;

/**
 * 地图相关接口
 * 
 * @author yao
 * 
 */
@Controller
@RequestMapping("/clientService/map")
public class MapFacade extends BaseController
{
	@Autowired
	private MapRoomService roomService;

	@Autowired
	private MapBuildingService buildingService;

	@Autowired
	private SysLoginService loginService;

	/**
	 * 建筑物列表
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/buildingList")
	@ResponseBody
	public List<BuildingInfo> buildingList(HttpServletRequest request, Model model) throws Exception
	{
		List<BuildingInfo> result = Lists.newArrayList();
		if (request.getParameter("loginId") != null)
		{
			// 根据用户信息查询用户房间权限
			Long loginId = Long.valueOf(request.getParameter("loginId"));
			SysLogin sysLogin = loginService.findById(loginId);
			LoginToken loginToken = loginService.getAdminLoginToken(sysLogin);
			// 当前用户拥有房间权限
			Map<Long, MapRoomInfo> roomPermissions = loginToken.getRoomPermissions();

			List<MapBuildingInfo> buildingList = buildingService.findAll();
			if (buildingList != null && buildingList.size() > 0)
			{
				for (MapBuildingInfo building : buildingList)
				{
					BuildingInfo buildingInfo = new BuildingInfo();
					buildingInfo.setBuildingId(building.getBuildingId());
					buildingInfo.setBuildingName(building.getBuildingName());
					List<MapRoomInfo> roomList = roomService.findByBuildingId(building.getBuildingId());
					if (roomList != null && roomList.size() > 0)
					{
						List<RoomInfo> rooms = Lists.newArrayList();
						for (MapRoomInfo room : roomList)
						{
							// 根据用户权限过滤房间
							if (roomPermissions.containsKey(room.getRoomId()))
							{
								RoomInfo roomInfo = new RoomInfo();
								roomInfo.setRoomId(room.getRoomId());
								roomInfo.setRoomName(room.getRoomName());
								roomInfo.setRoomImage(room.getRoomImage());
								rooms.add(roomInfo);
							}
						}
						buildingInfo.setRooms(rooms);
					}
					result.add(buildingInfo);
				}
			}
		}
		return result;
	}
}
