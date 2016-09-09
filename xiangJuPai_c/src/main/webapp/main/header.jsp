<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<title>Header</title>
		<link href="${pageContext.request.contextPath}/css/twitter/bootstrap.css" rel="stylesheet" />
		<link href="${pageContext.request.contextPath}/css/twitter/bootstrap-responsive.css" rel="stylesheet" />
		<script type="text/javascript">
		function loginOut(){
			if(confirm("确认退出么？")){
				window.parent.location.href="${pageContext.request.contextPath}/loginOut";
			}
		}
		</script>
	</head>

	<body>
	
		<div class="navbar navbar-fixed-top">
			<div class="navbar-inner" style="min-height:75px;">
				<div class="container-fluid">
					<a class="brand" href="#" onclick="return false;" style="font-size: 25px; color: white; font: bold; margin-top:10px;">贝酷业务管控平台</a>
					
					<div class="btn-group pull-right">
						<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
							<i class="icon-user"></i> 
								${erpCustomer.customerName} 
							<span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
							<li><a href="javascript:void(0)" onclick="loginOut()">退出</a></li>
						</ul>
					</div>
	
				</div>
			</div>
		</div>
	
		<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
		<script src="${pageContext.request.contextPath}/js/twitter/bootstrap-dropdown.js"></script>
	
	</body>
	
</html>