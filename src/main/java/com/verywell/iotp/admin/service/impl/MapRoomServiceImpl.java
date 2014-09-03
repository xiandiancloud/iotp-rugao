package com.verywell.iotp.admin.service.impl;

import java.util.List;

import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.verywell.framework.dao.BaseHibernateDAO;
import com.verywell.framework.service.BaseCrudServiceImpl;
import com.verywell.iotp.admin.dao.MapRoomInfoDAO;
import com.verywell.iotp.admin.entity.dev.DevInfo;
import com.verywell.iotp.admin.entity.map.MapRoomInfo;
import com.verywell.iotp.admin.service.DevInfoService;
import com.verywell.iotp.admin.service.MapRoomService;

/**
 * 房间相关服务
 * 
 * @author yao
 * 
 */
@Service
@Transactional(readOnly = true)
public class MapRoomServiceImpl extends BaseCrudServiceImpl<MapRoomInfo, Long> implements MapRoomService
{
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DevInfoService devInfoService;

	@Autowired
	@Override
	@Qualifier(value = "mapRoomInfoDAO")
	public void setBaseDao(BaseHibernateDAO<MapRoomInfo, Long> baseDao)
	{
		this.baseDao = baseDao;

	}

	@Override
	@Transactional(readOnly = true)
	public List<MapRoomInfo> findByBuildingId(Long buildingId) throws Exception
	{
		String hql = "from MapRoomInfo m where m.mapBuildingInfo.buildingId=? order by m.floorNo asc";
		List<MapRoomInfo> roomList = ((MapRoomInfoDAO) baseDao).findByHql(hql, buildingId);
		if (roomList != null && !roomList.isEmpty())
		{
			for (MapRoomInfo room : roomList)
			{
				room.setAlarmStatus(devInfoService.getAlarmDevCountByRoomId(room.getRoomId()) > 0 ? DevInfo.ALARM_STATUS_ABNORMAL : DevInfo.ALARM_STATUS_NORMAL);
			}
		}

		return roomList;
	}

	/**
	 * 获得所有房间列表，并按房间名称排序
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MapRoomInfo> findByRoomType(Integer roomType) throws Exception
	{
		Object[] values = new Object[] { roomType };
		String hql = "from MapRoomInfo as model where model.roomType = ? and model.status=1 order by roomName";
		return baseDao.findByHql(hql, values);
	}

	@Override
	public List<MapRoomInfo> findAll()
	{
		return baseDao.findAll(Order.asc("roomId"));
	}

	@Override
	public List<MapRoomInfo> findAllRoom() throws Exception
	{
		return baseDao.findAll();
	}

}
