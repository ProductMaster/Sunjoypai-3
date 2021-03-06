<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="u" uri="ueye"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0 ,user-scalable=no"/>
<title>我的店铺LOGO</title>
<link href="${pageContext.request.contextPath}/css/base.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
  	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js" ></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/amazeui.min.js" ></script>
<script type="text/javascript">

	function clickFile(){
		$("#uploadFile").click();
	}
	 function doSubmit(){
	
		$('#validateForm').submit(); 
	}
   
 function previewPic(file)  
 {  
	 
	 if (file.files && file.files[0])  
	 {  
		var reader = new FileReader();  
		reader.onload = function(evt){  
			document.getElementById("previewDiv").innerHTML = '<img width="160" height="160" src="' + evt.target.result + '" />';
		};	    
		reader.readAsDataURL(file.files[0]);  
	 }  	 
 }  
 
	 
</script>
</head>


<body>
<!--头部的开始
<div class="headTop">
	<a class="m_back" href=""><i class="iconfont">&#xe605;</i><span>返回</span></a>
	<h2>店铺头图</h2>
	<a class="r_back" href=""><i class="iconfont">&#xe606;</i></a>
</div>
--头部的结束-->

<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/store/settingStorePic" enctype="multipart/form-data">
	<div class="headPortrait">
		<ul>
			<li>
				<c:if test="${info.imgUrl == null  || info.imgUrl ==''}">
					<a href="javascript:void(0)" onclick="clickFile()" class="headPortrait-img" >
                   <div id="previewDiv"><img  src="${pageContext.request.contextPath}/images/headPortrait.jpg" alt="" width="160" height="160"/></div>
                   </a>
                  </c:if>
                  <c:if test="${info.imgUrl != null && info.imgUrl != ''}">
                  <a href="javascript:void(0)" onclick="clickFile()" class="headPortrait-img" >
                   <div id="previewDiv"><img src="http://images.sunjoypai.com/store/${info.photoPath}160X160_${info.imgUrl}?rnd=<%=Math.random()%>" alt="" width="160" height="160"/></div>
                   </a>
                  </c:if>
				<p>点击头像更换</p>
				<input id="uploadFile" style="display:none;" name="uploadFile" type="file" onchange="previewPic(this);"/>
			</li>
			<li><a href="javascript:void(0)" onclick="return doSubmit();" class="headPortrait-link1">上传</a></li>
			<!-- <li><a href="" class="headPortrait-link2">保存</a></li> -->
		</ul>
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
