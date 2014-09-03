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
	<div class="mianbaoxie">
		当前位置：<a href="#1">告警管理</a> > 设备告警列表
	</div>
	<form id="mainForm" action="${ctx}/devAlarmLogMgr" method="post">
		<input type="hidden" name="pageNo" id="pageNo" value="${page.pageNo}" />
		<input type="hidden" name="pageSize" id="pageSize" value="${page.pageSize}" />
		<input type="hidden" name="orderBy" id="orderBy" value="${page.orderBy}" />
		<input type="hidden" name="order" id="order" value="${page.order}" />
		<bgsound id="alarmSound" src="${ctx}/pages/alarm.wav"  loop="-1"/>
		<div class="tab_search" style="margin-top: 20px;">
			<div class="tab_top">
				<div class="tab_text">
					<div class="tab_text_left"></div>
					<div class="tab_text_con">按条件查询</div>
					<div class="tab_text_right" id="dateBtn"></div>
				</div>
			</div>
	
			<div class="table_div">
				<table width="100%" class="table_border">
					<tr>
						<td class="title">设备类型：</td>
						<td>
							<select name="filter_EQ_devClassId" id="devClassId" class="form_input" style="width: 200px;">
								<option value="">--全部--</option>
								<c:if test="${classListSize != '0'}">
									<c:forEach var="classInfo" items="${classList}" varStatus="status">
										<option value='${classInfo.devClassId}'>${classInfo.className}</option>
									</c:forEach>
								</c:if>
							</select>
						</td>
						<td class="title">告警类型：</td>
						<td>
							<select name="filter_EQ_alarmType" id="alarmType" class="form_input" style="width: 200px;">
								<option value="">--全部--</option>
								<option value="1">红外告警</option>
								<option value="2">烟雾或可燃性气体告警</option>
								<option value="3">电能告警</option>
								<option value="4">温度告警</option>
								<option value="5">湿度告警</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="title" width="100">房间名称：</td>
						<input type="hidden" name="filter_EQ_mapRoomInfo.roomId" id="roomId" value="${param['filter_EQ_mapRoomInfo.roomId']}"/>
						<td><input type="text" name="roomName" id="roomName" value="${param['roomName']}" size="20" class="form_input" readonly="readonly" style="cursor: pointer;"/>
						<div id="roomChooseDiv" style="position:absolute; border:solid 1px #CCCCCC; width:300px; height:300px; top:23px; left:0px; background:#FFFFFF;display: none">
					         <ul id="treeDemo" class="ztree" style="width: 180px;">
					         </ul>
				        </div>
						</td>
						<td class="title" width="100">告警时间：</td>
						<td>
							<input name="_startDate" id="_startDate" type="text" class="form_input"  style="width:130px;cursor: pointer;"  /> 
							<div style="display: none">
								<textarea rows="1" cols="12" name="filter_LIKE_alarmTime" id="filter_LIKE_alarmTime"></textarea>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center"><input name="" type="button" value="查询" class="form_botton" onclick="query()"/>&nbsp;&nbsp;&nbsp;&nbsp;<input
							name="" type="button" value="重置" class="form_botton" onclick="clearForm()"/>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>


	<div class="table_list table_div" style="margin-top: 20px;">
			<table id="tableList" cellspacing="1" class="table_border" width="100%">
						<tr align="center">
							<td class="table_list_title title_border_a" onclick="javascript:sort('alarmTime','asc')" width="10%"><div class="paixu_3">告警时间<div></td>
							<td class="table_list_title title_border_a" onclick="javascript:sort('status','asc')" width="10%"><div class="paixu_3">状态<div></td>
							<td class="table_list_title title_border_a" onclick="javascript:sort('alarmType','asc')" width="10%"><div class="paixu_3">告警类型<div></td>
							<td class="table_list_title" width="15%">设备名称</td>
							<td class="table_list_title title_border_a" onclick="javascript:sort('mapRoomInfo.roomId','asc')" width="10%"><div class="paixu_3">所属房间<div></td>
							<td class="table_list_title title_border_a" onclick="javascript:sort('mapRoomInfo.mapBuildingInfo.buildingId','asc')" width="10%"><div class="paixu_3">所属大楼</div></td>
							<td class="table_list_title" width="15%">告警描述</td>
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
									<c:out value="${alarmLog.mapRoomInfo.roomName}" />
								</td>
								<td>
									<c:out value="${alarmLog.mapRoomInfo.mapBuildingInfo.buildingName}" />
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
	<script language="JavaScript" type="text/JavaScript">
	Calendar.setup(
			{
		      inputField  : "_startDate",         // ID of the input field
		      displayArea : "filter_LIKE_alarmTime",
		      ifFormat    : "%Y-%m-%d",    // the date format
		      daFormat    : "%Y%m%d",
		      button      : "_startDate"       // ID of the button
			});
	
	$(document).ready(function() {
		//树形菜单
		var setting = {
			data: {
				simpleData: {enable: true}
			},
			callback: {
				onClick: onClick
			}
		};
		var zNodes = ${tree.json};  
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
	});

	function onClick(e, treeId, treeNode) {
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		var nodes = treeObj.getSelectedNodes();
		var roomId = nodes[0].id;
		var roomName = nodes[0].name;
		$("#roomName").val(roomName);
		$("#roomId").val(roomId);
		$.powerFloat.hide();
		$("#roomChooseDiv").css("display","none");
	}
	
	function query(){
		$("#mainForm").submit();
	}

	function clearForm(){
		$("#devName").val("");
		$("#devClassId").val("");
		$("#alarmType").val("");
		$("#roomName").val("");
		$("#roomId").val("");
		$("#_startDate").val("");
		$("#filter_LIKE_alarmTime").val("");
	}

	$("#roomName").powerFloat({
		eventType: "click",
		target: $("#roomChooseDiv")	
	});
	
	function init()
	{
		$("#devClassId").val("${param['filter_EQ_devClassId']}");
		$("#alarmType").val("${param['filter_EQ_alarmType']}");
		$("#_startDate").val("${param['_startDate']}");
		$("#filter_LIKE_alarmTime").val("${param['filter_LIKE_alarmTime']}");
		document.all.alarmSound.volume = -10000;
		setInterval(refreshAlarm,15000);//30秒更新一次 
	}
	init();

	var alarmId='';
	//定时检查告警信息
	function refreshAlarm()
	{
		$.getJSON("${ctx}/devAlarmLogMgr/refreshAlarmLog", function(data){
			if(data.id != '' && data.id != null)
			{
				var receiverName = data.receiverName;
				alarmId = data.id;
				ymPrompt.alert({message:'<div style="padding-right:20px;line-height:150%">用户'+receiverName+'发生警情<br><font color="red">请立即处理！</font></div>',title:'报警',btn:[['处理','ok'],['关闭','close']],autoClose:false,fixPosition:true,winPos:'rb',showMask:false,useSlide:true,slideCfg:{increment:0.1,interval:100},handler:btnClick});
				document.all.alarmSound.volume = 0;
				setTimeout(function(){ymPrompt.doHandler('close');},15000);
			}
		});
	}

	//窗口点击
	function btnClick(btn)
	{
		if(btn=='ok'){
			if(alarmId!='')
				$.getJSON("${ctx}/devAlarmLogMgr/precessAlarm?alarmId="+alarmId, function(data){
					if(data)
					{
						alert("处理成功");
						document.all.alarmSound.volume = -10000;
						ymPrompt.close();
					}else{
						alert("处理失败");
					}
				});
		}
		else
		{
			document.all.alarmSound.volume = -10000;
			ymPrompt.close();
		}
	}
	</script>
</body>
</html>
