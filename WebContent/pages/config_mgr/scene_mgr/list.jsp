<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/common/header.jsp"%>
	<%@ include file="/plugins/tablesorter.jsp"%>
	<%@ include file="/plugins/calendar.jsp"%>
	<%@ include file="/plugins/jquery-powerFloat.jsp"%>
	<%@ include file="/plugins/ztree.jsp"%>
</head>
<body>
	<div class="mianbaoxie">
		当前位置：<a href="#1">配置管理</a> &gt; 场景配置 &gt;<a id="roomChoose" href="#" onclick="javascript:$('#roomChooseDiv').toggle()"> ${roomInfo.roomName}</a><font color="red">  &lt;&lt;点击此处切换房间</font>
	</div>
	<div id="roomChooseDiv" style="display: none;" class="shadow target_box">
     	<ul id="treeDemo" class="ztree"></ul>
    </div>
	<div class="table_list " style="margin-top: 20px;">
		<input type="button" class="form_botton" value="新  增" onclick="javascript:window.location='${ctx}/sceneMgr/add/${roomInfo.roomId}'"/>
	</div>
	<div class="table_list table_div" >
		<table id="tableList" cellspacing="1" class="table_border" width="100%">
			<tr align="center">
				<td class="table_list_title title_border_a" width="4%">
					序号
				</td>
				<td class="table_list_title title_border_a"  width="20%">
					场景名称
				</td>
				<td class="table_list_title title_border_a"  width="20%">
					触发方式
				</td>
				<td class="table_list_title title_border_a" width="35%">
					场景描述
				</td>
				<td class="table_list_title" width="15%">操作</td>
			</tr>
			<c:if test="${fn:length(resultList) != '0'}">
				<c:forEach var="item" items="${resultList}" varStatus="status">
					<tr align="center">
						<td>
							${status.count}
						</td>
						<td>
							<c:out value="${item.sceneName}" />
						</td>
						<td>
							<c:if test="${item.sceneType==1 }">手动触发</c:if>
							<c:if test="${item.sceneType==2 }">自动触发</c:if>
						</td>
						<td>
							<c:out value="${item.sceneDesc}" />
						</td>
						<td>
							<a href="${ctx}/sceneMgr/edit/${item.sceneId}">编辑</a>&nbsp;&nbsp;
							<a href="${ctx}/sceneMgr/delete/${item.sceneId}" onclick="if(!confirm('你确认要删除该场景？'))return false;">删除</a>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${fn:length(resultList) == '0'}">						
				<tr align="center">
					<td colspan="4" align="left">
						暂无符合条件的记录
					</td>
				</tr>
			</c:if>
		</table>
	</div>
	<script language="JavaScript" type="text/JavaScript">

	$(document).ready(function() {
		//树形菜单
		var setting = {
			data: {
				simpleData: {enable: true}
			}
		};
		var zNodes = ${tree.json};  
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
	});


	$("#roomChoose").powerFloat({
		width: 130,
		target: $("#roomChooseDiv"),
		eventType: "click"
	});

	</script>
</body>
</html>
