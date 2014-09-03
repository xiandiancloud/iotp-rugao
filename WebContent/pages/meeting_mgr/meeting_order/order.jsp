<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/header.jsp"%>
<%@ include file="/plugins/jquery-nyroModal.jsp" %>
<%@ include file="/plugins/jquery-validation.jsp"%>
<%@ include file="/plugins/calendar.jsp"%>
</head>
<body>
	<div class="mianbaoxie">
		当前位置：<a href="#1">会议室预定管理</a> &gt; 会议室预定 &gt; 新建会议
	</div>
	<div class="tab_search" style="margin-top: 20px;">
		<div class="table_div" style="overflow: auto;">
			<form:form id="mainForm" name="mainForm" action="${ctx}/meetingMgr/save" method="post" modelAttribute="resultObject">
			<form:hidden path="meetingId"/>
			<form:hidden path="screenFilePath"/>
			<table width="100%" class="table_border">
				<tr>
					<td colspan="2">
						<b>会议信息</b>
					</td>
					<td colspan="2">
						<b>会议时间</b>
					</td>
				</tr>
				<tr>
					<td class="title" width="15%"><font color="red">*</font>会议名称：</td>
					<td width="35%">
						<form:input path="meetingName" cssClass="form_input required" maxlength="20" />
					</td>
					<td class="title" width="15%"><font color="red">*</font>会议日期：</td>
					<td>
						<form:input path="startDate" cssClass="form_input required" readonly="readonly"  cssStyle="cursor: pointer;"/>
						<img id="startDateBtn" src="${ctx}/plugins/dhtmlxScheduler/codebase/imgs/calendar.gif" style="cursor: pointer;"/>
					</td>
				</tr>
				<tr>
					<td class="title" ><font color="red">*</font>会议地点：</td>
					<td>
						<form:select path="roomId"  cssClass="form_input required" onchange="roomChange()">
							<form:option value=""></form:option>
							<c:forEach var="room" items="${roomList}">
								<form:option value="${room.roomId}">${room.roomName}</form:option>
							</c:forEach>
						</form:select>
					</td>
					<td class="title"><font color="red">*</font>开始时间：</td>
					<td>
						<form:select path="startTimeHour" cssClass="form_input">
							<form:option value="08">08</form:option>
							<form:option value="09">09</form:option>
							<form:option value="10">10</form:option>
							<form:option value="11">11</form:option>
							<form:option value="12">12</form:option>
							<form:option value="13">13</form:option>
							<form:option value="14">14</form:option>
							<form:option value="15">15</form:option>
							<form:option value="16">16</form:option>
							<form:option value="17">17</form:option>
							<form:option value="18">18</form:option>
							<form:option value="19">19</form:option>
							<form:option value="20">20</form:option>
							<form:option value="21">21</form:option>
						</form:select> :
						<form:select path="startTimeMin" cssClass="form_input">
							<form:option value="00">00</form:option>
							<form:option value="05">05</form:option>
							<form:option value="10">10</form:option>
							<form:option value="15">15</form:option>
							<form:option value="20">20</form:option>
							<form:option value="25">25</form:option>
							<form:option value="30">30</form:option>
							<form:option value="35">35</form:option>
							<form:option value="40">40</form:option>
							<form:option value="45">45</form:option>
							<form:option value="50">50</form:option>
							<form:option value="55">55</form:option>
						</form:select>
					</td>
				</tr>
				<tr>
					<td class="title" width="100">预定人：</td>
					<td>
						<form:input path="subscribeByName" cssClass="form_input required" maxlength="20" readonly="true"/>
					</td>
					<td class="title"><font color="red">*</font>结束时间：</td>
					<td>
						<form:select path="endTimeHour" cssClass="form_input">
							<form:option value="08">08</form:option>
							<form:option value="09">09</form:option>
							<form:option value="10">10</form:option>
							<form:option value="11">11</form:option>
							<form:option value="12">12</form:option>
							<form:option value="13">13</form:option>
							<form:option value="14">14</form:option>
							<form:option value="15">15</form:option>
							<form:option value="16">16</form:option>
							<form:option value="17">17</form:option>
							<form:option value="18">18</form:option>
							<form:option value="19">19</form:option>
							<form:option value="20">20</form:option>
							<form:option value="21">21</form:option>
						</form:select> :
						<form:select path="endTimeMin" cssClass="form_input">
							<form:option value="00">00</form:option>
							<form:option value="05">05</form:option>
							<form:option value="10">10</form:option>
							<form:option value="15">15</form:option>
							<form:option value="20">20</form:option>
							<form:option value="25">25</form:option>
							<form:option value="30">30</form:option>
							<form:option value="35">35</form:option>
							<form:option value="40">40</form:option>
							<form:option value="45">45</form:option>
							<form:option value="50">50</form:option>
							<form:option value="55">55</form:option>
						</form:select>
						<label id="endTimeError" style="color: red;font-style: italic"></label>
					</td>
				</tr>
				<tr>
					<td class="title">会议提醒：</td>
					<td colspan="3">
						<form:radiobutton path="noticeFlag" onclick="$('#noticeContentTr').hide()" value="0" cssClass="form_input"/>不提醒 
						<form:radiobutton path="noticeFlag" id="noticeFlagYes" onclick="$('#noticeContentTr').show()" value="1" cssClass="form_input"/>短信提醒
					</td>
					
				</tr>
				<tr id="noticeContentTr" style="display:none">
					<td class="title"><font color="red">*</font>短信内容</td>
					<td colspan="3">
						<form:textarea path="noticeContent" cols="70" rows="3" cssClass="form_input {required:'#noticeFlagYes:checked',maxlength:70}"></form:textarea>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<b>参会人员</b>
					</td>
				</tr>
				<tr>
					<td class="title">参与人员：</td>
					<td colspan="3">
						<form:hidden path="targetUserIds"/> 
						<img src="${ctx}/images/search.png" style="cursor:pointer;" onclick="javascript:showModalPage('${ctx}/meetingMgr/userSelect?userIdObj=targetUserIds&userNameObj=targetUserNames&selectedIds='+$('#targetUserIds').val(),'参与人员选择')"/>
						<div id="targetUserNames" style="display:inline-block;color:blue">${resultObject.targetUserNames }</div>
						<label id="treeError" style="color: red;font-style: italic"></label>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<b>筹备人员</b>
					</td>
				</tr>
				<tr>
					<td class="title">筹备人员：</td>
					<td width="300px" colspan="3">
						<form:hidden path="prepareBy"/> 
						<img src="${ctx}/images/search.png" style="cursor:pointer;" onclick="javascript:showModalPage('${ctx}/meetingMgr/userSelect?userIdObj=prepareBy&userNameObj=prepareByNames&selectedIds='+$('#prepareBy').val(),'筹备人员选择')"/>
						<div id="prepareByNames" style="display:inline-block;color:blue">${resultObject.prepareByNames }</div>
						<label id="prepareUserTreeError" style="color: red;font-style: italic"></label>
					</td>
				</tr>
				<tr>
					<td class="title">提前筹备时间：</td>
					<td colspan="3">
						<form:select path="prepareEarlyTime" cssClass="form_input">
							<form:option value=""></form:option>
							<form:option value="30">提前30分钟</form:option>
							<form:option value="40">提前40分钟</form:option>
							<form:option value="50">提前50分钟</form:option>
							<form:option value="60">提前60分钟</form:option>
						</form:select>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<b>场景设置</b>
					</td>
				</tr>
				<tr>
					<td class="title">启用场景：</td>
					<td colspan="3">
						<form:radiobutton path="sceneFlag" onclick="sceneFlagChange('0')" value="0" cssClass="form_input"/>不启用
					 	<form:radiobutton path="sceneFlag" id="sceneFlagYes" onclick="sceneFlagChange('1')" value="1" cssClass="form_input"/>启用
					</td>
				</tr>
				<tr id="sceneTr1" style="display:none">
					<td class="title">场景选择：</td>
					<td colspan="3">
						<c:forEach var="item" items="${sceneMap}">
							<select id="sceneId_${item.key }" name="sceneId" class="{required:'#sceneFlagYes:checked'}" style="display: none">
								<c:forEach var="scene" items="${item.value}">
									<option value="${scene.sceneId}" roomId="${scene.roomId}">${scene.sceneName}</option>
								</c:forEach>
							</select>
						</c:forEach>
					</td>
				</tr>
				<tr id="sceneTr2" style="display:none">
					<td class="title">启用时间：</td>
					<td colspan="3">
						<form:select path="sceneEarlyTime" cssClass="form_input">
							<form:option value="0">提前0分钟</form:option>
							<form:option value="10">提前10分钟</form:option>
							<form:option value="20">提前20分钟</form:option>
							<form:option value="30">提前30分钟</form:option>
							<form:option value="40">提前40分钟</form:option>
							<form:option value="50">提前50分钟</form:option>
							<form:option value="60">提前60分钟</form:option>
						</form:select>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<b>显示屏设置</b>
					</td>
				</tr>
				<tr>
					<td class="title">启用显示屏：</td>
					<td colspan="3">
						<form:radiobutton path="screenFlag" name="screenFlag" onclick="screenFlagChange('0')" value="0" cssClass="form_input"/>不启用
					 	<form:radiobutton path="screenFlag" id="screenFlagYes" name="screenFlag"  onclick="screenFlagChange('1')" value="1" cssClass="form_input"/>启用
					</td>
				</tr>
				<tr id="screenTr1" style="display:none">
					<td class="title">显示屏配置：</td>
					<td colspan="3">
						<a href="javascript:screenConfig()">配置</a>
						<label id="screenError" style="color: red;font-style: italic"></label>
					</td>
				</tr>
				<tr id="screenTr2" style="display:none">
					<td class="title">启用时间：</td>
					<td colspan="3">
						<form:select path="screenEarlyTime" cssClass="form_input">
							<form:option value="0">提前0分钟</form:option>
							<form:option value="10">提前10分钟</form:option>
							<form:option value="20">提前20分钟</form:option>
							<form:option value="30">提前30分钟</form:option>
							<form:option value="40">提前40分钟</form:option>
							<form:option value="50">提前50分钟</form:option>
							<form:option value="60">提前60分钟</form:option>
						</form:select>
					</td>
				</tr>
				<tr id="screenTr3" style="display:none">
					<td class="title">应用到：</td>
					<td colspan="3">
						<c:forEach var="dev" items="${screenList}">
							<input type="checkbox" name="screenRoomIds" value="${dev.macAddr}" class="{required:'#screenFlagYes:checked'} form_input"/>${dev.roomName}&nbsp;&nbsp;
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td colspan="4"><b>备&nbsp;&nbsp;&nbsp;&nbsp;注</b></td>
				</tr>
				<tr>
					<td colspan="4"><form:textarea path="meetingDesc" rows="3" cssClass="form_input" cssStyle="width:100%"></form:textarea></td>
				</tr>
				<tr>
					<td colspan="4" align="center">
					<input id="saveBtn"  type="submit" value="保存" class="form_botton"/>&nbsp;&nbsp;&nbsp;&nbsp;
					<c:if test="${not empty resultObject.meetingId}">
					<input id="cancelBtn" type="button" value="取消会议" class="form_botton" onclick="cancelMeeting('${resultObject.meetingId}')"/>&nbsp;&nbsp;&nbsp;&nbsp;
					</c:if>
					<input  type="button" value="关闭" class="form_botton" onclick="window.location='${ctx}/meetingMgr?viewDate=${param.viewDate}&viewMode=${param.viewMode}'"/>&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
			</table>
			</form:form>
		</div>
	</div>
	<a id="modalWindowAction" class="nyroModal" href="#" target="_blank" style="display: none" title="新增">触发新增操作</a>
	<script language=javascript>
