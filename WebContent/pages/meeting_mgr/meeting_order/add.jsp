<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/header.jsp"%>
<%@ include file="/plugins/jquery-validation.jsp"%>
<%@ include file="/plugins/calendar.jsp"%>
<%@ include file="/plugins/ztree.jsp"%>

</head>
<body>
	<div class="mianbaoxie">
		当前位置：<a href="#1">会议室预定管理</a> > 会议室预定 > 新建会议
	</div>
	<div class="tab_search" style="margin-top: 20px;">
		<div class="table_div" style="overflow: auto;">
			<form:form id="mainForm" name="mainForm" action="${ctx}/meetingMgr/save" method="post"  modelAttribute="resultObject">
			<table width="100%" class="table_border">
				<tr>
					<td class="title" width="100"><font color="red">*</font>会议名称：</td>
					<td  width="300px">
						<form:input path="meetingName" cssClass="form_input required" maxlength="20"/></td>
					<td class="title" width="100"><font color="red">*</font>会议地点：</td>
					<td width="300px">
						<form:select path="mapRoomInfo.roomId" items="${roomList}" itemLabel="roomName" itemValue="roomId" cssClass="required"/>
					</td>
				</tr>
				<tr>
					<td class="title"><font color="red">*</font>会议日期：</td>
					<td>
						<fmt:parseDate pattern="yyyy-MM-dd" var="_startDateVal" parseLocale="en_US">${meetingDate}</fmt:parseDate>
						<input type="text" name="_startDate" id="_startDate" value="${meetingDate}" class="form_input greatThanEqualToday" size="20" readonly style="cursor: pointer;"/>
						<div style="display: none">
							<textarea rows="1" cols="12" name="startDate" id="startDate" readonly="readonly"><fmt:formatDate value='${_startDateVal}' pattern='yyyyMMdd'/></textarea>
						</div>
					</td>
					<td class="title" width="100"><font color="red">*</font>会议时间：</td>
					<td>
						<form:select path="startTime" cssClass="form_input" cssStyle="width:80px">
							<option value="0800">08:00</option>
							<option value="0830">08:30</option>
							<option value="0900">09:00</option>
							<option value="0930">09:30</option>
							<option value="1000">10:00</option>
							<option value="1030">10:30</option>
							<option value="1100">11:00</option>
							<option value="1130">11:30</option>
							<option value="1200">12:00</option>
							<option value="1230">12:30</option>
							<option value="1300">13:00</option>
							<option value="1330">13:30</option>
							<option value="1400">14:00</option>
							<option value="1430">14:30</option>
							<option value="1500">15:00</option>
							<option value="1530">15:30</option>
							<option value="1600">16:00</option>
							<option value="1630">16:30</option>
							<option value="1700">17:00</option>
							<option value="1730">17:30</option>
							<option value="1800">18:00</option>
							<option value="1830">18:30</option>
							<option value="1900">19:00</option>
							<option value="1930">19:30</option>
							<option value="2000">20:00</option>
							<option value="2030">20:30</option>
						</form:select>
						~
						<form:select path="endTime" cssClass="form_input {greatThan:'#startTime'}" cssStyle="width:80px" >
							<option value="0800">08:00</option>
							<option value="0830">08:30</option>
							<option value="0900">09:00</option>
							<option value="0930">09:30</option>
							<option value="1000">10:00</option>
							<option value="1030">10:30</option>
							<option value="1100">11:00</option>
							<option value="1130">11:30</option>
							<option value="1200">12:00</option>
							<option value="1230">12:30</option>
							<option value="1300">13:00</option>
							<option value="1330">13:30</option>
							<option value="1400">14:00</option>
							<option value="1430">14:30</option>
							<option value="1500">15:00</option>
							<option value="1530">15:30</option>
							<option value="1600">16:00</option>
							<option value="1630">16:30</option>
							<option value="1700">17:00</option>
							<option value="1730">17:30</option>
							<option value="1800">18:00</option>
							<option value="1830">18:30</option>
							<option value="1900">19:00</option>
							<option value="1930">19:30</option>
							<option value="2000">20:00</option>
							<option value="2030">20:30</option>
							<option value="2100">21:00</option>
						</form:select>
					</td>
				</tr>
				<tr>
					<td class="title">会议描述</td>
					<td colspan="3"><form:textarea path="meetingDesc" cols="70" rows="4" cssClass="form_input"/></td>
				</tr>
				<tr>
					<td class="title"><font color="red">*</font>参与人员：</td>
					<td width="300px">
					<form:hidden path="targetUserIds"/> 
					<div >
						<ul id="treeDemo" class="ztree"></ul>
					</div>
					</td>
					<td colspan="2" valign="bottom">
						<label id="treeError" style="color: red;font-style: italic"></label>
					</td>
				</tr>
				<tr>
					<td class="title">会议通知</td>
					<td colspan="3"><form:radiobutton path="noticeFlag" onclick="$('#noticeContentTr').hide()" value="0" />否 <form:radiobutton path="noticeFlag"  onclick="$('#noticeContentTr').show()" value="1"/>是</td>
				</tr>
				<tr id="noticeContentTr" style="display:none">
					<td class="title"><font color="red">*</font>会议通知内容</td>
					<td colspan="3"><form:textarea path="noticeContent" cols="65" rows="4" class="form_input {required:'#noticeFlagYes:checked',maxlength:70}"/></td>
				</tr>
				<tr>
					<td colspan="4" align="center">
					<input  type="submit" value="确定" class="form_botton"/>&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="" type="button" value="返回" class="form_botton"  onclick="window.location='${ctx}/meetingMgr?meetingDate=${meetingDate}'"  />
					</td>
				</tr>
			</table>
			</form:form>
		</div>
	</div>

