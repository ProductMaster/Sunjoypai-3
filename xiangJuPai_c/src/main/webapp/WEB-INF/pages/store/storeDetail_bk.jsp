<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="u" uri="ueye"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0 ,user-scalable=no">
		<title>整单商品满3000元，即可立减500元现金 (每单限减一次)</title>
	    <meta name="Description" content="享居派，只给你最好的！" />
    	<link rel="shortcut icon" href="http://sunjoypai.com/resource/system/front/images/favicon.ico"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/product_detail.css"/>
		<script src="${pageContext.request.contextPath}/js/utils/common/viewport.js" type="text/javascript" charset="utf-8"></script>
		<script src="${pageContext.request.contextPath}/js/utils/seajs/sea.js" type="text/javascript" charset="utf-8" id="sea-js"></script>
		<script src="${pageContext.request.contextPath}/js/utils/common/config.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			seajs.use(PATH+'product_detail');
			
			function showAndHidden1(){  
				  var product=document.getElementById("product_list");  
				  var cases=document.getElementById("case_list");  
				  product.style.display='none';  
				  cases.style.display='inline';  
				} 
			function showAndHidden2(){  
				  var product=document.getElementById("product_list");  
				  var cases=document.getElementById("case_list");  
				  product.style.display='inline';  
				  cases.style.display='none';  
				} 
		</script>
	</head>
	<body>
		<div class="big_pic">
			<div class="shop_desc">
				<h1>${info.storeName}</h1>
				<p>${info.major}</p>
			</div>
			<img src="${pageContext.request.contextPath}/images/store/${info.imgUrl}" width="750" height="396" alt="" />
		</div>
		<div class="promotion">
			<img src="${pageContext.request.contextPath}/images/cu.png"/>
			<label> ${info.promotion}</label>
		</div>
		<div class="information">
			<img src="${pageContext.request.contextPath}/images/icons.jpg" alt="" class="left" />
			<div class="middle">
				<span>${info.addr}</span>
				<span>${info.mobile}</span>
				<span>${info.opening}</span>
			</div>
			<div class="right">
				
				<c:if test="${isInterest==1}">
				<img src="${pageContext.request.contextPath}/images/follow.png"/>
					<h1>已关注</h1>
				</c:if>
				<c:if test="${isInterest==0}">
					<a href = "${pageContext.request.contextPath}/user/interestStore/${info.id}" style='text-decoration:none;'>
					<img src="${pageContext.request.contextPath}/images/follow.png"/>
					<h1>关注</h1>
					</a>
				</c:if>
			</div>
		</div>
		<div class="channel">
			<span class="active" data="cases" onclick = "showAndHidden1()">实物案例</span>
			<span data="products" onclick = "showAndHidden2()">全部商品</span>
		</div>
		
		<ul class="case_list" id="case_list">
			<c:forEach items="${cases}" var="cases" varStatus="status">
			<li class="clearfix">
				<div class="cases">
					<img src="${pageContext.request.contextPath}/images/store/${info.imgUrl}" alt="" class="headPic" />
					<div class="detail">
						<h2 class="ellipsis">${cases.title}</h2>
						<p>${cases.describe}</p>
						<c:forEach items="${cases.imgs}" var="img" varStatus="status">
		              	<span class="col-xs-4"><a href="#"><img src="http://images.sunjoypai.com/case/${cases.path}${cases.id}/${img.imgUrl}" alt=""/></a></span>
		              </c:forEach>
					</div>
				</div>
			</li>
			</c:forEach>
		</ul>
		<ul class="product_list" id="product_list" style="display:none;">
			<c:forEach items="${catalogs}" var="catalog" varStatus="status">
			<li class="clearfix product">
				<div class="product">
					<img src="http://images.sunjoypai.com/product/${catalog.path}${catalog.picUrl}" alt="" />
					<h2 class="ellipsis">${catalog.catalogName}</h2>
					<h3>￥${catalog.salePrice}</h3>
				</div>
			</li>
			</c:forEach>
		</ul>

		<ul class="product">
			<div id="foot" style="margin-bottom:0;">CopyRight©2016 享居派 All Rights Reserved</div>
		</ul>


		<div class="foot1">
			<a href=""><img src="${pageContext.request.contextPath}/images/foot2.png"></a>
		</div>
	</body>

</html>
