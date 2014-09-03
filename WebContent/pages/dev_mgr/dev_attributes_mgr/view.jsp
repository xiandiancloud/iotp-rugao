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
			当前位置：<a href="#1">设备管理</a> > <a href="${ctx}/devClassInfoMgr">设备类型管理</a>> 属性查看
		</div>
		<form id="mainForm" action="${ctx}/devClassGroupInfoMgr" method="post">
			<div class="table_list " style="margin-top: 20px;">
				<input type="button" class="form_botton" value="新  增" onclick="javascript:window.location='${ctx}/devAttributesMgr/add/${resultObject.devClassId}'"/>
				&nbsp;&nbsp;&nbsp;
				<input type="button" class="form_botton" value="删除所选" onclick="delChecked()" />
				&nbsp;&nbsp;&nbsp;
				<input type="button" class="form_botton" value="返回" onclick="javascript:window.location='${ctx}/devClassInfoMgr'"/>
				&nbsp;&nbsp;&nbsp;
			</div>
			<div class="table_list table_div" >
				<table id="tableList" cellspacing="1" class="table_border" width="100%">
						<tr align="center">
							<td class="table_list_title title_border_a" width="4%"><input class="checkbox" type="checkbox" name="operid"
									onclick="javascript:selectAll('id',this.checked)"></td>
							<td class="table_list_title title_border_a" width="20%">属性编码</td>
							<td class="table_list_title title_border_a" width="20%">属性名称</td>
							<td class="table_list_title title_border_a" width="10%">属性单位</td>
							<td class="table_list_title title_border_a" width="30%">属性描述</td>
							<td class="table_list_title" width="10%">操作描述</td>
						</tr>
						<c:if test="${attrListSize != '0'}">
							<c:forEach var="attr" items="${attrList}" varStatus="status">
								<tr align="center">
									<td>
										<input class="checkbox" type="checkbox" name="id" value="${attr.attrKey}" />
									</td>
									<td>
										<c:out value="${attr.attrKey}" />
									</td>
									<td>
										<c:out value="${attr.attrName}" />
									</td>
									<td>
										<c:out value="${attr.attrMeasurement}" />
									</td>
									<td>
										<c:out value="${attr.attrDesc}" />
									</td>
									<td>
										<a href="${ctx}/devAttributesMgr/edit/${attr.attrKey}">编辑</a>
										<a href="${ctx}/devAttributesMgr/delete/${attr.attrKey}" onclick="if(!confirm('确认要删除该设备属性？'))return false;">删除</a>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${attrListSize == '0'}">						
							<tr align="center">
								<td colspan="6" align="left">
									暂无符合条件的记录
								</td>
							</tr>
						</c:if>
				</table>
			</div>
		</form>
	</body>
	<script type="text/javascript">
	function delChecked(){
		if(getCheckboxCheckedValue("id") != "")
		{
			if(confirm('你确认要删除所选的设备属性？'))
			{
				$("#mainForm").attr("action","${ctx}/devAttributesMgr/batchDelete");
				$("#mainForm").submit();
			}
		}
		else
		{
			alert("请选择需要删除的设备属性!");
		}
	}
	</script>
</html>