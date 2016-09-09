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
<script type="text/javascript">
	var href = "${pageContext.request.contextPath}";
	if ("1" == "${param.status}") {
		href = "/auth/timeout";
	}
	window.parent.location = href;
	// window.parent.location.href = href;  
</script>
</head>

<body>
</body>
</html>
