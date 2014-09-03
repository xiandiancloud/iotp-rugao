/**
 * 
 */
package com.verywell.iotp.admin.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.verywell.framework.dao.BaseHibernateDAO;
import com.verywell.framework.service.BaseCrudServiceImpl;
import com.verywell.framework.utils.DateTimeUtil;
import com.verywell.framework.utils.web.WebContextHolder;
import com.verywell.iotp.admin.commons.LoginToken;
import com.verywell.iotp.admin.entity.sys.SysLog;
import com.verywell.iotp.admin.service.SysLogService;

/**
 * @title: FSysLogBOImpl
 * @description: 系统日志管理接口的实现
 * 
 * 
 * @author: zhujie
 * 
 */
@Service
public class SysLogServiceImpl extends BaseCrudServiceImpl<SysLog, Long> implements SysLogService
{
	/**
	 * 保存日志信息
	 * 
	 * @param request
	 * @param operType
	 *            操作类型
	 * @param operDesc
	 *            操作说明
	 * @throws Exception
	 */
	@Override
	public void save(Integer operType, String operDesc)
	{
		LoginToken loginToken = WebContextHolder.getCurrLoginToken();
		HttpServletRequest request = WebContextHolder.getRequest();
		if (request != null)
		{
			SysLog sysLog = new SysLog();
			sysLog.setOperDesc(operDesc);
			sysLog.setOperIp(request.getRemoteAddr());
			sysLog.setOperTime(DateTimeUtil.getChar14());
			sysLog.setOperType(operType);
			sysLog.setOperUserId(loginToken.getSysLogin().getLoginId());
			sysLog.setOperUserName(loginToken.getSysLogin().getUserName());
			sysLog.setSystemId(loginToken.getSysLogin().getSystemId());
			baseDao.save(sysLog);
		}
	}

	@Autowired
	@Qualifier("sysLogDAO")
	@Override
	public void setBaseDao(BaseHibernateDAO<SysLog, Long> baseDao)
	{
		this.baseDao = baseDao;

	}
}
