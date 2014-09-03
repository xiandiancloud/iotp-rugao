package com.verywell.iotp.admin.service;

import java.util.List;

import com.verywell.framework.service.BaseCrudService;
import com.verywell.iotp.admin.entity.dev.DevAttrHistoryInfo;

/**
 * 设备属性历史信息相关服务
 * 
 * @author yao
 * 
 */
public interface DevAttrHistoryInfoService extends BaseCrudService<DevAttrHistoryInfo, Long>
{
	public List<DevAttrHistoryInfo> findLastestByDevId(Long devId, int recordNum);

	public DevAttrHistoryInfo getLastestByDevId(Long devId);
}
