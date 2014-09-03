/**
 * 
 */
package com.verywell.iotp.admin.commons;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.verywell.framework.commons.Tree;
import com.verywell.framework.commons.TreeNode;
import com.verywell.iotp.admin.entity.map.MapBuildingInfo;
import com.verywell.iotp.admin.entity.map.MapRoomInfo;
import com.verywell.iotp.admin.entity.sys.SysCorp;
import com.verywell.iotp.admin.entity.sys.SysLogin;
import com.verywell.iotp.admin.entity.sys.SysPermission;

/**
 * 
 * @title: 系统登录令牌类
 * 
 * @description: 用户登录后，该用户的所有信息将通过该对象保存至用户会话中
 * 
 * 
 * @author: Yao
 * 
 */
@Scope("prototype")
@Component
public class LoginToken
{
	// 登录信息
	private SysLogin sysLogin;
	// 单位信息
	private SysCorp sysCorp;

	// 菜单权限
	private Map<Long, SysPermission> menuPermissions;

	private Map<Long, MapRoomInfo> roomPermissions;

	public SysLogin getSysLogin()
	{
		return sysLogin;
	}

	public void setSysLogin(SysLogin sysLogin)
	{
		this.sysLogin = sysLogin;
	}

	public Map<Long, SysPermission> getMenuPermissions()
	{
		return menuPermissions;
	}

	public void setMenuPermissions(Map<Long, SysPermission> menuPermissions)
	{
		this.menuPermissions = menuPermissions;
	}

	public SysCorp getSysCorp()
	{
		return sysCorp;
	}

	public void setSysCorp(SysCorp sysCorp)
	{
		this.sysCorp = sysCorp;
	}

	public Map<Long, MapRoomInfo> getRoomPermissions()
	{
		return roomPermissions;
	}

	public void setRoomPermissions(Map<Long, MapRoomInfo> roomPermissions)
	{
		this.roomPermissions = roomPermissions;
	}

	/**
	 * 根据用户房间权限获得树形结构
	 * 
	 * @param request
	 * @return
	 */
	public Tree getRoomTree(HttpServletRequest request)
	{
		Tree tree = new Tree();
		Map<Long, MapRoomInfo> roomMap = this.getRoomPermissions();
		// 用于保存处理过的上级接点ID
		List<Long> parentNodeIdList = new ArrayList<Long>();
		String context = request.getContextPath();

		for (Long key : roomMap.keySet())
		{
			MapRoomInfo room = roomMap.get(key);
			TreeNode roomNode = new TreeNode();
			// 获得该房间的上级建筑物信息
			MapBuildingInfo buildingInfo = room.getMapBuildingInfo();
			Long buildinId = buildingInfo.getBuildingId();
			roomNode.setId(room.getRoomId().toString());
			roomNode.setpId("b_" + buildinId);
			roomNode.setName(room.getRoomName());
			if (room.getRoomType().equals(MapRoomInfo.ROOM_TYPE_METTING))
				roomNode.setIcon(context + "/plugins/zTree/css/zTreeStyle/img/diy/7.png");
			else if (room.getRoomType().equals(MapRoomInfo.ROOM_TYPE_CLASSROOM))
				roomNode.setIcon(context + "/plugins/zTree/css/zTreeStyle/img/diy/4.png");
			else
				roomNode.setIcon(context + "/plugins/zTree/css/zTreeStyle/img/diy/8.png");

			tree.addNode(roomNode);
			// 如果没有处理过这个建筑物信息，则将该节点加入进树中
			if (!parentNodeIdList.contains(buildinId))
			{
				TreeNode buildingNode = new TreeNode();
				parentNodeIdList.add(buildingInfo.getBuildingId());
				buildingNode.setId("b_" + buildinId);
				buildingNode.setName(buildingInfo.getBuildingName());
				buildingNode.setIcon(context + "/plugins/zTree/css/zTreeStyle/img/diy/1_open.png");
				buildingNode.setNocheck(true);
				tree.addNode(buildingNode);
			}
		}
		return tree;
	}

}
