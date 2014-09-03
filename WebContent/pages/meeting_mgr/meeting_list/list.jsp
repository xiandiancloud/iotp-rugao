<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/header.jsp"%>
<%@ include file="/plugins/calendar.jsp"%>
<%@ include file="/plugins/tablesorter.jsp"%>
</head>
<body>
	<div class="mianbaoxie">
		当前位置：<a href="#1">会议室预定管理</a> > 已预定会议室
	</div>
<form id="mainForm" action="${ctx}/meetingMgr/list"  method="post" >
	<input type="hidden" name="pageNo" id="pageNo" value="${page.pageNo}" />
	<input type="hidden" name="pageSize" id="pageSize" value="${page.pageSize}" />
	<input type="hidden" name="orderBy" id="orderBy" value="${page.orderBy}" />
	<input type="hidden" name="order" id="order" value="${page.order}" />
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
		           <td class="title" width="100">会议名称：</td>
		           <td><input name="filter_LIKE_meetingName"  id="meetingName" type="text" class="form_input" style="width:130px;" /></td>
		           <td class="title">会议地点：</td>
		           <td>
		           	<select name="filter_EQ_mapRoomInfo.roomId"  id="roomId" style="width:150px" class="form_input">
		           		<option value="">--全部--</option>
			          	<c:forEach var="roomList" items="${roomList}">
							<option value="${roomList.roomId}">${roomList.roomName}</option>
						</c:forEach>
					</select>
		           </td>
	          </tr>
	          <tr>
		           <td class="title">会议时间：</td>
		           <td>
		           		<input name="_startDate" id="_startDate" type="text" class="form_input"  style="width:130px;cursor: pointer;"  /> 
						<div style="display: none">
							<textarea rows="1" cols="12" name="filter_EQ_startDate" id="filter_EQ_startDate"></textarea>
						</div>
		           </td>
		           <td class="title">会议状态：</td>
		           <td><select name="filter_EQ_meetingStatus" id="meetingStatus" class="form_input" style="width:150px;">
		           		<option value="">--全部--</option>
		           		<option value="1">正常</option>
		           		<option value="0">取消</option>
		           		<option value="2">结束</option>
		           </select></td>
	          </tr>
	          <tr>
	             <td colspan="4" align="center">
	             	<input name="sub" type="submit" value="查询" class="form_botton" />&nbsp;&nbsp;&nbsp;&nbsp;
	             	<input type="reset" value="重置" class="form_botton" onclick="resetForm()"/>
	             </td>
	          </tr>
	       </table>
	    </div>
   </div>
	<div class="table_list table_div" style="margin-top: 20px;">
		<table width="100%" class="table_border">
			<tr>
				<td class="table_list_title title_border_a" onclick="javascript:sort('meetingName','asc')"><div class="paixu_3">会议名称</div></td>
				<td class="table_list_title title_border_a" onclick="javascript:sort('mapRoomInfo','asc')"><div class="paixu_3">会议地点</div></td>
				<td class="table_list_title title_border_a" onclick="javascript:sort('startDate','asc')"><div class="paixu_3">会议日期</div></td>
				<td class="table_list_title title_border_a" onclick="javascript:sort('startTime','asc')"><div class="paixu_3">开始时间</div></td>
				<td class="table_list_title title_border_a" onclick="javascript:sort('endTime','asc')"><div class="paixu_3">结束时间</div></td>
				<td class="table_list_title title_border_a" onclick="javascript:sort('meetingStatus','asc')"><div class="paixu_3">会议状态</div></td>
			</tr>
			<c:if test="${page.totalCount != '0'}">
				<c:forEach var="item" items="${page.result}" varStatus="status">
					<tr style="text-align: center">
						<td>${item.meetingName}</td>
						<td>${item.mapRoomInfo.roomName}</td>
						<td>
						<fmt:parseDate pattern="yyyyMMdd" var="parsedDateTime" parseLocale="en_US">
							<c:out value="${item.startDate}" />
							</fmt:parseDate>
							<fmt:formatDate value='${parsedDateTime}' pattern="yyyy-MM-dd"/>
						</td>
						
						<td>
						<fmt:parseDate pattern="HHmm" var="parsedDateTime" parseLocale="en_US">
							<c:out value="${item.startTime}" />
							</fmt:parseDate>
							<fmt:formatDate value='${parsedDateTime}' pattern="HH:mm"/>
						</td>
						<td>
						<fmt:parseDate pattern="HHmm" var="parsedDateTime" parseLocale="en_US">
							<c:out value="${item.endTime}" />
							</fmt:parseDate>
							<fmt:formatDate value='${parsedDateTime}' pattern="HH:mm"/>
						</td>
						<td>
							<c:if test="${item.meetingStatus==1}">正常</c:if>
							<c:if test="${item.meetingStatus==0}">取消</c:if>
							<c:if test="${item.meetingStatus==2}">结束</c:if>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${page.totalCount == '0' || empty page}">
				<tr align="center">
					<td colspan="8" align="left">
						暂无符合条件的记录
					</td>
				</tr>
			</c:if>
		</table>
	</div>
	<%@ include file="/plugins/pager.jsp"%>
	</form>
</body>
<script>
	Calendar.setup(
		{
	      inputField  : "_startDate",         // ID of the input field
	      displayArea : "filter_EQ_startDate",
	      ifFormat    : "%Y-%m-%d",    // the date format
	      daFormat    : "%Y%m%d",
	      button      : "_startDate"       // ID of the button
		});
	
	//页面加载初始化方法，实现查询条件下拉列表的回显功能
	function init()
	{
		var roomId = "${param['filter_EQ_mapRoomInfo.roomId']}";
		$("#roomId").val(roomId);	
		var status = "${param['filter_EQ_meetingStatus']}";
		$("#meetingStatus").val(status);
		$("#meetingName").val("${param['filter_LIKE_meetingName']}");
		$("#_startDate").val("${param['_startDate']}");
		$("#filter_EQ_startDate").val("${param['filter_EQ_startDate']}");
		
	}
	init();
	
	function resetForm()
	{
		$("#roomId").val("");	
		$("#meetingStatus").val("");
		$("#meetingName").val("");
		$("#filter_EQ_startDate").val("");
		$("#_startDate").val("");
	}
	
</script>
</html>
