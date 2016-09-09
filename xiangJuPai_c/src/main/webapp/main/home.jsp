<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<title>微营销管理平台</title>
</head>

<frameset rows="75,*" cols="*" frameborder="no" border="2"
	bordercolor="#999999" framespacing="0">
	<frame src="${pageContext.request.contextPath}/main/header.jsp"
		name="topFrame" scrolling="No" noresize="noresize" id="topFrame"
		title="topFrame" />
	<frameset rows="*" cols="200,*" framespacing="0" frameborder="no"
		border="0">
		<frame src="${pageContext.request.contextPath}/menu/left"
			bordercolor="#999999" name="leftFrame" scrolling="auto"
			noresize="noresize" id="leftFrame" title="菜单" />
		<frame src="main.jsp"
			name="mainFrame" id="mainFrame" title="mainFrame" />
	</frameset>
</frameset>

</html>
