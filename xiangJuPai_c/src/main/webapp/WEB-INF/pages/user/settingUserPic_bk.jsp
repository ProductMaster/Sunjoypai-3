<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="u" uri="ueye"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0 ,user-scalable=no" />	
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>我的</title>
    <meta name="Description" content="享居派，只给你最好的！" />
    <link rel="shortcut icon" href="http://sunjoypai.com/resource/system/front/images/favicon.ico"/>
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/css.css" rel="stylesheet"/>
    <!--<link rel="stylesheet" type="text/css" href="${ctx}/resource/system/phone/css/scripts/utils/layer/need/layer.css"/>-->
    
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<%--     <script type="text/javascript" src="${pageContext.request.contextPath}/js/utils/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/utils/js.js"></script>
    <script src="${pageContext.request.contextPath}/js/utils/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/utils/layer/layer.js"></script> --%>
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
	    function doSubmit(){
	    	
	    	$('#validateForm').submit(); 
	    }
    </script>
  </head>
  <body>
    
    <!--主体-->
    <div class="sc_content">
       <div class="container">
           <div class="row">
           	   <div class="col-xs-3"></div>
               <div class="col-xs-6">
                   <form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/user/settingUserPic" enctype="multipart/form-data">
                        <div class="tx_img">
	                        <c:if test="${user.picUrl == null }">
	                            <img src="${user.logo}" alt="" width="299" height="299"/>
                            </c:if>
                            <c:if test="${user.picUrl != null }">
	                            <img src="http://images.sunjoypai.com/user/${photoPath}${userInfo.picUrl}" alt="" width="299" height="299"/>
                            </c:if>
                        </div>
                        <div class="tx_text">
                        点击头像更换
                        </div>
                        <div class="tx_sc">
                        <label for="sctx"><img src="${pageContext.request.contextPath}/images/tx_btn_03.png" alt=""/></label>
                        <input id="sctx" style="display:none;" name="uploadFile" type="file"/>
                        </div>
                        <a href="javascript:void(0)" onclick="return doSubmit();">
                        <div class="tx_bc" >
                        <label for="scbc"><img src="${pageContext.request.contextPath}/images/tx_btn_06.png" alt=""/></label>
                        <!-- <input id="scbc" style="display:none;" name="" type="submit"> -->
                        </div>
                        </a>
                   </form>
               </div>
               <div class="col-xs-3"></div>
           </div>
       </div>
    </div>
    <!--浮动导航-->
	    <div id="foot" style="bottom:50px;position: absolute;margin:0;">CopyRight©2016 享居派 All Rights Reserved</div>
		<div class="foot">
	       	<span><a href="${pageContext.request.contextPath}/store/index"><img src="${pageContext.request.contextPath}/images/foot-p1-2.png"/></a></span>
			<span><a href="${pageContext.request.contextPath}/user/myInterest"><img src="${pageContext.request.contextPath}/images/foot-p2.png"/></a></span>
			<span><a href="${pageContext.request.contextPath}/store/addCase"><img src="${pageContext.request.contextPath}/images/foot-p3.png"/></a></span>
			<span><a href="${pageContext.request.contextPath}/user/userCenter"><img src="${pageContext.request.contextPath}/images/foot-p4.png"/></a></span>
	   	</div> 
  </body>
</html>
