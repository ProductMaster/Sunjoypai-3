<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="u" uri="ueye"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0 ,user-scalable=no">
<title>智能家居</title>
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
              "sourcePage":"智能家居"
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
	<img src="${pageContext.request.contextPath}/pic/renovation_banner2.jpg"  alt="" />
	<p>智能家居</p>
</div>

<div class="renovationBox">
	<h3>服务介绍</h3>
	<div class="renovationAbout">
		开启未来智慧生活，打造全新生活模式，形成智能家居自选超市，让您瞬间体验智能生活带来的便捷和乐趣，拥有更安全、更健康、更舒适的家。	
	</div>
	<h3>服务内容</h3>
	<div class="renovationTable">
		<dl>
			<dt>场景控制-Scene Control</dt>
			<dd>用户可以自定义多个场景模式，只需轻轻一按，即可轻松一次性完成家中多个设备的控制，从此告别逐个开关的繁琐操控方式。</dd>
		</dl>
		<dl>
			<dt>联动控制-Linkage Control</dt>
			<dd>智能家居设备之间存在联动关系，用户可以根据需要自由组合使用，构建属于自己的个性化智能家居系统。</dd>
		</dl>
		<dl>
			<dt>远程控制-Remote Control</dt>
			<dd>随时随地，用户都可以通过接入互联网的手机或者平板电脑等，连接至您的智能家居系统，轻松控制家中的智能设备。</dd>
		</dl>
		<dl>
			<dt>定时控制-Timing Control</dt>
			<dd>可按照用户每日的生活、工作习惯设定各种丰富的定时功能，满足各种对定时开关有需求的客户。功能强大，操作简单，让您的生活更加规律。</dd>
		</dl>
	</div>
	<h3>服务特色</h3>
	<div class="renovationCh">
		<dl>
			<dt>安全</dt>
			<dd>双重加密技术能更好保护您的隐私和居家生活安全</dd>
		</dl>
		<dl>
			<dt>健康</dt>
			<dd>通过智能设备的监测，可随时掌握家人的身体健康</dd>
		</dl>
		<dl>
			<dt>低碳</dt>
			<dd>将太阳能转换为家庭使用的电能和水能，监测并控制用量</dd>
		</dl>
		<dl>
			<dt>智慧</dt>
			<dd>远程一键启动，自定义情景模式，根据行为数据自主执行</dd>
		</dl>
	</div>
	<h3>服务流程</h3>
	<div class="renovationProcess">
		<ul>
			<li class="Process-1">1.咨询定制</li>
			<li class="Process-2">4.智能换新</li>
			<li class="Process-3">2.需求确认</li>
			<li class="Process-7">5.售后跟踪</li>
			<li class="Process-5">3.上门服务</li>
		</ul>
	</div>
</div>
 <p>&nbsp;</p> <p>&nbsp;</p> <p>&nbsp;</p>
<div class="indexSearch-form">
  <input name="mobile" id="mobile" class="indexSearch-form-input" placeholder="输入手机号立即预约，24小时极速反馈！" />
  <input name="" type="submit" class="indexSearch-form-button" value="立即预约"   onclick="addAppoint()"/>
</div>
</body>
</html>
