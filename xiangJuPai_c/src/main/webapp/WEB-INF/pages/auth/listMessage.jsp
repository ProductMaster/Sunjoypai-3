<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html;   charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="Author" content="" />
<meta name="Keywords" content="" />
<meta name="Description" content="" />
<title>信息提示</title>
</head>

<body>
	<b class="round_border"> <b class="round_border_layer3"></b> <b
		class="round_border_layer2"></b> <b class="round_border_layer1"></b>
	</b>
	<div class="r_content">
		<div class="editTip">
			<center>
				<h1 class="editTip_title">信息出错啦！</h1>
				<p class="editTip_cont">
					<strong>${message}</strong> <a href="javascript:void(0);"
						onclick="history.back();return false;">返回</a>
				</p>
				<img src="${pageContext.request.contextPath}/images/404.jpg" border="0" />
			</center>
		</div>
	</div>
	<b class="round_border"> <b class="round_border_layer1"></b> <b
		class="round_border_layer2"></b> <b class="round_border_layer3"></b>
	</b>
</body>
</html>
