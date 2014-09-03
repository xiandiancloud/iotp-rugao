package com.verywell.iotp.admin.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.verywell.framework.dao.BaseHibernateDAO;
import com.verywell.framework.service.BaseCrudServiceImpl;
import com.verywell.iotp.admin.entity.car.Department;
import com.verywell.iotp.admin.service.DepartmentService;

@Service
public class DepartmentServiceImpl extends BaseCrudServiceImpl<Department, Integer> implements DepartmentService{

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Override
	@Qualifier(value = "departmentDAO")
	public void setBaseDao(BaseHibernateDAO<Department, Integer> baseDao)
	{
		this.baseDao = baseDao;

	}
}
