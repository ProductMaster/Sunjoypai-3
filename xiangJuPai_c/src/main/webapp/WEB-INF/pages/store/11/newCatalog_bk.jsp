<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="u" uri="ueye"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0 ,user-scalable=no">
		<title>上传商品</title>
    	<meta name="Description" content="享居派，只给你最好的！" />
	    <link rel="shortcut icon" href="http://sunjoypai.com/resource/system/front/images/favicon.ico"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/upload.css"/>
	  	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/amazeui.min.js" ></script>
<%-- 		<script src="${pageContext.request.contextPath}/js/utils/common/viewport.js" type="text/javascript" charset="utf-8"></script>
		<script src="${pageContext.request.contextPath}/js/utils/seajs/sea.js" type="text/javascript" charset="utf-8" id="sea-js"></script>
		<script src="${pageContext.request.contextPath}/js/utils/common/config.js" type="text/javascript" charset="utf-8"></script> --%>
		<script type="text/javascript">
			/* seajs.use(PATH+'add_product'); */
			
		</script>
		<script type="text/javascript">
		
			function clickFile(){
	
				 $("#uploadFile").click();
				 
			}
			 function doSubmit(){
	    	
		    	$('#validateForm').submit(); 
	    	}
			 
			 
		</script>
	</head>
	<body>
		<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/store/addCatalog" enctype="multipart/form-data">
			<input type="file" style="display: none;" name="uploadFile" id="uploadFile" multiple="multiple" accept="image/*">
			<div class="title">
				<div id="upload">
					<a href="javascript:void(0)" onclick="return doSubmit();">
						<img src="${pageContext.request.contextPath}/images/publish.png" alt="" />
					</a>
				</div>
			</div>
			<div class="content">
				商品名称：<input type="text" name="catalogName" id="catalogName" /><br/><br/>
				<textarea name="catalogDesc" id="desc" placeholder="请输入商品描述，12字内……" maxlength="13"></textarea>
				<div class="picList clearfix">
					<a href="javascript:void(0)" onclick="clickFile()">
					<img src="${pageContext.request.contextPath}/images/addPIc.jpg" alt="" id="addBtn" class="pics"/>
					</a>
				</div>
				<div id="demo">
				    <div id="as" ></div>
				</div>
			</div>
			<div class="priceC">
				<label class="left">商品价格（选填）：</label>
				
				<label class="right">元</label>
				<input type="number" name="salePrice" placeholder="0.00" id='price'/>
			</div>
		</form>
		<div id="foot" style="bottom:114px;position: absolute;margin:0;">CopyRight©2016 享居派 All Rights Reserved</div>
		<div class="foot">
       		<span><a href="${pageContext.request.contextPath}/store/index"><img src="${pageContext.request.contextPath}/images/foot-p1-2.png"/></a></span>
			<span><a href="${pageContext.request.contextPath}/user/myInterest"><img src="${pageContext.request.contextPath}/images/foot-p2.png"/></a></span>
			<span><a href="${pageContext.request.contextPath}/store/addCase"><img src="${pageContext.request.contextPath}/images/foot-p3.png"/></a></span>
			<span><a href="${pageContext.request.contextPath}/user/userCenter"><img src="${pageContext.request.contextPath}/images/foot-p4.png"/></a></span>
    	</div>
	</body>
</html>