Calendar.setup(
{
     inputField  : "startDate",         // ID of the input field
     displayArea : "startDate",
     ifFormat    : "%Y-%m-%d",    // the date format
     daFormat    : "%Y-%m-%d",
     button      : "startDateBtn"
});
//当前登录用户名
var loginName = '${sessionScope.loginToken.sysLogin.loginName}';
//表单验证
$(function(){
	$('#mainForm').validate({
		 submitHandler: function(form) { 
			 $('#endTimeError').html('');
			 $('#screenError').html('');
			if($('#screenFlagYes').attr('checked')=='checked' & $('#screenFilePath').val()=='')
			{
				$('#screenError').html('尚未配置显示屏显示内容！');
				return false;
			}
			var startTime = $('#startTimeHour').val()+$('#startTimeMin').val();
			var endTime = $('#endTimeHour').val()+$('#endTimeMin').val();
			if(startTime>endTime)
			{
				$('#endTimeError').html('结束时间不能小于开始时间！');
				return false;
			}
			
		 	$(form).ajaxSubmit({
				dataType:  'json',
				success:   saveMeeting		
		 	});
		 }
	});
});

$(document).ready(function(){
	init();
	$('.nyroModal').nyroModal();
});

//会议保存
function saveMeeting(data) {
    if(data.messageType=='1')
    {
    	alert(data.promptInfo);
    	window.location='${ctx}/meetingMgr?viewDate=${param.viewDate}&viewMode=${param.viewMode}';
    }
    else
    {
    	alert(data.promptInfo);
    }
}
//会议取消
function cancelMeeting(meetingId)
{
	if(confirm('您确定需要取消该次会议？'))
	{
		$.getJSON("${ctx}/meetingMgr/cancel/"+meetingId+"?r="+Math.random(), function(data){
			if(data.messageType=='1')
		    {
				alert(data.promptInfo);
				window.location='${ctx}/meetingMgr?viewDate=${param.viewDate}&viewMode=${param.viewMode}';
		    }
		    else
		    {
		    	alert(data.promptInfo);
		    }
			});
	}
}
//是否会议通知
function noticeFlagChange(noticeFlag)
{
	if(noticeFlag=='0')
	{
		$('#noticeContentTr').hide();
	}
	if(noticeFlag=='1')
	{
		$('#noticeContentTr').show();
	}
}
//是否启动场景
function sceneFlagChange(sceneFlag)
{
	if(sceneFlag=='0')
	{
		$('#sceneTr1').hide();
		$('#sceneTr2').hide();
	}
	if(sceneFlag=='1')
	{
		$('#sceneTr1').show();
		$('#sceneTr2').show();
	}
}
//是否启动显示屏
function screenFlagChange(screenFlag)
{
	if(screenFlag=='0')
	{
		$('#screenTr1').hide();
		$('#screenTr2').hide();
		$('#screenTr3').hide();
	}
	if(screenFlag=='1')
	{
		$('#screenTr1').show();
		$('#screenTr2').show();
		$('#screenTr3').show();
	}
}

