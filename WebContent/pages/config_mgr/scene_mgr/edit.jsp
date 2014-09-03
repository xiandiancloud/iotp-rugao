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
			当前位置：<a href="#1">配置管理</a> &gt; 场景配置 &gt; 场景修改
		</div>
		<form:form id="mainForm" name="mainForm" method="POST" action="${ctx}/sceneMgr/update/${resultObject.sceneId}"  modelAttribute="resultObject">
		<form:hidden path="roomId" />
		<form:hidden path="sceneStatus"/>
		<div class="tab_search" style="margin-top: 20px;">
		<div class="table_div">
			<table width="100%" class="table_border">
				<tr>
					<td class="title" width="100"></td>
					<td align="left">带<font color="red">&nbsp;*&nbsp;</font>为必填项</td>
				</tr>
				<tr>
					<td class="title" width="100">
						房间名称
					</td>
					<td>
						<input name="roomName" class="form_input" size="20" value="${roomInfo.roomName}" disabled="disabled"/>
					</td>
				</tr>
				<tr>
					<td class="title" width="100">
						<font color="red">*&nbsp;</font>场景名称
					</td>
					<td>
						<form:input path="sceneName" cssClass="form_input {required:true,maxlength:20}" size="20" maxlength="20"/>
					</td>
				</tr>
				<tr>
					<td class="title" width="100">
						<font color="red">*&nbsp;</font>场景触发方式
					</td>
					<td>
					<form:radiobutton path="sceneType" id="sceneType1" onclick="sceneTypeChange('1')" value="1" cssClass="form_input"/>手动触发 
					<form:radiobutton path="sceneType" id="sceneType2" onclick="sceneTypeChange('2')" value="2" cssClass="form_input"/>自动触发
					</td>
				</tr>
				<tr id="sceneTimeTr" style="display:none">
					<td class="title" width="100">
						<font color="red">*&nbsp;</font>自动触发时间
					</td>
					<td>
						<form:select path="sceneStartTime" cssClass="form_input">
							<form:option value="0800">08:00</form:option>
							<form:option value="0830">08:30</form:option>
							<form:option value="0900">09:00</form:option>
							<form:option value="0930">09:30</form:option>
							<form:option value="1000">10:00</form:option>
							<form:option value="1030">10:30</form:option>
							<form:option value="1100">11:00</form:option>
							<form:option value="1130">11:30</form:option>
							<form:option value="1200">12:00</form:option>
							<form:option value="1230">12:30</form:option>
							<form:option value="1300">13:00</form:option>
							<form:option value="1330">13:30</form:option>
							<form:option value="1400">14:00</form:option>
							<form:option value="1430">14:30</form:option>
							<form:option value="1500">15:00</form:option>
							<form:option value="1530">15:30</form:option>
							<form:option value="1600">16:00</form:option>
							<form:option value="1630">16:30</form:option>
							<form:option value="1700">17:00</form:option>
							<form:option value="1730">17:30</form:option>
							<form:option value="1800">18:00</form:option>
							<form:option value="1830">18:30</form:option>
							<form:option value="1900">19:00</form:option>
							<form:option value="1930">19:30</form:option>
						</form:select>&nbsp;&nbsp;-&nbsp;
						<form:select path="sceneEndTime" cssClass="form_input">
							<form:option value="0830">08:30</form:option>
							<form:option value="0900">09:00</form:option>
							<form:option value="0930">09:30</form:option>
							<form:option value="1000">10:00</form:option>
							<form:option value="1030">10:30</form:option>
							<form:option value="1100">11:00</form:option>
							<form:option value="1130">11:30</form:option>
							<form:option value="1200">12:00</form:option>
							<form:option value="1230">12:30</form:option>
							<form:option value="1300">13:00</form:option>
							<form:option value="1330">13:30</form:option>
							<form:option value="1400">14:00</form:option>
							<form:option value="1430">14:30</form:option>
							<form:option value="1500">15:00</form:option>
							<form:option value="1530">15:30</form:option>
							<form:option value="1600">16:00</form:option>
							<form:option value="1630">16:30</form:option>
							<form:option value="1700">17:00</form:option>
							<form:option value="1730">17:30</form:option>
							<form:option value="1800">18:00</form:option>
							<form:option value="1830">18:30</form:option>
							<form:option value="1900">19:00</form:option>
							<form:option value="1930">19:30</form:option>
							<form:option value="2000">20:00</form:option>
						</form:select>
					</td>
				</tr>
				<tr id="triggerTr" style="display:none">
					<td class="title" width="100">
						<font color="red">*&nbsp;</font>触发条件
					</td>
					<td>
						<form:select path="triggerType" cssClass="form_input" onchange="triggerTypeChange($('#triggerType').val())">
							<form:option value="0">----</form:option>
							<form:option value="1">温度</form:option>
							<form:option value="2">湿度</form:option>
							<form:option value="3">光照</form:option>
						</form:select>
						<form:select path="triggerCondition" cssClass="form_input" style="display:none">
							<form:option value="0">----</form:option>
							<form:option value="1">大于</form:option>
							<form:option value="2">大于等于</form:option>
							<form:option value="3">小于</form:option>
							<form:option value="4">小于等于</form:option>
						</form:select>
						<form:input path="triggerValue" cssClass="form_input {required:'#sceneType2:checked'}" size="6" maxlength="6" style="display:none"/><label id="unit">度</label>
					</td>
				</tr>
				<tr id="validDateTr" style="display:none">
					<td class="title" width="100">
						<font color="red">*&nbsp;</font>场景有效期
					</td>
					<td>
						<fmt:parseDate pattern="yyyyMMdd" var="parsedValidStartDate" parseLocale="en_US">
							${resultObject.validStartDate}
						</fmt:parseDate>
						<fmt:formatDate value='${parsedValidStartDate}' pattern="yyyy-MM-dd" var="formatValidStartDate"/>
						<fmt:parseDate pattern="yyyyMMdd" var="parsedValidEndDate" parseLocale="en_US">
							${resultObject.validEndDate}
						</fmt:parseDate>
						<fmt:formatDate value='${parsedValidEndDate}' pattern="yyyy-MM-dd" var="formatValidEndtDate"/>
						<input name="_validStartDate" id="_validStartDate" type="text" class="form_input {required:'#sceneType2:checked'}"  style="cursor: pointer;width:80px"  value="${formatValidStartDate }"/>&nbsp;&nbsp;-&nbsp;
						<input name="_validEndDate" id="_validEndDate" type="text" class="form_input {required:'#sceneType2:checked'}"  style="cursor: pointer;width:80px" value="${formatValidEndtDate}" />
						<div style="display: none">
							<textarea rows="1" cols="12" name="validStartDate" id="validStartDate">${resultObject.validStartDate}</textarea>
							<textarea rows="1" cols="12" name="validEndDate" id="validEndDate">${resultObject.validEndDate}</textarea>
						</div>
					</td>
				</tr>
				<tr>
					<td class="title" width="100">
						场景描述
					</td>
					<td>
						<form:textarea path="sceneDesc" rows="3" cssClass="form_input {maxlength:100}" cols="70"/>
					</td>
				</tr>
				<tr>
					<td class="title" width="100">
						设备设置
					</td>
					<td>
						<table width="100%" class="table_border">
							<tr>
								<td class="table_list_title title_border_a" width="5%">序号</td>
								<td class="table_list_title title_border_a">设备名称</td>
								<td class="table_list_title title_border_a">设备类型</td>
								<td class="table_list_title title_border_a" width="10%">是否启用</td>
								<td class="table_list_title title_border_a">设置</td>
							</tr>
							<c:if test="${page.totalCount != '0'}">
								<c:forEach var="item" items="${devList}" varStatus="status">
									<tr style="text-align: center">
										<td>${status.count}</td>
										<td><a href="${ctx }/sceneMgr/viewDev/${roomInfo.roomId}/${item.devId}?r=<%=Math.random() %>" title="设备位置查看" class="nyroModal">${item.devName}</a></td>
										<td>${item.devClassInfo.className}</td>
										<td>
											<select name="enable-${item.devId}" id="enable-${item.devId}" onchange="changeEnable('${item.devId}')">
												<option value="0">不启用</option>
												<option value="1">启用</option>
											</select>
										</td>
										<td align="left">
											<select name="configs-switch-${item.devId}" id="configs-switch-${item.devId}" disabled="disabled" onchange="changeSwitch('${item.devId}')">
												<option value="1">开启</option>
												<option value="0">关闭</option>
											</select>
											<c:if test="${item.devClassInfo.devClassGroupInfo.groupId=='2' && item.devStatus!='2'}">
												<select id="configs-air_mode-${item.devId}" name="configs-air_mode-${item.devId}" disabled="disabled">
													<option value="1">制冷</option>
													<option value="2">制热</option>
													<option value="3">送风</option>
												</select>
												<select id="configs-air_temp-${item.devId}" name="configs-air_temp-${item.devId}" disabled="disabled">
													<option value="16">16度</option>
													<option value="17">17度</option>
													<option value="18">18度</option>
													<option value="19">19度</option>
													<option value="20">20度</option>
													<option value="21">21度</option>
													<option value="22">22度</option>
													<option value="23">23度</option>
													<option value="24">24度</option>
													<option value="25">25度</option>
													<option value="26">26度</option>
													<option value="27">27度</option>
													<option value="28">28度</option>
													<option value="29">29度</option>
													<option value="30">30度</option>
													<option value="31">31度</option>
													<option value="32">32度</option>
													<option value="32">33度</option>
												</select>
												<select id="configs-air_wind-${item.devId}" name="configs-air_wind-${item.devId}" disabled="disabled">
													<option value="1">大风</option>
													<option value="2">小风</option>
												</select>
											</c:if>
										</td>
									</tr>
								</c:forEach>
							</c:if>
							<c:if test="${empty devList}">
								<tr align="center">
									<td colspan="7" align="left">
										暂无符合条件的记录
									</td>
								</tr>
							</c:if>
						</table>
					</td>
				</tr>
				<tr>
					<td align="center" valign="middle" colspan="2">
						<input type="submit" class="form_botton" value="提  交" />&nbsp;&nbsp;
						<input type="button" class="form_botton" value="返  回" onclick="window.location='${ctx}/sceneMgr/${roomInfo.roomId}'" />
					</td>					
				</tr>
			</table>	
			</div>
			</div>
		</form:form>
		<script>
	Calendar.setup(
	{
      inputField  : "_validStartDate",         // ID of the input field
      displayArea : "validStartDate",
      ifFormat    : "%Y-%m-%d",    // the date format
      daFormat    : "%Y%m%d",
      button      : "_validStartDate"       // ID of the button
	});
	Calendar.setup(
	{
      inputField  : "_validEndDate",         // ID of the input field
      displayArea : "validEndDate",
      ifFormat    : "%Y-%m-%d",    // the date format
      daFormat    : "%Y%m%d",
      button      : "_validEndDate"       // ID of the button
	});
	$(function(){
		$('.nyroModal').nyroModal();
		$('#mainForm').validate();
	});
	
	function triggerTypeChange(triggerType)
	{
		if(triggerType=='0')
		{
			$('#triggerCondition').hide();
			$('#triggerValue').hide();
			$('#unit').text("");
		}
		else
		{
			$('#triggerCondition').show();
			$('#triggerValue').show();	
		}
		if(triggerType=='1')
			$('#unit').text("度");
		if(triggerType=='2')
			$('#unit').text("%RH");
		if(triggerType=='3')
			$('#unit').text("lx");
	}
	
	function sceneTypeChange(sceneType)
	{
		if(sceneType=='1')
		{
			$('#sceneTimeTr').hide();
			$('#triggerTr').hide();
			$('#validDateTr').hide();
		}
		if(sceneType=='2')
		{
			$('#sceneTimeTr').show();
			$('#triggerTr').show();
			$('#validDateTr').show();
		}
	}
	
	//初始化方法
	function init()
	{
		var sceneType = '${resultObject.sceneType}';
		sceneTypeChange(sceneType);
		var triggerType = '${resultObject.triggerType}';
		triggerTypeChange(triggerType);
	}
	init();
	//是否启用
	function changeEnable(devId)
	{
		var enable = $("#enable-"+devId).val();
		enableChange(devId,enable);
	}
	function enableChange(devId,enable)
	{
		if(enable==1)
		{
			$("#configs-switch-"+devId).removeAttr("disabled");
			$("#configs-air_mode-"+devId).removeAttr("disabled");
			$("#configs-air_temp-"+devId).removeAttr("disabled");
			$("#configs-air_wind-"+devId).removeAttr("disabled");
		}
		else
		{
			$("#configs-switch-"+devId).attr("disabled","disabled");
			$("#configs-air_mode-"+devId).attr("disabled","disabled");
			$("#configs-air_temp-"+devId).attr("disabled","disabled");
			$("#configs-air_wind-"+devId).attr("disabled","disabled");
		}
		$("#enable-"+devId).val(enable);
	}
	
	//是否开启设备
	function switchChange(devId,switchFlag)
	{
		if(switchFlag==1)
		{
			$("#configs-air_mode-"+devId).removeAttr("disabled");
			$("#configs-air_temp-"+devId).removeAttr("disabled");
			$("#configs-air_wind-"+devId).removeAttr("disabled");
		}
		else
		{
			$("#configs-air_mode-"+devId).attr("disabled","disabled");
			$("#configs-air_temp-"+devId).attr("disabled","disabled");
			$("#configs-air_wind-"+devId).attr("disabled","disabled");
		}
	}
	function changeSwitch(devId){
		var switchFlag = $("#configs-switch-"+devId).val();
		switchChange(devId,switchFlag);
	}
	</script>
		<c:forEach var="configInfo" items="${resultObject.sceneConfigInfos}" varStatus="status">
			<script>
				var devId = '${configInfo.devId}';
				var attrKey = '${configInfo.attrKey}';
				var attrValue = '${configInfo.attrValue}';
				enableChange(devId,'1');
				if(attrKey=='switch')
				{
					switchChange(devId,attrValue);
				}
				$("#configs-"+attrKey+"-"+devId).val(attrValue);
			</script>
		</c:forEach>
	</body>
	
</html>