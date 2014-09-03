<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/header.jsp"%>
</head>
<body>
<c:if test="${not empty devInfo}">
	<div style="width: 630px">
		<div class="tab_top">
			<div id="tab0" class="tab_text_over" onclick="changeTab('0')">
				<div class="left"></div>
				<div class="con">&nbsp;&nbsp;电源管理&nbsp;&nbsp;</div>
				<div class="right" id="dateBtn"></div>
			</div>
			<div id="tab1" class="tab_text_out" onclick="changeTab('1')">
				<div class="left"></div>
				<div class="con">&nbsp;&nbsp;声音控制&nbsp;&nbsp;</div>
				<div class="right"></div>
			</div>
		</div>
		<div id="tabContent0" class="table_div" style="height: 200px;text-align: center;padding-top: 40px">
			<button class="form_botton" onclick="sendCmd('${devInfo.devId}','POW','ON',event);">电  源  开</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button class="form_botton" onclick="sendCmd('${devInfo.devId}','POW','OFF',event);">电  源  关</button>
		</div>
		
		<div id="tabContent1" class="table_div" style="height: 200px;text-align: center;padding-top: 40px;display:none">
			<button class="form_botton" onclick="sendCmd('${devInfo.devId}','VOL','UP',event);">音  量+</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button class="form_botton" onclick="sendCmd('${devInfo.devId}','VOL','DOWN',event);">音  量-</button>
		</div>
		
	</div>
</c:if>
<c:if test="${empty devInfo}">
	<div style="width: 300px;height: 300px;text-align: center;padding-top: 50px"><font color="red"><b>暂无相应设备</b></font></div>
</c:if>
</body>
<script>
	function changeTab(tabIndex)
	{
		for(var i=0;i<3;i++)
		{
			if(tabIndex==i)
			{
				$('#tab'+i).attr("class","tab_text_over");
				$('#tabContent'+i).show();
			}
			else
			{
				$('#tab'+i).attr("class","tab_text_out");
				$('#tabContent'+i).hide();
			}
		}
	}
</script>
</html>
