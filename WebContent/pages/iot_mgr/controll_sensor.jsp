<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.verywell.framework.utils.socket.PoCModel"%>
<%@page import="java.io.*,java.util.*, java.text.*"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <%-- @ include file="/common/header.jsp" --%> 
</head>
<body>
	<div>
		<c:if test="${not empty resultList}">
			<!-- 
	<div style="border-bottom: solid 1px #cee0f4;padding-bottom: 2px">
		<button class="page_anniu" onclick="switchDev('${devIds}','1',event)">全部开启</button>
		<button class="page_anniu" onclick="switchDev('${devIds}','0',event)">全部关闭</button>
	</div>
	 -->
			<c:forEach var="item" items="${resultList}" varStatus="status">
				<div style="display: inline-block; float: left; text-align: center; border-right: solid 1px #cee0f4; padding: 50px; width: 400px; height:300px">
					<div title="${item.devStatusDesc}">
						<img src="${ctx}/images/sensor.png" />
					</div>
					<dl><b>Room 1#: ${item.devName}</b>	</dl>
					<div>
						<table width="100%">
							<tr>
								<td><b>电压：
								 <%
								 response.setIntHeader("Refresh", 2);
								 float v = PoCModel.getPocModel().getVoltage();	
								 DecimalFormat decimalFormat=new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
								 String pv=decimalFormat.format(v);//format 返回的是字符串
								 //out.println(pv);
								 out.println(Math.random());
		 			   			  %>
								</b></td>
								<td align="right">
									<!-- 			${item.attrMap['TEMPERATURE']}  -->
								</td>
								<td align="left">伏特(V)</td>
							</tr>
							<tr>
								<td><b>电流： 
								<%
						 		float c = PoCModel.getPocModel().getCurrent();	
						 		String pc=decimalFormat.format(c);//format 返回的是字符串
						 		out.println(pc);
		 			           %>
								</b></td>
								<td align="right">${item.attrMap['HUMIDITY']}</td>
								<td align="left">安培(A)</td>
							</tr>
							<tr>
								<td><b>功率： 
								<%
						 		float p = PoCModel.getPocModel().getPower();									
						 		String pp=decimalFormat.format(p);//format 返回的是字符串
						 		out.println(pp);
		 			     	   %>
								</b></td>
								<td align="right"">${item.attrMap['LIGHT']}</td>
								<td align="left">瓦特(W)</td>
							</tr>
						</table>
					</div>
				</div>
			</c:forEach>
		</c:if>
		<c:if test="${empty resultList}">
			<div
				style="width: 300px; height: 300px; text-align: center; padding-top: 50px">
				<font color="red"><b>暂无相应设备</b></font>
			</div>
		</c:if>
	</div>
</body>
<script>

</script>
</html>
