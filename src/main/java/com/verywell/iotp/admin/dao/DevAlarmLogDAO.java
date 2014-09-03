package com.verywell.iotp.admin.dao;

import org.springframework.stereotype.Repository;

import com.verywell.framework.dao.BaseHibernateDAO;
import com.verywell.iotp.admin.entity.dev.DevAlarmLog;

/**
 * 设备告警日志表据库访问类
 * 
 * @author yao
 * 
 */
@Repository
public class DevAlarmLogDAO extends BaseHibernateDAO<DevAlarmLog, Long>
{

}
