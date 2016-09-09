<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="u" uri="ueye"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0 ,user-scalable=no"/>
		<title>店铺设置</title>
    	<meta name="Description" content="享居派，只给你最好的！" />
	    <link rel="shortcut icon" href="http://sunjoypai.com/resource/system/front/images/favicon.ico"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/setting.css"/>
		<script src="${pageContext.request.contextPath}/js/utils/common/viewport.js" type="text/javascript" charset="utf-8"></script>
		<script src="${pageContext.request.contextPath}/js/utils/seajs/sea.js" type="text/javascript" charset="utf-8" id="sea-js"></script>
		<script src="${pageContext.request.contextPath}/js/utils/common/config.js" type="text/javascript" charset="utf-8"></script>
	    <style type="text/css">
	      body{background-color:#fafafa;}
	    	#foot{width:100%;position:absolute;bottom:9%;height:8.47%;text-align: center;font-size:0.75em;}
	
				.foot{width:100%;position: fixed;bottom:0;height:8.47%;background-color: #f3f3f3;}
				.foot span{width:24%;line-height: 113px;height:100%;text-align: center;position: relative;display: inline-block;padding-top:10px;;box-sizing: border-box;}
				.foot span img{width:auto;vertical-align: top;height:85%;}
	    </style>
		<script type="text/javascript">
			seajs.use(PATH+'shop_setting');
		</script>
	</head>
	<body>
		<div class="content">
			<div class="head" style="height:164px;border:none;" data-to="dptx">
				<label style="line-height: 164px;">店铺头图</label>
				<!-- <a href="../html/dptx.html"> -->
				<c:if test="${info.imgUrl == null || info.imgUrl ==''}">
					<img src="${pageContext.request.contextPath}/images/TX-6.png" alt="店铺头图" class="shop_head"/>
				</c:if>
				<c:if test="${info.imgUrl != null && info.imgUrl !=''}">
					<img src="${pageContext.request.contextPath}/images/store/${info.imgUrl}" alt="店铺头图" class="shop_head"/>
				</c:if>
				<!-- </a> -->
			</div>
		</div>
		<div class="content">
			<a href="${pageContext.request.contextPath}/store/settingStoreInfo">
			<div class="head" style="height:82px;" data-to="chg_name_dp">
				
				<font color="black">
				<label>店名</label>
				</font>
				<span>${info.storeName}</span>
				
			</div>
			</a>
			<a href="${pageContext.request.contextPath}/store/settingStoreInfo">
			<div class="head" style="height:82px;" data-to="promotion">
				
				<font color="black">
				<label>促销信息</label>
				</font>
				<span>${info.promotion}</span>
			</div>
			</a>
			<div class="head" style="height:82px;" data-to="address">
				<label>地址</label>
				<span>${info.addr}</span>
			</div>
			<a href="${pageContext.request.contextPath}/store/settingStoreInfo">
			<div class="head" style="height:82px;" data-to="mobile">
				
				<font color="black">
				<label>联系电话</label>
				</font>
				<span>${info.mobile}</span>
			</div>
			</a>
			<a href="${pageContext.request.contextPath}/store/settingStoreInfo">
			<div class="head" style="height:82px;border:none;" data-to="times">
				<font color="black">
				<label>营业时间</label>
				</font>
				<span>${info.opening}</span>
				
			</div>
			</a>
		</div>
		<div class="content">
			<div class="head" style="height:82px;border:none;">
				<label>店铺ID</label>
				<span>${info.id}</span>
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
