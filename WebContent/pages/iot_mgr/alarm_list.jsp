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
		<%@ include file="/plugins/ymPrompt.jsp"%>
		<script language="javascript" src="${ctx}/plugins/jquery/jquery-1.3.2.min.js"></script>
	</head>
<body>
	<form id="mainForm" action="${ctx}/devAlarmLogMgr" method="post">
		<input type="hidden" name="pageNo" id="pageNo" value="${page.pageNo}" />
		<input type="hidden" name="pageSize" id="pageSize" value="${page.pageSize}" />
		<input type="hidden" name="orderBy" id="orderBy" value="${page.orderBy}" />
		<input type="hidden" name="order" id="order" value="${page.order}" />
		<div class="table_div">
			<table id="tableList" cellspacing="1" class="table_border" width="100%">
						<tr align="center">
							<td class="table_list_title title_border_a" onclick="javascript:sort('alarmTime','asc')" width="10%"><div class="paixu_3">告警时间<div></td>
							<td class="table_list_title title_border_a" onclick="javascript:sort('status','asc')" width="10%"><div class="paixu_3">状态<div></td>
							<td class="table_list_title title_border_a" onclick="javascript:sort('alarmType','asc')" width="10%"><div class="paixu_3">告警类型<div></td>
							<td class="table_list_title title_border_a" onclick="javascript:sort('groupId','asc')" width="15%"><div class="paixu_3">设备名称<div></td>
							<td class="table_list_title title_border_a" onclick="javascript:sort('groupId','asc')" width="10%"><div class="paixu_3">所属房间<div></td>
							<td class="table_list_title title_border_a" onclick="javascript:sort('groupId','asc')" width="10%"><div class="paixu_3">所属大楼<div></td>
							<td class="table_list_title title_border_a" width="15%">告警描述</td>
							<td class="table_list_title title_border_a" onclick="javascript:sort('disposeTime','asc')" width="10%"><div class="paixu_3">处理时间<div></td>
							<td class="table_list_title title_border_a" onclick="javascript:sort('receiverName','asc')" width="10%"><div class="paixu_3">处理人<div></td>
						</tr>
						<c:if test="${page.totalCount != '0'}">
						<c:forEach var="alarmLog" items="${page.result}" varStatus="status">
							<tr align="center">
								<td>
									<fmt:parseDate pattern="yyyyMMddHHmmss" var="parsedDateTime" parseLocale="en_US">
										${alarmLog.alarmTime}
									</fmt:parseDate>
									<fmt:formatDate value='${parsedDateTime}' pattern="yyyy-MM-dd HH:mm:ss"/>
								</td>
								<td>
									<c:choose>
										<c:when test="${alarmLog.status=='0'}"><font color="red">未处理</font></c:when>
										<c:when test="${alarmLog.status=='1'}">已处理</c:when>
									</c:choose>
								</td>
								<td>
									<c:choose>
										<c:when test="${alarmLog.alarmType=='1'}">红外告警</c:when>
										<c:when test="${alarmLog.alarmType=='2'}">烟雾或可燃性气体告警</c:when>
										<c:when test="${alarmLog.alarmType=='3'}">电能告警</c:when>
										<c:when test="${alarmLog.alarmType=='4'}">温度告警</c:when>
										<c:when test="${alarmLog.alarmType=='5'}">湿度告警</c:when>
									</c:choose>
								</td>
								<td>
									<c:out value="${alarmLog.devName}" />
								</td>
								<td>
									<c:out value="${alarmLog.roomName}" />
								</td>
								<td>
									<c:out value="${alarmLog.buildingName}" />
								</td>
								<td>
									<c:out value="${alarmLog.alarmDesc}" />
								</td>
								<td>
									<fmt:parseDate pattern="yyyyMMddHHmmss" var="parsedDateTime2" parseLocale="en_US">
										${alarmLog.disposeTime}
									</fmt:parseDate>
									<fmt:formatDate value='${parsedDateTime2}' pattern="yyyy-MM-dd HH:mm:ss"/>
								</td>
								<td>
									<c:out value="${alarmLog.receiverName}" />
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${page.totalCount == '0'}">						
						<tr align="center">
							<td colspan="9" align="left">
								暂无符合条件的记录
							</td>
						</tr>
					</c:if>
				</table>
	</div>
	<%@ include file="/plugins/pager.jsp"%>
	</form>

</body>
</html>
