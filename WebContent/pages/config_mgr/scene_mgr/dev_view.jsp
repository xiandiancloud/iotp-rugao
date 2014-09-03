<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/header.jsp"%>
	</head>
	<body>
		<div class="tab_search" >
			<div id="roomDiv" class="div_map table_div" style="height:100%;">
				<img src="${ctx}/images/${roomInfo.roomImage}" width="100%">
				<c:forEach var="dev" items="${devList}">
				<div id="dev_${dev.devId}" class="action_green_01" devId="${dev.devId}" devName="${dev.devName}" devClassId="${dev.devClassInfo.devClassGroupInfo.groupId}" style="left:${dev.positionX/100}%; top:${dev.positionY/100}%;">
				   <p class="action_p">(${dev.devName})</p>
				</div>
				</c:forEach>	
			</div>
		</div>
	</body>
</html>