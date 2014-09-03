package com.verywell.iotp.admin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.verywell.framework.dao.BaseHibernateDAO;
import com.verywell.framework.service.BaseCrudServiceImpl;
import com.verywell.iotp.admin.constants.ResultConstants;
import com.verywell.iotp.admin.dao.DevClassInfoDAO;
import com.verywell.iotp.admin.dao.DevInfoDAO;
import com.verywell.iotp.admin.entity.dev.DevClassGroupInfo;
import com.verywell.iotp.admin.entity.dev.DevClassInfo;
import com.verywell.iotp.admin.service.DevClassGroupInfoService;

/**
 * 设备分类分组服务实现类
 * 
 * @author yao
 * 
 */
@Service
public class DevClassGroupInfoServiceImpl extends BaseCrudServiceImpl<DevClassGroupInfo, Long> implements
		DevClassGroupInfoService
{
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DevClassInfoDAO devClassInfoDAO;
	
	@Autowired
	private DevInfoDAO devInfoDAO;

	@Autowired
	@Override
	@Qualifier(value = "devClassGroupInfoDAO")
	public void setBaseDao(BaseHibernateDAO<DevClassGroupInfo, Long> baseDao)
	{
		this.baseDao = baseDao;

	}
	
	@Override
	@Transactional(readOnly = false)
	public int save(DevClassGroupInfo entity) throws Exception{
		Object[] params = {entity.getGroupName()};
		String hql = "select count(*) from DevClassGroupInfo as model where model.groupName=?)";
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
	public int delete(Long id) throws Exception{
		String hql = "from DevClassInfo  model where model.devClassGroupInfo.groupId = ?";
		List<DevClassInfo> list = this.devClassInfoDAO.findByHql(hql, id);
		if(!list.isEmpty()){
			return ResultConstants.DELETE_FAILED_IS_REF;
		}else{
			baseDao.deleteById(id);
			return ResultConstants.DELETE_SUCCEED;
		}
	}
	
	@Override
	@Transactional(readOnly = false)
	public int batchDelete(Long[] ids) throws Exception{
		List<Long> idList = new ArrayList<Long>();
		for(Long id : ids){
			idList.add(id);
		}
		Map m = new HashMap();
		m.put("groupIds", ids);
		String hql = "from DevClassInfo  model where model.devClassGroupInfo.groupId in (:groupIds)";
		List<DevClassInfo> list = this.devClassInfoDAO.findByHql(hql, m);
		if(!list.isEmpty()){
			return ResultConstants.DELETE_FAILED_IS_REF;
		}else{
			baseDao.deleteById(idList);
			return ResultConstants.DELETE_SUCCEED;
		}
		
	}
	
	@Override
	@Transactional(readOnly = false)
	public int update(DevClassGroupInfo entity) throws Exception{
		Object[] params1 = {entity.getGroupName(),entity.getGroupId()};
		String hql = "select count(*) from DevClassGroupInfo as model where model.groupName = ? and model.groupId != ?)";
		long count = baseDao.findLong(hql, params1);
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
