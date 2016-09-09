<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="u" uri="ueye"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>我的</title>
    <meta name="Description" content="享居派，只给你最好的！" />
    <link rel="shortcut icon" href="http://sunjoypai.com/resource/system/front/images/favicon.ico"/>
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/css.css" rel="stylesheet">
    <!--<link rel="stylesheet" type="text/css" href="${ctx}/resource/system/phone/css/scripts/utils/layer/need/layer.css"/>-->
    
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/utils/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/utils/js.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${pageContext.request.contextPath}/js/utils/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/utils/layer/layer.js"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
      body{background-color:#fafafa;}
    	#foot{width:100%;position:absolute;bottom:9%;height:8.47%;text-align: center;font-size:0.75em;}

			.foot{width:100%;position: fixed;bottom:0;height:8.47%;background-color: #f3f3f3;}
			.foot span{width:24%;line-height: 113px;height:100%;text-align: center;position: relative;display: inline-block;padding-top:10px;;box-sizing: border-box;}
			.foot span img{width:auto;vertical-align: top;height:85%;}
    </style>
  </head>
  <body style="background:#fafafa;">
    <!--头部-->
    <!--<div class="container wz_top">
        <div class="row bg01">
          <div class="col-xs-4">
           <a href="#" class="color_bs f18"><span class="glyphicon glyphicon-chevron-left"></span><i>返回</i></a>
          </div>
          <div class="col-xs-4 text-center">
            <span class="color_bs f18">店铺图像</span>
          </div>
          <div class="col-xs-4">
            <span class="color_bs f18 pull-right"><img class="lb_btn"  src="../images/dd.jpg" alt=""></span>
          </div>
        </div>
    </div>-->
    <div class="dh_nav_con1">
     <a href="wd.html">我的</a>
     <a href="algl.html">案例管理</a>
     <a href="spgl.html">商品管理</a>
     <a href="dptx.html">店铺图像</a>
     <a href="gz.html">关注</a>
     <a href="grtx.html">上传图像</a>
    </div>
    <!--主体-->
    <div class="sc_content">
        <div class="my_top" style="background: #ffffff;">
           <div class="container">
            <a href="../html/setting.html">
               <div class="row">
                   <div class="col-xs-3">
                     <img class=" img-rounded" src="${pageContext.request.contextPath}/images/tx-1.png" alt="">
                   </div>
                   <div class="col-xs-9" style=" padding-left:0px;">
                      <span class="my_name">HCG和成卫浴店老大</span>
                      <span class="my_shop_name">店铺:HCG和成卫浴</span>
                   </div>
               </div>
             </a>
           </div>
        </div>
        <div class="my_bottom">
        <div class="my_list_con" style="background: #ffffff;">
           <div class="container" style="padding-right:0px;">
            <div class="my_list_1" style="border:none;">
             <div class="container" style="padding-left:0px;">
             	<div class="row">
                 <div class="col-xs-12" id='qr'>
                  <a href="#"><img src="${pageContext.request.contextPath}/images/gj-5.png" alt="">我的店铺二维码</a>
                 </div>
                </div>
              </div>
             </div>
            </div>
            <div class="container" style="padding-right:0px;">
             <div class="my_list_1">
             <div class="container" style="padding-left:0px;">
             	<a href="../html/spgl.html">
                <div class="row">
                  <div class="col-xs-12">
                  <span><img src="../images/gj-4.png" alt="">商品管理</span>
                 </div>
                </div>
                </a>
              </div>
             </div>
             </div>
             <div class="container" style="padding-right:0px;">
             <div class="my_list_1">
             <div class="container" style="padding-left:0px;">
              <a href="../html/algl.html">
             	<div class="row">
                 <div class="col-xs-12">
                  <span><img src="${pageContext.request.contextPath}/images/gj-3.png" alt="">案例管理</span>
                 </div>
                </div>
              </a>
              </div>
             </div>
             </div>
         </div>
             
             
             <div class="my_list" style="background: #ffffff;">
             <div class="container">
              <a href="../html/product_detail.html">
             	<div class="row">
                 <div class="col-xs-12">
                  <span><img src="${pageContext.request.contextPath}/images/gj-2.png" alt="">查看店铺</span>
                 </div>
                </div>
              </a>
              </div>
             </div>
             <div class="my_list" style="background: #ffffff;">
             <div class="container">
              <a href="shop_setting.html">
             	<div class="row">
                 <div class="col-xs-12">
                  <span><img src="${pageContext.request.contextPath}/images/gj-1.png" alt="">店铺设置</span>
                 </div>
                </div>
              </a>
              </div>
             </div>

       </div>
    </div>
    <!--浮动导航-->
    <div id="foot" style="bottom:10%;position: absolute;margin:0;">CopyRight©2016 享居派 All Rights Reserved</div>
		<div class="foot">
        <span><a href="../html/index.html"><img src="${pageContext.request.contextPath}/images/foot-p1-2.png"/></a></span>
        <span><a href="../html/gz.html"><img src="${pageContext.request.contextPath}/images/foot-p2.png"/></a></span>
        <span><a href="../html/upload.html"><img src="${pageContext.request.contextPath}/images/foot-p3.png"/></a></span>
        <span><a href="#"><img src="${pageContext.request.contextPath}/images/foot-p4-2.png"/></a></span>
    </div> 
    <script type="text/javascript">
      $('#qr').on('click',function(){
        layer.open({content:'<img src="${pageContext.request.contextPath}/images/qr.jpg">'});
      })
      $('.need_click').on('click',function(){
        console.log('123');
        location.href = './' +$(this).attr('id')+'.html';
      })
    </script> 
  </body>
</html>
