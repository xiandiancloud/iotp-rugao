/**
 * 
 */
package com.verywell.iotp.admin.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.verywell.framework.dao.BaseHibernateDAO;
import com.verywell.iotp.admin.entity.sys.SysPermission;
import com.verywell.iotp.admin.entity.sys.SysRolePermission;

/**
 * @title:
 * @description:
 * 
 * @author: dongyuese
 * 
 */
@Repository
public class SysRolePermissionDAO extends BaseHibernateDAO<SysRolePermission, Long>
{
	public void deleteByRoleId(Long roleId)
	{
		List<SysRolePermission> list = this.findByProperty("sysRole.roleId", roleId);
	}
}
