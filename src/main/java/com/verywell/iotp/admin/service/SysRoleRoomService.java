package com.verywell.iotp.admin.service;

import java.util.List;

import com.verywell.framework.service.BaseCrudService;
import com.verywell.iotp.admin.entity.map.MapRoomInfo;
import com.verywell.iotp.admin.entity.sys.SysLogin;
import com.verywell.iotp.admin.entity.sys.SysRoleRoom;

public interface SysRoleRoomService extends BaseCrudService<SysRoleRoom, Long>{

	/**
	 * <p>
	 * Description:[根据用户拥有的角色ID曲获取房间的集合]
	 * </p>
	 * 
	 * @param roleIds
	 * @return
	 * @throws Exception
	 */
	public List<MapRoomInfo> findByRoleIds(Long[] roleIds) throws Exception;
	
	/**
	 * <p>
	 * Description:[根据角色id查找房间权限组装的string 以 “,” 分割]
	 * </p>
	 * 
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public String findRoomIdsByRoleIds(Long roleId) throws Exception;
	
	/**
	 * <p>
	 * Description:[根据登陆用户获取房间权限]
	 * </p>
	 * 
	 * @param login
	 * @return
	 * @throws Exception
	 */
	public List<MapRoomInfo> findRoomByLogin(SysLogin login) throws Exception;
	
	/**
	 * <p>
	 * Description:[根据角色id查找房间权限集合]
	 * </p>
	 * 
	 * @param roleId
	 * @return
	 * @throws Exception
	 */
	public List<MapRoomInfo> findRoomByRoleId(Long roleId) throws Exception;
}
