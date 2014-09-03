<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/header.jsp"%>
<%@ include file="/plugins/jquery-validation.jsp"%>
</head>
<body>
	<form:form id="mainForm" name="mainForm" action="${ctx}/devMgr/update" method="post"  modelAttribute="resultObject">
	<div class="tab_search" style="margin-top: 20px;">
		<div class="table_div">
			<form:hidden path="devId"/>
			<form:hidden path="devStatus"/>
			<form:hidden path="alarmStatus"/>
			<form:hidden path="positionX"/>
			<form:hidden path="positionY"/>
			<form:hidden path="roomId"/>
			<table width="100%" class="table_border">
				<tr>
					<td class="title" width="100"><font color="red">*</font>设备类型：</td>
					<td  width="300px">
						<form:select path="devClassInfo.devClassId" cssClass="form_input required">
							<form:option value="">--请选择--</form:option>
							<form:options items="${devClassList}" itemLabel="className" itemValue="devClassId" />
						</form:select>
					</td>
				</tr>
				<tr>
					<td class="title" width="100"><font color="red">*</font>网络地址：</td>
					<td  width="300px">
						<form:input path="networkAddr" cssClass="form_input {required:true}" maxlength="40"/>
					</td>
				</tr>
				<tr>
					<td class="title" width="100"><font color="red">*</font>物理地址：</td>
					<td  width="300px">
						<form:input path="macAddr" cssClass="form_input required" maxlength="40"/>
					</td>
				</tr>
				<tr>
					<td class="title" width="100"><font color="red">*</font>设备名称：</td>
					<td  width="300px">
						<form:input path="devName" cssClass="form_input required" maxlength="40"/>
					</td>
				</tr>
				<tr>
					<td class="title">设备描述</td>
					<td ><form:textarea path="devDesc" cols="70" rows="4" cssClass="form_input"/></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
					<input  type="submit" value="确定" class="form_botton"/>&nbsp;&nbsp;&nbsp;&nbsp;
					<input id="resetBtn"  type="reset" value="重置" class="form_botton"  />
					</td>
				</tr>
			</table>
		</div>
	</div>
	</form:form>
</body>

<script>
	$(function(){
		$('#mainForm').validate();
		
		$('#mainForm').ajaxForm({
			 dataType:  'json',
		     success:   onSuccess
		});
	});
	
	function onSuccess(data) {
	    if(data.messageType=='1')
	    {
	    	alert(data.promptInfo);
	    	parent.closeModalWindow();
	    }
	    else
	    {
	    	alert(data.promptInfo);
	    }
	}
</script>
</html>
