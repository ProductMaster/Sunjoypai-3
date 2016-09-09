<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" uri="ueye"%>
<!doctype html>
<html>
<head>
<style type="text/css">
html{
	width: 100%;
  	height: 100%;
  	
}
.top {
    width: 75%;
    margin: 0em auto;
    display: block;
    position: relative;
    left: 0em;z-index:1
}
.qrcode {
    width: 200px;
    margin: -1em auto 0;
    display: block; position: relative;z-index:2
}
.bottom {
  	width: 80%;
  	margin: -0em auto 0;
  	display: block; position: relative;z-index:3
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>我的店铺二维码</title>
<meta http-equiv="X-UA-Compatible" content="IE=10,IE=9,IE=8,chrome=1"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0,user-scalable=no">
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">

	var appId = "${appId}";
	var timestamp = "${map.timestamp}";
	var nonceStr = "${map.nonceStr}";
	var signature = "${map.signature}";
	var shareUrl="http://m.sunjoypai.com/user/displayQRCode?userId="+${userId}+"&tempId="+${tempId};
	var imgUrl="http://m.sunjoypai.com/beiku/beiku_logo.jpg";
	var title="享居派推广者";
	var desc="享居派推广者!"
		wx.config({
	        debug : false,
	        appId : appId,
	        timestamp : timestamp,
	        nonceStr : nonceStr,
	        signature : signature,
	        jsApiList: [
		            'onMenuShareTimeline',
		            'onMenuShareAppMessage'
		        ]
	    });
 		wx.ready(function () {
	    	//分享给朋友
	        wx.onMenuShareAppMessage({
			    title: title, // 分享标题
			    desc: desc, // 分享描述
			    link: shareUrl, // 分享链接
			    imgUrl: imgUrl, // 分享图标
			    type: '', // 分享类型,music、video或link，不填默认为link
			    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
			    success: function () { 
			        // 用户确认分享后执行的回调函数
			    },
			    cancel: function () { 
			    },
			});
			
			//分享到朋友圈
	        wx.onMenuShareTimeline({
			    title: desc, // 分享标题
	            link: shareUrl,
	            imgUrl:imgUrl,
	            trigger: function (res) {
	            },
	            success: function (res) {
	            },
	            cancel: function (res) {
	            },
	            fail: function (res) {
	                alert('wx.onMenuShareTimeline:fail: '+JSON.stringify(res));
	            }
	        });
	    });
</script>
</head>
<body>
	<div >
		
		<img width="60%" height="60%" class="top" src="http://images.sunjoypai.com/qrcode/${mkdirNum}/${userId}.png"  alt="扫描二维码" />
		
	</div>
</body>
</html>