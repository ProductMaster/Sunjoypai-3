<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="u" uri="ueye"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0 ,user-scalable=no"/>
<title>我的关注</title>
<link href="${pageContext.request.contextPath}/css/base.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/amazeui.min.js" ></script>
<style type="text/css">
.fangda{display:none;  background:transparency;  position:fixed; top:0%; left:0%; width:100%; height:100%; margin:0 auto;  z-index:1;}
.fangda img { padding-top:10%;}
</style>
<script type="text/javascript">

$(document).ready(function(){
	$(".fangd").click(function(index){
		$(".fangda img").attr("src",$(this).attr("src"));
		$(".fangda").css("display","block").show(100);
		$(".footerbox").css("display","none");
		
	})
	$(".fangda").click(function(){
		$(".fangda").hide(200);
		$(".footerbox").css("display","block");
	})
})

</script>
</head>

<body>
<!--头部的开始-->
<!-- <div class="headTop">
	<a class="m_back" href=""><i class="iconfont">&#xe605;</i><span>返回</span></a>
	<h2>享居派</h2>
	<a class="r_back" href=""><i class="iconfont">&#xe606;</i></a>
</div> -->
<!--头部的结束-->

<div class="attentionTop">
	<i class="m_back"></i>
	<h2>我的关注</h2>
</div>
<div align="center" class="fangda"><img src="" width="80%"/></div>
<div class="practiceCase">
	<div class="practiceCase-list">
		<c:forEach items="${cases}" var="cases" varStatus="status">
			<dl>  
				<dt>
				
					<a href="${pageContext.request.contextPath}/store/storeDetail/${cases.storeInfo.id}">
					<c:if test="${cases.storeInfo.imgUrl== ''}">
					<img src="${pageContext.request.contextPath}/pic/porductImg-01.jpg" alt="" />
					</c:if>
					<c:if test="${cases.storeInfo.imgUrl!= ''}">
					<img  width="160" height="160" src="http://images.sunjoypai.com/store/${cases.storeInfo.photoPath}160X160_${cases.storeInfo.imgUrl}" alt="" />
					</c:if>
					</a>
				</dt>
				<dd><h3>${cases.storeInfo.storeName}</h3></dd>
				<dd>${cases.describe}</dd>
				<dd>
					<c:forEach items="${cases.imgs}" var="img" varStatus="status">
					<span><img width="160" height="160" class="fangd" src="http://images.sunjoypai.com/case/${cases.path}${cases.id}/160X160_${img.imgUrl}" alt="" /></span>
					</c:forEach>
				</dd>
			</dl>
		</c:forEach>

	</div>
</div>

<!--版权-->
<div class="copyright mb50">
	CopyRight©2016 享居派 All Rights Reserved
</div>

<!--底部菜单 开始-->
<div class="footerbox">
	<ul>
		<li><a href="${pageContext.request.contextPath}/store/index"><i class="iconfont">&#xe600;</i><span>列表</span></a></li>
		<li class="hover"><a href="${pageContext.request.contextPath}/user/myInterest"><i class="iconfont">&#xe603;</i><span>关注</span></a></li>
		<li><a href="${pageContext.request.contextPath}/store/addCase"><i class="iconfont">&#xe604;</i><span>上传</span></a></li>
		<li><a href="${pageContext.request.contextPath}/user/userCenter"><i class="iconfont">&#xe602;</i><span>我的</span></a></li>
	</ul>
</div>
<!--底部菜单 结束-->
 
</body>
</html>
