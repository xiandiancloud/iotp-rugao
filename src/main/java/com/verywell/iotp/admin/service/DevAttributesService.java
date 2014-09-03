package com.verywell.iotp.admin.service;

import java.util.List;

import com.verywell.framework.service.BaseCrudService;
import com.verywell.iotp.admin.entity.dev.DevAttributes;

public interface DevAttributesService extends BaseCrudService<DevAttributes, String>
{

	public int save(DevAttributes entity) throws Exception;

	public int update(DevAttributes entity) throws Exception;

	public int batchDelete(String[] ids) throws Exception;

	public List<DevAttributes> findByDevClassId(Long devClassId);
}
