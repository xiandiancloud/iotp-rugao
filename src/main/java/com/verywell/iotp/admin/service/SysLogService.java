package com.verywell.iotp.admin.service;

import com.verywell.framework.service.BaseCrudService;
import com.verywell.iotp.admin.entity.sys.SysLog;

/**
 * @title: FSysLogBO
 * @description: 日志管理接口类
 * 
 * 
 * @author: zhujie
 * 
 */
public interface SysLogService extends BaseCrudService<SysLog, Long>
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
	void save(Integer operType, String operDesc);

}
