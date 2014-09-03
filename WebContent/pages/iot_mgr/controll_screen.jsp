<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/header.jsp"%>
</head>
<body>
	<c:if test="${not empty devInfo}">
		<form id="mainForm" name="mainForm" action="${ctx}/roomMonitor/saveScreen" method="post">
			<input type="hidden" name="fromMeeting" value="${fromMeeting}"/>
			<input type="hidden" name="fileName" value="${fileName}"/>
			<div class="tab_search" style="margin-top: 10px">
				<div class="table_div">
					<table width="100%" class="table_border">
						<tr>
							<td class="title" width="150px">标题：</td>
							<td>
								<input type="text" name="title" id="title" class="form_input" onKeyUp="titleChange()" value="${screenInfo.title}">
							</td>
							<td class="title">标题颜色：</td>
							<td>
								<select id="titleColor" name="titleTextColor" style="width: 70px" onchange="titleColorChange($('#titleColor').val())">
									<option value="1" style="background-color: white;">白色</option>
									<option value="2" style="background-color: black;">黑色</option>
									<option value="3" style="background-color: blue;">蓝色</option>
									<option value="4" style="background-color: green;">绿色</option>
									<option value="5" style="background-color: red;">红色</option>
									<option value="6" style="background-color: yellow;" selected="selected">黄色</option>
									<option value="7" style="background-color: #FFC0CB">粉色</option>
									<option value="8" style="background-color: purple;">紫色</option>
									<option value="9" style="background-color: orange;">橙色</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="title">背景颜色：</td>
							<td>
								<select id="backgroundColor" name="backgroundColor" style="width: 70px" onchange="bgColorChange($('#backgroundColor').val())">
									<option value="1" style="background-color: white;">白色</option>
									<option value="2" style="background-color: black;">黑色</option>
									<option value="3" style="background-color: blue;">蓝色</option>
									<option value="4" style="background-color: green;">绿色</option>
									<option value="5" style="background-color: red;" selected="selected">红色</option>
									<option value="6" style="background-color: yellow;">黄色</option>
									<option value="7" style="background-color: #FFC0CB">粉色</option>
									<option value="8" style="background-color: purple;">紫色</option>
									<option value="9" style="background-color: orange;">橙色</option>
								</select>
							</td>
							<td class="title">内容颜色：</td>
							<td>
								<select id="contentColor" name="contentTextColor" style="width: 70px" onchange="contentColorChange($('#contentColor').val())">
									<option value="1" style="background-color: white;">白色</option>
									<option value="2" style="background-color: black;">黑色</option>
									<option value="3" style="background-color: blue;">蓝色</option>
									<option value="4" style="background-color: green;">绿色</option>
									<option value="5" style="background-color: red;">红色</option>
									<option value="6" style="background-color: yellow;" selected="selected">黄色</option>
									<option value="7" style="background-color: #FFC0CB">粉色</option>
									<option value="8" style="background-color: purple;">紫色</option>
									<option value="9" style="background-color: orange;">橙色</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="title">内容分屏：</td>
							<td>
								<select id="screenNum" name="screenNum" style="width: 70px" onchange="screenNumChange($('#screenNum').val())">
									<option value="1">1屏</option>
									<option value="2">2屏</option>
									<option value="3">3屏</option>
									<option value="4">4屏</option>
									<option value="5">5屏</option>
								</select>
							</td>
							<td class="title">时间间隔：</td>
							<td>
								<select id="intervalTime" name="intervalTime" style="width: 70px">
									<option value="01">1秒</option>
									<option value="02">2秒</option>
									<option value="03">3秒</option>
									<option value="04">4秒</option>
									<option value="05" selected="selected">5秒</option>
									<option value="06">6秒</option>
									<option value="07">7秒</option>
									<option value="08">8秒</option>
									<option value="09">9秒</option>
									<option value="10">10秒</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="title">显示内容：</td>
							<td colspan="3">
								<div style="padding: 5px">
									<a id="screen1" href="#" onclick="showScreen('1')" style="font-weight:bold;color:black;">第1屏</a>&nbsp;&nbsp; 
									<a id="screen2" href="#" onclick="showScreen('2')" style="display: none">第2屏</a>&nbsp;&nbsp; 
									<a id="screen3" href="#" onclick="showScreen('3')" style="display: none">第3屏</a>&nbsp;&nbsp;
									<a id="screen4" href="#" onclick="showScreen('4')" style="display: none">第4屏</a>&nbsp;&nbsp;
									<a id="screen5" href="#" onclick="showScreen('5')" style="display: none">第5屏</a>&nbsp;&nbsp;
								</div>
								<div id="screenDiv" style="background-color: #FF0000; width: 340px; display: block; cursor: pointer; border: 15px solid #000000;">
									<table width="100%" style="height: 100%" border="0">
										<tr>
											<td height="20px" align="center">
												<input id="titleLabel" type="text" value="${screenInfo.title}" readonly="readonly"
														style="height: 20px; width: 100%; text-align: center; font-size: 20px; color: yellow; background-color: red; font-weight: bold; border: 0px;">
											</td>
										</tr>
										<c:forEach begin="1" end="7" varStatus="status">
											<tr id="tr_${status.index}">
												<td>
													<input id="message_${status.index}" name="message_${status.index}" maxlength="15" type="text" value="${screenInfo.messages[status.index-1]}"
														style="height: 20px; width: 100%; text-align: center; font-size: 20px; color: yellow; background-color: red; font-weight: bold; border: 0px;">
												</td>
											</tr>
										</c:forEach>
										<c:forEach begin="8" end="35" varStatus="status">
											<tr id="tr_${status.index}" style="display: none;">
												<td>
													<input id="message_${status.index}" name="message_${status.index}" maxlength="15" type="text" value="${screenInfo.messages[status.index-1]}"
														style="height: 20px; width: 100%; text-align: center; font-size: 20px; color: yellow; background-color: red; font-weight: bold; border: 0px;">
												</td>
											</tr>
										</c:forEach>
									</table>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<c:if test="${param['readonly']!='1' }">
									<input type="submit" value="确定" class="form_botton" />&nbsp;&nbsp;&nbsp;&nbsp; <input id="resetBtn" type="reset" value="重置" class="form_botton" />
								</c:if>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</form>
		<script>
			var titleColor = '${screenInfo.titleTextColor}';
			var bgColor = '${screenInfo.backgroundColor}';
			var contentColor = '${screenInfo.contentTextColor}';
			var screenNum = '${screenInfo.screenNum}';
			var intervalTime = '${screenInfo.intervalTime}';
			var fromMeeting = '${fromMeeting}';
			var fileName = '${fileName}';
			$(function()
			{
				$('#mainForm').ajaxForm({
					dataType : 'json',
					success : onSuccess

				});
				if(titleColor!='')
				{
					$('#titleColor').val(titleColor);
					titleColorChange(titleColor);
				}
				if(bgColor!='')
				{
					$('#bgColor').val(bgColor);
					bgColorChange(bgColor);
				}
				if(contentColor!='')
				{
					$('#contentColor').val(contentColor);
					contentColorChange(contentColor);
				}
				if(screenNum!='')
				{
					$('#screenNum').val(screenNum);
					screenNumChange(screenNum);
				}
				if(intervalTime!='')
					$('#intervalTime').val(intervalTime);
				
			});
			//表单提交成功操作
			function onSuccess(data)
			{
				if (data.messageType == '1')
				{
					alert(data.promptInfo);
					if(fromMeeting=='1')
					{
						parent.setScreenFileName(fileName);
						parent.closeModalWindow(false);
						
					}
				}
				else
				{
					alert(data.promptInfo);
				}
			}
			//第1屏->第5屏
			function showScreen(screenIndex)
			{
				var startIndex = (screenIndex - 1) * 7 + 1;
				var endIndex = screenIndex * 7;

				for ( var i = 1; i <= 35; i++)
				{
					if (i >= startIndex && i <= endIndex)
					{
						$('#tr_' + i).show();
					}
					else
					{
						$('#tr_' + i).hide();
					}
				}
				for ( var i = 1; i <= 5; i++)
				{
					if (i == screenIndex)
					{
						//$('#screen' + i).attr("disabled","disabled");
						$('#screen' + i).css("font-weight","bold");
						$('#screen' + i).css("color","black");
					}
					else
					{
						//$('#screen' + i).removeAttr("disabled");
						$('#screen' + i).css("font-weight","normal");
						$('#screen' + i).css("color","");
					}
				}

			}
			//内容分屏数量改变处理
			function screenNumChange(screenNum)
			{
				for ( var i = 1; i <= 5; i++)
				{
					if (i <= screenNum)
					{
						$('#screen' + i).show();
					}
					else
					{
						$('#screen' + i).hide();
					}
				}
				showScreen(1);
			}
			//标题输入处理
			function titleChange()
			{
				var title = $('#title').val();
				$('#titleLabel').val(title);
			}
			//标题字体颜色
			function titleColorChange(titleColor)
			{
				if(titleColor!=null)
				{
					var color = getColor(titleColor);
					$('#titleLabel').css("color", color);
				}
			}
			//标题字体颜色
			function contentColorChange(contentColor)
			{
				if(contentColor!='')
				{
					var color = getColor(contentColor);
					for ( var i = 1; i <= 35; i++)
					{
						$('#message_' + i).css("color", color);
					}
				}
			}
			//背景颜色
			function bgColorChange(backgroundColor)
			{
				if(backgroundColor!='')
				{
					var color = getColor(backgroundColor);
	
					$('#screenDiv').css("background-color", color);
					$('#titleLabel').css("background-color", color);
					for ( var i = 1; i <= 35; i++)
					{
						$('#message_' + i).css("background-color", color);
					}
				}
			}
			//根据颜色值获得相应颜色
			function getColor(backgroundColor)
			{
				var color = "";
				if (backgroundColor == "1")
					color = "white";
				else if (backgroundColor == "2")
					color = "black";
				else if (backgroundColor == "3")
					color = "blue";
				else if (backgroundColor == "4")
					color = "green";
				else if (backgroundColor == "5")
					color = "red";
				else if (backgroundColor == "6")
					color = "yellow";
				else if (backgroundColor == "7")
					color = "#FFC0CB";
				else if (backgroundColor == "8")
					color = "purple";
				else if (backgroundColor == "9")
					color = "orange";
				return color;

			}
		</script>
	</c:if>
	<c:if test="${empty devInfo}">
		<div style="width: 300px; height: 300px; text-align: center; padding-top: 50px">
			<font color="red"><b>暂无相应设备</b></font>
		</div>
	</c:if>
</body>

</html>
