<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/header.jsp"%>
</head>
<body>
<div style="width: 400px;">
<c:if test="${not empty resultList}">
 	<%-- <div style="border-bottom: solid 1px #cee0f4;padding-bottom: 2px">
	    <button class="page_anniu" onclick="switchLight('${devIds}','1',event)">全部开启</button>
		<button class="page_anniu" onclick="switchLight('${devIds}','0',event)">全部关闭</button>
	</div> --%>
	<c:forEach var="item" items="${resultList}" varStatus="status">
		<div style="display:inline-block;  float: left;text-align: center;border-right: solid 1px #cee0f4;padding: 5px;width: 135px">
<%-- 			<dl title="${item.devStatusDesc}">
				<c:if test="${item.devStatus!='1'}">
					<img id="${item.devId}_img" src="${ctx}/images/light_on.png" />
				</c:if>
				<c:if test="${item.devStatus=='1'}">
					<img id="${item.devId}_img" src="${ctx}/images/light_off.png" />
				</c:if>
			</dl> --%>
			<dl><b>${item.devName}</b></dl>
			<dl>
			    <button class="page_anniu" onclick="switchLight('${item.devId}','1',event)">强制开启</button></br></br>
				<button class="page_anniu" onclick="switchLight('${item.devId}','0',event)">强制关闭</button></br></br>
				<button class="page_anniu" onclick="switchLight('${item.devId}','2',event)">手动模式</button>		
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
//设备开关 1-开 0-关
function switchLight(devId,switchFlag,event)
{
	$.getJSON("${ctx}/roomMonitor/switchDev/"+devId+"/"+switchFlag+"?r="+Math.random(), function(result){
		if(result=='1')
		{
			if(devId.indexOf(",")>0)
			{
				var devIds = devId.split(",");
				for(var i=0;i<devIds.length;i++)
				{
					if(switchFlag=='0')
						$("#"+devIds[i]+'_img').attr("src","${ctx}/images/light_off.png");
					else
						$("#"+devIds[i]+'_img').attr("src","${ctx}/images/light_on.png");
				}
			}
			else
			{
				if(switchFlag=='0')
					$("#"+devId+'_img').attr("src","${ctx}/images/light_off.png");
				else
					$("#"+devId+'_img').attr("src","${ctx}/images/light_on.png");
				
			}
		}
		else
		{
			alert('操作失败！');
		}
	});
	stopBubble(event);
}
</script>
</html>
