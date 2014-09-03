/**
 * 
 */
package com.verywell.iotp.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.verywell.framework.dao.BaseHibernateDAO;
import com.verywell.iotp.admin.entity.sys.SysLoginRole;

/**
 * @title:
 * @description:
 * 
 * @author: dongyuese
 * 
 */
@Repository
public class SysLoginRoleDAO extends BaseHibernateDAO<SysLoginRole, Long>
{
	public void deleteByLoginId(Long loginId)
	{
		List<SysLoginRole> list = this.findByProperty("sysLogin.loginId", loginId);
		for (SysLoginRole sysLoginRole : list)
		{
			this.delete(sysLoginRole);
		}
	}
}
