<%@page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="u" uri="ueye" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<title>${article.title}</title>
<link href="../../css/base.css" rel="stylesheet" type="text/css" />
<link href="../../css/common.css" rel="stylesheet" type="text/css" />
<link href="../../css/page_index.css" rel="stylesheet" type="text/css" />
<jsp:include page="/common/header.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/kindeditor/plugins/jwplayer/jwplayer.js"></script>
<script type='text/javascript'>  
//非视频，不加载播放器  
if(document.getElementById('player')!=null)  
{  
 jwplayer('player').onReady(function() {});  
 jwplayer('player').onPlay(function() {});  
 //jwplayer('player').play(); //自动播放？  
 }  
</script>
<script type=text/javascript><!--//--><![CDATA[//><!--
function menuFix() {
var sfEls = document.getElementById("nav").getElementsByTagName("li");
for (var i=0; i<sfEls.length; i++) {
sfEls[i].onmouseover=function() {
this.className+=(this.className.length>0? " ": "") + "sfhover";
}
sfEls[i].onMouseDown=function() {
this.className+=(this.className.length>0? " ": "") + "sfhover";
}
sfEls[i].onMouseUp=function() {
this.className+=(this.className.length>0? " ": "") + "sfhover";
}
sfEls[i].onmouseout=function() {
this.className=this.className.replace(new RegExp("( ?|^)sfhover\\b"),
"");
}
}
}
window.onload=menuFix;
//--><!]]></script>
<style>
.headbg {
background: url(http://www.liwuka.com.cn/images/headbg.png) no-repeat top center;
height: 136px;
width: 100%;
}
</style>
</head>
<body>
<!--网站头部的开始-->
<!--导航的结束   banner的开始-->
<div class="inBanner" >
<img src="/images/hf_mobile_head.jpg" width="100%" />
</div>
<!--banner的结束  中间内容的开始-->
<div class="wrap" style="margin-top:15px;">
	<!--左边的开始-->
	
	<!--左边的结束  右边的开始-->
	<div class="sideR">
		<div class="rightIn" style="width:100%">
			<div class="productTitle">
			<a href="${pageContext.request.contextPath}/weixin/article/listArticle/${article.articleCategory.id}?weixinId=${weixinId}" >返回列表</a>
			</div>
			<div class="rightIn-content" style="width:95%;padding:0 10px 0 10px;">
			<p style="text-align: center;">${article.title}</p>
			${article.content}
			</div>
		</div>
	</div>
	<!--右边的结束-->
<p><br /></p>
<p><img src="${pageContext.request.contextPath}/images/1.jpg" width="100%" /></p>
</div>

<!--中间内容的结束  尾部的开始-->
<!--尾部的结束-->
<script src="${pageContext.request.contextPath}/js/zepto.min.js"></script>
    <script type="text/javascript">

    var shareData = {
    	"send2Friend":{
    	"content":'${articleMsg.description}',
    	"img":'${articleMsg.picUrl}',
    	"link":"http://m.temall.com/weixin/article/showArticleMsg/${articleMsg.id}?weixinId=${weixinId}",
    	"title":'${articleMsg.title}'
    	},
    	"share2Friend":{
    		"img":"${articleMsg.picUrl}",
    		"link":"http://m.temall.com/weixin/article/showArticleMsg/${articleMsg.id}?weixinId=${weixinId}",
    		"title":'${articleMsg.title}'},
    	"share2qqBlog":{"content":'${articleMsg.description}',
		"link":"http://m.temall.com/catalog/showProduct/${catalog.catalogId}?urecommender=${weixinId}"}};

    !function () { function c() { var a = WeixinJSBridge; a.on("menu:share:appmessage", e), a.on("menu:share:weibo", f), a.on("menu:share:timeline", g), a.invoke("getNetworkType", {}, d) } function d(a) { var b, c; switch (a.err_msg) { case "network_type:wwan": b = 2e3; break; case "network_type:edge": b = 3e3; break; case "network_type:wifi": b = 4e3 } c = new Image, c.onerror = c.onload = function () { c = null } } function e() { var a = window.shareData.send2Friend, b = h(a); a.content = b ? b.content : a.content, a.img = b && b.img ? b.img : a.img, WeixinJSBridge.invoke("sendAppMessage", { img_url: a.img, img_width: "640", img_height: "640", link: a.link, desc: a.content, title: a.title }, function () { }) } function f() { var b = window.shareData.share2qqBlog; WeixinJSBridge.invoke("shareWeibo", { content: a.isios ? b.content + b.link : b.content, url: b.link }, function () { }) } function g() { var a = window.shareData.share2Friend; WeixinJSBridge.invoke("shareTimeline", { img_url: a.img, img_width: "640", img_height: "640", link: a.link, desc: " ", title: a.title }, function () { }) } function h() { return "function" == typeof b ? b() : "" } var b, a = function () { var a = window.navigator.userAgent; return this.isAndroid = a.match(/(Android)\s+([\d.]+)/) || a.match(/Silk-Accelerated/) ? !0 : !1, this.isiPad = a.match(/iPad/) ? !0 : !1, this.isiPod = a.match(/(iPod).*OS\s([\d_]+)/) ? !0 : !1, this.isiPhone = !this.isiPad && a.match(/(iPhone\sOS)\s([\d_]+)/) ? !0 : !1, this.isios = this.isiPhone || this.isiPad || this.isiPod, this }(); window.shareData && document.addEventListener("WeixinJSBridgeReady", c, !1), window.setShareListener = function (a) { b = a } }();

        
    </script>
</body>
</html>
