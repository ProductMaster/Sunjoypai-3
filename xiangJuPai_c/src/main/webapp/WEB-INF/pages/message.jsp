<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="u" uri="ueye"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="format-detection" content="telephone=no">
    <title>提示</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/amazeui.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css"/>
    <style>
    	body{
    		background: #f6f6f6;
    	}
    </style>
</head>
<body>
	<div class="am-g">
		<div class="am-text-center success-txt">${message}</div>		
	</div>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js" ></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/amazeui.min.js" ></script>
	
</body>
</html>