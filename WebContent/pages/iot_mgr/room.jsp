<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.verywell.framework.utils.RandomUtil"%>
<%@page import="com.verywell.framework.utils.socket.PoCModel"%>
<%@ page import="java.io.*,java.util.*, java.text.*" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/header.jsp"%>
<%@ include file="/plugins/jquery-nyroModal.jsp" %>
<%@ include file="/plugins/jquery-powerFloat.jsp" %>
 
</head>
<body>
	<div class="mianbaoxie">
		当前位置：<a href="${ctx }/map">综合楼</a>&gt;${roomInfo.roomName}
	</div>
	<!-- 菜单 -->
	<div id="menu_real" class="meeting_div_anniu">
	    <a href="${ctx }/roomMonitor/devList/${roomInfo.roomId}/1?r=<%=Math.random() %>" class="anniu_a nyroModal" title="灯光控制"><img src="${ctx}/images/ico_tongdeng.png" /><br />灯光</a>
	    <a href="${ctx }/roomMonitor/devList/${roomInfo.roomId}/7?r=<%=Math.random() %>" class="anniu_a nyroModal" title="门禁控制"><img src="${ctx}/images/ico_door_open.png" /><br />门禁</a>
	    <a href="${ctx }/roomMonitor/devList/${roomInfo.roomId}/2?r=<%=Math.random() %>" class="anniu_a nyroModal" title="空调控制"><img src="${ctx}/images/ico_kongtiao.png" /><br />空调</a>
	    <a href="${ctx }/roomMonitor/devList/${roomInfo.roomId}/3?r=<%=Math.random() %>" class="anniu_a nyroModal-dianbiao" title="电表控制"><img src="${ctx}/images/ico_chuanganqi.png" /><br />电表</a>
	    <a href="${ctx }/roomMonitor/devList/${roomInfo.roomId}/4?r=<%=Math.random() %>" class="anniu_a nyroModal" title="窗帘控制"><img src="${ctx}/images/chuanlian_open.png" /><br />窗帘</a>
	    <a href="${ctx }/roomMonitor/viewMutiMedia/${roomInfo.roomId}?r=<%=Math.random() %>" class="anniu_a nyroModal" title="会议中控控制"><img src="${ctx}/images/meeting_ctrl.png" /><br />会议中控</a>
	    <a href="${ctx }/roomMonitor/viewScreen/${roomInfo.roomId}?r=<%=Math.random() %>" class="anniu_a nyroModal" title="显示屏控制" target="_blank"><img src="${ctx}/images/screen.png" /><br />显示屏</a>
	    <span class="anniu_line"></span>
		<a id="sceneMenu" href="javascript:;" class="anniu_a"><img src="${ctx}/images/anniu_a.png" /><br />场景</a>
    	<div id="sceneDiv" class="shadow target_box dn">
			<c:forEach var="item" items="${sceneList}">
			<div class="target_list">
		    	<a href="javascript:sceneSelected('${item.sceneId}')">${item.sceneName }</a>
		    </div>
		    </c:forEach>
		</div>
	    <!-- 
	    <span class="anniu_line"></span>
	    <a href="${ctx }/roomMonitor/trendChart/${roomInfo.roomId}/1?r=<%=Math.random() %>" class="anniu_a nyroModal" target="_blank" title="趋势图"><img src="${ctx}/images/ico_qushi.png" /><br />趋势图</a>
	     -->
	    <span class="anniu_line"></span>
	    <a href="${ctx }/devAlarmLogMgr/alarmList/${roomInfo.roomId}?r=<%=Math.random() %>" class="anniu_a nyroModal" title="告警列表"><img src="${ctx}/images/warning_yellow.png" /><br />告警</a>
		 <span class="anniu_line"></span>
		</div>
	<!-- 房间&设备 -->
	<div class="div_map table_div" style="height:100%;">
		<img src="${ctx}/images/${roomInfo.roomImage}" width="100%">
		<c:forEach var="dev" items="${devList}">
			<div id="dev_${dev.devId}" class="${dev.currentClass }" style="left:${dev.positionX/100}%; top:${dev.positionY/100}%;" onclick="showDevDetail('${dev.devId}',event)">
			    <!-- <p class="action_p">(${dev.devName})</p> --> 
			</div>
		</c:forEach>
	</div>
	<!-- 触发弹出面板 -->
	<a id="controll-panel" href="#"  style="display:none">控制面板</a>
	<script language="javascript" type="text/javascript">
	var currentShowDevId='';//当前展示设备ID
	//点击页面其他区域时关闭当前设备展示窗口
	$(document).click(function(event){
		  if($(event.target)!=$("#tip_"+currentShowDevId))
		  {
			  $("#tip_"+currentShowDevId).remove();
			  currentShowDevId='';
		  }
	});
	
	$(document).ready(function () {
		var timeDianbiao = null;
		$("#sceneMenu").powerFloat({
			width: 150,
			target: $("#sceneDiv"),
			eventType: "click"	
		});
		$('.nyroModal').nyroModal();
		$('.nyroModal-dianbiao').nyroModal({callbacks : {
            afterClose: function() {
            	if (timeDianbiao){
            		clearTimeout(timeDianbiao);
            	}
            },
            initElts : function(){
            	timeDianbiao = setInterval("refreshDianbiao()",1000);
            }
        }});
		setInterval("doDevIcoEffect()",250);
		setInterval("refreshData()",5000);
	});
	
	function refreshDianbiao(){
		//console.debug("refreshDianbiao");
		//console.debug($('.nyroModal-dianbiao').attr('href'));
		$('.nyroModalLink').load($('.nyroModal-dianbiao').attr('href'));
	}
	
	//定时切换图标样式，实现图标闪烁效果，解决gif毛边问题
	function doDevIcoEffect()
	{
	    $("div[id^='dev_']").each(
	    function (){
	    	//空调开启动态图标
	    	if($(this).hasClass("kongtiao_open_01"))
	    		$(this).attr("class","kongtiao_open_02");
	    	else if($(this).hasClass("kongtiao_open_02"))
	    		$(this).attr("class","kongtiao_open_03");
	    	else if($(this).hasClass("kongtiao_open_03"))
	    		$(this).attr("class","kongtiao_open_04");
	    	else if($(this).hasClass("kongtiao_open_04"))
	    		$(this).attr("class","kongtiao_open_05");
	    	else if($(this).hasClass("kongtiao_open_05"))
	    		$(this).attr("class","kongtiao_open_06");
	    	else if($(this).hasClass("kongtiao_open_06"))
	    		$(this).attr("class","kongtiao_open_01");
	    	//空调告警动态图标
	     	if($(this).hasClass("kongtiao_alarm_01"))
	    		$(this).attr("class","kongtiao_alarm_02");
	    	else if($(this).hasClass("kongtiao_alarm_02"))
	    		$(this).attr("class","kongtiao_alarm_01");
	    	//灯光开启动态图标
	     	if($(this).hasClass("deng_open_01"))
	    		$(this).attr("class","deng_open_02");
	    	else if($(this).hasClass("deng_open_02"))
	    		$(this).attr("class","deng_open_01");
	    	//门禁开启动态图标
	    	if($(this).hasClass("door_open_01"))
	    		$(this).attr("class","door_open_02");
	    	else if($(this).hasClass("door_open_02"))
	    		$(this).attr("class","door_open_01");
	     	//传感器开启动态图标
	    	if($(this).hasClass("chuanganqi_open_01"))
	    		$(this).attr("class","chuanganqi_open_02");
	    	else if($(this).hasClass("chuanganqi_open_02"))
	    		$(this).attr("class","chuanganqi_open_03");
	    	else if($(this).hasClass("chuanganqi_open_03"))
	    		$(this).attr("class","chuanganqi_open_04");
	    	else if($(this).hasClass("chuanganqi_open_04"))
	    		$(this).attr("class","chuanganqi_open_01");
	    	//传感器告警动态图标
	    	if($(this).hasClass("chuanganqi_alarm_01"))
	    		$(this).attr("class","chuanganqi_alarm_02");
	    	else if($(this).hasClass("chuanganqi_alarm_02"))
	    		$(this).attr("class","chuanganqi_alarm_01");
	    	
	     	
	    	if($(this).hasClass("action_green_01"))
	    		$(this).attr("class","action_green_02");
	    	else if($(this).hasClass("action_green_02"))
	    		$(this).attr("class","action_green_03");
	    	else if($(this).hasClass("action_green_03"))
	    		$(this).attr("class","action_green_04");
	    	else if($(this).hasClass("action_green_04"))
	    		$(this).attr("class","action_green_01");
	    	
	    	if($(this).hasClass("action_red_01"))
	    		$(this).attr("class","action_red_02");
	    	else if($(this).hasClass("action_red_02"))
	    		$(this).attr("class","action_red_03");
	    	else if($(this).hasClass("action_red_03"))
	    		$(this).attr("class","action_red_04");
	    	else if($(this).hasClass("action_red_04"))
	    		$(this).attr("class","action_red_05");
	    	else if($(this).hasClass("action_red_05"))
	    		$(this).attr("class","action_red_01");
	    });
	}
	//定时更新设备状态
	function refreshData()
	{
		$.getJSON("${ctx}/roomMonitor/devDetailList/${roomInfo.roomId}?r="+Math.random(), function(data){
			 $.each(data, function(i, dev){
			     var devId = dev.devId;
			     var devStatus = dev.devStatus;
			     var devAlarmStatus = dev.alarmStatus;
			     $('#dev_'+devId).attr("class",dev.currentClass);
			 });
		});
	}
	
	
	//展示设备详细信息
	function showDevDetail(devId,event)
	{
		//关闭前一个窗口
		if(currentShowDevId!='')
		{
			$("#tip_"+currentShowDevId).remove();
		}
		var tipId = "tip_"+devId;
		$.getJSON("${ctx}/roomMonitor/devDetail/"+devId+"?r="+Math.random(), function(dev){
			$('#dev_'+devId).html("");
			var devStatus= dev.devStatus;
			var alarmStatus=dev.alarmStatus;
			var devClassId = dev.devClassId;//设备类型名称
			var devClassGroupId = dev.devClassGroupId;//设备类型名称
			var devStatusDesc=dev.devStatusDesc;//设备状态
			var devClassName = dev.devClassName;//设备类型名称
			var alarmStatusDesc = dev.alarmStatusDesc;//设备告警状态
			var lastestData = dev.lastestData;//最新数据
			if(alarmStatus=='1')
				alarmStatusDesc ="<font color='red'>"+alarmStatusDesc+"</font>";
			else
				alarmStatusDesc ="<font color='green'>"+alarmStatusDesc+"</font>";
			if(devStatus=='2')
				devStatusDesc = "<font color='red'>"+devStatusDesc+"</font>";
		
			var tipHtml = "";
			tipHtml+="<div id='"+tipId+"' class='info_con' style='z-index:50;'>";
			tipHtml+="<div class='info_jiantou'>";
			tipHtml+="<div class='info_border' style='width:170px;'>";
			tipHtml+="<p>设备名称："+dev.devName+"</p>";
			tipHtml+="<p>设备类型："+devClassName+"</p>";
			tipHtml+="<p>设备状态："+devStatusDesc+"</p>";
			tipHtml+="<p>告警状态："+alarmStatusDesc+"</p>";
			tipHtml+="<p>最新数据："+lastestData+"</p>";
			tipHtml+="<hr noshade='noshade' size='1' />";
			if(devClassGroupId=='2' && devStatus!='2')
				tipHtml+="<input type='button' value='空调控制' class='page_anniu' onclick=\"controllDev('"+devId+"',event)\"/>&nbsp;&nbsp;";
			else
				tipHtml+="<input type='button' value='  开  启  ' class='page_anniu' onclick=\"switchDev(\'"+devId+"\','1',event)\"/>&nbsp;&nbsp;";
			//if(devStatus=='1')
				tipHtml+="<input type='button' value='  关  闭  ' class='page_anniu' onclick=\"switchDev(\'"+devId+"\','0',event)\"/>&nbsp;&nbsp;";
			
			
			tipHtml+="</div>";
			tipHtml+="</div>";
			tipHtml+="</div>";
			//tipHtml+="<p class='action_p'>("+dev.devName+")</p>";
			$('#dev_'+devId).html(tipHtml);
			currentShowDevId=dev.devId;
		});
		stopBubble(event);
	}
	
	//关闭展示窗口
	function closeTip(event)
	{
		//关闭前一个窗口
		if(currentShowDevId!='')
		{
			$("#tip_"+currentShowDevId).remove();
			currentShowDevId='';
		}
		stopBubble(event);
	}
	
	//设备开关 1-开 0-关
	function switchDev(devId,switchFlag,event)
	{
		$.getJSON("${ctx}/roomMonitor/switchDev/"+devId+"/"+switchFlag+"?r="+Math.random(), function(result){
			if(result=='1')
			{
				//alert('操作成功！');
				$("#tip_"+currentShowDevId).remove();
			}
			else
			{
				alert('操作失败！');
			}
		});
		stopBubble(event);
	}
	
	//设备开关 1-开 0-关
	function sendCmd(devId,cmd,value,event)
	{
		$.getJSON("${ctx}/roomMonitor/controllDev/"+devId+"/"+cmd+"/"+value+"?r="+Math.random(), function(result){
			if(result=='1')
			{
				//alert('操作成功！');
				$("#tip_"+currentShowDevId).remove();
			}
			else
			{
				alert('操作失败！');
			}
		});
		stopBubble(event);
	}
	
	//设备控制-根据设备类型弹出相应设备控制面板
	function controllDev(devId,event)
	{
		$("#tip_"+currentShowDevId).remove();
		var url = "${ctx}/roomMonitor/devControll/"+devId+"?r="+Math.random();
		$('#controll-panel').attr("href",url);
		$('#controll-panel').nyroModal(
		{
			_close:function close()
			{
				//取消控制面板页面的定时器
				if (typeof(panelInterval) != "undefined") { 
					clearInterval(panelInterval);
				}
			}
		}		
		).nmCall();
		stopBubble(event);
	}
	
	//场景选择
	function sceneSelected(sceneId)
	{
		$.getJSON("${ctx}/roomMonitor/setScene/"+sceneId+"?r="+Math.random(), function(result){
			if(result=='1')
			{
				alert('场景指令发送成功！');
				$.powerFloat.hide();
			}
			else
			{
				alert('场景指令发送失败！请稍后再试！');
			}
		});
	}
	</script>
</body>
</html>
