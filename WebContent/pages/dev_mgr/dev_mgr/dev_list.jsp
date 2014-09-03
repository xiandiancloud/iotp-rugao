<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/header.jsp"%>
</head>
<body>
<div class="table_div">
<table width="100%" class="table_border">
	<tr>
		<td class="table_list_title title_border_a">序号</td>
		<td class="table_list_title title_border_a">设备名称</td>
		<td class="table_list_title title_border_a">设备类型</td>
		<td class="table_list_title title_border_a">设备网络地址</td>
		<td class="table_list_title title_border_a">设备物理地址</td>
		<td class="table_list_title title_border_a">操作</td>
	</tr>
	<c:if test="${page.totalCount != '0'}">
		<c:forEach var="item" items="${resultList}" varStatus="status">
			<tr style="text-align: center">
				<td>${status.count}</td>
				<td>${item.devName}</td>
				<td>${item.devClassInfo.className}</td>
				<td>${item.networkAddr}</td>
				<td>${item.macAddr}</td>
				<td>
					<a href="${ctx }/devMgr/edit/${item.devId }" class="nyroModal" target="_blank" title="设备修改">设备修改</a>
					<a href="javascript:deleteDev('${item.devId}')">设备删除</a>
				</td>
			</tr>
		</c:forEach>
	</c:if>
	<c:if test="${empty resultList}">
		<tr align="center">
			<td colspan="7" align="left">
				暂无符合条件的记录
			</td>
		</tr>
	</c:if>
</table>
</div>
<script>
	function deleteDev(devId)
	{
		$.getJSON("${ctx}/devMgr/delete/"+devId+"?r="+Math.random(), function(data){
			if(data.messageType=='1')
		    {
		    	alert(data.promptInfo);
		    	closeModalWindow();
		    }
		    else
		    {
		    	alert(data.promptInfo);
		    }
		});
	}
</script>
</body>
</html>
