package com.verywell.iotp.admin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.verywell.framework.dao.BaseHibernateDAO;
import com.verywell.framework.service.BaseCrudServiceImpl;
import com.verywell.iotp.admin.dao.MapRoomInfoDAO;
import com.verywell.iotp.admin.entity.map.MapRoomInfo;
import com.verywell.iotp.admin.entity.sys.SysLogin;
import com.verywell.iotp.admin.entity.sys.SysRoleRoom;
import com.verywell.iotp.admin.service.SysRoleRoomService;

@Service
public class SysRoleRoomServiceImpl extends BaseCrudServiceImpl<SysRoleRoom, Long> implements SysRoleRoomService {

	@Autowired
	private MapRoomInfoDAO mapRoomInfoDAO;
	
	@Autowired
	@Override
	@Qualifier(value = "sysRoleRoomDAO")
	public void setBaseDao(BaseHibernateDAO<SysRoleRoom, Long> baseDao)
	{
		this.baseDao = baseDao;

	}
	
	@Override
	public List<MapRoomInfo> findRoomByRoleId(Long roleId) throws Exception {
		String hql ="from SysRoleRoom as model where model.sysRole.roleId = ?";
		List<SysRoleRoom> list = baseDao.findByHql(hql, roleId);
		List<MapRoomInfo> listRoom = new ArrayList<MapRoomInfo>();
		if(list.isEmpty()){
			return new ArrayList<MapRoomInfo>();
		}else{
			for(SysRoleRoom srr:list){
				if(!listRoom.contains(srr.getMapRoomInfo()))
					listRoom.add(srr.getMapRoomInfo());
			}
		}
		return listRoom;
	}

	@Override
	public List<MapRoomInfo> findByRoleIds(Long[] roleIds) throws Exception {
		String hql ="from SysRoleRoom as model where model.sysRole.roleId in (:roleIds)";
		Map m = new HashMap();
		m.put("roleIds", roleIds);
		List<SysRoleRoom> list = baseDao.findByHql(hql, m);
		Map<Long,MapRoomInfo> map = new HashMap<Long, MapRoomInfo>();
		if(list.isEmpty()){
			return new ArrayList<MapRoomInfo>();
		}else{
			for(SysRoleRoom srr:list){
				MapRoomInfo room = srr.getMapRoomInfo();
				if(!map.containsKey(room.getRoomId())){
					map.put(room.getRoomId(), room);
				}
			}
		}
		Iterator<Long> iterator = map.keySet().iterator();
		List<MapRoomInfo> listRoom = new ArrayList<MapRoomInfo>();
		while(iterator.hasNext()){
			listRoom.add(map.get(iterator.next()));
		}
		return listRoom;
	}

	@Override
	public String findRoomIdsByRoleIds(Long roleId) throws Exception {
		String hql ="from SysRoleRoom as model where model.sysRole.roleId = ?";
		List<SysRoleRoom> list = baseDao.findByHql(hql, roleId);
		String returnStr = "";
		if(!list.isEmpty()){
			for(int i =0;i<list.size();i++){
				SysRoleRoom srr = list.get(i);
				if(i == (list.size()-1)){
					returnStr+=(srr.getMapRoomInfo().getRoomId()+":"+srr.getControllFlag());
				}else{
					returnStr+=(srr.getMapRoomInfo().getRoomId()+":"+srr.getControllFlag()+",");
				}
			}
		}
		return returnStr;
	}

	@Override
	public List<MapRoomInfo> findRoomByLogin(SysLogin login) throws Exception {
		String hql = "from ";
		if(login.getUserType() == 0){//超级管理员
			
		}
		return null;
	}
}
