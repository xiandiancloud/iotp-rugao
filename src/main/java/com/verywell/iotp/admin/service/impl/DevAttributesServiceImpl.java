package com.verywell.iotp.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.verywell.framework.dao.BaseHibernateDAO;
import com.verywell.framework.dao.Page;
import com.verywell.framework.dao.PropertyFilter;
import com.verywell.framework.service.BaseCrudServiceImpl;
import com.verywell.iotp.admin.constants.ResultConstants;
import com.verywell.iotp.admin.entity.dev.DevAttributes;
import com.verywell.iotp.admin.service.DevAttributesService;

@Service
public class DevAttributesServiceImpl extends BaseCrudServiceImpl<DevAttributes, String> implements DevAttributesService
{

	@Autowired
	@Override
	@Qualifier(value = "devAttributesDAO")
	public void setBaseDao(BaseHibernateDAO<DevAttributes, String> baseDao)
	{
		this.baseDao = baseDao;
	}

	@Override
	@Transactional(readOnly = false)
	public int save(DevAttributes entity) throws Exception
	{
		Object[] params = { entity.getAttrName(), entity.getDevClassInfo().getDevClassId() };
		String hql = "select count(*) from DevAttributes as model where model.attrName = ? and model.devClassInfo.devClassId = ?)";
		long count = baseDao.findLong(hql, params);
		if (count > 0)
		{
			return ResultConstants.SAVE_FAILED_NAME_IS_EXIST;
		}
		else
		{
			baseDao.save(entity);
			return ResultConstants.SAVE_SUCCEED;
		}
	}

	@Override
	@Transactional(readOnly = false)
	public int update(DevAttributes entity) throws Exception
	{
		Object[] params = { entity.getAttrName(), entity.getAttrKey(), entity.getDevClassInfo().getDevClassId() };
		String hql = "select count(*) from DevAttributes as model where model.attrName = ? and model.attrKey != ? and model.devClassInfo.devClassId = ?)";
		long count = baseDao.findLong(hql, params);
		if (count > 0)
		{
			return ResultConstants.UPDATE_FAILED_NAME_IS_EXIST;
		}
		else
		{
			baseDao.update(entity);
			return ResultConstants.UPDATE_SUCCEED;
		}
	}

	@Override
	@Transactional(readOnly = false)
	public int batchDelete(String[] ids) throws Exception
	{
		for (int i = 0; i < ids.length; i++)
		{
			baseDao.deleteById(ids[i]);
		}
		return ResultConstants.DELETE_SUCCEED;
	}

	@Override
	@Transactional(readOnly = true)
	public List<DevAttributes> findByDevClassId(Long devClassId)
	{
		String hql = "from DevAttributes as model where model.devClassInfo.devClassId = ?)";
		return baseDao.findByHql(hql, devClassId);
	}
}
