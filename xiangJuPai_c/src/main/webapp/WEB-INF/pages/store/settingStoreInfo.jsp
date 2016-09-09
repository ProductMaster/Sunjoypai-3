<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="u" uri="ueye"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0 ,user-scalable=no"/>
<title>修改店铺信息</title>
<link href="${pageContext.request.contextPath}/css/base.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/amazeui.min.js" ></script>
		<script type="text/javascript">
			 function doSubmit(){	    	
	    		$('#validateForm').submit(); 
	    	}

			function doClear(s_name){
    	
    	$('#'+s_name).val(""); 
    }
		</script>
</head>


<body>
<!--头部的开始-->
<!-- <div class="headTop">
	<a class="m_back" href=""><i class="iconfont">&#xe605;</i><span>返回</span></a>
	<h2>修改店铺信息</h2>
	<a class="r_back" href=""><i class="iconfont">&#xe606;</i></a>
</div> -->
<!--头部的结束-->
<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/store/updateStoreInfo/${sInfo.id}" >
	<div class="storeName">
		<ul>
			<li>
				<c:if test="${type == 1}">
					<span>店铺名称：<input type="text" class="main" name="storeName" id="storeName" value = "${sInfo.storeName}" /></span><i class="iconfont" onclick="doClear('storeName')">&#xe60c;</i>
				</c:if>	
				<c:if test="${type == 2}">
					<span>促销信息：<input type="text" class="main" name="promotion" id="promotion" value = "${sInfo.promotion}" /></span><i class="iconfont" onclick="doClear('promotion')">&#xe60c;</i>
				</c:if>	
				<c:if test="${type == 3}">
					<span>地址：<input type="text" class="main" name="addr" id="addr" value = "${sInfo.addr}" /></span><i class="iconfont" onclick="doClear('addr')">&#xe60c;</i>
				</c:if>	
				<c:if test="${type == 4}">
					<span>联系电话：<input type="text" class="main" name="mobile" id="mobile" value = "${sInfo.mobile}" /></span><i class="iconfont" onclick="doClear('mobile')">&#xe60c;</i>
				</c:if>		
				<c:if test="${type == 5}">
					<span>营业时间：<input type="text" class="main" name="opening" id="opening" value = "${sInfo.opening}" /></span><i class="iconfont" onclick="doClear('opening')">&#xe60c;</i>
				</c:if>			
				<c:if test="${type == 6}">
					<span>主营：<input type="text" class="main" name="major" id="major" value = "${sInfo.major}" /></span><i class="iconfont" onclick="doClear('major')">&#xe60c;</i>
				</c:if>																			

			</li>
		</ul>
	</div>

	<div class="storeName-button">
		<a href="javascript:void(0)" onclick="return doSubmit();">保存</a>
	</div>
</form>


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
