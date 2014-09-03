package com.verywell.iotp.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.verywell.framework.dao.BaseHibernateDAO;
import com.verywell.framework.dao.Page;
import com.verywell.framework.dao.PropertyFilter;
import com.verywell.framework.dao.PropertyFilter.MatchType;
import com.verywell.framework.service.BaseCrudServiceImpl;
import com.verywell.framework.utils.web.WebContextHolder;
import com.verywell.iotp.admin.commons.LoginToken;
import com.verywell.iotp.admin.constants.CommonConstants;
import com.verywell.iotp.admin.constants.ResultConstants;
import com.verywell.iotp.admin.dao.SysCorpDAO;
import com.verywell.iotp.admin.dao.SysPermissionDAO;
import com.verywell.iotp.admin.dao.SysRoleDAO;
import com.verywell.iotp.admin.dao.SysRolePermissionDAO;
import com.verywell.iotp.admin.dao.SysRoleRoomDAO;
import com.verywell.iotp.admin.entity.map.MapRoomInfo;
import com.verywell.iotp.admin.entity.sys.SysCorp;
import com.verywell.iotp.admin.entity.sys.SysLog;
import com.verywell.iotp.admin.entity.sys.SysLogin;
import com.verywell.iotp.admin.entity.sys.SysPermission;
import com.verywell.iotp.admin.entity.sys.SysRole;
import com.verywell.iotp.admin.entity.sys.SysRolePermission;
import com.verywell.iotp.admin.entity.sys.SysRoleRoom;
import com.verywell.iotp.admin.service.SysLogService;
import com.verywell.iotp.admin.service.SysLoginRoleService;
import com.verywell.iotp.admin.service.SysLoginService;
import com.verywell.iotp.admin.service.SysRoleService;

/**
 * @title: FSysRoleBOImpl.java
 * @description: 角色管理业务处理类
 * 
 * 
 * 
 * @author: dongyuese
 */
@Service
public class SysRoleServiceImpl extends BaseCrudServiceImpl<SysRole, Long> implements SysRoleService
{
	/**
	 * 角色信息数据访问对象
	 */
	@Autowired
	private SysRoleDAO sysRoleDAO;

	/**
	 * 单位信息数据访问对象
	 */
	@Autowired
	private SysCorpDAO sysCorpDAO;

	/**
	 * 角色权限关系信息数据访问对象
	 */
	@Autowired
	private SysRolePermissionDAO sysRolePermissionDAO;

	/**
	 * 用户信息业务操作对象
	 */
	@Autowired
	private SysLoginService sysLoginService;
	
	@Autowired
	private SysLoginRoleService sysLoginRoleService;

	/**
	 * 权限信息数据访问对象
	 */
	@Autowired
	private SysPermissionDAO sysPermissionDAO;
	
	/**
	 * 房间权限信息数据访问对象
	 */
	@Autowired
	private SysRoleRoomDAO sysRoleRoomDAO;

	/**
	 * 系统日志操作接口
	 */
	@Autowired
	private SysLogService sysLogService;

