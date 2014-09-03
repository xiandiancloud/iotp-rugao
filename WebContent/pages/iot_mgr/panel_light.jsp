<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/header.jsp"%>
</head>
<body>
<!-- 灯光控制面板 -->
<div id="light-controll-panel" >
    <div class="riguangdeng_info"><!--/*灯控背景*/-->
	    <span class="riguangdeng_open"></span><!--灯控开关-->
	    <span class="riguangdeng_liangdu_add"></span><!--亮度加-->
	    <span class="riguangdeng_liangdu_del"></span><!--亮度减-->
	    <span class="riguangdeng_liangdu">亮度等级</span>
	    <span class="riguangdeng_liangdu_disp">100%</span><!--亮度显示-->
    </div>
</div>
	<script>
	
	//空调温度
	function changWendu(add,event)
	{
		var currClass = $('#temperature').attr("class");
		var classArray = currClass.split("_");
		var num =Number(classArray[2])+Number(add);
		if(num>=16 & num<=36)
		{
			$('#temperature').attr("class",classArray[0]+"_"+classArray[1]+"_"+num);	
		}
		stopBubble(event);
	}
	//空调模式切换
	function changeMod(event)
	{
		if($('#coolMod').hasClass("kongtiao_leng_b"))
		{
			$('#coolMod').attr("class","kongtiao_leng_a");
			$('#hotMod').attr("class","kongtiao_re_b");
			$('#windMod').attr("class","kongtiao_feng_a");
			stopBubble(event);
			return;
		}
		if($('#hotMod').hasClass("kongtiao_re_b"))
		{
			$('#coolMod').attr("class","kongtiao_leng_a");
			$('#hotMod').attr("class","kongtiao_re_a");
			$('#windMod').attr("class","kongtiao_feng_b");
			stopBubble(event);
			return;
		}
		if($('#windMod').hasClass("kongtiao_feng_b"))
		{	
			$('#coolMod').attr("class","kongtiao_leng_b");
			$('#hotMod').attr("class","kongtiao_re_a");
			$('#windMod').attr("class","kongtiao_feng_a");
			stopBubble(event);
			return;
		}
		
	}
	//空调风速切换
	function changeWind(event)
	{
		if($('#speed1').hasClass("kongtiao_speed1_b"))
		{
			$('#speed1').attr("class","kongtiao_speed1_a");
			$('#speed2').attr("class","kongtiao_speed2_b");
			stopBubble(event);
			return;
		}
		if($('#speed2').hasClass("kongtiao_speed2_b"))
		{
			$('#speed1').attr("class","kongtiao_speed1_b");
			$('#speed2').attr("class","kongtiao_speed2_a");
			stopBubble(event);
			return;
		}
	}
	
	//空调开关
	function switchAirConditioner(event)
	{
		if($('#switchController').hasClass('kongtiao_open'))
		{
			$('#switchController').attr("class","kongtiao_close");
			$('#coolMod').attr("class","kongtiao_leng_a");
			$('#hotMod').attr("class","kongtiao_re_a");
			$('#windMod').attr("class","kongtiao_feng_a");
			$('#speed1').attr("class","kongtiao_speed1_a");
			$('#speed2').attr("class","kongtiao_speed2_a");
			$('#temperature').attr("class","");
		}
		else
		{
			$('#switchController').attr("class","kongtiao_open");
			$('#coolMod').attr("class","kongtiao_leng_b");
			$('#hotMod').attr("class","kongtiao_re_a");
			$('#windMod').attr("class","kongtiao_feng_a");
			$('#speed1').attr("class","kongtiao_speed1_a");
			$('#speed2').attr("class","kongtiao_speed2_b");
			$('#temperature').attr("class","kongtiao_num_26");
		}
		stopBubble(event);
	}
	</script>
</body>
</html>
