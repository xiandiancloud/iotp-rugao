<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/header.jsp"%>
<%@ include file="/plugins/dhtmlxScheduler.jsp"%>
<%@ include file="/plugins/ztree.jsp"%>
	<style type="text/css" media="screen">
		html, body {
			margin: 0px;
			padding: 0px;
			height: 100%;
			overflow: hidden;
		}
		#meetingForm {
			position: absolute;
			top: 100px;
			left: 200px;
			z-index: 10001;
			display: none;
			background-color: white;
			border: 2px outset gray;
			padding: 20px;
		}
	</style>
</head>
<body onload="init();">
	<div class="mianbaoxie">
		当前位置：<a href="#1">会议室预定管理</a> > 会议室预定
	</div>
	<div style="margin-left: 10px ">
	<input name="" type="button" value="会议预定" class="form_botton" onclick="meetingOrder()"/>
	</div>
	<div id="scheduler_here" class="dhx_cal_container" style='width:auto; height:90%;margin-left: 10px;margin-right: 10px'>
		<div class="dhx_cal_navline">
			<div class="dhx_cal_prev_button">&nbsp;</div>
			<div class="dhx_cal_next_button">&nbsp;</div>
			<div class="dhx_cal_today_button"></div>
			<div class="dhx_cal_date"></div>
			<div class="dhx_minical_icon" id="dhx_minical_icon" onclick="show_minical()">&nbsp;</div>
			<div class="dhx_cal_tab" name="week_tab" style="right:150px;"></div>
			<div class="dhx_cal_tab" name="timeline_tab" style="right:220px;"></div>
			<div class="dhx_cal_tab" name="month_tab" style="right:80px;"></div>
		</div>
		<div class="dhx_cal_header">
		</div>
		<div class="dhx_cal_data">
		</div>
	</div>
	
	<SCRIPT language=javascript>
	//当前登录用户名
	var loginName = '${sessionScope.loginToken.sysLogin.loginName}';
	//初始化日历
	function init() {

		scheduler.locale.labels.timeline_tab = "日";
		scheduler.locale.labels.section_custom = "Section";
		scheduler.config.xml_date = "%Y-%m-%d %H:%i";

		//===============
		//Configuration
		//===============
		var sections = ${roomJson};

		var durations = {
			day: 24 * 60 * 60 * 1000,
			hour: 60 * 60 * 1000,
			minute: 60 * 1000
		};

		var get_formatted_duration = function(start, end) {
			var diff = end - start;

			var days = Math.floor(diff / durations.day);
			diff -= days * durations.day;
			var hours = Math.floor(diff / durations.hour);
			diff -= hours * durations.hour;
			var minutes = Math.floor(diff / durations.minute);

			var results = [];
			if (days) results.push(days + " 天");
			if (hours) results.push(hours + " 小时");
			if (minutes) results.push(minutes + " 分钟");
			return results.join(", ");
		};


		var resize_date_format = scheduler.date.date_to_str(scheduler.config.hour_date);

		scheduler.templates.event_bar_text = function(start, end, event) {
			var state = scheduler.getState();
			if (state.drag_id == event.id) {
				return resize_date_format(start) + " - " + resize_date_format(end) + " (" + get_formatted_duration(start, end) + ")";
			}
			return event.text; // default
		};

		scheduler.createTimelineView({
			name:	"timeline",
			x_unit:	"minute",
			x_date:	"%H:%i",
			x_step:	30,
			x_size: 24,
			x_start: 16,
			x_length:	48,
			y_unit:	sections,
			y_property:	"roomId",
			render:"bar",
			event_dy: "full"
		});

		//===============
		//Data loading
		//===============
		var format=scheduler.date.date_to_str("%Y-%m-%d %H:%i"); 
		scheduler.templates.tooltip_text = function(start,end,event) {
			if(loginName!=event.subscribeBy)
			{
				return "<b>会议名称:</b> "+event.text+"<br/><b>开始时间:</b> "+format(start)+"<br/><b>结束时间:</b> "+format(end)+"<br/><b>会议室:</b> "+event.roomName+"<br/><b>预定人:</b> "+event.subscribeByName;
			}
			else
			{
				return "<b>会议名称:</b> "+event.text+"<br/><b>开始时间:</b> "+format(start)+"<br/><b>结束时间:</b> "+format(end)+"<br/><b>会议室:</b> "+event.roomName+"<br/><b>预定人:</b> "+event.subscribeByName+"<br/><a href='javascript:meetingOrderEdit("+ event.meetingId+")'>详情</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='javascript:cancelMeeting("+ event.meetingId+")'>取消预定</a>";
			}
		};
		var viewDate = '${param.viewDate}';
		var viewMode = '${param.viewMode}';
		if(viewDate!='')
		{
			var year = viewDate.split('-')[0];
			var month = viewDate.split('-')[1]-1;
			var day = viewDate.split('-')[2];
			scheduler.init('scheduler_here', new Date(year,month,day), viewMode);
		}
		else
			scheduler.init('scheduler_here', new Date(), "timeline");
	}

	
	var eventId;
	var currentDate;//当前时间
	var currentMode;//当前视图模式
	//日期、视图改变事件
	scheduler.attachEvent("onBeforeViewChange", function (old_mode, old_date, mode , date){

		if(mode==undefined)
		{
			mode=old_mode;
		}
		currentDate = date;
		currentMode = mode;
		var dateStr = currentDate.format('yyyy-MM-dd');
		scheduler.clearAll();
		scheduler.load("${ctx}/meetingMgr/meetingList/"+dateStr+"/"+currentMode+"?r="+Math.random(), "json");
		scheduler.updateView(currentDate, mode);
		return false;
	});
	scheduler.attachEvent("onBeforeDrag",function (event_id, mode, native_event_object){
	       return false;
	});
	scheduler.attachEvent("onClick",function (event_id, native_event_object){
		if (!event_id) return true;
		var ev = scheduler.getEvent(event_id);
		if(loginName!=ev.subscribeBy)
		{
			return false;
		}
		else
		{
			meetingOrderEdit( ev.meetingId);
			return false;
		}
	});
	
	//小日历弹出事件
	function show_minical(){
		if (scheduler.isCalendarVisible())
			scheduler.destroyCalendar();
		else
			scheduler.renderCalendar({
				position:"dhx_minical_icon",
				date:scheduler._date,
				navigation:true,
				handler:function(date,calendar){
					scheduler.setCurrentView(date);
					scheduler.destroyCalendar();
				}
			});
	}
	//刷新当前视图
	function refreshScheduler()
	{
		var dateStr = currentDate.format('yyyy-MM-dd');
		scheduler.clearAll();
		scheduler.load("${ctx}/meetingMgr/meetingList/"+dateStr+"/"+currentMode+"?r="+Math.random(), "json");
		scheduler.updateView(currentDate, currentMode);
	}
	
	//会议预定
	function meetingOrder()
	{
		var dateStr = currentDate.format('yyyy-MM-dd');
		window.location = "${ctx}/meetingMgr/order?viewDate="+dateStr+"&viewMode="+currentMode+"&r="+Math.random();
	}
	
	//会议预定
	function meetingOrderEdit(meetingId)
	{
		var dateStr = currentDate.format('yyyy-MM-dd');
		window.location = "${ctx}/meetingMgr/orderEdit/"+meetingId+"?viewDate="+dateStr+"&viewMode="+currentMode+"&r="+Math.random();
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
					var dateStr = currentDate.format('yyyy-MM-dd');
					window.location='${ctx}/meetingMgr?viewDate='+dateStr+'&viewMode='+currentMode;
			    }
			    else
			    {
			    	alert(data.promptInfo);
			    }
				});
		}
	}
	
	</SCRIPT>
</body>
</html>
