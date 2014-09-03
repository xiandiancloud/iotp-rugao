package com.verywell.iotp.admin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.verywell.framework.dao.BaseHibernateDAO;
import com.verywell.framework.dao.Page;
import com.verywell.framework.dao.PropertyFilter;
import com.verywell.framework.dao.PropertyFilter.MatchType;
import com.verywell.framework.service.BaseCrudServiceImpl;
import com.verywell.iotp.admin.dao.DevInfoDAO;
import com.verywell.iotp.admin.entity.dev.DevAlarmLog;
import com.verywell.iotp.admin.entity.map.MapRoomInfo;
import com.verywell.iotp.admin.service.DevAlarmLogService;

/**
 * 设备告警相关服务实现类
 * 
 * @author yao
 * 
 */
@Service
public class DevAlarmLogServiceImpl extends BaseCrudServiceImpl<DevAlarmLog, Long> implements DevAlarmLogService
{
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DevInfoDAO devInfoDAO;

	@Autowired
	@Override
	@Qualifier(value = "devAlarmLogDAO")
	public void setBaseDao(BaseHibernateDAO<DevAlarmLog, Long> baseDao)
	{
		this.baseDao = baseDao;

	}
	
	public Page query(Page page,List<PropertyFilter> filters,Map<Long,MapRoomInfo> roomMap) throws Exception{
		Set<Long> set = roomMap.keySet();
		Iterator<Long> iterator = set.iterator();
		List<Long> idList = new ArrayList<Long>();
		while(iterator.hasNext()){
			idList.add(iterator.next());
		}
		boolean flag = true;
		if(!filters.isEmpty()){
			for(PropertyFilter pf : filters){
				if("mapRoomInfo.roomId".equals(pf.getPropertyName())){
					flag = false;
					break;
				}
			}
		}
		if(flag){
			filters.add(new PropertyFilter("IN_mapRoomInfo.roomId",idList));
		}

		page = this.baseDao.findByPage(page, filters);
		List<DevAlarmLog> resultList = page.getResult();
		if(!resultList.isEmpty()){
			List<DevAlarmLog> resultTemp = new ArrayList<DevAlarmLog>();
			for(DevAlarmLog dal:resultList){
				dal.setDevName(devInfoDAO.findById(dal.getDevId()).getDevName());
				resultTemp.add(dal);
			}
			page.setResult(resultTemp);
		}
		
		return page;
	}

	@Override
	public DevAlarmLog queryLastTime(Map<Long, MapRoomInfo> map) throws Exception {
		Set<Long> set = map.keySet();
		Iterator<Long> iterator = set.iterator();
		List<Long> idList = new ArrayList<Long>();
		while(iterator.hasNext()){
			idList.add(iterator.next());
		}
		
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		filters.add(new PropertyFilter("status",MatchType.EQ,0));
		filters.add(new PropertyFilter("IN_mapRoomInfo.roomId",idList));
		Page page = new Page();
		page.setPageSize(1);
		page.setOrderBy("alarmTime");
		page.setOrder("desc");
		page = this.baseDao.findByPage(page, filters);
		if(!page.getResult().isEmpty()){
			return (DevAlarmLog)page.getResult().get(0);
		}else{
			return new DevAlarmLog();
		}
	}

	@Override
	@Transactional(readOnly = false)
	public boolean precessAlarm(Long alarmId) throws Exception {
		DevAlarmLog log = this.findById(alarmId);
		if(log != null){
			log.setStatus(1);
			this.update(log);
			return true;
		}
		return false;
	}

}
