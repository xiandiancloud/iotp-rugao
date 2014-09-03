<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<%@ include file="/common/header.jsp"%>
		<%@ include file="/plugins/tablesorter.jsp"%>
		<%@ include file="/plugins/calendar.jsp"%>
	</head>
	<body>
	<div class="mianbaoxie">
		当前位置：<a href="#1">车辆管理</a> > 场内车辆
	</div>
	<form id="mainForm" action="${ctx}/carOfflineMgr" method="post">
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
						<td class="title" width="100">卡物理号：</td>
						<td><input type="text" name="filter_EQ_cardno" id="cardno" value="${param['filter_EQ_cardno']}" size="20" class="form_input" />
						</td>
						<td class="title" width="100">员工编号：</td>
						<td><input type="text" name="filter_EQ_empno" id="empno" value="${param['filter_EQ_empno']}" size="20" class="form_input" />
						</td>
					</tr>
					<tr>
						<td class="title" width="100">员工姓名：</td>
						<td><input type="text" name="filter_LIKE_empname" id="empname" value="${param['filter_LIKE_empname']}" size="20" class="form_input" />
						</td>
						<td class="title" width="100">车牌号码：</td>
						<td><input type="text" name="filter_EQ_carlicense" id="carlicense" value="${param['filter_EQ_carlicense']}" size="20" class="form_input" />
						</td>
					</tr>
					<tr>
						<td class="title" width="100">入控制机号：</td>
						<td><input type="text" name="filter_EQ_machnum" id="machnum" value="${param['filter_EQ_machnum']}" size="20" class="form_input" />
						</td>
						<td class="title" width="100">入场时间：</td>
						<td>
							<input name="_inDate" id="_inDate" type="text" class="form_input"  style="width:130px;cursor: pointer;"  /> 
							<div style="display: none">
								<textarea rows="1" cols="12" name="filter_LIKE_inouttime" id="filter_LIKE_inouttime"></textarea>
							</div>
						</td>
					</tr>
					<!-- 
					<tr>
						<td class="title" width="100">卡片状态：</td>
						<td>
							<select name="filter_EQ_cardstate" id="cardstate" class="form_input" style="width: 150px;">
								<option value="">-请选择-</option>
								<option>有效</option>
								<option>无效</option>
								<option>被锁</option>
								<option>未激活</option>
							</select>
						</td>
						<td class="title" width="100">卡片类型：</td>
						<td>
							<select name="filter_EQ_cardType" id="cardType" class="form_input" style="width: 150px;">
								<option value="">-请选择-</option>
								<option>A卡</option>
								<option>B卡</option>
								<option>C卡</option>
							</select>
						</td>
					</tr>
					 -->
					<tr>
						<td colspan="4" align="center"><input name="" type="button" value="查询" class="form_botton" onclick="query()"/>&nbsp;&nbsp;&nbsp;&nbsp;<input
							name="" type="button" value="重置" class="form_botton" onclick="clearForm()"/>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="table_list " style="margin-top: 20px;">
			 <input type="button" class="form_botton" value="导出Excel" onclick="javascript:exportFile()"/> 
		</div>
		<div class="table_list table_div" >
			<table id="tableList" class="table_border" width="100%">
					<tr align="center">
						<td class="table_list_title title_border_a" onclick="javascript:sort('cardno','asc')" width="5%"><div class="paixu_3">卡物理号</div></td>
						<td class="table_list_title title_border_a" onclick="javascript:sort('cardid','asc')" width="5%"><div class="paixu_3">卡片编号</div></td>
						<td class="table_list_title title_border_a" onclick="javascript:sort('cardtype','asc')" width="5%"><div class="paixu_3">卡片类型</div></td>
						<td class="table_list_title title_border_a" onclick="javascript:sort('cardstate','asc')" width="5%"><div class="paixu_3">卡片状态</div></td>
						<td class="table_list_title title_border_a" onclick="javascript:sort('empno','asc')" width="5%"><div class="paixu_3">员工编号</div></td>
						<td class="table_list_title title_border_a" onclick="javascript:sort('empname','asc')" width="5%"><div class="paixu_3">员工姓名</div></td>
						<td class="table_list_title title_border_a" onclick="javascript:sort('carlicense','asc')" width="5%"><div class="paixu_3">车牌号码</div></td>
						<td class="table_list_title title_border_a" onclick="javascript:sort('inouttime','asc')" width="5%"><div class="paixu_3">入场时间</div></td>
						<td class="table_list_title title_border_a" onclick="javascript:sort('machnum','asc')" width="5%"><div class="paixu_3">入控制机号</div></td>
						<td class="table_list_title title_border_a" onclick="javascript:sort('inoutposition','asc')" width="5%"><div class="paixu_3">入场地点</div></td>
						<td class="table_list_title title_border_a" onclick="javascript:sort('parkname','asc')" width="5%"><div class="paixu_3">停车场名称</div></td>
					</tr>
					<c:if test="${page.totalCount != '0'}">
						<c:forEach var="carOffline" items="${page.result}" varStatus="status">
							<tr align="center">		
								<td>${carOffline.cardno}</td>
								<td>${carOffline.cardid}</td>
								<td>${carOffline.cardtype}</td>
								<td>${carOffline.cardstate}</td>
								<td>${carOffline.empno}</td>
								<td>${carOffline.empname}</td>
								<td>${carOffline.carlicense}</td>
								<td>
									${carOffline.inouttime}
									<%-- <fmt:parseDate pattern="yyyyMMddHHmmss" var="inoutDateTime" parseLocale="en_US">
										${carOffline.inouttime}
									</fmt:parseDate>
									<fmt:formatDate value='${inoutDateTime}' pattern="yyyy-MM-dd HH:mm:ss"/> --%>
								</td>
								<td>${carOffline.machnum}</td>
								<td>${carOffline.inoutposition}</td>
								<td>${carOffline.parkname}</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${page.totalCount == '0' || empty page}">
						<tr align="center">
							<td colspan="11" align="left">
								暂无符合条件的记录
							</td>
						</tr>
					</c:if>
				</table>
			</div>
		</form>
		<form id="exportForm"  action="${ctx}/carOfflineMgr/exportFile" method="post" onsubmit="return false;" target="_blank">
			<input type="hidden" name="filter_EQ_cardno" value="${param['filter_EQ_cardno']}">
			<input type="hidden" name="filter_EQ_empno" value="${param['filter_EQ_empno']}">
			<input type="hidden" name="filter_LIKE_empname" value="${param['filter_LIKE_empname']}">
			<input type="hidden" name="filter_EQ_carlicense" value="${param['filter_EQ_carlicense']}">
			
			<input type="hidden" name="filter_EQ_machnum" value="${param['filter_EQ_machnum']}">
			<input type="hidden" name="filter_LIKE_inouttime" value="${param['filter_LIKE_inouttime']}">
			<input type="hidden" name="filter_EQ_cardstate" value="${cardstate}">
			<input type="hidden" name="filter_EQ_cardtype" value="${cardType}">
		</form>
		<jsp:include page="/plugins/pager.jsp" flush="true" />
	</body>
	<script type="text/javascript">
	//页面加载初始化方法，实现查询条件下拉列表的回显功能
	Calendar.setup(
			{
		      inputField  : "_inDate",         // ID of the input field
		      displayArea : "filter_LIKE_inouttime",
		      ifFormat    : "%Y-%m-%d",    // the date format
		      daFormat    : "%Y%m%d",
		      button      : "_inDate"       // ID of the button
			});

	function init()
	{
		var cardstate = "${param['filter_EQ_cardstate']}";
		$("#cardstate").val(cardstate);

		var cardtype = "${param['filter_EQ_cardtype']}";
		$("#cardtype").val(cardtype);

		$("#_inDate").val("${param['_inDate']}");
		$("#filter_LIKE_inouttime").val("${param['filter_LIKE_inouttime']}");
	}
	init();

	function query(){
		$("#mainForm").submit();
	}
	
	function clearForm(){
		$("#filter_LIKE_inouttime").val("");
		$("#_inDate").val("");
		$("#cardtype").val("");
		$("#cardstate").val("");
		$("#cardno").val("");
		$("#empno").val("");
		$("#empname").val("");
		$("#carlicense").val("");
		$("#machnum").val("");
	}

	function exportFile(){
		exportForm.submit();
	}
	</script>
</html>