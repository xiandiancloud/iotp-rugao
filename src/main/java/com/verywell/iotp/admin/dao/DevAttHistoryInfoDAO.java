package com.verywell.iotp.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.verywell.framework.dao.BaseHibernateDAO;
import com.verywell.iotp.admin.entity.dev.DevAttrHistoryInfo;

@Repository
public class DevAttHistoryInfoDAO extends BaseHibernateDAO<DevAttrHistoryInfo, String>
{
	public List<DevAttrHistoryInfo> findByDevId(Long devId)
	{
		String hql = "from DevAttrHistoryInfo model where model.devInfo.devId=?";
		return this.findByHql(hql, devId);
	}
}
