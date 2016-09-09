<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="u" uri="ueye"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0 ,user-scalable=no"/>
<title>上传案例</title>
<link href="${pageContext.request.contextPath}/css/base.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/amazeui.min.js" ></script>


<script type="text/javascript">
function clickFile(imageId){

	 $("#fileImage").click();
	 
	$('#imageId').val(imageId);
}
function clickFile2(imageId){

	 $("#fileImage2").click();
	 
	$('#imageId').val(imageId);
}
function clickFile3(imageId){

	 $("#fileImage3").click();
	 
	$('#imageId').val(imageId);
}
function clickFile4(imageId){

	 $("#fileImage4").click();
	 
	$('#imageId').val(imageId);
}
function clickFile5(imageId){

	 $("#fileImage5").click();
	 
	$('#imageId').val(imageId);
}
function clickFile6(imageId){

	 $("#fileImage6").click();
	 
	$('#imageId').val(imageId);
}
function clickFile7(imageId){

	 $("#fileImage7").click();
	 
	$('#imageId').val(imageId);
}
function clickFile8(imageId){

	 $("#fileImage8").click();
	 
	$('#imageId').val(imageId);
}
function clickFile9(imageId){

	 $("#fileImage9").click();
	 
	$('#imageId').val(imageId);
}
</script>

<script type="text/javascript">

	function doSubmit(){
			 
		var describe = $('#describe').val();
		if(describe==""){ 
			alert("请输入案例描述！"); 		 
			return false; 
		}
	    $('#validateForm').submit(); 
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
<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/store/addStoreCase" enctype="multipart/form-data">
<input type="hidden" name="caseId" id="caseId" value="${storeCase.id}" />
<input type="hidden" maxlength="20" id="imgSize" name="imgSize" value="${imgSize}">
<input type="hidden" maxlength="20" id="imageId" name="imageId" value="-99">
	<div class="attentionTop">
		<i class="m_back"></i>
		<h2>添加案例</h2>
		<a href="javascript:void(0)" onclick="return doSubmit();" class="r_back">上传</a>
	</div>
	
	<div class="upload">
		<textarea name="describe" placeholder="输入商品描述，12字内..." id="describe"></textarea>
		
		<div class="uploadImg">
		   <input style="display:none" id="fileImage" type="file" accept="image/*" name="uploadFile" multiple="multiple"/>
			<ul>
			 <%int a=0;%>
                 <c:forEach items="${storeCaseImgs}" var="imgs" varStatus="status">
                     <%a++;%>
                     <c:if test="${status.index<9}">
                         <li><a href="javascript:;" onclick="clickFile(imgs.id)"><img width="100%" height="100%" name='img' src='http://images.sunjoypai.com/case/${imgPath}${imgs.imgUrl}'/></a></li>
                     </c:if>
                 </c:forEach>
                 <%for(int i=a;i<9;i++){%>
                     <li><a href="javascript:;" onclick="clickFile(-99)"><img width="100%" height="100%" name='img' src=''/></a></li>
                  <%}%>
			</ul>
		</div>
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
		<li class="hover"><a href="${pageContext.request.contextPath}/store/addCase"><i class="iconfont">&#xe604;</i><span>上传</span></a></li>
		<li><a href="${pageContext.request.contextPath}/user/userCenter"><i class="iconfont">&#xe602;</i><span>我的</span></a></li>
	</ul>
</div>
<!--底部菜单 结束-->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/zxxFile.js"></script>
<script>
var params = {
    //血肉们
    fileInput: $("#fileImage").get(0),    //file控件元素
    filter: function(files) {    //对选择的文件过滤
        var arrFiles = [];
        for (var i = 0, file; file = files[i]; i++) {
            if (file.type.indexOf("image") == 0) {
                //if (file.size >= 512000) {
                   // alert('您这张"'+ file.name +'"图片大小过大，应小于500k');	
                //} else {
                    arrFiles.push(file);	
               // }			
            } else {
                alert('文件"' + file.name + '"不是图片。');	
            }
        }
        return arrFiles;
    },
    //文件（这里就是图片）选择后执行的方法
	onSelect: function(files) {
		var browser = {
			versions: function () {
				var u = navigator.userAgent, app = navigator.appVersion;
				return { //移动终端浏览器版本信息 
					ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端 
					android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或uc浏览器 
					iPhone: u.indexOf('iPhone') > -1, //是否为iPhone或者QQHD浏览器 
					iPad: u.indexOf('iPad') > -1, //是否iPad 
				};
			}()
		};
		if (browser.versions.iPhone || browser.versions.iPad || browser.versions.ios) {
			var html = '', i = 0;
			var funAppendImage = function() {
				file = files[i];
				if (file) {
				    var reader = new FileReader();
				    reader.onload = function(e) {
				    	html = html + "<a href='javascript:;' onclick='delect_a(this)'><img width='42px' height='42px' name='img' src='"+e.target.result+"'/></a>";
		                i++;
		                funAppendImage();
		            };
		            reader.readAsDataURL(file);
				} else {
					if(i<9){
						for(var j=8-i;j>0;j--){
							html = html + "<a href='javascript:;'></a>";
						}
						$("#preview").html(html);
						if (html) {
							//删除方法
							$(".upload_delete").click(function() {
								ZXXFILE.funDeleteFile(files[parseInt($(this).attr("data-index"))]);
								return false;	
							});
							//提交按钮显示
							$("#fileSubmit").show();	
						}
					}
				}
			};
			funAppendImage();
		}
		/* if (browser.versions.android) {
			fromSubmit();
		} */
		fromSubmit();
	},
	//图片上传完毕或是删除之时执行飞方法:让其渐隐
	onDelete: function(file) {
	    $("#uploadList_" + file.index).fadeOut();
	}
};
ZXXFILE = $.extend(ZXXFILE, params);
ZXXFILE.init();

function fromSubmit(){
	$('#validateForm').attr("action","/store/addStoreCaseImage");
	$('#validateForm').submit(); 
}

</script> 
</body>
</html>
