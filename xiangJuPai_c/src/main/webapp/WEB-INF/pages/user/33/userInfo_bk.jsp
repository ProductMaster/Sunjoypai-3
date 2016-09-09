<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="u" uri="ueye"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0 ,userInfo-scalable=no" />
		<title>个人信息</title>
    	<meta name="Description" content="享居派，只给你最好的！" />
	    <link rel="shortcut icon" href="http://sunjoypai.com/resource/system/front/images/favicon.ico"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/setting.css"/>
		<script src="${pageContext.request.contextPath}/js/utils/common/viewport.js" type="text/javascript" charset="utf-8"></script>
		<script src="${pageContext.request.contextPath}/js/utils/seajs/sea.js" type="text/javascript" charset="utf-8" id="sea-js"></script>
		<script src="${pageContext.request.contextPath}/js/utils/common/config.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			seajs.use(PATH+'setting');
		</script>
	</head>
	<body>
		<div class="content">
			<div class="head" style="height:164px;">
				<label style="line-height: 164px;">头像</label>
				<a href="${pageContext.request.contextPath}/user/settingUserPic">
				<c:if test="${userInfo.picUrl == null  || userInfo.picUrl ==''}">
                	<img src="${userInfo.logo}" alt="" />
                </c:if>
				<c:if test="${userInfo.picUrl != null }">
                   <img src="http://images.sunjoypai.com/user/${photoPath}${userInfo.picUrl}" alt="" />
                </c:if>
				</a>
			</div>
			<a href="${pageContext.request.contextPath}/user/settingInfo">
				<div class="head" style="height:82px;">
					<label>昵称</label>
					<span>${userInfo.userName}</span>
				</div>
			</a>
			<div class="head2">
				<label>性别</label>
				<div class="sexBox">
					<c:if test="${userInfo.sex==1}">
					<div class="sex active">男</div>
					<div class="sex">女</div>
					</c:if>
					<c:if test="${userInfo.sex==0}">
					<div class="sex">男</div>
					<div class="sex active">女</div>
					</c:if>
				</div>
			</div>
		</div>
		<div id="foot" style="bottom:114px;position: absolute;margin:0;">CopyRight©2016 享居派 All Rights Reserved</div>
		<div class="foot">
        	<span><a href="${pageContext.request.contextPath}/store/index"><img src="${pageContext.request.contextPath}/images/foot-p1-2.png"/></a></span>
			<span><a href="${pageContext.request.contextPath}/user/myInterest"><img src="${pageContext.request.contextPath}/images/foot-p2.png"/></a></span>
			<span><a href="${pageContext.request.contextPath}/store/addCase"><img src="${pageContext.request.contextPath}/images/foot-p3.png"/></a></span>
			<span><a href="${pageContext.request.contextPath}/user/userCenter"><img src="${pageContext.request.contextPath}/images/foot-p4.png"/></a></span>
    	</div>
	</body>
</html>