	/**
	 * 保存角色信息
	 * 
	 * @param sysRole
	 *            　角色信息数据传输对象
	 * @return　保存成功或失败
	 * 
	 * 
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Throwable.class)
	public int save(SysRole sysRole) throws Exception
	{
		LoginToken loginToken = WebContextHolder.getCurrLoginToken();
		sysRole.setCorpId(loginToken.getSysLogin().getSysCorp().getCorpId());
		sysRole.setSystemId(loginToken.getSysLogin().getSystemId());

		// 判断是否已经存在同名角色
		if (sysRoleDAO.findUnique("roleName", sysRole.getRoleName()) == null)
		{
			String permissionIds = sysRole.getPermissionIds();
			String roomIds = sysRole.getRoomIds();
			
			// 保存角色信息
			sysRoleDAO.save(sysRole);
			
			//保存角色菜单
			saveRoleMenu(permissionIds, sysRole);
			//保存角色房间
			saveRoleRoom(roomIds, sysRole);
			// 保存角色权限关联信息
			// this.saveRoleMenu(sysRole.getMenuIdStr(), sysRole);
			sysLogService.save(SysLog.OPERATE_TYPE_ADD, "添加角色成功,roleName:" + sysRole.getRoleName());
			return ResultConstants.SAVE_SUCCEED;
		}
		return ResultConstants.SAVE_FAILED_NAME_IS_EXIST;
	}
	
	/**
	 * 根据角色ID删除角色信息
	 * 
	 * @param roleId
	 *            　角色ID
	 * @return　删除成功或失败
	 * 
	 * 
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Throwable.class)
	public int delete(Long roleId) throws Exception
	{
		// 有用户引用的角色不能被删除
		Long[] roleIds = new Long[1];
		roleIds[0] = roleId;
		if (this.validationRole(roleIds))
		{
			// 删除角色信息
			sysRolePermissionDAO.deleteByRoleId(roleId);
			sysRoleDAO.deleteById(roleId);
			sysLogService.save(SysLog.OPERATE_TYPE_DELETE, "删除角色成功,roleId:" + roleId);
			return ResultConstants.DELETE_SUCCEED;
		}
		else
			return ResultConstants.DELETE_FAILED_IS_REF;
	}

	/**
	 * 按照条件分页查询角色信息
	 * 
	 * @param page
	 *            　分页信息
	 * @param filters
	 *            　过滤条件列表
	 * @return　角色信息集合
	 * @throws Exception
	 */
	@Override
	public Page query(Page page, List<PropertyFilter> filters) throws Exception
	{
		LoginToken loginToken = WebContextHolder.getCurrLoginToken();
		filters.add(new PropertyFilter("systemId", MatchType.EQ, loginToken.getSysLogin().getSystemId()));
		filters.add(new PropertyFilter("corpId", MatchType.EQ, loginToken.getSysLogin().getSysCorp().getCorpId()));
		Page pageTemp = sysRoleDAO.findByPage(page, filters);
		List resultList = pageTemp.getResult();
		if (resultList != null)
		{
			SysRole sysRole = null;
			SysCorp tempCorp = null;
			for (int i = 0; i < resultList.size(); i++)
			{
				sysRole = (SysRole) resultList.get(i);
				tempCorp = sysCorpDAO.findById(sysRole.getCorpId());
				if (tempCorp != null)
				{
					sysRole.setCorpName(tempCorp.getCorpName());
				}
			}
		}
		return pageTemp;
	}

	/**
	 * 验证角色是否被用户引用，被引用返回true，否则返回false
	 * 
	 * @param roleId
	 *            角色ID
	 * @return
	 * @throws Exception
	 */
	private boolean validationRole(Long[] roleId) throws Exception
	{
		return sysLoginRoleService.findLoginByRole(roleId);
	}

