<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="u" uri="ueye"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0 ,user-scalable=no"/>
<title>商品管理</title>
<link href="${pageContext.request.contextPath}/css/base.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
<!--头部的开始
<div class="headTop">
	<a class="m_back" href=""><i class="iconfont">&#xe605;</i><span>返回</span></a>
	<h2>享居派</h2>
	<a class="r_back" href=""><i class="iconfont">&#xe606;</i></a>
</div>
头部的结束-->

<div class="attentionTop">
	<i class="m_back"></i>
	<ul>
		<li><a href="${pageContext.request.contextPath}/store/listCatalog/0">已上架</a></li>
		<li class="hover"><a href="${pageContext.request.contextPath}/store/listCatalog/1">已下架</a></li>
	</ul>
	<%-- <a href="${pageContext.request.contextPath}/store/newCatalog" class="r_back">增加</a> --%>
</div>


<div class="contentbox">
	<ul>
		<c:forEach items="${catalogs}" var="catalog" varStatus="status">
			<li>
				<a href="" title=""><img width="340" height="250" src="http://images.sunjoypai.com/product/${catalog.path}340X250_${catalog.picUrl}" alt=""/></a>
				<a href="" title=""><p>${catalog.catalogName}</p></a>
				<span>
					￥${catalog.salePrice}
					<a href="${pageContext.request.contextPath}/store/updateCatalogStatus/${catalog.id}" class="contentbox-link2">上架</a>
					<a href="${pageContext.request.contextPath}/store/deleteCatalog/${catalog.id}" class="contentbox-link1">删除</a>
				</span>
			</li>
		</c:forEach>		
	</ul>
</div>

<!--版权-->
<div class="copyright mb50">
	CopyRight©2016 享居派 All Rights Reserved
</div>

<!--底部菜单 开始-->
<div class="footerbox">
	<ul>
		<li><a href="${pageContext.request.contextPath}/store/index"><i class="iconfont">&#xe600;</i><span>列表</span></a></li>
		<li><a href="${pageContext.request.contextPath}/user/myInterest"><i class="iconfont">&#xe603;</i><span>关注</span></a></li>
		<li><a href="${pageContext.request.contextPath}/store/addCase"><i class="iconfont">&#xe604;</i><span>上传</span></a></li>
		<li class="hover"><a href="${pageContext.request.contextPath}/user/userCenter"><i class="iconfont">&#xe602;</i><span>我的</span></a></li>
	</ul>
</div>
<!--底部菜单 结束-->
 
</body>
</html>
