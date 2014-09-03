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
			当前位置：<a href="#1">设备管理</a> > <a href="${ctx}/devClassInfoMgr">设备类型管理</a>> 属性管理 >编辑
		</div>
		<form:form id="mainForm" name="mainForm" method="POST" action="${ctx}/devAttributesMgr/update"  modelAttribute="resultObject">
		<div class="tab_search" style="margin-top: 20px;">
		<div class="table_div">
			<table width="100%" class="table_border">
				<tr>
					<td class="title" width="100"></td>
					<td align="left">带<font color="red">&nbsp;*&nbsp;</font>为必填项</td>
					<input type="hidden" name="devClassId" value="${resultObject.devClassInfo.devClassId}"/>
				</tr>
				<tr>
					<td class="title" width="100">
						<font color="red">*&nbsp;</font>属性编码
					</td>
					<td>
						<form:hidden path="attrKey"/>
						<input name="attrKey" class="form_input {required:true,maxlength:25,alphanumeric:true}" disabled="false" value="${resultObject.attrKey}"/>
					</td>
				</tr>
				<tr>
					<td class="title" width="100">
						<font color="red">*&nbsp;</font>属性名称
					</td>
					<td>
						<form:input path="attrName" cssClass="form_input {required:true,maxlength:25}" size="20"/>
					</td>
				</tr>
				<tr>
					<td class="title" width="100">
						属性单位
					</td>
					<td>
						<form:input path="attrMeasurement" cssClass="form_input {maxlength:20}" size="20"/>
					</td>
				</tr>
				<tr>
					<td class="title" width="100">
						<font color="red">*&nbsp;</font>是否可编辑
					</td>
					<td>
						<form:radiobutton path="allowEdit" cssClass="form_input {required:true}" value="0"/>否
						<form:radiobutton path="allowEdit" cssClass="form_input {required:true}" value="1"/>是
					</td>
				</tr>
				<tr>
					<td class="title" width="100">
						属性描述
					</td>
					<td>
						<form:textarea path="attrDesc" cols="70" rows="4" id="attrDesc" cssClass="form_input {maxlength:70}"/>
					</td>
				</tr>
				<tr>
					<td class="title" width="100">
						<font color="red">*&nbsp;</font>状态
					</td>
					<td>
						<form:select path="attrStatus" cssClass="form_input">
							<form:option value="1">有效</form:option>
							<form:option  value="0">无效</form:option>
						</form:select>
					</td>
				</tr>
				<tr>
					<td align="center" valign="middle" colspan="2">
						<input type="submit" class="form_botton" value="提  交" />&nbsp;&nbsp;
						<input type="button" class="form_botton" value="返  回" onclick="window.location='${ctx}/devClassInfoMgr/view/${resultObject.devClassInfo.devClassId}'" />
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
	});
	</script>
</html>