//页面弹出
function showModalPage(url,title)
{
	$('#modalWindowAction').attr("href",url);
	$('#modalWindowAction').attr("title",title);
  	$('#modalWindowAction').click();
}

//显示屏配置
function screenConfig()
{
	var roomId = $('#roomId').val();
	if(roomId=='')
	{
		alert('请先选择会议地点');
		return;
	}
	var url = "${ctx }/roomMonitor/viewScreen/"+roomId+"?r="+Math.random()+"&fileName=";
	if($('#screenFilePath').val()!='')
	{
		url+=($('#screenFilePath').val());
	}
	else
	{
		var fileName = new Date().format("yyyy-MM-dd").replaceAll("-","")+new Date().format("HH:mm:ss").replaceAll(":","");
		url+=fileName+".txt";
	}
	showModalPage(url,'显示屏配置');
}
//显示屏设置回调函数
function setScreenFileName(fileName)
{
	$('#screenFilePath').val(fileName);
}

//会议室变更
function roomChange()
{
	$("select[id^='sceneId_']").each(
	  function(){
	    $(this).hide();
	    $(this).attr("disabled","disabled");
	 });
	var roomId = $('#roomId').val();
	if(roomId!='')
	{
		$('#sceneId_'+roomId).show();
		$('#sceneId_'+roomId).removeAttr("disabled");
	}
}

function init()
{
	noticeFlagChange("${resultObject.noticeFlag}");
	sceneFlagChange("${resultObject.sceneFlag}");
	screenFlagChange("${resultObject.screenFlag}");
	roomChange();
	setCheckboxCheckedValue('screenRoomIds','${resultObject.screenIds}');
	$('#sceneId_${resultObject.roomId}').val('${resultObject.sceneId}');
}
</script>
</body>
</html>
