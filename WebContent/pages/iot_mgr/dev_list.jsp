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
		<td class="table_list_title title_border_a">设备状态</td>
		<td class="table_list_title title_border_a">最新数据</td>
		<td class="table_list_title title_border_a">更新时间</td>
		<td class="table_list_title title_border_a">告警状态</td>
		<td class="table_list_title title_border_a">操作</td>
	</tr>
	<c:if test="${not empty resultList}">
		<c:forEach var="item" items="${resultList}" varStatus="status">
			<tr style="text-align: center">
				<td>${status.count}</td>
				<td>${item.devName}</td>
				<td>${item.devClassInfo.className}</td>
				<td>
					<c:if test="${item.devStatus!='2'}">
						${item.devStatusDesc}
					</c:if>
					<c:if test="${item.devStatus=='2'}">
						<font color='red'>${item.devStatusDesc}</font>
					</c:if>
				</td>
				<td>${item.lastestData}</td>
				<td>
					<fmt:parseDate pattern="HHmm" var="parsedDateTime" parseLocale="en_US">
					<c:out value="${item.lastModifyTime}" />
					</fmt:parseDate>
					<fmt:formatDate value='${parsedDateTime}' pattern="HH:mm"/>
				</td>
				<td>
					<c:if test="${item.alarmStatus=='1'}">
						<font color='red'>${item.alarmStatusDesc}</font>
					</c:if>
					<c:if test="${item.alarmStatus=='0'}">
						<font color='green'>${item.alarmStatusDesc}</font>
					</c:if>
				</td>
				<td>
					<c:if test="${item.devStatus=='0'}">
						<button class="page_anniu" onclick="switchDev('${item.devId}','1',event)">设备开启</button>
					</c:if>
					<c:if test="${item.devStatus=='1'}">
						<button class="page_anniu" onclick="switchDev('${item.devId}','0',event)">设备关闭</button>
					</c:if>
					<c:if test="${item.devClassInfo.devClassGroupInfo.groupId=='2' && item.devStatus!='2'}">
						<button onclick="controllDev('${item.devId}','${item.devClassInfo.devClassId}',event)" class="page_anniu">设备控制</button>
					</c:if>
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
</body>
<script>

</script>
</html>
