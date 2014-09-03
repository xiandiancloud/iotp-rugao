package com.verywell.iotp.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.verywell.framework.dao.BaseHibernateDAO;
import com.verywell.framework.service.BaseCrudServiceImpl;
import com.verywell.iotp.admin.constants.ResultConstants;
import com.verywell.iotp.admin.dao.SceneConfigInfoDAO;
import com.verywell.iotp.admin.entity.scene.SceneInfo;
import com.verywell.iotp.admin.service.SceneService;

/**
 * 场景相关服务实现类
 * 
 * @author yao
 * 
 */
@Service
@Transactional(readOnly = true)
public class SceneServiceImpl extends BaseCrudServiceImpl<SceneInfo, Long> implements SceneService
{
	@Autowired
	private SceneConfigInfoDAO sceneConfigInfoDAO;

	@Autowired
	@Override
	@Qualifier(value = "sceneInfoDAO")
	public void setBaseDao(BaseHibernateDAO<SceneInfo, Long> baseDao)
	{
		this.baseDao = baseDao;
	}

	@Override
	public List<SceneInfo> findByRoomId(Long roomId)
	{
		return baseDao.findByHql("from SceneInfo dev where dev.roomId=?", roomId);
	}

	@Override
	public List<SceneInfo> findByRoomIdAndSceneType(Long roomId, Integer sceneType)
	{
		return baseDao.findByHql("from SceneInfo dev where dev.roomId=? and dev.sceneType=?", roomId, sceneType);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = Throwable.class)
	public int update(SceneInfo entity)
	{
		sceneConfigInfoDAO.deleteBySceneId(entity.getSceneId());
		baseDao.update(entity);
		return ResultConstants.UPDATE_SUCCEED;
	}

	@Override
	public List<SceneInfo> findBySceneType(Integer sceneType)
	{
		return baseDao.findByHql("from SceneInfo dev where dev.sceneType=?", sceneType);
	}
}
