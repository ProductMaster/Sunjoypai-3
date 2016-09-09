<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="u" uri="ueye"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0 ,user-scalable=no">
<title>派叔焕新</title>
<link href="${pageContext.request.contextPath}/css/base.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/info-style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/owl.carousel.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/owl.theme.css">

<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.1.min.js"></script> 
<script src="${pageContext.request.contextPath}/js/owl.carousel.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
  $("#owl-demo").owlCarousel({
    items : 1,
    lazyLoad : true,
   
    autoPlay: 3000
  }); 
 
});

  function addAppoint() {

          var mobile = $('#mobile').val();		
          if(mobile==""){ 
            alert("请输入您的手机号码！"); 		 
            return false; 
          }
          var telReg = !!mobile.match(/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/);
            //如果手机号码不能通过验证
            if(telReg == false){
              alert("手机号码格式不正确,请检查后重新提交!");
              return false;
            }
				   $.ajax({   
				    type : "POST",   
				    url : "http://m.sunjoypai.com/appoint/addMobileApplication", 
				    data : {
				     "mobile": $("#mobile").val(),
              "sourcePage":"主页"
				     },  
				    dataType: "json",   
				    success : function(data) {   
			         	if(data.success == "1"){
		                  alert("预约成功！");
		                }else{
		                  alert("您已提交过啦，请不要重复提交！");
		                }
				    },   
				    error :function(){   
				       alert("error");
      		}  
				});
 
  }
</script>
</head>

<body>
<!--头部的开始
<div class="headTop">
	<a href="javascript:history.go(-1);" class="m_back"><i class="iconfont">&#xe604;</i><span>返回</span></a>
	<h2>享居派直营</h2>
</div>
头部的结束-->
<!--    
<div class="renovationBanner">
	<img src="${pageContext.request.contextPath}/pic/indexBanner.jpg"  alt="" />
</div>
-->
<div class="banner">
	<div class="owl-carousel" id="owl-demo" >
			<a href="http://mp.weixin.qq.com/s?__biz=MzI5MDA2OTc1NA==&mid=2650674674&idx=1&sn=d16d7f82dc4137a08743aee41d9ffb2a#rd" class="item"><img src="${pageContext.request.contextPath}/pic/img-1.jpg"></a>
			<a href="http://mp.weixin.qq.com/s?__biz=MzI5MDA2OTc1NA==&mid=2650674711&idx=1&sn=ef37fc7769acdcb09cf68079ecc53785#rd" class="item"><img src="${pageContext.request.contextPath}/pic/img-2.jpg"></a>
			<a href="http://mp.weixin.qq.com/s?__biz=MzI5MDA2OTc1NA==&mid=2650674714&idx=1&sn=7bae37d5d8d06e7c1355de7f6e294c5a#rd" class="item"><img src="${pageContext.request.contextPath}/pic/img-3.jpg"></a>			
	</div>
</div>

<div class="navbox">
	<ul>
		<li><a href="${pageContext.request.contextPath}/info/neiqiang.html"><i class="bookmark"></i><p>内墙翻新</p></a></li>
		<li><a href="${pageContext.request.contextPath}/info/waiqiang.html"><i class="tags"></i><p>外墙刷新</p></a></li>
		<li><a href="${pageContext.request.contextPath}/info/zhineng.html"><i class="thumbs-o-up"></i><p>智能家居</p></a></li>
		<li><a href=""><i class="stack-exchange"></i><p>敬请期待</p></a></li>
		<li><a href=""><i class="stack-exchange"></i><p>敬请期待</p></a></li>
		<li><a href=""><i class="stack-exchange"></i><p>敬请期待</p></a></li>
		<li><a href=""><i class="stack-exchange"></i><p>敬请期待</p></a></li>
		<li><a href=""><i class="stack-exchange"></i><p>敬请期待</p></a></li>
	</ul>
</div>

<!--搜索t-->
<div class="indexSearch-box">
	<div class="indexSearch">
		<img src="${pageContext.request.contextPath}/pic/indexSearch.jpg" alt="" />
		<p>派叔焕新——享居派旗下子品牌</p>
	</div>
	<div class="indexSearch-bottom">
			专注互联网家装后市场，提供家装换新一站式解决方案。
			<p>针对新房、二手房、出租房、写字楼提供的整体翻新或局部翻新服务。全面修复各种因素造成的房屋损坏，改善房屋质量，提升居住品质。为您解决房屋换新过程中的一切问题。
一个电话，足不出户，轻松焕新！</p>
	</div>
</div>


<div class="indexBox">
	<h3>服务特色</h3>
	<div class="indexCharacteristic">
		<dl>
			<dt>价格</dt>
			<dd>
				透明报价，一步到位，杜绝不合理增项和变相加价。
				<p>诚信</p>
			</dd>
		</dl>
		<dl>
			<dt>材料</dt>
			<dd>
				直营店，大品牌，相同价格，品质提升40%。
				<p>优质</p>
			</dd>
		</dl>
		<dl>
			<dt>施工</dt>
			<dd>
				施工前进场保护，施工后组织验收。
				<p>安全</p>
			</dd>
		</dl>
		<dl>
			<dt>售后</dt>
			<dd>
				一年超长质保年限。客服全天候售后服务。
				<p>保障</p>
			</dd>
		</dl>
	</div>
	<h3>服务流程</h3>
	<div class="indexCharacteristic">
		<ul>
			<li><a href=""><i class="characteristic-1"><span>01</span></i><p>智能下单</p></a></li>
			<li><a href=""><i class="characteristic-2"><span>02</span></i><p>服务确认</p></a></li>
			<li><a href=""><i class="characteristic-3"><span>03</span></i><p>上门测量</p></a></li>
			<li><a href=""><i class="characteristic-4"><span>04</span></i><p>焕新进行</p></a></li>
			<li><a href=""></a></li>
			<li><a href=""><i class="characteristic-5"><span>05</span></i><p>快捷施工</p></a></li>
			<li><a href=""><i class="characteristic-6"><span>06</span></i><p>售后保障</p></a></li>
		</ul>
	</div>
	<h3>关注我们</h3>
	<div class="indexfoot">
		<img src="${pageContext.request.contextPath}/images/footLogo.jpg" alt=""/>
		<img src="${pageContext.request.contextPath}/images/erwei.jpg" alt="" />
		<h3>咨询电话:400-8210-178</h3>
	</div>
	<p class="link"><a href="${pageContext.request.contextPath}/info/about" class="link-about">公司简介</a><a href="${pageContext.request.contextPath}/info/company" class="link-content">联系我们</a></p>	
</div>
<div class="indexSearch-form">
  <input name="mobile" id="mobile" class="indexSearch-form-input" placeholder="输入手机号立即预约，24小时极速反馈！" />
  <input name="" type="submit" class="indexSearch-form-button" value="立即预约"   onclick="addAppoint()"/>
</div>
</body>
</html>
