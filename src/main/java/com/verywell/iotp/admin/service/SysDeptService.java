package com.verywell.iotp.admin.service;

import java.util.List;

import com.verywell.framework.service.BaseCrudService;
import com.verywell.iotp.admin.entity.sys.SysDept;

public interface SysDeptService extends BaseCrudService<SysDept, Long>
{
	public List<SysDept> findByCorpId(Long corpId);
	
}
