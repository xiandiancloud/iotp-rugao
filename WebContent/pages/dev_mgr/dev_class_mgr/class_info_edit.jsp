<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/common/header.jsp"%>
		<%@ include file="/plugins/jquery-validation.jsp"%>
	</head>
	<body>
		<div class="mianbaoxie">
			当前位置：<a href="#1">设备管理</a> > <a href="${ctx}/devClassGInfoMgr">设备类型管理</a>  > 编辑
		</div>
		<form:form id="mainForm" name="mainForm" method="POST" action="${ctx}/devClassInfoMgr/update"  modelAttribute="resultObject">
		<div class="tab_search" style="margin-top: 20px;">
		<div class="table_div">
			<table width="100%" class="table_border">
				<tr>
					<td class="title" width="100"></td>
					<td align="left">带<font color="red">&nbsp;*&nbsp;</font>为必填项</td>
				</tr>
				<tr>
					<td class="title" width="100">
						<font color="red">*&nbsp;</font>所属分组
					</td>
					<td>
						<input type="hidden" name="groupSize" id="groupSize" value="${groupListSize}"/>
						<select id="groupId" cssClass="form_input {required:true}" name="groupId">
							<c:forEach items="${groupList}" var="group">
								<option value="${group.groupId}" <c:if test="${group.groupId == resultObject.devClassGroupInfo.groupId}">selected="selected"</c:if>><c:out value="${group.groupName}"/></option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="title" width="100">
						<font color="red">*&nbsp;</font>类型编号
					</td>
					<td>
						<form:hidden path="devClassId"/>
						<input name="devClassId" class="form_input {required:true,maxlength:10,digits:true}" disabled="false" value="${resultObject.devClassId}"/>
					</td>
				</tr>
				<tr>
					<td class="title" width="100">
						<font color="red">*&nbsp;</font>类型名称
					</td>
					<td>
						<form:input path="className" cssClass="form_input {required:true,maxlength:40}" size="25"/>
					</td>
				</tr>
				<tr>
					<td class="title" width="100">
						开启状态样式
					</td>
					<td>
						<form:input path="openClass" cssClass="form_input {maxlength:50}" size="35"/>
					</td>
				</tr>
				<tr>
					<td class="title" width="100">
						关闭状态样式
					</td>
					<td>
						<form:input path="closeClass" cssClass="form_input {maxlength:50}" size="35"/>
					</td>
				</tr>
				<tr>
					<td class="title" width="100">
						告警状态样式
					</td>
					<td>
						<form:input path="alarmClass" cssClass="form_input {maxlength:50}" size="35"/>
					</td>
				</tr>
				<tr>
					<td class="title" width="100">
						无效状态样式
					</td>
					<td>
						<form:input path="invalidClass" cssClass="form_input {maxlength:50}" size="35"/>
					</td>
				</tr>
				<tr>
					<td class="title" width="100">
						类型描述
					</td>
					<td colspan="3">
						<form:textarea path="classDesc" cols="70" rows="4" cssClass="form_input {maxlength:70}"/>
					</td>
				</tr>
				<tr>
					<td align="center" valign="middle" colspan="2">
						<input type="submit" class="form_botton" value="提  交" />&nbsp;&nbsp;
						<input type="button" class="form_botton" value="返  回" onclick="window.location='${ctx}/devClassInfoMgr'" />
					</td>					
				</tr>
			</table>	
			</div>
			</div>
		</form:form>
	</body>
	<script>
	function init(){
	}
	init();
	$(function(){
		$('#mainForm').validate({
			submitHandler: function(form) {
				var groupSize = $("#groupSize").val();
				if(groupSize == 0)
				{
					alert("没有可选择的设备类型分组，不能创建设备类型，请先创建设备类型分组");
					return false;
				}
			 	form.submit();
	 		}
		});
	});
	</script>
</html>