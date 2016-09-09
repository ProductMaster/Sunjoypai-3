<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="u" uri="ueye"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0 ,user-scalable=no">
		<title>促销信息</title>
    	<meta name="Description" content="享居派，只给你最好的！" />
	    <link rel="shortcut icon" href="http://sunjoypai.com/resource/system/front/images/favicon.ico"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/setting.css"/>
		<script src="${pageContext.request.contextPath}/js/utils/common/viewport.js" type="text/javascript" charset="utf-8"></script>
		<script src="${pageContext.request.contextPath}/js/utils/seajs/sea.js" type="text/javascript" charset="utf-8" id="sea-js"></script>
		<script src="${pageContext.request.contextPath}/js/utils/common/config.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			seajs.use(PATH+'promotion');
		</script>
	</head>
	<body>
		<input type="text" class="main"/>
		<label class="delete"><img src="${pageContext.request.contextPath}/images/delete.png" alt="" /></label>
		<div class="saveBtn"><img src="${pageContext.request.contextPath}/images/saveBtn.png" alt="" /></div>
		<div id="foot" style="bottom:114px;position: absolute;margin:0;">CopyRight©2016 享居派 All Rights Reserved</div>
		<div class="foot">
			<span><img src="${pageContext.request.contextPath}/images/foot-p1-2.png"/></span>
			<span><img src="${pageContext.request.contextPath}/images/foot-p2.png"/></span>
			<span><img src="${pageContext.request.contextPath}/images/foot-p3.png"/></span>
			<span><img src="${pageContext.request.contextPath}/images/foot-p4.png"/></span>
		</div>
	</body>
</html>
