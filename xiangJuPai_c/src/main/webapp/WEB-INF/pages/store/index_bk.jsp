<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="u" uri="ueye"%>


	<head>
	
		<title>建材优选</title>
    	<meta name="Description" content="享居派，只给你最好的！" />
		<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"/>
    	<link href="${pageContext.request.contextPath}/css/css.css" rel="stylesheet"/>
    	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css"/>

	    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=wqBXfIN3HkpM1AHKWujjCdsi"></script>
	    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/amazeui.min.js" ></script>
	    <style type="text/css">
	    	/* .title{background-image: url(${pageContext.request.contextPath}/images/title.png);width:100%;height:102px;z-index:10;line-height: 102px;font-size:28px;text-align: center;}
	    	.title #upload{position: absolute;top:-7px;right:0px;} */
	    	#foot{width:100%;margin-bottom:113px;height:87px;text-align: center;line-height: 87px;background: #fafafa;font-size:18px;}
	
				.foot{width:100%;position: fixed;bottom:0;height:8.47%;background-color: #f3f3f3;}
				.foot span{width:24%;line-height: 113px;height:100%;text-align: center;position: relative;display: inline-block;padding-top:10px;;box-sizing: border-box;}
				.foot span img{width:auto;vertical-align: top;height:85%;}
	    </style>
		<script type="text/javascript">
			seajs.use(PATH+'index');
		</script>
		<script type="text/javascript">
		//默认显示当前位置，并初始化经纬度
		var geolocation = new BMap.Geolocation();
		geolocation.getCurrentPosition(function(r){
			if(this.getStatus() == BMAP_STATUS_SUCCESS){
				var mk = new BMap.Marker(r.point);
				//alert('您的位置：'+r.point.lng+','+r.point.lat);
				//document.getElementById('jwmap').innerHTML=+r.point.lng+','+r.point.lat;
				
				var myGeo = new BMap.Geocoder();
				myGeo.getLocation(new BMap.Point(r.point.lng,r.point.lat), function(rs){
					//console.log(MyApp.app.mapCenter);
					console.log(rs);
					var addComp = rs.addressComponents;
					//alert(addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber);
					$('#addr').html('当前位置：'+addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber);
					document.getElementById('jingdu').value=r.point.lng;
					document.getElementById('weidu').value=r.point.lat;
				});
				findMerchantList(1);
			}
			else {
				alert('failed'+this.getStatus());
			}
			});
		
		//根据选择端商户分类AJAX获取商户列表，并按经纬度排序，由近到远
		 function findMerchantList(cateId) {
			 doTabCont(cateId,20);
		
			 		$('#container').empty();
				   	
					var jingdu = $('#jingdu').val();	
					var weidu = $('#weidu').val();					  
					//$('#tiaoshi').html('cateNo：'+cateId+'   jingdu：'+jingdu+'  weidu：'+weidu)
					
					//$('#json1').html('2、组装好参数，AJAX模拟请求服务器。。。。');
					//这里请求组织好参数ajax请求服务器
					
					//这里是AJAX请求数据，可用参考
				 
				   	$.ajax({   
					type : "POST",   
					url : "${pageContext.request.contextPath}/store/listByJingWeidu", 
					data : {
					 "cateId": cateId,"jingdu": $("#jingdu").val(),"weidu": $("#weidu").val()
					 }, 
					 async:true,
					dataType: "json",   
					success : function(json) { 
						//解析JSON列表,并显示到页面中
						
						if(typeof json == 'object'){
							//alert(json.datas.length);
							for(var i=0; i<json.datas.length; i++){  
			                    obj = json.datas[i]; 
			                    
			                    var resultValue='';
								resultValue+='<li>';		
								resultValue+='	<a href="${pageContext.request.contextPath}/store/storeDetail/'+obj.id+'">';
								resultValue+='	<img src="${pageContext.request.contextPath}/images/store/'+obj.imgUrl+'" width="200" height="200" alt=""/>';
								resultValue+='	<div class="shopData">';
								resultValue+='		<h2 class="ellipsis">'+obj.storeName+'</h2>';
								resultValue+='		<h3 class="ellipsis">'+obj.major+'</h3>';
								resultValue+='		<h3 class="ellipsis">距您：'+obj.distance+'km '+obj.addr+'</h3>';
								resultValue+='		<hr/>';
								resultValue+='		<div class="promotion">';
								resultValue+='			<img src="${pageContext.request.contextPath}/images/cu.png"/>';
								resultValue+='			<label>'+obj.promotion+'</label>';
								
								resultValue+='		</div>';
								resultValue+='	</div>';
								resultValue+='	</a>';
								resultValue+='</li>';
								//显示到页面
								//$('.chartBox').append(item);
								//$('#container').append(resultValue);
								$('#container').append(resultValue);
								//$('#result').html(resultValue);
							}
						}
					},   
					error :function(){   
					   alert("error11");
						}  
					});
					
		  }
		  
	
		  
		  
		  //页面加载完毕执行此js
			$(document).ready(function() {	
				//默认加载默认分类显示所有商户
				//setTimeout(findMerchantList('001001'),"2000");
				//setTimeout(function(){findMerchantList(1);},1000); 
				
			
			});
		  
		  
			function doTabCont(tabId,count){
				
				for(var i=1;i<=count;i++){
					
					if(i==tabId){
						$("#tab_"+tabId).attr("class","active");
					}else{
						$("#tab_"+i).attr("class","");
					}
				}
				
			}
		</script>
	</head>
	<body>
	<div ><p id="addr">正在获取当前位置。。。</p></div>
		<div class="title">
			<div class="navi_container">
				<div class="navi clearfix">
					
					<c:forEach items="${cates}" var="cate" varStatus="status">
						
						
						<c:if test="${status.count == 1 }">
							<span  id="tab_${cate.id}" class="active"   onclick = "findMerchantList(${cate.id})">${cate.cateName}</span>
						</c:if>
						<c:if test="${status.count != 1}">
							<span  id="tab_${cate.id}" class="" onclick = "findMerchantList(${cate.id})">${cate.cateName}</span>
						</c:if>
						
					</c:forEach>
					
					<!--<span id="ornament">电器</span>
					<span id="ornament">家具</span>
					<span id="ornament">灯饰</span>
					<span id="ornament">其它</span>-->
				</div>
			</div>
		</div>
		
		<form id="validateForm" class="form-horizontal" method="post" >
		<!-- <div id="addr">地址：</div> -->
		<input type="hidden" id="addr" name="addr"  size="20"  value="-99" placeholder="地址" />
		<input type="hidden" id="jingdu" name="jingdu"  size="20"  value="-99" placeholder="经度" /><br>
		<input type="hidden" id="weidu" name="weidu"  size="20"  value="-99" placeholder="纬度" /><br>
		
		</form>
		
		
		<ul  class="container" id="container">
			
			<%-- <li>
		
				<a href="../html/product_detail.html">
				<img src="" alt="" class="pic" />
				<div class="shopData">
					<h2 class="ellipsis">XXXX</h2>
					<h3 class="ellipsis">XXXX</h3>
					<h3 class="ellipsis">XXXXX</h3>
					<hr/>
					<div class="promotion">
						<img src="${pageContext.request.contextPath}/images/cu.png"/>
						<label>ceshiiii</label>
					</div>
				</div>
				</a>
			</li> --%>
		</ul>
	<div id="foot">CopyRight©2016 享居派 All Rights Reserved</div> 
		<div class="foot">
			<span><a href="${pageContext.request.contextPath}/store/index"><img src="${pageContext.request.contextPath}/images/foot-p1.png"/></a></span>
			<span><a href="${pageContext.request.contextPath}/user/myInterest"><img src="${pageContext.request.contextPath}/images/foot-p2.png"/></a></span>
			<span><a href="${pageContext.request.contextPath}/store/addCase"><img src="${pageContext.request.contextPath}/images/foot-p3.png"/></a></span>
			<span><a href="${pageContext.request.contextPath}/user/userCenter"><img src="${pageContext.request.contextPath}/images/foot-p4.png"/></a></span>
		</div>
	</body>
</html>