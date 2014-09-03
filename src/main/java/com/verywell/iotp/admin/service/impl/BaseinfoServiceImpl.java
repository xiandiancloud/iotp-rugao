package com.verywell.iotp.admin.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.verywell.framework.dao.BaseHibernateDAO;
import com.verywell.framework.service.BaseCrudServiceImpl;
import com.verywell.iotp.admin.entity.car.Baseinfo;
import com.verywell.iotp.admin.service.BaseinfoService;

@Service
public class BaseinfoServiceImpl extends BaseCrudServiceImpl<Baseinfo, Integer> implements BaseinfoService {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Override
	@Qualifier(value = "baseinfoDAO")
	public void setBaseDao(BaseHibernateDAO<Baseinfo, Integer> baseDao)
	{
		this.baseDao = baseDao;

	}

}
