package com.verywell.iotp.admin.service;

import java.util.List;
import java.util.Map;

import com.verywell.framework.dao.Page;
import com.verywell.framework.dao.PropertyFilter;
import com.verywell.framework.service.BaseCrudService;
import com.verywell.iotp.admin.entity.dev.DevAlarmLog;
import com.verywell.iotp.admin.entity.map.MapRoomInfo;

/**
 * 设备告警日志相关服务
 * 
 * @author yao
 * 
 */
public interface DevAlarmLogService extends BaseCrudService<DevAlarmLog, Long>
{
	public Page query(Page page,List<PropertyFilter> filters,Map<Long,MapRoomInfo> roomMap) throws Exception;
	
	public DevAlarmLog queryLastTime(Map<Long, MapRoomInfo> map) throws Exception;
	
	public boolean precessAlarm(Long alarmId) throws Exception;
}
