<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="u" uri="ueye"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0 ,user-scalable=no">
<title>内墙翻新</title>

<link href="${pageContext.request.contextPath}/css/base.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/info-style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/refresh/work_update.css" rel="stylesheet" type="text/css" />

 <script>
	var ctx = '${pageContext.request.contextPath}';
</script>

 <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.1.min.js"></script> 
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/refresh/innerwall.js"></script>
 

 
<script type="text/javascript">
  function addAppoint() {

          var mobile = $('#mobile').val();		
          if(mobile==""){ 
            alert("请输入您的手机号码！"); 		 
            return false; 
          }
          var telReg = !!mobile.match(/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/);
            //如果手机号码不能通过验证
            if(telReg == false){
              alert("手机号码格式不正确,请检查后重新提交!");
              return false;
            }
				   $.ajax({   
				    type : "POST",   
				    url : "http://m.sunjoypai.com/appoint/addMobileApplication", 
				    data : {
				     "mobile": $("#mobile").val(),
              "sourcePage":"内墙翻新"
				     },  
				    dataType: "json",   
				    success : function(data) {   
			         	if(data.success == "1"){
		                  alert("预约成功！");
		                }else{
		                  alert("您已提交过啦，请不要重复提交！");
		                }
				    },   
				    error :function(){   
				       alert("error");
      		}  
				    
				});
 
  }
</script>
</head>

<body>
<!--头部的开始
<div class="headTop">
	<a href="javascript:history.go(-1);" class="m_back"><i class="iconfont">&#xe604;</i><span>返回</span></a>
	<h2>内墙翻新</h2>
</div>
头部的结束-->    
<div class="renovationBanner">
	<img src="${pageContext.request.contextPath}/pic/renovation_banner.jpg"  alt="" />
	<p>内墙翻新</p>
</div>

<div class="renovationBox">
	<h3>服务介绍</h3>
	<div class="renovationAbout">
		可选择家用刷墙或商用刷墙，墙面刷漆服务选用享居派水性环保光触媒硅藻面层涂料，欧洲进口涂料，易擦涂易清洗；纳米硅藻涂料，净化有害物质；色彩丰富多彩，装饰多彩空间。	
	</div>
	<h3>参考报价</h3>
	<div class="renovationTable">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<th colspan="2">基础刷漆服务</th>
				<th colspan="2">墙面铲除</th>
			</tr>
			<tr>
				<td>面积≤30平米</td>
				<td>面积＞30平米</td>
				<td>铲除至水泥层</td>
				<td>铲除至砖头层</td>
			</tr>
			<tr>
				<td><p>￥2900</p></td>
				<td><p>￥36/m²</p></td>
				<td>
					每增加1平米
					<p>￥53/m²</p>
				</td>
				<td>
					每增加1平米
					<p>￥120/m²</p>
				</td>
			</tr>
		</table>
	</div>
	<h3>服务流程</h3>
	<div class="renovationProcess">
		<ul>
			<li class="Process-1">1.智能下单</li>
			<li class="Process-2">4.家居保护</li>
			<li class="Process-3">2.个性化需求确认</li>
			<li class="Process-4">5.开始焕新</li>
			<li class="Process-5">3.上门服务</li>
			<li class="Process-6">6.家居复位及清洁</li>
		</ul>
	</div>
</div>



<!-- 家居内墙智能报价  start-->
		<div id="myTabContent" class="notice">
			<article id="indoor_wall">
				<section class="list">
					<label>建筑面积:</label>
					<input type="text" value="" placeholder="请输入建筑面积" id="area1" /><b>㎡</b>
					<p class="clearfix">小提示：这里所填的面积是整个房型的总面积（如2室1厅的建筑面积是70㎡,就输入70即可）。</p>
				</section>
				<section class="list" id="houseTitle">
					<ul class="renovation">
						<li class="active">新房装修</li>
						<li>旧房改造</li>
					</ul>
					<section id="decorationContent">						
						<div id="newHouse" class="houseDecoration">													
						</div>
						<div id="oldHouse" class="houseDecoration" style="display:none">							
						</div>
					</section>
				</section>
<%-- 				<section class="list">
					<label>房屋户型:</label>
					<select onchange="change1()" id="shi">
						<option>0</option>
						<option selected>1</option>
						<option>2</option>
						<option>3</option>
						<option>4</option>
                        <option>5</option>
					</select>&nbsp;&nbsp;
					<span>室</span>&nbsp;&nbsp;
					<select onchange="change2()" id="ting">
						<option>0</option>
						<option selected>1</option>
						<option>2</option>
						<option>3</option>
					</select>&nbsp;&nbsp;
					<span>厅</span>
				</section>
				
				<div id="keting">
					<section class="update_style clearfix" >
						<div class="update_left">
							<p class="u"></p>
							<label>客厅1</label>
						</div>
						<section class="update_right ">
							<div id="tab_list">
								<ul id="ulist" class="style_list" >
									<li class="active"id="tuliao">涂料</li>
									<li>艺术涂料</li>
								</ul>
							</div>
						</section>
					</section>					
				</div>
				<div id="woshi">
					<section class="update_style">
						<div class="update_left">
							<p class="u"></p>
							<label>卧室1</label>
						</div>
						<section class="update_right">
							<div id="tab_list">
								<ul class="style_list">
									<li class="active">涂料</li>
									<li>艺术涂料</li>
								</ul>
							</div>
						</section>
					</section>
              	</div> --%>
				<section class="calculate" onclick="autoPrice()">智能报价</section>
			</article>		
		</div>	
<!-- 家居内墙智能报价  end-->

 <div style="margin-top:100px"></div>
<p>&nbsp;</p>


<div class="indexSearch-form">
  <input name="mobile" id="mobile" class="indexSearch-form-input" placeholder="输入手机号立即预约，24小时极速反馈！" />
  <input name="" type="submit" class="indexSearch-form-button" value="立即预约"   onclick="addAppoint()"/>
</div>
</body>
</html>
