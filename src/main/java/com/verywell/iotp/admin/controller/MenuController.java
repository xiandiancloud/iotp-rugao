/**
 * 
 */
package com.verywell.iotp.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.verywell.framework.commons.Tree;
import com.verywell.framework.commons.TreeNode;
import com.verywell.framework.controller.BaseController;
import com.verywell.iotp.admin.commons.LoginToken;
import com.verywell.iotp.admin.entity.map.MapRoomInfo;
import com.verywell.iotp.admin.entity.sys.SysPermission;
import com.verywell.iotp.admin.service.MapBuildingService;
import com.verywell.iotp.admin.service.MapRoomService;
import com.verywell.iotp.admin.service.SysPermissionService;

/**
 * 
 * @title:登录操作控制器
 * @description:
 * 
 * @author: Yao
 * 
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController
{

	@Autowired
	private MapRoomService roomService;

	@Autowired
	private MapBuildingService buildingService;

	@Autowired
	private SysPermissionService sysPermissionService;

	@RequestMapping
	public String index(HttpServletRequest request, Model model) throws Exception
	{
		LoginToken loginToken = this.getLoginToken();
		// 当前用户拥有菜单权限
		Map<Long, SysPermission> menuPermissions = loginToken.getMenuPermissions();
//		Map<Long, MapRoomInfo> roomPermissions = loginToken.getRoomPermissions();

		// 房间树形菜单
		Tree tree = this.getLoginToken().getRoomTree(request);
		for (TreeNode treeNode : tree)
		{
			if (!treeNode.getpId().equals(Tree.DEFAULT_ROOT_ID))
			{
				treeNode.setUrl(request.getContextPath() + "/map/roomDetail/" + treeNode.getId());
				treeNode.setTarget("mainFrame");
			}
			else
			{
				treeNode.setUrl(request.getContextPath() + "/map");
				treeNode.setTarget("mainFrame");
			}
		}
		model.addAttribute("mapTree", tree);

		// 取得数据库中所有一级菜单列表
		List<SysPermission> allLevel1MenuList = sysPermissionService.findLevel1Menu(1);

		List<SysPermission> level1MenuList = new ArrayList<SysPermission>();
		Map<Long, List<SysPermission>> level2MenuMap = new HashMap<Long, List<SysPermission>>();

		if (allLevel1MenuList != null && allLevel1MenuList.size() > 0)
		{
			for (SysPermission level1Menu : allLevel1MenuList)
			{
				Long level1MenuId = level1Menu.getPermissionId();

				List<SysPermission> allLevel2Menus = sysPermissionService.findMenuByparentIdAndType(level1MenuId,
						SysPermission.PERMISSION_TYPE_1);
				List<SysPermission> level2Menus = new ArrayList<SysPermission>();
				if (allLevel2Menus != null && allLevel2Menus.size() > 0)
				{
					for (SysPermission level2menu : allLevel2Menus)
					{
						Long level2MenuId = level2menu.getPermissionId();
						if (menuPermissions.containsKey(level2MenuId))
						{
							level2Menus.add(level2menu);
						}
					}
					if (level2Menus.size() > 0)
					{
						level1MenuList.add(level1Menu);
						level2MenuMap.put(level1MenuId, level2Menus);
					}
				}
			}
		}
		model.addAttribute("level1MenuList", level1MenuList);
		model.addAttribute("level2MenuMap", level2MenuMap);
		return "menu";
	}
}
