<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/header.jsp"%>
<%@ include file="/plugins/calendar.jsp"%>
</head>
<body>
	<div class="mianbaoxie">
		当前位置：<a href="#1">告警管理</a> > 设备告警配置
	</div>

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
					<td class="title" width="100">设备名称：</td>
					<td><input name="" type="text" class="form_input" />
					</td>
					<td class="title">设备类型：</td>
					<td><select name="" class="form_input" style="width: 200px;">
							<option>--全部--</option>
					</select>
					</td>
				</tr>
				<tr>
					<td class="title" width="100">房间名称：</td>
					<td><input name="" type="text" class="form_input" />
					</td>
					<td class="title">所属大楼：</td>
					<td><select name="" class="form_input" style="width: 200px;">
							<option>--全部--</option>
							<option>综合楼</option>
							<option>教学楼</option>
					</select>
					</td>
				</tr>
				
				<tr>
					<td colspan="4" align="center"><input name="" type="button" value="查询" class="form_botton" />&nbsp;&nbsp;&nbsp;&nbsp;<input
						name="" type="button" value="重置" class="form_botton" />
					</td>
				</tr>
			</table>
		</div>
	</div>
	</div>


	<div class="table_list table_div" style="margin-top: 20px;">
		<table width="100%" class="table_border">
			<tr>

				<td class="table_list_title title_border_a">设备名称</td>
				<td class="table_list_title title_border_a">所属房间</td>
				<td class="table_list_title title_border_a">所属大楼</td>
				<td class="table_list_title title_border_a">操作</td>
			</tr>
			<tr>
				<td>温度传感器</td>
				<td>综合楼</td>
				<td>大会议室</td>
				<td><a href="#">告警配置</a></td>
			</tr>
			<tr>
				<td>红外传感器</td>
				<td>综合楼</td>
				<td>校史馆</td>
				<td><a href="#">告警配置</a></td>
			</tr>
			<tr>
				<td>烟雾传感器</td>
				<td>教学楼</td>
				<td>阶梯教室</td>
				<td><a href="#">告警配置</a></td>
			</tr>
		</table>
	</div>
	<%@ include file="/plugins/pager.jsp"%>
</body>
</html>
