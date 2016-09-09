<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="u" uri="ueye"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0 ,user-scalable=no"/>
<title>建材直选</title>
	<link href="${pageContext.request.contextPath}/css/base.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=wqBXfIN3HkpM1AHKWujjCdsi"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js" ></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/amazeui.min.js" ></script>
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
					//$('#addr').html('当前位置：'+addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber);
					$('#addr_dis').html(addComp.district);
					document.getElementById('jingdu').value=r.point.lng;
					document.getElementById('weidu').value=r.point.lat;

					setTimeout(function(){findMerchantList(1);},1000); 
				});
				//if($("#jingdu").val()!=""){
				//	findMerchantList(1);
				//}
			}
			else {
				alert('failed'+this.getStatus());
			}
			});
		
		//根据选择端商户分类AJAX获取商户列表，并按经纬度排序，由近到远
		 function findMerchantList(cateId) {
			 doTabCont(cateId,20);
		
			 		$('#productList').empty();
				   	
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
								resultValue+='<dl>';		
								resultValue+='	<dt>';
								resultValue+='		<a href="${pageContext.request.contextPath}/store/storeDetail/'+obj.id+'">';
								if(obj.imgUrl==""){
									resultValue+='		<img src="${pageContext.request.contextPath}/pic/porductImg-01.jpg" />';
								}else{
									
									resultValue+='		<img src="http://images.sunjoypai.com/store/'+obj.path+'160X160_'+obj.imgUrl+'" />';
								}
								
								resultValue+='		</a>';
								resultValue+='		<p>浏览:'+obj.pageCount+'</p>';
								resultValue+='	</dt>';
								resultValue+='		<a href="${pageContext.request.contextPath}/store/storeDetail/'+obj.id+'">';
								resultValue+='	<dd><h3>'+obj.storeName+'</h3></dd>';
								resultValue+='	<dd>主营：'+obj.major+'</dd>';
								resultValue+='	<dd>';
								resultValue+='		<span><i class="iconfont">&#xe601;</i>'+obj.addr+'</span>';
								resultValue+='		<label>'+obj.distance+'km</label>';
								resultValue+='	</dd>';
								resultValue+='	<dd><p><i>促</i>'+obj.promotion+'</p></dd>';
								resultValue+='		</a>';
								resultValue+='</dl>';
								//显示到页面
								//$('.chartBox').append(item);
								//$('#container').append(resultValue);
								$('#productList').append(resultValue);
								//$('#result').html(resultValue);
							}
						}
					},   
					error :function(){   
					   alert("请刷新页面，重试");
						}  
					});
					
		  }
		  
	
		  
		  
		  //页面加载完毕执行此js
			$(document).ready(function() {	
				//默认加载默认分类显示所有商户
				//setTimeout(findMerchantList('001001'),"2000");
				setTimeout(function(){findMerchantList(1);},1200); 
				
			
			});
		  
			function doTabCont(tabId,count){
				
				for(var i=1;i<=count;i++){
					
					if(i==tabId){
						$("#tab_"+tabId).attr("class","hover");
					}else{
						$("#tab_"+i).attr("class","");
					}
				}
				
			}
	</script>
 
</head>

<body>
<!--头部的开始-->
<!-- <div class="headTop">
	<h2>享居派</h2>
</div> -->
<!--头部的结束-->

<div class="areaTitle">
	<a href="" class="areaLink"><span id="addr_dis">定位中</span></a>
	<input type="hidden" id="addr" name="addr"  size="20"  value="-99" placeholder="地址" />
	<input type="hidden" id="jingdu" name="jingdu"  size="20"  value="-99" placeholder="经度" />
	<input type="hidden" id="weidu" name="weidu"  size="20"  value="-99" placeholder="纬度" />
	<div class="areaRight">
		<ul>
			<c:forEach items="${cates}" var="cate" varStatus="status">
				<c:if test="${status.count == 1 }">
				<li class="hover" id="tab_${cate.id}" onclick = "findMerchantList(${cate.id})"><a>${cate.cateName}</a></li>
				</c:if>
				<c:if test="${status.count != 1}">
				<li class="" id="tab_${cate.id}" onclick = "findMerchantList(${cate.id})"><a >${cate.cateName}</a></li>
				</c:if>
			</c:forEach>
		</ul>
	</div>
</div>

<div class="productList" id="productList">
	
</div>

<!--版权-->
<div class="copyright mb50">
	CopyRight©2016 享居派 All Rights Reserved
</div>

<!--底部菜单 开始-->
<div class="footerbox">
	<ul>
		<li class="hover"><a href="${pageContext.request.contextPath}/store/index"><i class="iconfont">&#xe600;</i><span>列表</span></a></li>
		<li><a href="${pageContext.request.contextPath}/user/myInterest"><i class="iconfont">&#xe603;</i><span>关注</span></a></li>
		<li><a href="${pageContext.request.contextPath}/store/addCase"><i class="iconfont">&#xe604;</i><span>上传</span></a></li>
		<li><a href="${pageContext.request.contextPath}/user/userCenter"><i class="iconfont">&#xe602;</i><span>我的</span></a></li>
	</ul>
</div>
<!--底部菜单 结束-->

 
</body>
</html>
