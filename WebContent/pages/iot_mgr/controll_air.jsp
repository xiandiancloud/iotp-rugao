<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/header.jsp"%>
</head>
<body>
<div>
<c:if test="${not empty resultList}">
	<div style="border-bottom: solid 1px #cee0f4;padding-bottom: 2px">
		<button class="page_anniu" onclick="controllDev('${devIds}',event)">全部控制</button>
		<button class="page_anniu" onclick="switchDev('${devIds}','0',event)">全部关闭</button>
	</div>
	<c:forEach var="item" items="${resultList}" varStatus="status">
		<div style="display:inline-block;  float: left;text-align: center;border-right: solid 1px #cee0f4;padding: 5px">
			<div title="${item.devStatusDesc}">
				<img src="${ctx}/images/air.png" />
			</div>
			<div><b>${item.devName}</b><br/>最新数据：${item.lastestData}</div>
			<dl>
				<button onclick="controllDev('${item.devId}',event)" class="page_anniu">空调控制</button>
				<button class="page_anniu" onclick="switchDev('${item.devId}','0',event)">空调关闭</button>
			</dl>
		</div>
	</c:forEach>
</c:if>
<c:if test="${empty resultList}">
	<div style="width: 300px;height: 300px;text-align: center;padding-top: 50px"><font color="red"><b>暂无相应设备</b></font></div>
</c:if>
</div>
</body>
<script>

</script>
</html>
