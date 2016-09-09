<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" uri="ueye"%>
<!DOCTYPE html>
<html>
	<head>
		<title>${articleMsg.title}</title>
		<meta http-equiv=Content-Type content="text/html;charset=utf-8">
		<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="format-detection" content="telephone=no">
		<link rel="stylesheet" type="text/css"	href="http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/style/client-page1da716.css" />
		 <link media="screen and (min-width:1000px)" rel="stylesheet" type="text/css" href="http://res.wx.qq.com/mmbizwap/zh_CN/htmledition/style/pc-page1d8b20.css"/>
		<style>
#nickname {
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
	max-width: 90%;
}

ol,ul {
	list-style-position: inside;
}
</style>
		<style>
#activity-detail .page-content .text {
	font-size: 16px;
}
</style>
	</head>
	<body id="activity-detail">
		<div class="page-bizinfo">
			<div class="header">
				<h1 id="activity-name">${articleMsg.title}</h1>
				<p class="activity-info">
					<span id="post-date" class="activity-meta no-extra">${articleMsg.createDateStr}</span>
          <a href="javascript:viewProfile();" id="post-user" class="activity-meta">
          <span class="text-ellipsis">贝酷</span><i class="icon_link_arrow"></i>
          </a>
				</p>
			</div>
		</div>
		<div class="page-content" id="page-content">
		<div id="img-content">
			<div class="media" id="media">
				<a href="javascript:void(0)"><img src="${articleMsg.picUrl}" /></a>
			</div>
			<div class="text">
				${articleMsg.content}
      <p><br /></p>
      <p><img src="${pageContext.request.contextPath}/images/1.jpg" /></p>
      <p><br /></p>
      <p><img src="${pageContext.request.contextPath}/images/0.jpg" /></p>
			<p class="page-toolbar">
            	<a href="${pageContext.request.contextPath}/weixin/article/showArticle/${articleMsg.id}" class="page-url">阅读原文</a>
            </p>
			</div>
		</div>
		</div>
		<jsp:include page="/common/weixinfooter.jsp" />
		<script type="text/javascript">
				function changeHref(obj){
					var href=$(obj).attr('href');
					if(href.lastIndexOf('?')==-1){
						href=$(obj).attr('href')+"?recommender=${recommendUserName}";
						obj.href=href;
					}
					alert(href);
					window.location=href;
					return false;
				}
		</script>
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
    		"link":'http://m.temall.com/weixin/article/showArticleMsg/${articleMsg.id}?weixinId=${weixinId}',
    		"title":'${articleMsg.title}'},
    	"share2qqBlog":{"content":'${articleMsg.description}',
		"link":'http://m.temall.com/catalog/showProduct/${catalog.catalogId}?urecommender=${weixinId}'}};

    !function () { function c() { var a = WeixinJSBridge; a.on("menu:share:appmessage", e), a.on("menu:share:weibo", f), a.on("menu:share:timeline", g), a.invoke("getNetworkType", {}, d) } function d(a) { var b, c; switch (a.err_msg) { case "network_type:wwan": b = 2e3; break; case "network_type:edge": b = 3e3; break; case "network_type:wifi": b = 4e3 } c = new Image, c.onerror = c.onload = function () { c = null } } function e() { var a = window.shareData.send2Friend, b = h(a); a.content = b ? b.content : a.content, a.img = b && b.img ? b.img : a.img, WeixinJSBridge.invoke("sendAppMessage", { img_url: a.img, img_width: "640", img_height: "640", link: a.link, desc: a.content, title: a.title }, function () { }) } function f() { var b = window.shareData.share2qqBlog; WeixinJSBridge.invoke("shareWeibo", { content: a.isios ? b.content + b.link : b.content, url: b.link }, function () { }) } function g() { var a = window.shareData.share2Friend; WeixinJSBridge.invoke("shareTimeline", { img_url: a.img, img_width: "640", img_height: "640", link: a.link, desc: " ", title: a.title }, function () { }) } function h() { return "function" == typeof b ? b() : "" } var b, a = function () { var a = window.navigator.userAgent; return this.isAndroid = a.match(/(Android)\s+([\d.]+)/) || a.match(/Silk-Accelerated/) ? !0 : !1, this.isiPad = a.match(/iPad/) ? !0 : !1, this.isiPod = a.match(/(iPod).*OS\s([\d_]+)/) ? !0 : !1, this.isiPhone = !this.isiPad && a.match(/(iPhone\sOS)\s([\d_]+)/) ? !0 : !1, this.isios = this.isiPhone || this.isiPad || this.isiPod, this }(); window.shareData && document.addEventListener("WeixinJSBridgeReady", c, !1), window.setShareListener = function (a) { b = a } }();

        
    </script>
            
	</body>
</html>