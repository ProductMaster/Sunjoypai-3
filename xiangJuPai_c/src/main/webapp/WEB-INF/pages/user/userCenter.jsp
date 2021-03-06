<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="u" uri="ueye"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0 ,user-scalable=no"/>
<title>我的</title>
<link href="${pageContext.request.contextPath}/css/base.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/amazeui.min.js" ></script>

</head>


<body>
<!--头部的开始
<div class="headTop">
	<a class="m_back" href=""><i class="iconfont">&#xe605;</i><span>返回</span></a>
	<h2>我的</h2>
	<a class="r_back" href=""><i class="iconfont">&#xe606;</i></a>
</div>
头部的结束-->

<!--昵称-->
<div class="nickname">
	<dl>
		<dt>
			<c:if test="${userInfo.picUrl == null || userInfo.picUrl ==''}">
            	<a href="${pageContext.request.contextPath}/user/userInfo"><img src="${userInfo.logo}" alt=""/></a>
            </c:if>
            <c:if test="${userInfo.picUrl != null &&  userInfo.picUrl != ''}">
               <a href="${pageContext.request.contextPath}/user/userInfo"><img src="http://images.sunjoypai.com/user/${photoPath}160X160_${userInfo.picUrl}?rnd=<%=Math.random()%>" alt="" /></a>
            </c:if>
		</dt>
		<dd><h3>${userInfo.userName}(ID:${userInfo.id})</h3></dd>
		<dd>店铺:${storeInfo.storeName}</dd>
	</dl>
</div>

<div class="userList">
	<c:if test="${currentUser.userType==1}">
		<ul>  
			<li ><a href="${pageContext.request.contextPath}/user/getQRCode" ><i class="iconfont userList-ico1">&#xe607;</i>我的店铺二维码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
			<li><a href="${pageContext.request.contextPath}/store/listCatalog/0"><i class="iconfont userList-ico2">&#xe60f;</i>商品管理</a></li>
			<li><a href="${pageContext.request.contextPath}/store/listCase"><i class="iconfont userList-ico3">&#xe610;</i>案例管理</a></li>
		</ul>
		<ul>
			<li><a href="${pageContext.request.contextPath}/store/storeDetail/${storeInfo.id}"><i class="iconfont userList-ico4">&#xe60b;</i>查看店铺</a></li>
		</ul>
		<ul>
			<li><a href="${pageContext.request.contextPath}/store/storeInfo"><i class="iconfont userList-ico5">&#xe608;</i>店铺设置</a></li>
		</ul>
	</c:if>
	<c:if test="${currentUser.userType==0}">
		<ul>
			<li><a href="${pageContext.request.contextPath}/user/addStore"><i class="iconfont userList-ico4">&#xe60b;</i>激活店铺</a></li>
		</ul>
	</c:if>
</div>

<!--二维码弹出框-->
<div id="light" class="white_content">
	<img src="images/userEr.jpg" alt="" />
	<p>
		XXX瓷砖专卖
		<span>长按关注</span>
	</p>
</div> 
<div id="fade" class="black_overlay" onclick = "document.getElementById('light').style.display='none';document.getElementById('fade').style.display='none'"></div> 
		

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
