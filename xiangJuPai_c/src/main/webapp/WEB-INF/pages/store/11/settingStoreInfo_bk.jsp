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
    	<meta name="Description" content="享居派，只给你最好的！" />
	    <link rel="shortcut icon" href="http://sunjoypai.com/resource/system/front/images/favicon.ico"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/setting.css"/>
<%-- 		<script src="${pageContext.request.contextPath}/css/utils/common/viewport.js" type="text/javascript" charset="utf-8"></script>
		<script src="${pageContext.request.contextPath}/css/utils/seajs/sea.js" type="text/javascript" charset="utf-8" id="sea-js"></script>
		<script src="${pageContext.request.contextPath}/css/utils/common/config.js" type="text/javascript" charset="utf-8"></script> --%>
	  	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/amazeui.min.js" ></script>
	    <style type="text/css">
	      body{background-color:#fafafa;}
	    	#foot{width:100%;position:absolute;bottom:9%;height:8.47%;text-align: center;font-size:0.75em;}
	
				.foot{width:100%;position: fixed;bottom:0;height:8.47%;background-color: #f3f3f3;}
				.foot span{width:24%;line-height: 113px;height:100%;text-align: center;position: relative;display: inline-block;padding-top:10px;;box-sizing: border-box;}
				.foot span img{width:auto;vertical-align: top;height:85%;}
	    </style>
		<script type="text/javascript">
			seajs.use(PATH+'chg_name');
		</script>
		<script type="text/javascript">
			function doSubmit(){
				var name = $("#storeName");
				if(name==null||name.val().trim()==""){
					alert("名称不能为空！");
					return false;
				}
				var promotion = $("#promotion");
				if(promotion==null||promotion.val().trim()==""){
					alert("促销信息不能为空！");
					return false;
				}
				var mobile = $("#mobile");
				if(mobile==null||mobile.val().trim()==""){
					alert("联系电话不能为空！");
					return false;
				}
	    		$('#validateForm').submit(); 
	    	}
		</script>
	</head>
	<body>
	<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/store/updateStoreInfo/${sInfo.id}" >
		<div style="margin-top:0px;">
		店铺名称：<input type="text" class="main" name="storeName" id="storeName" value = "${sInfo.storeName}" /><br/>
		促销信息：<input type="text" class="main" name="promotion" id="promotion" value = "${sInfo.promotion}" /><br/>
		联系电话：<input type="text" class="main" name="mobile" id="mobile" value = "${sInfo.mobile}" />
		</div>
		<label class="delete"><img src="${pageContext.request.contextPath}/images/delete.png" alt="" /></label>
		<a href="javascript:void(0)" onclick="return doSubmit();">
		<div class="saveBtn" style="margin-top:10px;">
			
			<img src="${pageContext.request.contextPath}/images/saveBtn.png" alt="" />
			
		</div>
		</a>
		<div id="foot" style="bottom:114px;position: absolute;margin:0;">CopyRight©2016 享居派 All Rights Reserved</div>
		</form>
		<div class="foot">
        	<span><a href="${pageContext.request.contextPath}/store/index"><img src="${pageContext.request.contextPath}/images/foot-p1-2.png"/></a></span>
			<span><a href="${pageContext.request.contextPath}/user/myInterest"><img src="${pageContext.request.contextPath}/images/foot-p2.png"/></a></span>
			<span><a href="${pageContext.request.contextPath}/store/addCase"><img src="${pageContext.request.contextPath}/images/foot-p3.png"/></a></span>
			<span><a href="${pageContext.request.contextPath}/user/userCenter"><img src="${pageContext.request.contextPath}/images/foot-p4.png"/></a></span>
    	</div>
	</body>
</html>
