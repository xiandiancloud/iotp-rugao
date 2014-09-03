<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/header.jsp"%>
<%@ include file="/plugins/jquery-nyroModal.jsp" %>
<%@ include file="/plugins/jquery-powerFloat.jsp" %>
<%@ include file="/plugins/jquery-ui.jsp"%>
<%@ include file="/plugins/jquery-contextmenu.jsp" %>
<%@ include file="/plugins/ztree.jsp"%>

</head>

<body>
	<div class="mianbaoxie">
		<div class="menu">当前位置：设备管理&gt; 设备管理&gt;</div>
		<div class="menu"><a id="roomChoose" href="#" onclick="javascript:$('#roomChooseDiv').toggle()">${roomInfo.roomName}</a><font color="red">  &lt;&lt;点击此处切换房间</font>
      	</div>
	</div>
	<div id="roomChooseDiv" style="display: none;" class="shadow target_box">
      	<ul id="treeDemo" class="ztree"></ul>
    </div>
	<div class="meeting_div_anniu">
	    <a href="${ctx }/devMgr/devList/${roomInfo.roomId}/1?r=<%=Math.random() %>" class="anniu_a nyroModal" title="灯光设备"><img src="${ctx}/images/ico_tongdeng.png" /><br />灯光</a>
	    <a href="${ctx }/devMgr/devList/${roomInfo.roomId}/7?r=<%=Math.random() %>" class="anniu_a nyroModal" title="门禁设备"><img src="${ctx}/images/ico_door_open.png" /><br />门禁</a>
	    <a href="${ctx }/devMgr/devList/${roomInfo.roomId}/2?r=<%=Math.random() %>" class="anniu_a nyroModal" title="空调设备"><img src="${ctx}/images/ico_kongtiao.png" /><br />空调</a>
	    <a href="${ctx }/devMgr/devList/${roomInfo.roomId}/3?r=<%=Math.random() %>" class="anniu_a nyroModal" title="传感器设备"><img src="${ctx}/images/ico_chuanganqi.png" /><br />传感器</a>
	    <a href="${ctx }/devMgr/devList/${roomInfo.roomId}/4?r=<%=Math.random() %>" class="anniu_a nyroModal" title="电动窗帘设备"><img src="${ctx}/images/chuanlian_open.png" /><br />窗帘</a>
	    <a href="${ctx }/devMgr/devList/${roomInfo.roomId}/5?r=<%=Math.random() %>" class="anniu_a nyroModal" title="会议中控设备"><img src="${ctx}/images/meeting_ctrl.png" /><br />会议中控</a>
	    <a href="${ctx }/devMgr/devList/${roomInfo.roomId}/6?r=<%=Math.random() %>" class="anniu_a nyroModal" title="显示屏控制" target="_blank"><img src="${ctx}/images/screen.png" /><br />显示屏</a>
	    <span class="anniu_line"></span>
		<a id="viewMenu" href="javascript:;" class="anniu_a"><img src="${ctx}/images/ico_chakan.png" /><br />显示</a>
	</div>
    <div id="viewDiv" class="shadow target_box dn">
	    <div class="target_list">
	    	<input id="lightViewMod" type="checkbox" onclick="javascript:hideDevClass(this,'1')"/><a href="#" style="cursor: pointer;" onclick="document.getElementById('lightViewMod').click();">灯光隐藏</a>
	    </div>
	    <div class="target_list">
	    	<input id="kongtiaoViewMod" type="checkbox"  onclick="javascript:hideDevClass(this,'2')"/><a href="#" style="cursor: pointer;" onclick="document.getElementById('kongtiaoViewMod').click()">空调隐藏</a>
	    </div>
	    <div class="target_list">
	    	<input id="chuanganqiViewMod" type="checkbox" onclick="javascript:hideDevClass(this,'3')"/><a href="#" style="cursor: pointer;" onclick="document.getElementById('chuanganqiViewMod').click()">传感器隐藏</a>
	    </div>
	</div>
	<!-- 房间设备 -->
	<div id="roomDiv" class="div_map table_div" style="height:100%;">
		<img src="${ctx}/images/${roomInfo.roomImage}" width="100%">
		<c:forEach var="dev" items="${devList}">
		${dev.devName}+--------${dev.currentClass }
		<div id="dev_${dev.devId}" class="${dev.currentClass }" devId="${dev.devId}" devName="${dev.devName}" devClassId="${dev.devClassInfo.devClassGroupInfo.groupId}" style="left:${dev.positionX/100}%; top:${dev.positionY/100}%;">
		   <!-- <p class="action_p">(${dev.devName})</p> -->
		</div>
		</c:forEach>	
	</div>
	</div>
	<!-- 设备右键菜单 -->
	<div class="contextMenu" id="devMenu">
      <ul>
        <li id="edit"><img src="${ctx}/plugins/jquery-easyui/themes/icons/pencil.png" />修改配置</li>
        <li id="delete"><img src="${ctx}/plugins/jquery-easyui/themes/icons/cancel.png" />删除设备</li>        
      </ul>
    </div>
    <!-- 背景右键菜单 -->
    <div class="contextMenu" id="roomMenu">
      <ul>
        <li id="addDev"><img src="${ctx}/plugins/jquery-easyui/themes/icons/edit_add.png" />新增设备</li>
      </ul>
    </div>
	<a id="devAction" class="nyroModal" href="#" target="_blank" style="display: none" title="设备新增">触发设备新增</a>
	<script language="JavaScript" type="text/JavaScript">
	var currentX; //当前鼠标在房间DIV中的X位置
	var currentY; //当前鼠标在房间DIV中的Y位置
	$(document).ready(function() {
		//树形菜单
		var setting = {
			data: {
				simpleData: {enable: true}
			}
		};
		var zNodes =${mapTree.json};
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			//setInterval("doDevIcoEffect()",250);
		});
		//弹出窗口
		$('.nyroModal').nyroModal();

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
		    });
		}
		
		
		//设备可拖动
		var posX;//拖动设备的X位置
		var posY;//拖动设备的Y位置
		$("div[id^='dev_']").each(
			function(){
			    $(this).draggable(
	    		{
	    			containment:"#roomDiv",
	    			start: function() {
	    				var pos = $(this).position();
	    				posX = pos.left;
	    				posY = pos.top;
	    			},
	    			drag: function() {

	    			},
	    			stop: function() {
	    				if(confirm('你刚刚调整了\"'+$(this).attr("devName")+'\"设备的位置，是否保存本次操作？'))
	    				{
		    				var pos = $(this).position();
		    				var devId= $(this).attr("devId");
		    				var positionX = getLeftPrecent(pos.left+42);
		    		    	var positionY = getTopPrecent(pos.top+38);
		    				$.getJSON("${ctx}/devMgr/updatePosition/"+devId+"/"+positionX+"/"+positionY+"?r="+Math.random(), function(data){
		    					if(data.messageType=='1')
	    					    {
	    					    	alert(data.promptInfo);
	    					    }
	    					    else
	    					    {
	    					    	alert(data.promptInfo);
	    					    	$(this).css("left",posX);
	    	    					$(this).css("top",posY);
	    					    }
		    				});
	    				}
	    				else
	    				{
	    					$(this).css("left",posX);
	    					$(this).css("top",posY);
	    				}

	    			}
	    		});
		});
		//浮动层
		$("#roomChoose").powerFloat({
			width: 130,
			target: $("#roomChooseDiv"),
			eventType: "click"
		});
		$("#viewMenu").powerFloat({
			width: 130,
			target: $("#viewDiv"),
			eventType: "click"	
		});
		//设备右键菜单
		$("div[id^='dev_']").contextMenu('devMenu', {
		    bindings: {
		      'edit': function(t) {
		    	  var devId= $("#"+t.id).attr("devId");
		    	  var url = "${ctx}/devMgr/edit/"+devId;
			   	  $('#devAction').attr("href",url);
			   	  $('#devAction').attr("title","设备修改");
			      $('#devAction').click();
		      },
		      'delete': function(t) {
		    	  if(confirm('你确定要删除设备\"'+$("#"+t.id).attr("devName")+'\"吗？'))
		    	  {
		    		  var devId= $("#"+t.id).attr("devId");
	    				$.getJSON("${ctx}/devMgr/delete/"+devId+"?r="+Math.random(), function(data){
	    				if(data.messageType=='1')
  					    {
  					    	alert(data.promptInfo);
  					    	$("#"+t.id).remove();
  					    }
  					    else
  					    {
  					    	alert(data.promptInfo);
  					    }
	    				});
		    	  }
		      }
		    }
		});
		//背景右键菜单
		$('div.div_map').contextMenu('roomMenu', {
		    bindings: {
		      'addDev': function(t) {
		    	 var positionX = getLeftPrecent(currentX);
		    	 var positionY = getTopPrecent(currentY);
		    	 var url = "${ctx}/devMgr/add/${roomInfo.roomId}/"+positionX+"/"+positionY;
		    	 $('#devAction').attr("href",url);
		    	 $('#devAction').attr("title","设备新增");
		    	 $('#devAction').click();
		      }
		    }
		  });
		//记录当前鼠标位置
		$('#roomDiv').mousemove(function(e) { 
			currentX = e.originalEvent.x || e.originalEvent.layerX || 0; 
			currentY = e.originalEvent.y || e.originalEvent.layerY || 0; 
		}); 
	});	
	
	//设备隐藏
	function hideDevClass(obj,devClass)
	{
		if(!obj.checked)
		{
			$("div[devClassId='"+devClass+"']").each(
				function(){$(this).show();});
		}
		else
		{
			$("div[devClassId='"+devClass+"']").each(
				function(){$(this).hide();}
			);
			
		}
		
	}
	
	
	//获得当前鼠标位置相对房间背景的X的百分比值
	function getLeftPrecent(posX)
	{
		var	width =  $('#roomDiv').width();
		var newPosX = posX-42;//减去图标一半的宽度
		if(newPosX<0)
			newPosX=0;
		var precent = newPosX/width*10000;
		return precent.toFixed(0);//取小数点后两位
	}
	
	//获得当前鼠标位置相对房间背景的Y的百分比值
	function getTopPrecent(posY)
	{
		var	height =  $('#roomDiv').height();
		var newPosY = posY-38;//减去图标一半的高度
		if(newPosY<0)
			newPosY=0;
		var precent = newPosY/height*10000;
		return precent.toFixed(0);//取小数点后两位
	}
	//关闭弹出窗口并刷新页面
	function closeModalWindow()
	{
		$.nmTop().close();
		location.reload();
	}
	</script>
</body>
</html>
