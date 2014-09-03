package com.verywell.iotp.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.verywell.framework.dao.BaseHibernateDAO;
import com.verywell.framework.service.BaseCrudServiceImpl;
import com.verywell.iotp.admin.constants.ResultConstants;
import com.verywell.iotp.admin.dao.DevInfoDAO;
import com.verywell.iotp.admin.entity.dev.DevClassGroupInfo;
import com.verywell.iotp.admin.entity.dev.DevClassInfo;
import com.verywell.iotp.admin.service.DevClassGroupInfoService;
import com.verywell.iotp.admin.service.DevClassInfoService;

/**
 * 设备类型相关服务实现类
 * 
 * @author yao
 * 
 */
@Service
@Transactional(readOnly = true)
public class DevClassInfoServiceImpl extends BaseCrudServiceImpl<DevClassInfo, Long> implements DevClassInfoService
{
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DevClassGroupInfoService devClassGroupInfoService;

	@Autowired
	private DevInfoDAO devInfoDAO;

	@Autowired
	@Override
	@Qualifier(value = "devClassInfoDAO")
	public void setBaseDao(BaseHibernateDAO<DevClassInfo, Long> baseDao)
	{
		this.baseDao = baseDao;

	}

	@Override
	@Transactional(readOnly = false)
	public int save(DevClassInfo entity, Long groupId) throws Exception
	{
		Object[] params = { groupId, entity.getClassName() };
		String hql = "select count(*) from DevClassInfo as model where model.devClassGroupInfo.groupId= ? and model.className=?)";
		long count = baseDao.findLong(hql, params);
		if (count > 0)
		{
			return ResultConstants.SAVE_FAILED_NAME_IS_EXIST;
		}
		else
		{
			DevClassGroupInfo dcgi = devClassGroupInfoService.findById(groupId);
			entity.setDevClassGroupInfo(dcgi);
			baseDao.save(entity);
			return ResultConstants.SAVE_SUCCEED;
		}
	}

	@Override
	@Transactional(readOnly = false)
	public int delete(Long id) throws Exception
	{
		String hql = "select count(*) from DevInfo model where model.devClassInfo.devClassId = ?";
		long count = this.devInfoDAO.findLong(hql, id);
		if (count > 0)
		{
			return ResultConstants.DELETE_FAILED_IS_REF;
		}
		else
		{
			baseDao.deleteById(id);
			return ResultConstants.DELETE_SUCCEED;
		}
	}

	@Override
	@Transactional(readOnly = false)
	public int batchDelete(Long[] ids) throws Exception
	{
		List<Long> idList = new ArrayList<Long>();
		for (Long id : ids)
		{
			idList.add(id);
		}
		String hql = "select count(*) from DevInfo as model where model.devClassId in ?";
		long count = this.devInfoDAO.findLong(hql, idList);
		if (count > 0)
		{
			return ResultConstants.DELETE_FAILED_IS_REF;
		}
		else
		{
			baseDao.deleteById(idList);
			return ResultConstants.DELETE_SUCCEED;
		}
	}

	@Override
	@Transactional(readOnly = false)
	public int update(DevClassInfo entity, Long groupId) throws Exception
	{
		entity.setDevClassGroupInfo(devClassGroupInfoService.findById(groupId));
		Object[] params = { entity.getClassName(), entity.getDevClassId() };
		String hql = "select count(model.devClassId) from DevClassInfo as model where model.className=? and model.devClassId != ?)";
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
}
