<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/header.jsp"%>
</head>
<body>
<div style="height: 420px;width:780px;">
<c:if test="${not empty resultList}">
	<div style="border-bottom: solid 1px #cee0f4;padding-bottom: 2px">
		<button class="page_anniu" onclick="curtainDev('${devIds}','0',event)">全部开启</button>
		<button class="page_anniu" onclick="curtainDev('${devIds}','1',event)">全部关闭</button>
		<button class="page_anniu" onclick="curtainDev('${devIds}','2',event)">全部停止转动</button>
		<button class="page_anniu" onclick="enterLight()">进入自动</button>
		<button class="page_anniu" onclick="leaveLight()">进入手动</button>
	</div>
	<c:forEach var="item" items="${resultList}" varStatus="status">
		<div style="float: left;text-align: center;border-right: solid 1px #cee0f4;padding: 5px">
			<dl><img src="${ctx}/images/chuanlian_open_big.png" /></dl>
			<dl><b>${item.devName}</b></dl>
			<dl>
				<button class="page_anniu" onclick="dhlswitchDev('${item.devId}','1',event)">开启</button>
				<button class="page_anniu" onclick="dhlswitchDev('${item.devId}','0',event)">关闭</button>
				<button class="page_anniu" onclick="dhlswitchDev('${item.devId}','2',event)">停止转动</button>
			</dl>
			<%-- <dl style="margin-top: 10px">
				<button class="page_anniu" onclick="switchDev('${item.devId}','1,1',event)">布帘开启</button>
				<button class="page_anniu" onclick="switchDev('${item.devId}','0,1',event)">布帘关闭</button>
			</dl>
			<dl>
				<button class="page_anniu" onclick="switchDev('${item.devId}','1,0',event)">整个开启</button>
				<button class="page_anniu" onclick="switchDev('${item.devId}','0,0',event)">整个关闭</button>
			</dl> --%>
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
