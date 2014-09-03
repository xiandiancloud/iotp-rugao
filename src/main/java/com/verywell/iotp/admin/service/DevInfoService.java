package com.verywell.iotp.admin.service;

import java.util.List;

import com.verywell.framework.service.BaseCrudService;
import com.verywell.iotp.admin.entity.dev.DevInfo;

/**
 * 设备相关服务
 * 
 * @author yao
 * 
 */
public interface DevInfoService extends BaseCrudService<DevInfo, Long>
{

	/**
	 * 根据房间编号获得该房间内产生告警设备数量
	 * 
	 * @param roomId
	 * @return
	 */
	long getAlarmDevCountByRoomId(Long roomId) throws Exception;

	/**
	 * 根据房间编号获得房间中的设备信息
	 * 
	 * @param roomId
	 * @return
	 */
	List<DevInfo> findByRoomId(Long roomId) throws Exception;

	/**
	 * 根据房间编号获得房间中的设备信息
	 * 
	 * @param roomId
	 * @return
	 */
	List<DevInfo> findByRoomIdAndExceptClassGroupId(Long roomId, Long... classGroupId) throws Exception;

	/**
	 * 根据房间ID和设备类型分组ID获得设备列表
	 * 
	 * @param roomId
	 * @param classGroupId
	 * @return
	 */
	List<DevInfo> findByClassGroupId(Long roomId, Long classGroupId) throws Exception;

	/**
	 * 根据设备类型分组ID，获得所有设备列表
	 * 
	 * @param classGroupId
	 * @return
	 * @throws Exception
	 */
	List<DevInfo> findByClassGroupId(Long classGroupId) throws Exception;

	/**
	 * 更新设备位置
	 * 
	 * @param devId
	 * @param posX
	 * @param posY
	 * @return
	 * @throws Exception
	 */
	int updatePosition(Long devId, Integer posX, Integer posY) throws Exception;

	/**
	 * 设备控制
	 * 
	 * @param devId
	 * @param cmd
	 * @param value
	 * @return
	 * @throws Exception
	 */
	String controllDev(String devIds, String cmd, String value) throws Exception;

	/**
	 * 场景设置
	 * 
	 * @param sceneId
	 * @return
	 * @throws Exception
	 */
	String setScene(Long sceneId) throws Exception;

}
