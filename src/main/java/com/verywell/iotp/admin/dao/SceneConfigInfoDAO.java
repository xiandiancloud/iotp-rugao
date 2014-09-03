package com.verywell.iotp.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.verywell.framework.dao.BaseHibernateDAO;
import com.verywell.iotp.admin.entity.scene.SceneConfigInfo;

/**
 * 设备场景配置数据库访问类
 * 
 * @author yao
 * 
 */
@Repository
public class SceneConfigInfoDAO extends BaseHibernateDAO<SceneConfigInfo, Long>
{
	public void deleteBySceneId(Long sceneId)
	{
		this.executeHQL("delete from SceneConfigInfo where sceneInfo.sceneId=?", sceneId);
	}
	
	public List<SceneConfigInfo> getSceneConfigInfo(Long sceneId)
	{
		String hql = "from SceneConfigInfo where sceneInfo.sceneId=?";
		return this.findByHql(hql, sceneId);
	}
}
