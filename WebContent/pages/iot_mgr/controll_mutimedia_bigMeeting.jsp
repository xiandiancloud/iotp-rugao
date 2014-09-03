<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/header.jsp"%>
</head>
<body>
<c:if test="${not empty devInfo}">
	<div style="width: 630px">
		<div class="tab_top">
			<div id="tab0" class="tab_text_over" onclick="changeTab('0')">
				<div class="left"></div>
				<div class="con">&nbsp;&nbsp;电源管理&nbsp;&nbsp;</div>
				<div class="right" id="dateBtn"></div>
			</div>
			<div id="tab1" class="tab_text_out" onclick="changeTab('1')">
				<div class="left"></div>
				<div class="con">&nbsp;&nbsp;投影融合&nbsp;&nbsp;</div>
				<div class="right"></div>
			</div>
			<div id="tab2" class="tab_text_out" onclick="changeTab('2')">
				<div class="left"></div>
				<div class="con">&nbsp;&nbsp;声音控制&nbsp;&nbsp;</div>
				<div class="right"></div>
			</div>
			<div id="tab3" class="tab_text_out" onclick="changeTab('3')">
				<div class="left"></div>
				<div class="con">&nbsp;&nbsp;信号切换&nbsp;&nbsp;</div>
				<div class="right"></div>
			</div>
		</div>
		<div id="tabContent0" class="table_div" style="height: 350px;text-align: center;padding-top: 40px">
			<button class="form_botton" onclick="sendCmd('${devInfo.devId}','POW','ON',event);">电  源  开</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button class="form_botton" onclick="sendCmd('${devInfo.devId}','POW','OFF',event);">电  源  关</button>
		</div>
		<div id="tabContent1" class="table_div" style="height: 350px;text-align: center;padding-top: 40px;display:none">
			<dl>
				<button class="form_botton" onclick="sendCmd('${devInfo.devId}','PRO','ON',event);">投  影  开</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="form_botton" onclick="sendCmd('${devInfo.devId}','PRO','OFF',event);">投  影  关</button>
			</dl>
			<dl>&nbsp;&nbsp;</dl>
			<dl>
				<button class="form_botton" onclick="sendCmd('${devInfo.devId}','SCREEN','ON',event);">融  合  器  开</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="form_botton" onclick="sendCmd('${devInfo.devId}','SCREEN','OFF',event);">融  合  器  关</button>
			</dl>
			<dl>&nbsp;&nbsp;</dl>
			<dl>
				<button class="form_botton" onclick="sendCmd('${devInfo.devId}','SCREEN','MODE1',event);">模式1</button>&nbsp;&nbsp;&nbsp;
				<button class="form_botton" onclick="sendCmd('${devInfo.devId}','SCREEN','MODE2',event);">模式2</button>&nbsp;&nbsp;&nbsp;
				<button class="form_botton" onclick="sendCmd('${devInfo.devId}','SCREEN','MODE3',event);">模式3</button>&nbsp;&nbsp;&nbsp;
				<button class="form_botton" onclick="sendCmd('${devInfo.devId}','SCREEN','MODE4',event);">模式4</button>&nbsp;&nbsp;&nbsp;
				<button class="form_botton" onclick="sendCmd('${devInfo.devId}','SCREEN','MODE5',event);">模式5</button>
			</dl>
		</div>
		<div id="tabContent2" class="table_div" style="height: 350px;text-align: center;padding-top: 40px;display:none">
			<button class="form_botton" onclick="sendCmd('${devInfo.devId}','VOL','UP',event);">音  量+</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button class="form_botton" onclick="sendCmd('${devInfo.devId}','VOL','DOWN',event);">音  量-</button>
		</div>
		<div id="tabContent3" class="table_div" style="height: 350px;padding-left:5px;display:none">
			<div>
				<dl style="text-align: left;"><b>RGB信号切换</b></dl>
				<dl style="text-align: left;padding-left: 20px">
					<table width="100%">
						<tr>
							<td>融合窗口1: 
							</td>
							<td>
								<button class="form_botton" onclick="sendCmd('${devInfo.devId}','RGB1','IN1',event);">信息接口1</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button class="form_botton" onclick="sendCmd('${devInfo.devId}','RGB1','IN2',event);">信息接口2</button>
							</td>
						</tr>
						<tr>
							<td>融合窗口2: 
							</td>
							<td>
								<button class="form_botton" onclick="sendCmd('${devInfo.devId}','RGB2','IN1',event);">信息接口1</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button class="form_botton" onclick="sendCmd('${devInfo.devId}','RGB2','IN2',event);">信息接口2</button>
							</td>
						</tr>
						<tr>
							<td>等   离   子1: 
							</td>
							<td>
								<button class="form_botton" onclick="sendCmd('${devInfo.devId}','RGB3','IN1',event);">信息接口1</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button class="form_botton" onclick="sendCmd('${devInfo.devId}','RGB1','IN2',event);">信息接口2</button>
							</td>
						</tr>
						<tr>
							<td>等   离   子2: 
							</td>
							<td>
								<button class="form_botton" onclick="sendCmd('${devInfo.devId}','RGB4','IN1',event);">信息接口1</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button class="form_botton" onclick="sendCmd('${devInfo.devId}','RGB4','IN2',event);">信息接口2</button>
							</td>
						</tr>
					</table>
				</dl>
			</div>
			<div>
				<dl style="text-align: left;"><b>AV信号切换</b></dl>
				<dl style="text-align: left;padding-left: 20px">
					<table width="100%">
						<tr>
							<td>融合窗口1: 
							</td>
							<td>
								<button class="form_botton" onclick="sendCmd('${devInfo.devId}','AV1','IN1',event);">DVD</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button class="form_botton" onclick="sendCmd('${devInfo.devId}','AV1','IN2',event);">视频会议</button>
							</td>
						</tr>
						<tr>
							<td>融合窗口2: 
							</td>
							<td>
								<button class="form_botton" onclick="sendCmd('${devInfo.devId}','AV2','IN1',event);">DVD</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button class="form_botton" onclick="sendCmd('${devInfo.devId}','AV2','IN2',event);">视频会议</button>
							</td>
						</tr>
					</table>
				</dl>
			</div>
		</div>
	</div>
</c:if>
<c:if test="${empty devInfo}">
	<div style="width: 300px;height: 300px;text-align: center;padding-top: 50px"><font color="red"><b>暂无相应设备</b></font></div>
</c:if>
</body>
<script>
	function changeTab(tabIndex)
	{
		for(var i=0;i<4;i++)
		{
			if(tabIndex==i)
			{
				$('#tab'+i).attr("class","tab_text_over");
				$('#tabContent'+i).show();
			}
			else
			{
				$('#tab'+i).attr("class","tab_text_out");
				$('#tabContent'+i).hide();
			}
		}
	}
</script>
</html>
