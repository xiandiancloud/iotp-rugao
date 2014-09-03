package com.verywell.iotp.admin.service;

import com.verywell.framework.service.BaseCrudService;
import com.verywell.iotp.admin.entity.dev.DevClassGroupInfo;

/**
 * 设备类型分组相关服务
 * 
 * @author yao
 * 
 */
public interface DevClassGroupInfoService extends BaseCrudService<DevClassGroupInfo, Long>
{
	public int save(DevClassGroupInfo entity) throws Exception;
	
	public int delete(Long id) throws Exception;
	
	public int batchDelete(Long[] ids) throws Exception;
	
	public int update(DevClassGroupInfo entity) throws Exception;
}
