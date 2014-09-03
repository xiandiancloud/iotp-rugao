<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/header.jsp"%>
</head>
<body>
<div id="kongtiao-controll-panel">
	<div class="kongtiao_info">
		<span id="switchController" class="kongtiao_open" title="开关" onclick="changeSwitch(event)"></span>
		<span class="kongtiao_wendu_add" title="温度增加" onclick="changTemperature('1',event)"></span>
		<span class="kongtiao_wendu_del" title="温度减少" onclick="changTemperature('-1',event)"></span>
		<span class="kongtiao_wendu_mode"  onclick="changeMod(event)"></span>
		<span class="kongtiao_wendu_speed" onclick="changeWind(event)"></span>
		<span id="speed1" class="kongtiao_speed1_b" title="小风" ></span>
		<span id="speed2" class="kongtiao_speed2_b" title="大风"></span>
		<span id="coolMod" class="kongtiao_leng_a" title="制冷"></span>
		<span id="hotMod" class="kongtiao_re_a" title="制热"></span>
		<span id="windMod" class="kongtiao_feng_a" title="通风"></span>
		<span id="temperature" class="kongtiao_num_26"></span>
	</div>
</div>
	<script>
	var panelInterval;
	var devId;
	$(document).ready(function () {
		devId = '${devInfo.devId}';
		var devStatus = '${devInfo.devStatus}';//设备状态
		var windSpeed = "${devInfo.attrMap['AIR_WIND']}";//风量
		var mode = "${devInfo.attrMap['AIR_MODE']}";//模式
		var temperature = "${devInfo.attrMap['AIR_TEMP']}";//温度
		
		if(devStatus=='1') //开启状态
		{
			setSwitch(1);
			setMod(mode);
			setWind(windSpeed);
			setTemperature(temperature);
		}
		else
		{
			setSwitch(0);
			setMod(0);
			setWind(0);
			setTemperature(0);
		}
		//panelInterval = setInterval("refreshData('"+devId+"')",3000);
	});
	//定时同步数据并更新控制面板
	function refreshData(devId)
	{
		$.getJSON("${ctx}/roomMonitor/devDetail/"+devId+"?r="+Math.random(), function(dev){
			var devId = dev.devId;
			var devStatus = dev.devStatus;//设备状态
			var windSpeed = dev.attrMap.AIR_WIND;//风量
			var mode = dev.attrMap.AIR_MODE;//模式
			var temperature = dev.attrMap.AIR_TEMP;//温度
			
			if(devStatus=='1') //开启状态
			{
				setSwitch(1);
				setMod(mode);
				setWind(windSpeed);
				setTemperature(temperature);
			}
			else
			{
				setSwitch(0);
				setMod(0);
				setWind(0);
				setTemperature(0);
			}
		});
	}
	
	
	//设置空调温度
	function setTemperature(temperature)
	{
		if(temperature>0)
			$('#temperature').attr("class","kongtiao_num_"+temperature);
		else
			$('#temperature').attr("class","");
	}
	//获得当前设置温度
	function getTemperature()
	{
		var currClass = $('#temperature').attr("class");
		if(currClass!='')
		{
			var classArray = currClass.split("_");
			var num =Number(classArray[2]);
			return num;	
		}
		return 0;
		
	}
	//改变空调温度
	function changTemperature(add,event)
	{
		var currTemp = getTemperature();
		if(currTemp!=0)
		{
			var newTemp =Number(currTemp)+Number(add);
			if(newTemp>=16 & newTemp<=36)
			{
				sendCmd(devId,"setTemp",newTemp,event);
				setTemperature(newTemp);
			}
		}
	}
	///设置模式 0-关闭 1-冷 2-热 3-送风
	function setMod(mod)
	{
		if(mod=='0')
		{
			$('#coolMod').attr("class","kongtiao_leng_a");
			$('#hotMod').attr("class","kongtiao_re_a");
			$('#windMod').attr("class","kongtiao_feng_a");
		}
		else if(mod=='1')
		{
			$('#coolMod').attr("class","kongtiao_leng_b");
			$('#hotMod').attr("class","kongtiao_re_a");
			$('#windMod').attr("class","kongtiao_feng_a");
		}
		else if(mod=='2')
		{
			$('#coolMod').attr("class","kongtiao_leng_a");
			$('#hotMod').attr("class","kongtiao_re_b");
			$('#windMod').attr("class","kongtiao_feng_a");
		}
		else if(mod=='3')
		{
			$('#coolMod').attr("class","kongtiao_leng_a");
			$('#hotMod').attr("class","kongtiao_re_a");
			$('#windMod').attr("class","kongtiao_feng_b");
		}
	}
	
	//获得当前空调模式 1-冷 2-热 3-送风 0-关闭
	function getMod()
	{
		if($('#coolMod').hasClass("kongtiao_leng_b"))
		{
			return 1;
		}
		else if($('#hotMod').hasClass("kongtiao_re_b"))
		{
			return 2;
		}
		else if($('#windMod').hasClass("kongtiao_feng_b"))
		{	
			return 3;
		}
		else
			return 0;
	}
	
	//空调模式切换
	function changeMod(event)
	{
		var currentMod = getMod();
		if(currentMod!=0)
		{
			var newMod;
			if(currentMod==3)
				newMod = 1;
			else
				newMod=currentMod+1;
			sendCmd(devId,"setMode",newMod,event);
			setMod(newMod);
		}
	}
	//设置风量 0-关闭 1-小风 2-大风
	function setWind(wind)
	{
		if(wind==0)
		{
			$('#speed1').attr("class","kongtiao_speed1_a");
			$('#speed2').attr("class","kongtiao_speed2_a");
		}
		else if(wind==1)
		{
			$('#speed1').attr("class","kongtiao_speed1_b");
			$('#speed2').attr("class","kongtiao_speed2_a");
		}
		else if(wind==2)
		{
			$('#speed1').attr("class","kongtiao_speed1_a");
			$('#speed2').attr("class","kongtiao_speed2_b");
		}
	}
	//获得当前风量 0-关闭 1-小风 2-大风
	function getWind()
	{
		if($('#speed1').hasClass("kongtiao_speed1_b"))
		{
			return 1;
		}
		else if($('#speed2').hasClass("kongtiao_speed2_b"))
		{
			return 2;
		}
		else
			return 0;
	}
	
	//空调风速切换
	function changeWind(event)
	{
		var currentWind = getWind();
		if(currentWind!=0)
		{
			if(currentWind==1)
			{
				sendCmd(devId,"setFaxSpeed","2",event);
				setWind(2);
			}
			else
			{
				sendCmd(devId,"setFaxSpeed","1",event);
				setWind(1);
			}
		}
	}
	//设置空调开关
	function setSwitch(onoff,event)
	{
		if(onoff==1)
		{
			$('#switchController').attr("class","kongtiao_open");	
		}
		else
		{
			$('#switchController').attr("class","kongtiao_close");
		}
	}
	//获得开关状态
	function getSwitch()
	{
		if($('#switchController').hasClass('kongtiao_open'))
			return 1;
		else
			return 0;
	}
	
	//空调开关
	function changeSwitch(event)
	{
		var currentSwitch = getSwitch();
		
		if(currentSwitch==1)
		{
			
			switchDev(devId,"0",event);
			setSwitch(0);
			setMod(0);
			setWind(0);
			setTemperature(0);
		}
		else
		{
			
			switchDev(devId,"1",event);
			setSwitch(1);
			setMod(1);
			setWind(1);
			setTemperature(26);
		}
	}
	
	</script>
</body>
</html>