</body>

<script>
	$(function(){
		//$('#roomId').val('${roomId}');	
		$('#startTime').val('${startTime}');
		$('#endTime').val('${endTime}');
		$('#mainForm').validate({
			 submitHandler: function(form) {
				 var treeObj = $.fn.zTree.getZTreeObj("treeDemo");  
					var nodes = treeObj.getCheckedNodes(true);  
					var checkIds = "";  
					if (nodes.length<=0) {
						$('#treeError').html('请选择参与人员');
						$('#treeDemo').attr("style","border: 1px dotted red;");
						return false;
					}
					for (var i = 0; i < nodes.length; i++) {  
						if(nodes[i].isParent==false){ 
						    if (checkIds != '')
						    	checkIds += ',';  
						    checkIds += nodes[i].id;  
						}
					}
					$("#targetUserIds").val(checkIds);
				 	form.submit();
			 }
		});
	});
		Calendar.setup(
		{
			inputField  : "_startDate",         // ID of the input field
		    displayArea : "startDate",
		    ifFormat    : "%Y-%m-%d",    // the date format
		    daFormat    : "%Y%m%d",
		    button      : "_startDate"       // ID of the button
		});
		var setting = {
				check: {
					enable: true
				},
				data: {
					simpleData: {
						enable: true
					}
				},
				callback: {
					onCheck: treeOnCheck
				}
		};
		var zNodes = ${tree.json};  

 		$(document).ready(function(){
 			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
 		});
 		

//新建会议预订验证
function validateForm(theForm){
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");  
	var nodes = treeObj.getCheckedNodes(true);  
	var checkIds = "";  
	if (nodes.length<=0) {
		$('#treeError').html('请选择参与人员');
		$('#treeDemo').attr("style","border: 1px dotted red;");
		return false;
	}
	for (var i = 0; i < nodes.length; i++) {  
		if(nodes[i].isParent==false){ 
		    if (checkIds != '')
		    	checkIds += ',';  
		    checkIds += nodes[i].id;  
		}
	}
	$("#targetUserIds").val(checkIds);
	return true;
}
//树勾选事件
function treeOnCheck(e, treeId, treeNode) {
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");  
	var nodes = treeObj.getCheckedNodes(true);  
	if (nodes.length>0) {
		$('#treeError').html("");
		$('#treeDemo').attr("style","");
	}
}
</script>
</html>
