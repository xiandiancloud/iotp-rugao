package com.verywell.iotp.admin.service;

import java.util.List;

import com.verywell.framework.service.BaseCrudService;
import com.verywell.iotp.admin.entity.map.MapRoomInfo;

/**
 * 房间相关服务
 * 
 * @author yao
 * 
 */
public interface MapRoomService extends BaseCrudService<MapRoomInfo, Long>
{
	/**
	 * 根据建筑物ID获得该建筑物下的所有房间列表
	 * 
	 * @param buildingId
	 * @return
	 */
	List<MapRoomInfo> findByBuildingId(Long buildingId) throws Exception;

	/**
	 * 根据房间类型获得房间信息
	 * 
	 * @return　会议室列表集合
	 * @throws Exception
	 */
	List<MapRoomInfo> findByRoomType(Integer roomType) throws Exception;

	/**
	 * 根据系统类型查询菜单信息
	 * 
	 * @param systemId
	 *            　系统类型
	 * @return　权限信息集合
	 * @throws Exception
	 */
	List<MapRoomInfo> findAllRoom() throws Exception;

}
