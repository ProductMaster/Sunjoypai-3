<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="u" uri="ueye"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1.0,maximum-scale=1.0 ,user-scalable=no"/>
<title>新增发布</title>
<link href="${pageContext.request.contextPath}/css/base.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
	  	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/amazeui.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/pic/jquery-1.7.2.js"></script>
		
		<link rel="stylesheet" href="${pageContext.request.contextPath}/js/pic/zyupload/skins/zyupload-1.0.0.css " type="text/css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/pic/zyupload/zyupload.basic-1.0.0.js"></script>



		<!-- <script type="text/javascript">
			seajs.use(PATH+'upload');
		</script> -->
		

		<script>
	window.URL = window.URL || window.webkitURL;
	var fileElem = document.getElementById("fileElem"),
	    fileList = document.getElementById("fileList");
	function handleFiles(obj) {
		var files = obj.files,
			img = new Image();
		if(window.URL){
			//File API
			  //alert(files[0].name + "," + files[0].size + " bytes");
		      img.src = window.URL.createObjectURL(files[0]); //´´½¨Ò»¸öobject URL£¬²¢²»ÊÇÄãµÄ±¾µØÂ·¾¶
		      img.width = 200;
		      img.onload = function(e) {
		         window.URL.revokeObjectURL(this.src); //Í¼Æ¬¼ÓÔØºó£¬ÊÍ·Åobject URL
		      }
		      fileList.appendChild(img);
		}else if(window.FileReader){
			//opera²»Ö§³ÖcreateObjectURL/revokeObjectURL·½·¨¡£ÎÒÃÇÓÃFileReader¶ÔÏóÀ´´¦Àí
			var reader = new FileReader();
			reader.readAsDataURL(files[0]);
			reader.onload = function(e){
				//alert(files[0].name + "," +e.total + " bytes");
				img.src = this.result;
				img.width = 200;
				fileList.appendChild(img);
			}
		}else{
			//ie
			obj.select();
			obj.blur();
			var nfile = document.selection.createRange().text;
			document.selection.empty();
			img.src = nfile;
			img.width = 200;
			img.onload=function(){
		      //alert(nfile+","+img.fileSize + " bytes");
		    }
			fileList.appendChild(img);
			//fileList.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src='"+nfile+"')";
		}
	}
</script>

<script type="text/javascript">
		
			function clickFile(){
	
				 $("#uploadFile").click();
				 
			}
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
	<!--input type="file" style="display: none;" name="uploadFile" id="uploadFile" multiple="multiple" accept="image/*" / -->
	<div class="attentionTop">
		<i class="m_back"></i>
		<h2>添加案例</h2>
		<a href="javascript:void(0)" onclick="return doSubmit();" class="r_back">上传</a>
	</div>

	<!--input type="file"  id="uploadFile" name="uploadFile"  multiple="multiple" accept="image/*"  onchange="handleFiles(this)" -->
	
	
	<div class="upload">
		<textarea name="describe" placeholder="输入商品描述，12字内..." id="describe"></textarea>
		
		<div class="uploadImg">
		   
			<ul>
			
				<!-- <li><img src="pic/porductImg-01.jpg" alt="" /></li>
				<li><img src="pic/porductImg-01.jpg" alt="" /></li>
				<li><img src="pic/porductImg-01.jpg" alt="" /></li>
				<li><img src="pic/porductImg-01.jpg" alt="" /></li>
				<li><img src="pic/porductImg-01.jpg" alt="" /></li>
				<li><img src="pic/porductImg-01.jpg" alt="" /></li> -->
				<!--li><a href="javascript:void(0)" onclick="clickFile()"><i class="iconfont">&#xe611;</i></a></li -->
			</ul>
		</div>
	</div>
	<div id="zyupload" class="zyupload"></div>
</form>
<!--div id="fileList" style="width:200px;height:200px;">调试上传图片</div -->





	    
	    <script type="text/javascript">
			$(function(){
				// 初始化插件
				$("#zyupload").zyUpload({
					width            :   "650px",                 // 宽度
					height           :   "400px",                 // 宽度
					itemWidth        :   "140px",                 // 文件项的宽度
					itemHeight       :   "115px",                 // 文件项的高度
					url              :   "/upload/UploadAction",  // 上传文件的路径
					fileType         :   ["jpg","png","jpeg","js","exe"],// 上传文件的类型
					fileSize         :   51200000,                // 上传文件的大小
					multiple         :   true,                    // 是否可以多个文件上传
					dragDrop         :   false,                   // 是否可以拖动上传文件
					tailor           :   false,                   // 是否可以裁剪图片
					del              :   true,                    // 是否可以删除文件
					finishDel        :   false,  				  // 是否在上传文件完成后删除预览
					/* 外部获得的回调接口 */
					onSelect: function(selectFiles, allFiles){    // 选择文件的回调方法  selectFile:当前选中的文件  allFiles:还没上传的全部文件
						console.info("当前选择了以下文件：");
						console.info(selectFiles);
					},
					onDelete: function(file, files){              // 删除一个文件的回调方法 file:当前删除的文件  files:删除之后的文件
						console.info("当前删除了此文件：");
						console.info(file.name);
					},
					onSuccess: function(file, response){          // 文件上传成功的回调方法
						console.info("此文件上传成功：");
						console.info(file.name);
						console.info("此文件上传到服务器地址：");
						console.info(response);
						$("#uploadInf").append("<p>上传成功，文件地址是：" + response + "</p>");
					},
					onFailure: function(file, response){          // 文件上传失败的回调方法
						console.info("此文件上传失败：");
						console.info(file.name);
					},
					onComplete: function(response){           	  // 上传完成的回调方法
						console.info("文件上传完成");
						console.info(response);
					}
				});
				
			});
		
		</script> 
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
 
</body>
</html>
