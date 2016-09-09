<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="u" uri="ueye"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0 ,user-scalable=no"/>
<title>${info.storeName}</title>
<link href="${pageContext.request.contextPath}/css/base.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
<style type="text/css">
.fangda{display:none;  background:rgba(0,0,0,52);  position:fixed; top:0%; left:0%; width:100%; height:100%; margin:0 auto;  z-index:1;}
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

<div class="shopTitle">
	<c:if test="${info.imgUrl== ''}">
	<img src="${pageContext.request.contextPath}/pic/porductImg-01.jpg" style="width:100%; height:300px;" alt="" />
	</c:if>
	<c:if test="${info.imgUrl!= ''}">
	<img src="http://images.sunjoypai.com/store/${info.path}${info.imgUrl}" style="width:100%; height:300px;" alt="" />
	</c:if>
	<div class="shopTitle-in">
		<h3>${info.storeName}</h3>
		<p>${info.major}</p>
	</div>
</div>

<div class="shoPromotions">
	<i>促</i>${info.promotion}
</div>

<div class="shopAbout">
	<dl>
		<dt>
				<c:if test="${isInterest==1}">
				<i class="iconfont">&#xe60e;</i>
					<p>已关注</p>
					<p><span>粉丝</span>${interestNum}</p>
				
				</c:if>

				<c:if test="${isInterest==0}">
				<a href = "${pageContext.request.contextPath}/user/interestStore/${info.id}" style='text-decoration:none;'>
				<i class="iconfont">&#xe60d;</i>
					<p>关注</p>
					<p><span>粉丝</span>${interestNum}</p>
					
				
					
					
				</a>
				</c:if>
				
			
		</dt>
		<dd>
			<p><i class="iconfont">&#xe601;</i><span>${info.addr}</span></p>
			<p><i class="iconfont">&#xe60a;</i><span>${info.mobile}</span></p>
			<p><i class="iconfont">&#xe609;</i><span>${info.opening}</span></p>
		</dd>
	</dl>
</div>

<!---->
<div class="practiceCase">

	<div align="center" class="fangda"><img src="" width="80%"/></div>
	<div class="practiceCase-title">
		<ul class="hctabs_nav">
			<li class="hover"><a href="javaScript:void();" title="实物案例">实物案例</a></li>
			<li><a href="javaScript:void();" title="全部商品">全部商品</a></li>
		</ul>
	</div>
	
	<div class="hctabs_info">
		<!--实物案例-->
		<div class="practiceCase-list">
		<c:forEach items="${cases}" var="cases" varStatus="status">
			<dl>
				<dt>
					<c:if test="${cases.storeInfo.imgUrl!=''}">
						<a href="${pageContext.request.contextPath}/store/storeDetail/${cases.storeInfo.id}"><img width="160" height="160" src="http://images.sunjoypai.com/store/${cases.storeInfo.photoPath}160X160_${cases.storeInfo.imgUrl}" alt="" /></a>
					</c:if>
					<c:if test="${cases.storeInfo.imgUrl==''}">
						<img width="160" height="160" src="http://images.sunjoypai.com/store/default_storeInfo.png" alt="" />
					</c:if>
				</dt>
				<dd><h3>${info.storeName}</h3></dd>
				<dd>${cases.describe}</dd>
				<dd>
					<c:forEach items="${cases.imgs}" var="img" varStatus="status">
						<span><img width="160" height="160" class="fangd" src="http://images.sunjoypai.com/case/${cases.path}${cases.id}/160X160_${img.imgUrl}" alt=""/></span>
					</c:forEach>
				</dd>
			</dl>
			</c:forEach>
		</div>
		<!--全部商品-->
		<div class="contentbox none">
			<ul>
				<c:forEach items="${catalogs}" var="catalog" varStatus="status">
				<li>
					<a href="" title=""><img width="340" height="250" src="http://images.sunjoypai.com/product/${catalog.path}340X250_${catalog.picUrl}" alt=""></a>
					<a href="" title=""><p>${catalog.catalogName}</p></a>
					<span>￥${catalog.salePrice}</span>
				</li>
				</c:forEach>
			</ul>
		</div>
	</div>


</div>

<!--入驻-->
<div class="settled">
	<span>高效推广，点此急速入驻 >>></span>
	<a href="">入驻</a>
</div>

<!--版权-->
<div class="copyright mb50">
	CopyRight©2016 享居派 All Rights Reserved
</div>
 
</body>
</html>
