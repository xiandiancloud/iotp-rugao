package com.verywell.iotp.admin.dao;

import org.springframework.stereotype.Repository;

import com.verywell.framework.dao.BaseHibernateDAO;
import com.verywell.iotp.admin.entity.car.Employee;

@Repository
public class EmployeeDAO extends BaseHibernateDAO<Employee, Integer> {

}
