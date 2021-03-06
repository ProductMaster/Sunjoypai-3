<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="u" uri="ueye"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <!--<meta name="viewport" content="width=device-width, initial-scale=1">-->
    <script type="text/javascript">
      var mengvalue = -1;
        //if(mengvalue<0){mengvalue=0;}
      var phoneWidth = parseInt(window.screen.width);
      var phoneScale = phoneWidth / 750;
      
      var ua = navigator.userAgent;
      if (/Android (\d+\.\d+)/.test(ua)) {
          var version = parseFloat(RegExp.$1);
          // andriod 2.3
          if (version > 2.3) {
              document.write('<meta name="viewport" content="width=750, minimum-scale = ' + phoneScale + ', maximum-scale = ' + phoneScale + ', target-densitydpi=device-dpi">');
              // andriod 2.3以上
          } else {
              document.write('<meta name="viewport" content="width=750, target-densitydpi=device-dpi">');
          }
          // 其他系统
      } else {
          document.write('<meta name="viewport" content="width=750, user-scalable=no, target-densitydpi=device-dpi">');
      }
    </script>
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>案例管理</title>
    <meta name="Description" content="享居派，只给你最好的！" />
    <link rel="shortcut icon" href="http://sunjoypai.com/resource/system/front/images/favicon.ico"/>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/css.css" rel="stylesheet"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/utils/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/utils/js.js"></script>
    <script src="${pageContext.request.contextPath}/js/utils/bootstrap.min.js"></script>
    <style type="text/css">
    	.title{background-image: url(../images/title.png);width:100%;height:102px;z-index:10;line-height: 102px;font-size:28px;text-align: center;}
    	.title #upload{position: absolute;top:-7px;right:0px;}
      #foot{width:100%;margin-bottom:113px;height:87px;text-align: center;line-height: 87px;background: #fafafa;font-size:18px;}
      .foot{width:100%;position: fixed;bottom:0;height:113px;background-color: #f3f3f3;}
      .foot span{width:24.5%;line-height: 113px;height:113px;text-align: center;position: relative;display: inline-block;padding-top:20px;box-sizing: border-box;}
      .foot span img{width:auto;vertical-align: top;}
    </style>
  </head>
  <body>
    <!--标题-->
    <div class="title">
    	案例管理
			<div id="upload">
        <a href="${pageContext.request.contextPath}/store/addCase">
          <img src="${pageContext.request.contextPath}/images/al_btn.png" alt="" />
        </a>
      </div>
		</div>
    <!-- <div class="title">
      <div class="col-xs-9 text-center">
              <div class="hd_top" style="margin-left:120px;">
                <div class="col-xs-5 f16 current">
                <a href="#">已上架</a>
                </div>
                <div class="col-xs-5 f16">
                <a href="#">未上架</a>
                </div>
              </div>
          </div>
          <div class="col-xs-2">
            <a href="#" class="f16 add_btn">增加</a>
          </div>
    </div> -->
    <!--主体-->
    <div class="gz">
      <div class="container prolist">
      
      	<c:forEach items="${cases}" var="cases" varStatus="status">
	        <div class="row gz_list">
	            <div class="col-xs-3 gz_tx">
	              <a href="#"><img src="${pageContext.request.contextPath}/images/TX-6.png" alt=""/></a>
	            </div>
	            <div class="col-xs-9 gz_rt">
	              <div class="gz_text">
	                <h3><a href="#">${cases.storeInfo.storeName}</a></h3>
	                <p>${cases.describe}</p>
	              </div>
	              <div class="row gz_tu">
	             	<c:forEach items="${cases.imgs}" var="img" varStatus="status">
		                <span class="col-xs-4"><a href="#"><img src="http://images.sunjoypai.com/case/${cases.path}${cases.id}/${img.imgUrl}" alt=""/></a></span>
	                </c:forEach>
	              </div>
	              <div class="dele">
	              	<a href="${pageContext.request.contextPath}/store/deleteCase/${cases.id}"><span><img src="${pageContext.request.contextPath}/images/dele.png" alt=""/></span><i>删除</i></a>
	              </div>
	            </div>
	       	</div>
        </c:forEach>
      </div>
    </div>
    <div class="foot">
<span><a href="${pageContext.request.contextPath}/store/index"><img src="${pageContext.request.contextPath}/images/foot-p1-2.png"/></a></span>
			<span><a href="${pageContext.request.contextPath}/user/myInterest"><img src="${pageContext.request.contextPath}/images/foot-p2.png"/></a></span>
			<span><a href="${pageContext.request.contextPath}/store/addCase"><img src="${pageContext.request.contextPath}/images/foot-p3.png"/></a></span>
			<span><a href="${pageContext.request.contextPath}/user/userCenter"><img src="${pageContext.request.contextPath}/images/foot-p4.png"/></a></span>    </div>  
    <!--列表加载js-->
    <div class="jiazai">
    </div>
    <script>
    var page=1;
    var finished=0;
    var sover=0;
    //如果屏幕未到整屏自动加载下一页补满
    var setdefult=setInterval(function (){
        if(sover==1)
            clearInterval(setdefult);	
        else if($(".prolist").height()<$(window).height())
            loadmore($(window));
        else
            clearInterval(setdefult);
    },500);
    //加载完
    function loadover(){
        if(sover==1)
        {	
            var overtext="没有更多了";
            $(".loadmore").remove();
            if($(".loadover").length>0)
            {
                $(".loadover span").eq(0).html(overtext);
            }
            else
            {
                var txt='<div class="loadover"><span>'+overtext+'</span></div>';
                $(".jiazai").append(txt);
            }
        }
    }
    //加载更多
    var vid=0;
    function loadmore(obj){
        if(finished==0 && sover==0)
        {
            var scrollTop = $(obj).scrollTop();
            var scrollHeight = $(document).height();
            var windowHeight = $(obj).height();
            
            if($(".loadmore").length==0)
            {
                var txt='<div class="loadmore"><span class="loading"></span>加载中..</div>';
                $(".jiazai").append(txt);
            }
            if (scrollTop + windowHeight -scrollHeight<=50 ) {
                //此处是滚动条到底部时候触发的事件，在这里写要加载的数据，或者是拉动滚动条的操作
                //防止未加载完再次执行
                finished=1;
                var result = '';
                for(var i = 0; i < 6; i++){
                    vid++;
                    result+='<div class="row gz_list"><div class="col-xs-3 gz_tx"><a href="#"><img src="../images/TX-6.png" alt=""></a></div><div class="col-xs-9 gz_rt"><div class="gz_text"><h3><a href="#">XXX欧式专业瓷砖</a></h3><p>欧式复古瓷砖，色泽古典自然，触感细腻，让整个空间蕴含低调奢华的尊贵气息。</p></div><div class="row gz_tu"><span class="col-xs-4"><a href="#"><img src="../images/x2.png" alt=""></a></span><span class="col-xs-4"><a href="#"><img src="../images/x3.png" alt=""></a></span><span class="col-xs-4"><a href="#"><img src="../images/x2.png" alt=""></a></span><span class="col-xs-4"><a href="#"><img src="../images/x3.png" alt=""></a></span><span class="col-xs-4"><a href="#"><img src="../images/x2.png" alt=""></a></span><span class="col-xs-4"><a href="#"><img src="../images/x3.png" alt=""></a></span><span class="col-xs-4"><a href="#"><img src="../images/x2.png" alt=""></a></span><span class="col-xs-4"><a href="#"><img src="../images/x3.png" alt=""></a></span><span class="col-xs-4"><a href="#"><img src="../images/x2.png" alt=""></a></span></div><div class="dele"><a href="#"><span><img src="../images/dele.png" alt=""></span><i>删除</i></a></div></div>'+parseInt(vid)+'</div>'//此处用于加载数据 测试
                }
                setTimeout(function(){
                    $(".loadmore").remove();
                    $('.prolist').append(result);
                    page+=1;
                    finished=0;
                    //最后一页
                    if(page==5)
                    {
                        sover=1;
                        loadover();
                    }
                },1000);
                /*$.ajax({
                    type: 'GET',
                    url: 'json/more.json?t=25&page='+page,
                    dataType: 'json',
                    success: function(data){
                        var result = '';
                        for(var i = 0; i < data.lists.length; i++){
                            result+='<li>'
                                        +'<a href="'+data.lists[i].link+'">'+data.lists[i].title+parseInt(page+1)+"-"+i+'</a>'
                                    +'</li>';
                        }
                        
                        // 为了测试，延迟1秒加载
                        setTimeout(function(){
                            $(".loadmore").remove();
                            $('.prolist').append(result);
                            page+=1;
                            finished=0;
                            //最后一页
                            if(page==5)
                            {
                                sover=1;
                                loadover();
                            }
                        },1000);
                    },
                    error: function(xhr, type){
                        alert('Ajax error!');
                    }
                });*/
            }
        }
    }
    //页面滚动执行事件
    $(window).scroll(function (){
        loadmore($(this));
    });
    </script>
    
    <!--底部-->
    <div class="footer">
       <div class="container">
       CopyRight©2016 享居派 All Rights Reserved
      </div>
    </div>
  </body>
</html>
