<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<%@ include file="/common/header.jsp"%>
<%@ include file="/plugins/jquery-ui.jsp"%>
<%@ include file="/plugins/ztree.jsp"%>
<LINK href="${ctx}/css/left_menu.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div style="height: 100%; overflow: auto;">
<!-- 		<div style="padding: 10px 0; background-color: #34495e">
			<div class="head-img" style="width:50px;height:50px;background-color:#ccc;border-radius:50px;margin-left: 10px; float: left;">
				<img src="images/head.jpg" width="50" height="50" style="border-radius:50px;" />
			</div>
			<h4 style="float: left; height: 50px; line-height: 50px; margin:0 0 0 10px;padding:0; color: #fff;font-size: 14px;">   admin </h4>
			<div style="clear: both;"></div>
		</div> -->
		<div id="menu_0" class="menu_over">
			<img src="${ctx}/images/menu_school.png"><a href="#1" class="menu_ico1">如皋中等专业学校</a>
		</div>
		<div id="submenu_0" class="menu_con">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
		<c:forEach var="level1Menu" items="${level1MenuList}">
			<div id="menu_${level1Menu.permissionId}" class="menu_out">
				<img src="${ctx}/images/${level1Menu.permissionIco}"><a href="#1" class="menu_ico2">${level1Menu.permissionName}</a>
			</div>
			<div id="submenu_${level1Menu.permissionId}" class="menu_con" style="display: none">
				<c:forEach var="level2Menu" items="${level2MenuMap[level1Menu.permissionId]}">
				<a href="${ctx}/${level2Menu.permissionUrl}" target="mainFrame">${level2Menu.permissionName}</a>
				</c:forEach>
			</div>
		</c:forEach>
	</div>
	<script>
	var setting = {
			data: {
				simpleData: {
					enable: true
				}
			}
		};

	var zNodes =${mapTree.json};

	var openMenuId = "menu_0";
	$(document).ready(function(){
		//根据屏幕高度，重新设置每个子菜单的高度
		$("div[id^='submenu']").height($(document).height()-$("#menu_0").height()*'${fn:length(level1MenuList)+1}');
		
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		$("div[id^='menu']").click(function(){
				 var menuId= $(this).attr("id");
				 $("#sub"+menuId).slideToggle();
				 if(openMenuId!=menuId)
					 $('#'+openMenuId).click();
				 if($(this).hasClass("menu_out"))
				 {
					 $(this).attr("class","menu_over");
					 openMenuId = menuId;
				 }
				 else
				 {
					 $(this).attr("class","menu_out");
					 openMenuId="";
				 }
		});	
	});
	</script>
</body>
</html>
