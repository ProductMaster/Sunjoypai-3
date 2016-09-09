<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="u" uri="ueye"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0 ,user-scalable=no">
<title>外墙刷新</title>
<link href="${pageContext.request.contextPath}/css/base.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/info-style.css" rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.1.min.js"></script> 
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
              "sourcePage":"外墙刷新"
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
	<h2>享居派直营</h2>
</div>
头部的结束-->    
<div class="renovationBanner">
	<img src="${pageContext.request.contextPath}/pic/renovation_banner1.jpg"  alt="" />
	<p>外墙刷新</p>
</div>

<div class="renovationBox">
	<h3>服务介绍</h3>
	<div class="renovationAbout">
		可选择家用刷墙或商用刷墙，墙面刷漆服务选用享居派水性环保光触媒硅藻面层涂料，欧洲进口涂料，易擦涂易清洗；纳米硅藻涂料，净化有害物质；色彩丰富多彩，装饰多彩空间。	
	</div>
	<h3>参考报价</h3>
	<div class="renovationTable">
		<ul>
			<li><span>艺术涂料</span><label>￥39-￥249/㎡</label></li>
		</ul>
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
 <p>&nbsp;</p>
<div class="indexSearch-form">
  <input name="mobile" id="mobile" class="indexSearch-form-input" placeholder="输入手机号立即预约，24小时极速反馈！" />
   <input name="" type="submit" class="indexSearch-form-button" value="立即预约"   onclick="addAppoint()"/>
</div>
</body>
</html>
