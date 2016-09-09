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
<link href="${pageContext.request.contextPath}/css/base.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />

</head>


<body>
<!--头部的开始
<div class="headTop">
	<a class="m_back" href=""><i class="iconfont">&#xe605;</i><span>返回</span></a>
	<h2>设置</h2>
	<a class="r_back" href=""><i class="iconfont">&#xe606;</i></a>
</div>
头部的结束-->
<div class="setUp">
	<ul>
		<li>
			<a href="${pageContext.request.contextPath}/store/settingStorePic">
				<span>店铺头图</span>
				<label>
				<c:if test="${info.imgUrl == null || info.imgUrl ==''}">
					<img id="preview" width="70" height="70" src="${pageContext.request.contextPath}/images/headPortrait.jpg" alt="" />
				</c:if>
				<c:if test="${info.imgUrl != null && info.imgUrl !=''}">
					<img id="preview" width="70" height="70" src="http://images.sunjoypai.com/store/${info.photoPath}160X160_${info.imgUrl}?rnd=<%=Math.random()%>" />
				</c:if>
				</label>
			</a>
		</li>
	</ul>
	<ul>
		<li>
			<a href="${pageContext.request.contextPath}/store/settingStoreInfo/1">
				<span>店名</span><label>${info.storeName}&nbsp;</label>
			</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/store/settingStoreInfo/2">
				<span>促销信息</span><label>${info.promotion}&nbsp;</label>
			</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/store/settingStoreInfo/3">
				<span>地址</span><label>${info.addr}&nbsp;</label>
			</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/store/settingStoreInfo/4">
				<span>联系电话</span><label>${info.mobile}&nbsp;</label>
			</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/store/settingStoreInfo/5">
				<span>营业时间</span><label>${info.opening}&nbsp;</label>
			</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/store/settingStoreInfo/6">
				<span>主营</span><label>${info.major}&nbsp;</label>
			</a>
		</li>		
	</ul>
	<ul>
		<li>
			
				<span>店铺ID</span><p align="right">${info.id}</p>
			
		</li>
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
