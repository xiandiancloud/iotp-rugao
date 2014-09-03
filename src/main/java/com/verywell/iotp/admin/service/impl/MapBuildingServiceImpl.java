package com.verywell.iotp.admin.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.verywell.framework.dao.BaseHibernateDAO;
import com.verywell.framework.service.BaseCrudServiceImpl;
import com.verywell.iotp.admin.entity.map.MapBuildingInfo;
import com.verywell.iotp.admin.service.MapBuildingService;

/**
 * 建筑物相关服务实现类
 * 
 * @author yao
 * 
 */
@Service
public class MapBuildingServiceImpl extends BaseCrudServiceImpl<MapBuildingInfo, Long> implements MapBuildingService
{
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Override
	@Qualifier(value = "mapBuildingInfoDAO")
	public void setBaseDao(BaseHibernateDAO<MapBuildingInfo, Long> baseDao)
	{
		this.baseDao = baseDao;

	}

}
