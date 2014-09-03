package com.verywell.iotp.admin.service;

import java.util.List;

import com.verywell.framework.service.BaseCrudService;
import com.verywell.iotp.admin.entity.sys.SysPermission;
import com.verywell.iotp.admin.entity.sys.SysRolePermission;

public interface SysRolePermissionService extends BaseCrudService<SysRolePermission, Long>{

	/**
	 * <p>
	 * Description:[根据角色ID的集合获取权限【type =1 (菜单)】集合]，并排序
	 * </p>
	 * 
	 * @param roleIds
	 * @return
	 * @throws Exception
	 */
	public List<SysPermission> findByRoleIds(Long[] roleIds) throws Exception;
	
	/**
	 * <p>
	 * Description:[根据角色id查找菜单权限组装的string 以 “,” 分割]
	 * </p>
	 * 
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public String findPermissionIdsByRoleIds(Long roleId) throws Exception;
}
