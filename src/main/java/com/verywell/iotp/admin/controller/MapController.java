/**
 * 
 */
package com.verywell.iotp.admin.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.verywell.framework.controller.BaseController;
import com.verywell.iotp.admin.commons.LoginToken;
import com.verywell.iotp.admin.entity.dev.DevInfo;
import com.verywell.iotp.admin.entity.map.MapBuildingInfo;
import com.verywell.iotp.admin.entity.map.MapRoomInfo;
import com.verywell.iotp.admin.entity.scene.SceneInfo;
import com.verywell.iotp.admin.service.DevInfoService;
import com.verywell.iotp.admin.service.MapBuildingService;
import com.verywell.iotp.admin.service.MapRoomService;
import com.verywell.iotp.admin.service.SceneService;

/**
 * 地图相关服务
 * 
 * @author yao
 * 
 */
@Controller
@RequestMapping("/map")
public class MapController extends BaseController
{
	@Autowired
	private MapRoomService roomService;

	@Autowired
	private MapBuildingService buildingService;

	@Autowired
	private DevInfoService devInfoService;

	@Autowired
	private SceneService sceneService;

	// 基础目录
	private final String BASE_DIR = "/iot_mgr/";

	@RequestMapping
	public String index(HttpServletRequest request, Model model) throws Exception
	{
		List<MapBuildingInfo> buildingList = buildingService.findAll();
		model.addAttribute("buildingList", buildingList);
		return BASE_DIR + "map";
	}

	/**
	 * 根据建筑ID获得该建筑的所有房间列表
	 * 
	 * @param request
	 * @param buildingId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getRoomList/{buildingId}")
	@ResponseBody
	public List<MapRoomInfo> getRoomList(HttpServletRequest request, @PathVariable("buildingId") Long buildingId) throws Exception
	{
		LoginToken loginToken = this.getLoginToken();
		// 当前用户拥有房间权限
		Map<Long, MapRoomInfo> roomPermissions = loginToken.getRoomPermissions();
		List<MapRoomInfo> rooms = roomService.findByBuildingId(buildingId);
		for (int i = rooms.size() - 1; i > 0; i--)
		{
			if (!roomPermissions.containsKey(rooms.get(i).getRoomId()))
			{
				rooms.remove(i);
			}
		}
		return rooms;
	}

	@RequestMapping("/roomDetail/{roomId}")
	public String roomDetail(HttpServletRequest request, Model model, @PathVariable("roomId") Long roomId) throws Exception
	{
		MapRoomInfo roomInfo = roomService.findById(roomId);
		List<DevInfo> devList = devInfoService.findByRoomId(roomId);
		List<SceneInfo> sceneList = sceneService.findByRoomIdAndSceneType(roomId, SceneInfo.SCENETYPE_MANUAL);

		model.addAttribute("roomInfo", roomInfo);
		model.addAttribute("devList", devList);
		model.addAttribute("sceneList", sceneList);
		return BASE_DIR + "room";
	}

}
