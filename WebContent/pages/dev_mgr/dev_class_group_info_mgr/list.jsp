<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/header.jsp"%>
		<%@ include file="/plugins/tablesorter.jsp"%>
	</head>
	<body>
		<div class="mianbaoxie">
			当前位置：<a href="#1">设备管理</a> > 设备类型分组管理
		</div>
		<form id="mainForm" action="${ctx}/devClassGroupInfoMgr" method="post">
			<input type="hidden" name="pageNo" id="pageNo" value="${page.pageNo}" />
			<input type="hidden" name="pageSize" id="pageSize" value="${page.pageSize}" />
			<input type="hidden" name="orderBy" id="orderBy" value="${page.orderBy}" />
			<input type="hidden" name="order" id="order" value="${page.order}" />
			<div class="tab_search" style="margin-top: 20px;">
			<div class="tab_top">
				<div class="tab_text">
					<div class="tab_text_left"></div>
					<div class="tab_text_con">按条件查询</div>
					<div class="tab_text_right" id="dateBtn"></div>
				</div>
			</div>
			<div class="table_div">
				<table width="100%" class="table_border">
					<tr>
						<td class="title" width="100">分组名称：</td>
						<td><input type="text" name="filter_LIKE_groupName" id="filter_LIKE_groupName" value="${param['filter_LIKE_groupName']}" size="20" class="form_input"/>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center"><input name="" type="button" value="查询" class="form_botton" onclick="query()"/>&nbsp;&nbsp;&nbsp;&nbsp;<input
							name="" type="button" value="重置" class="form_botton" onclick="clearForm()"/>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="table_list " style="margin-top: 20px;">
			<input type="button" class="form_botton" value="新  增" onclick="javascript:window.location='${ctx}/devClassGroupInfoMgr/add'"/>
			&nbsp;&nbsp;&nbsp;
			<input type="button" class="form_botton" value="删除所选" onclick="delChecked()" />
		</div>
		<div class="table_list table_div" >
			<table id="tableList" cellspacing="1" class="table_border" width="100%">
					<tr align="center">
						<td class="table_list_title title_border_a" width="4%"><input class="checkbox" type="checkbox" name="operid"
								onclick="javascript:selectAll('id',this.checked)"></td>
						<td class="table_list_title title_border_a" onclick="javascript:sort('groupName','asc')" width="40%"><div class="paixu_3">分组名称</div></td>
						<td class="table_list_title" width="20%">操作描述</td>
					</tr>
					<c:if test="${page.totalCount != '0'}">
						<c:forEach var="groupInfo" items="${page.result}" varStatus="status">
							<tr align="center">
								<td>
									<input class="checkbox" type="checkbox" name="id" value="${groupInfo.groupId}" />
								</td>
								<td>
									<c:out value="${groupInfo.groupName}" />
								</td>
								<td>
									<a href="${ctx}/devClassGroupInfoMgr/edit/${groupInfo.groupId}">编辑</a>
									<a href="${ctx}/devClassGroupInfoMgr/delete/${groupInfo.groupId}" onclick="if(!confirm('你确认要删除该设备分组信息？'))return false;">删除</a>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${page.totalCount == '0'}">						
						<tr align="center">
							<td colspan="4" align="left">
								暂无符合条件的记录
							</td>
						</tr>
					</c:if>
			</table>
		</div>
		</form>
		<div>
			<jsp:include page="/plugins/pager.jsp" flush="true" />
		</div>
	</body>
	<script type="text/javascript">
	function query(){
		$("#mainForm").submit();
	}

	function clearForm(){
		$("#filter_EQ_groupId").val("");
		$("#filter_LIKE_groupName").val("");
	}

	function delChecked(){
		if(getCheckboxCheckedValue("id") != "")
		{
			if(confirm('你确认要删除该设备分组信息？'))
			{
				$("#mainForm").attr("action","${ctx}/devClassGroupInfoMgr/batchDelete");
				$("#mainForm").submit();
			}
		}
		else
		{
			alert("请选择需要删除的设备分组信息!");
		}
	}
	</script>
</html>