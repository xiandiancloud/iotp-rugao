package com.verywell.iotp.admin.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.verywell.framework.dao.BaseHibernateDAO;
import com.verywell.framework.service.BaseCrudServiceImpl;
import com.verywell.iotp.admin.entity.car.Employee;
import com.verywell.iotp.admin.service.EmployeeService;

@Service
public class EmployeeServiceImpl extends BaseCrudServiceImpl<Employee, Integer> implements EmployeeService {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Override
	@Qualifier(value = "employeeDAO")
	public void setBaseDao(BaseHibernateDAO<Employee, Integer> baseDao)
	{
		this.baseDao = baseDao;

	}
}