	/**
	 * 批量删除角色信息
	 * 
	 * @param roleId
	 *            　角色ID集合
	 * @return 删除成功或失败
	 * 
	 * 
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Throwable.class)
	public int delete(Long[] roleIds) throws Exception
	{
		boolean bool = validationRole(roleIds);
		// 判断是否有角色被用户引用
		if (bool)
		{
			for (Long roleId : roleIds)
			{
				// 删除角色信息
				sysRolePermissionDAO.deleteByRoleId(roleId);
				sysRoleDAO.deleteById(roleId);
				sysLogService.save(SysLog.OPERATE_TYPE_DELETE, "删除角色成功,roleId:" + roleId);
			}
			return ResultConstants.DELETE_SUCCEED;
		}
		return ResultConstants.DELETE_FAILED_IS_REF;
	}

	/**
	 * 角色信息修改
	 * 
	 * @param sysRole
	 *            　角色信息数据传输对象
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Throwable.class)
	public int update(SysRole sysRole) throws Exception
	{
		// 修改后的角色名是否跟原有的角色名存在重复
		SysRole sysRoleTemp = sysRoleDAO.findUnique("roleName", sysRole.getRoleName());

		if ((sysRoleTemp != null) && (sysRoleTemp.getRoleId().longValue() != sysRole.getRoleId().longValue()))
			return ResultConstants.UPDATE_FAILED_NAME_IS_EXIST;

		if (sysRoleTemp == null)
		{
			sysRoleTemp = sysRoleDAO.findById(sysRole.getRoleId());
		}

		// 先删除角色权限关联信息
		List<SysRolePermission> menuIdList = sysRoleTemp.getSysRolePermissions();
		List<SysRoleRoom> roomIdList = sysRoleTemp.getSysRoleRoom();
		sysRoleTemp.setSysRolePermissions(null);
		sysRoleTemp.setSysRoleRoom(null);
		if ((menuIdList != null) && (!menuIdList.isEmpty()))
		{
			sysRolePermissionDAO.deleteAll(menuIdList);
		}
		if ((roomIdList != null) && (!roomIdList.isEmpty()))
		{
			sysRoleRoomDAO.deleteAll(roomIdList);
		}

		// 保存角色权限关联信息
		this.saveRoleMenu(sysRole.getPermissionIds(), sysRoleTemp);
		//保存角色房间关联信息
		this.saveRoleRoom(sysRole.getRoomIds(), sysRoleTemp);

		// 更新角色信息
		sysRoleTemp.setRoleDesc(sysRole.getRoleDesc());
		sysRoleTemp.setRoleName(sysRole.getRoleName());
		sysRoleTemp.setStatus(sysRole.getStatus());
		sysRoleDAO.update(sysRoleTemp);
		sysLogService.save(SysLog.OPERATE_TYPE_UPDATE, "更新角色成功,roleId:" + sysRole.getRoleId());
		return ResultConstants.UPDATE_SUCCEED;
	}

	/**
	 * 保存角色权限关联信息
	 * 
	 * @param menuIds
	 *            　权限ID，以逗号分隔
	 * @param sysRole
	 *            角色信息
	 */
	private void saveRoleMenu(String menuIds, SysRole sysRole)
	{

		// 批量保存角色权限关联信息
		String[] menuIdArr = menuIds.split(CommonConstants.SPLIT_SYMBOL_COMMA);
		List<SysRolePermission> roleMenuList = new ArrayList<SysRolePermission>();
		for (String menuId : menuIdArr)
		{
			SysRolePermission rolePermission = new SysRolePermission();
			rolePermission.setSysRole(sysRole);
			rolePermission.setSysPermission(new SysPermission(Long.valueOf(menuId)));
			roleMenuList.add(rolePermission);
		}
		sysRolePermissionDAO.saveAll(roleMenuList);
	}
	
	private void saveRoleRoom(String roomIds, SysRole sysRole){
		String[] roomArr = roomIds.split(CommonConstants.SPLIT_SYMBOL_COMMA);
		List<SysRoleRoom> roleRoomList = new ArrayList<SysRoleRoom>();
		for(String roomId : roomArr){
			String[] roomIdAndFlag = roomId.split(CommonConstants.SPLIT_SYMBOL_COLON);
			SysRoleRoom srr = new SysRoleRoom();
			srr.setSysRole(sysRole);
			srr.setMapRoomInfo(new MapRoomInfo(Long.valueOf(roomIdAndFlag[0])));
			srr.setControllFlag(Integer.parseInt(roomIdAndFlag[1]));
			roleRoomList.add(srr);
		}
		sysRoleRoomDAO.saveAll(roleRoomList);
	}

	/**
	 * 根据单位ID查询角色信息
	 * 
	 * @param corpId
	 *            　单位ID
	 * @param status
	 *            　角色有效标志位
	 * 
	 * 
	 * @return　角色信息集合
	 * @throws Exception
	 */
	@Override
	public List<SysRole> findByCorpId(Long corpId) throws Exception
	{
		List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();
		filterList.add(new PropertyFilter("corpId", MatchType.EQ, corpId));
		filterList.add(new PropertyFilter("status", MatchType.EQ, CommonConstants.STATUS_VALID));
		return sysRoleDAO.findByFilters(filterList);
	}

	@Autowired
	@Override
	@Qualifier(value = "sysRoleDAO")
	public void setBaseDao(BaseHibernateDAO<SysRole, Long> baseDao)
	{
		this.baseDao = baseDao;

	}

}
