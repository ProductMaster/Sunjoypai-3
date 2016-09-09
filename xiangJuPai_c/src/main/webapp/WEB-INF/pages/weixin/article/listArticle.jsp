<%@page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="u" uri="ueye" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv=Content-Type content="text/html;charset=utf-8">
		<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="format-detection" content="telephone=no">
<title>资讯</title>
<link href="../css/base.css" rel="stylesheet" type="text/css" />
<link href="../css/common.css" rel="stylesheet" type="text/css" />
<link href="../css/page_index.css" rel="stylesheet" type="text/css" />
<jsp:include page="/common/header.jsp"></jsp:include>
<style>
.headbg {
background: url(http://www.liwuka.com.cn/images/headbg.png) no-repeat top center;
height: 136px;
width: 100%;
}
</style>
</head>
<body >
<!--导航的结束   banner的开始-->
<div class="inBanner" >
<img src="/images/hf_mobile_head.jpg" width="100%" />
</div>
<!--banner的结束  中间内容的开始-->
<div class="wrap" style="margin-top:0px;">
	<!--左边的开始-->
	<!--左边的结束  右边的开始-->
	<div class="sideR" >
		<div class="rightIn">
			<div class="productTitle">
			<p>${title}</p>
			</div>
			<div class="newsIn" style="padding:10px 10px 10px 10px;">
				<ul>
					<c:forEach var="article" items="${articles}">
						<li><a href="${pageContext.request.contextPath}/weixin/article/showArticle/${article.id}?weixinId=${weixinId}" >${article.title}</a></li>
					</c:forEach>
				</ul>
			</div>
			
		</div>
	</div>
	<!--右边的结束-->

</div>

<!--中间内容的结束  尾部的开始-->
<jsp:include page="/common/weixinfooter.jsp" />
<!--尾部的结束-->
</body>
</html>
