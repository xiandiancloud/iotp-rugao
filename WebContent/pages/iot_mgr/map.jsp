<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/common/header.jsp"%>
</head>
<body>
<!--弹出层-->
<div style="display:none" id="buildingDetailDiv">   
   <div class="mask_div2"><!--透明遮罩-->
          <table width="692" border="0" align="center" cellpadding="0" cellspacing="0">
             <tr>
             <td>      
                 <div class="open_div" id="detailDialog">
                   <div class="open_div_left"><img src="${ctx }/images/open_div_bg1.png" border="0" usemap="#Map2">
					<map name="Map2">
						<area title="1楼" shape="rect" coords="663,480,699,510" href="#" onclick="changeFloor('1')">
						<area title="2楼" shape="rect" coords="668,441,700,469" href="#" onclick="changeFloor('2')">
						<area title="3楼" shape="rect" coords="668,400,703,433" href="#" onclick="changeFloor('3')">
						<area title="4楼" shape="rect" coords="669,362,707,394" href="#" onclick="changeFloor('4')">
						<area title="5楼" shape="rect" coords="672,325,708,356" href="#" onclick="changeFloor('5')">
						<area title="6楼" shape="rect" coords="567,202,602,223" href="#" onclick="changeFloor('6')">
						<area title="7楼" shape="rect" coords="569,178,602,200" href="#" onclick="changeFloor('7')">
						<area title="8楼" shape="rect" coords="568,155,602,175" href="#" onclick="changeFloor('8')">
						<area title="9楼" shape="rect" coords="569,130,602,152" href="#" onclick="changeFloor('9')">
						<area title="10楼" shape="rect" coords="570,106,601,127" href="#" onclick="changeFloor('10')">
						<area title="11楼" shape="rect" coords="571,81,602,103" href="#" onclick="changeFloor('11')">
					</map>
                   </div>
				   <div class="open_div_right">
				   		<div class="close" onclick="closeDetail()"></div>
                       <div id="floorTitle" class="open_div_title">1楼</div>
                       <div id="roomList" class="open_div_list">
						  <ul floor="1">
                             <li class="a"><a href="${ctx}/map/roomDetail/1001">大会议室</a></li>
                             <li class="b"><img src="${ctx }/images/open_div_ico_a.png"></li>
                          </ul>
                          <ul floor="1">
                             <li class="a"><a href="${ctx}/map/roomDetail/1002">校史馆</a></li>
                             <li class="b"><img src="${ctx }/images/open_div_ico_c.png"></li>
                          </ul>
                           <ul floor="1">
                             <li class="a"><a href="${ctx}/map/roomDetail/1003">会议室</a></li>
                             <li class="b"><img src="${ctx }/images/open_div_ico_a.png"></li>
                          </ul>
                          <ul floor="2" style="display: none">
                             <li class="a"><a href="${ctx}/map/roomDetail/1004">阶梯教室1</a></li>
                             <li class="b"><img src="${ctx}/images/open_div_ico_a.png"></li>
                          </ul>
                          <ul floor="2" style="display: none">
                             <li class="a"><a href="${ctx}/map/roomDetail/1005">阶梯教室2</a></li>
                             <li class="b"><img src="${ctx}/images/open_div_ico_a.png"></li>
                          </ul>
                       </div>
                   </div>
                 </div>             
                 </td>
             </tr>
          </table>
  </div>
   
<div class="mask_div" style="_height:1500px;"></div>
<!--黑色背景遮罩-->
</div>
<!--弹出层 End-->
<table width="100%" height="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td>
			<table width="100%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_div" style="background:url(${ctx}/images/big_map1.jpg) center center;">
			  <tr>
			    <td align="center" valign="middle">
			       <img src="${ctx}/images/big_map.jpg" width="790" height="560" border="0" usemap="#Map">    </td>
			  </tr>
			</table>
		</td>
	</tr>
</table>
<map name="Map">
<area title="连体楼" shape="poly" coords="165,313,218,343,244,293,287,315,317,268,362,295,378,265,388,245,382,205,333,172,317,206,289,214,266,200,244,231,223,252,178,228,161,277,165,312" href="#">
<area title="综合楼" shape="poly" coords="615,206,615,231,635,268,656,278,678,279,708,259,699,231,663,175" href="#" onclick="javasrcrip:showDetail('1001')">
<area shape="poly" coords="392,183,396,162,439,135,482,212,440,238,418,229" href="#">
<area shape="poly" coords="560,152,594,134,621,189,579,226,559,195,560,153" href="#">
<area shape="poly" coords="472,119,506,175,533,161,529,138,497,76,473,92" href="#">
<area shape="poly" coords="514,94,545,148,569,134,569,108,533,55,513,66" href="#">
<area shape="poly" coords="551,46,555,78,586,129,606,119,606,90,575,36" href="#">
<area shape="poly" coords="235,448,232,416,244,409,248,364,248,334,255,322,262,335,264,360,301,422,305,451,308,476,261,494" href="#">
</map>

<SCRIPT language=javascript>
var bOnWin;
function showDetail(buildingId)
{
	$('#roomList').ajaxStart(function(){
		$('#roomList').html("<img src='${ctx}/images/loading.gif'>加载中....");
	});
	$.getJSON("${ctx}/map/getRoomList/"+buildingId+"?r="+Math.random(), function(data){
	$('#roomList').html("");
	$.each(data, function(i,room){
		 var alarmIco = "open_div_ico_a.png";
		 if(room.alarmStatus=='2')
			 alarmIco = "open_div_ico_c.png";
		 var display = "style='display:none'";
		 if(room.floorNo=='1')
			 display = "";
		 $('#roomList').append("<ul floor='"+room.floorNo+"' "+display+"><li class='a'><a href='${ctx}/map/roomDetail/"+room.roomId+"'>"+room.roomName+"</a></li><li class='b'><img src='${ctx}/images/"+alarmIco+"'></li></ul>");
	  });
	});
	$('#buildingDetailDiv').show();
	document.getElementById('detailDialog').onmouseover = function() {bOnWin = 1;};
	document.getElementById('detailDialog').onmouseout = function() {bOnWin = null;};

}
function closeDetail()
{
	$('#buildingDetailDiv').hide();
}

function changeFloor(floor)
{
	$("#floorTitle").html(floor+"楼");
	$("ul[floor='"+floor+"']").show();
	$("ul[floor!='"+floor+"']").hide();
}

document.documentElement.onmousedown= function() {
	if (bOnWin==null) 
		$('#buildingDetailDiv').hide();
};
</SCRIPT>
</body>
</html>