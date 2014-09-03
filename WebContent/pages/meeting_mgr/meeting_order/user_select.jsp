<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/header.jsp"%>
<%@ include file="/plugins/ztree.jsp"%>
</head>
<body>
	<div class="tab_search" style="margin-top: 10px">
		<div class="table_div">
			<table width="100%" class="table_border">
				<tr>
					<td  valign="top" width="250px">
						<b>组织架构</b>
						<div>
							<ul id="deptTree" class="ztree"  style="height: 400px;width: 270px;overflow: auto;"></ul>
						</div>
					</td>
					<td valign="top">
						<b>人员列表</b><br/>
						<div>
							<ul id="userTree" class="ztree"  style="border:dotted 1px #c4d8ef;background:#f6fafe;height: 180px;width: 99%;overflow: auto;"></ul>
						</div>
						<div style="margin-top: 5px;text-align: center">
							<img src="${ctx}/images/down_plus.png" height="16px" width="16px" onclick="addAllUser()" style="cursor: pointer;"/>&nbsp;&nbsp;&nbsp;&nbsp;
							<img src="${ctx}/images/down.png" height="16px" width="16px" onclick="addUser()" style="cursor: pointer;"/>&nbsp;&nbsp;&nbsp;&nbsp;
							<img src="${ctx}/images/up_plus.png" height="16px" width="16px" onclick="removeAllUser()" style="cursor: pointer;"/>&nbsp;&nbsp;&nbsp;&nbsp;
							<img src="${ctx}/images/up.png" height="16px" width="16px" onclick="removeUser()" style="cursor: pointer;"/>
						</div>
						<b>选中人员列表</b><br/>
						<div>
							<ul id="selectedUserTree" class="ztree"  style="border:dotted 1px #c4d8ef;background:#f6fafe;height: 180px;width: 99%;overflow: auto;"></ul>
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<c:if test="${param['readonly']!='1' }">
							<input type="button" value="确定" class="form_botton" onclick="userSelect()" />&nbsp;&nbsp;&nbsp;&nbsp; <input id="closeBtn" type="button" value="关闭" class="form_botton" onclick="parent.closeModalWindow(false);"/>
						</c:if>
					</td>
				</tr>
			</table>
		</div>
	</div>
<script>
var setting = {
	check: {
		enable: false
	},
	data: {
		simpleData: {
			enable: true
		}
	},
	callback: {
		onClick: deptNodeClick
	}
};
var userSetting = {
	check: {
		enable: false
	},
	data: {
		simpleData: {
			enable: true
		}
	}
};
var selectUserSetting = {
	check: {
		enable: false
	},
	data: {
		simpleData: {
			enable: true
		}
	}
};
var zNodes = ${tree.json};  
var selectedUserNodes = ${selectedUserTree.json};  

$(document).ready(function(){
	$.fn.zTree.init($("#deptTree"), setting, zNodes);
	$.fn.zTree.init($("#userTree"), userSetting, null);
	$.fn.zTree.init($("#selectedUserTree"), selectUserSetting, selectedUserNodes);
});

//部门树点击事件，加载用户信息
function deptNodeClick(event, treeId, treeNode) {
	var zTree = $.fn.zTree.init($("#userTree"), userSetting, null);
	$.getJSON("${ctx}/meetingMgr/getUserListByDeptId/"+treeNode.id+"?r="+Math.random(), function(data){
		$.each(data, function(i,user){
			zTree.addNodes(null, {id:user.id, pId:0, isParent:false, name:""+user.name,icon:""+user.icon});
		});
	});
}
//添加选中用户
function addUser()
{
	var userTree = $.fn.zTree.getZTreeObj("userTree");
	var selectedUserTree = $.fn.zTree.getZTreeObj("selectedUserTree");
	var nodes = userTree.getSelectedNodes();
	for(var i=0;i<nodes.length;i++)
	{
		if(selectedUserTree.getNodeByParam("id",nodes[i].id,null)==null)
			selectedUserTree.addNodes(null, {id:nodes[i].id, pId:0, isParent:false, name:""+nodes[i].name,icon:""+nodes[i].icon});
	}
}
//添加所有用户
function addAllUser()
{
	var userTree = $.fn.zTree.getZTreeObj("userTree");
	var selectedUserTree = $.fn.zTree.getZTreeObj("selectedUserTree");
	var nodes = userTree.getNodes();
	for(var i=0;i<nodes.length;i++)
	{
		if(selectedUserTree.getNodeByParam("id",nodes[i].id,null)==null)
			selectedUserTree.addNodes(null, {id:nodes[i].id, pId:0, isParent:false, name:""+nodes[i].name,icon:""+nodes[i].icon});
	}
}
//移除所选用户
function removeUser()
{
	var selectedUserTree = $.fn.zTree.getZTreeObj("selectedUserTree");
	var nodes = selectedUserTree.getSelectedNodes();
	for(var i=0;i<nodes.length;i++)
	{
		selectedUserTree.removeNode(nodes[i]);
	}
}
//移除所有用户
function removeAllUser()
{
	$.fn.zTree.init($("#selectedUserTree"), selectUserSetting, null);
}

function userSelect()
{
	var selectedIds = "";
	var selectedNames="";
	var selectedUserTree = $.fn.zTree.getZTreeObj("selectedUserTree");
	var nodes = selectedUserTree.getNodes();
	for(var i=0;i<nodes.length;i++)
	{
		if(i==0)
		{
			selectedIds +=nodes[i].id;
			selectedNames +=nodes[i].name;
		}
		else
		{
			selectedIds +=","+nodes[i].id;
			selectedNames +=","+nodes[i].name;
		}
	}
	window.parent.document.getElementById("${param['userIdObj']}").value=selectedIds;
	window.parent.document.getElementById("${param['userNameObj']}").innerHTML=selectedNames;
	parent.closeModalWindow(false);
}
	
</script>
</body>

</html>
