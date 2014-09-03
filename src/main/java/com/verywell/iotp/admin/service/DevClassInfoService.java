package com.verywell.iotp.admin.service;

import com.verywell.framework.service.BaseCrudService;
import com.verywell.iotp.admin.entity.dev.DevClassInfo;

/**
 * 设备类型相关服务
 * 
 * @author yao
 * 
 */
public interface DevClassInfoService extends BaseCrudService<DevClassInfo, Long>
{
	public int save(DevClassInfo entity,Long groupId) throws Exception;
	
	public int delete(Long id) throws Exception;
	
	public int batchDelete(Long[] ids) throws Exception;
	
	public int update(DevClassInfo entity,Long groupId) throws Exception;
	
}
