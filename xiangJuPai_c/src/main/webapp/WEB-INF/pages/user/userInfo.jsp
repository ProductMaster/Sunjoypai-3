<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="u" uri="ueye"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0 ,user-scalable=no"/>
<title>个人设置</title>
<link href="${pageContext.request.contextPath}/css/base.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/amazeui.min.js" ></script>
<script type="text/javascript">
		 function doSubmit(sex){
			 $('#u_sex').val(sex);
    	$('#validateForm').submit(); 
    }
	</script>
</head>

 
<body>
<!--头部的开始-->
<!-- <div class="headTop">
	<a class="m_back" href=""><i class="iconfont">&#xe605;</i><span>返回</span></a>
	<h2>个人设置</h2>
	<a class="r_back" href=""><i class="iconfont">&#xe606;</i></a>
</div> -->
<!--头部的结束-->
<div class="setUp">
	<ul>
		<li>
			<a href="${pageContext.request.contextPath}/user/settingUserPic">
				<span>头像</span>
				<c:if test="${userInfo.picUrl == null  || userInfo.picUrl ==''}">
                	<lb><img  width="70" height="70" src="${userInfo.logo}" alt="" /></lb>
                </c:if>
				<c:if test="${userInfo.picUrl != null &&  userInfo.picUrl != ''}">
                   <lb><img width="70" height="70" src="http://images.sunjoypai.com/user/${photoPath}160X160_${userInfo.picUrl}?rnd=<%=Math.random()%>" alt="" /></lb>
                </c:if>
				
			</a>
		</li>
		<li>
			<a href="${pageContext.request.contextPath}/user/settingInfo">
				<span>昵称</span><label>${userInfo.userName}</label>
			</a>
		</li>
		
		<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/user/updateUserInfo/${userInfo.id}" >
		<input tpye="hidden" value="" id="u_sex" name="sex"/>
		<li>
			<span>性别</span>
			<cite>
				
				<a  href="javascript:void(0)" onclick="return doSubmit(1);" class="${userInfo.sex==1?'hover':''}">男</a>
				<a  href="javascript:void(0)" onclick="return doSubmit(0);" class="${userInfo.sex==0?'hover':''}">女</a>
				
				
			</cite>
		</li>
		</form>
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
 <script>
$(function(){
		$('.setUp li cite a').click(function(){ 
			$(this).addClass('hover').siblings().removeClass('hover')
		})
	 })
</script> 
</body>
</html>
