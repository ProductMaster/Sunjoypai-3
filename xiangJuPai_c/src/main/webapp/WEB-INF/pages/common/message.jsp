<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>提示信息</title>
<jsp:include page="/common/header.jsp" />
</head>

<body>
	<a id="showdiv" href="#inline" style="display: none;" title="显示一个DIV内容">点击这里</a>

	<div id="inline"
		style="width:500px; height:360px; overflow:auto;display: none; vertical-align: middle; text-align: center;">
		${msg}
	</div>
	
	
	<jsp:include page="/common/footer.jsp" />
	<script type="text/javascript">
		$(function() {
			$("#showdiv").fancybox();
			$("#showdiv").click();
		});
	</script>
</body>
</html>
