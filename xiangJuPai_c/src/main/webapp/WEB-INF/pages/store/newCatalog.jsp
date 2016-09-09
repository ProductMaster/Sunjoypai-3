<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="u" uri="ueye"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0 ,user-scalable=no"/>
<title>添加商品</title>
<link href="${pageContext.request.contextPath}/css/base.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
	  	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/amazeui.min.js" ></script>
		<script type="text/javascript">
			/* seajs.use(PATH+'add_product'); */
			
		</script>
		<script type="text/javascript">
		
			function clickFile(){
	
				 $("#uploadFile").click();
				 
			}
			 function doSubmit(){
				 var catalogName = $('#catalogName').val();
				    if(catalogName==""){ 
						alert("请输入商品名称！"); 		 
						return false; 
					}
	    	
				    var textfield = $('#textfield').val();
				    if(textfield==""){ 
						alert("请输入商品描述！"); 		 
						return false; 
					}
		    	$('#validateForm').submit(); 
	    	}
			 
			
			 
			  function previewPic(file)  
 {  
	 
	 if (file.files && file.files[0])  
		 {  
			var reader = new FileReader();  
			reader.onload = function(evt){  
				document.getElementById("previewDiv").innerHTML = '<img width="80" height="80" src="' + evt.target.result + '" />';
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
	<h2>享居派</h2>
	<a class="r_back" href=""><i class="iconfont">&#xe606;</i></a>
</div>
头部的结束-->
<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/store/addCatalog" enctype="multipart/form-data">
	<input type="file" style="display: none;" name="uploadFile" id="uploadFile" onchange="previewPic(this);"  multiple="multiple" accept="image/*"/>
	<div class="attentionTop">
		<i class="m_back"></i>
		<h2>添加商品</h2>
		<a href="javascript:void(0)" onclick="return doSubmit();" class="r_back">上架</a>
	</div>
	
	<div class="upload">
		商品名称：<input type="text" name="catalogName" id="catalogName" placeholder="请输入商品名称" /><br/><br/>
		<textarea name="catalogDesc"  placeholder="输入商品描述，12字内..." id="textfield"></textarea>
		<div class="uploadImg"  id="previewDiv">
			<ul>
				<li><a href="javascript:void(0)" onclick="clickFile()"><i class="iconfont">&#xe611;</i></a></li>
			</ul>
		</div>
	</div>
	
	<div class="priceSelected">
		<span>商品价格（选填）：</span>
		<label><input name="salePrice" type="" placeholder="0.00" />元</label>
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
