<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>服诺学长系统</title>
<link href="${pageContext.request.contextPath}/css/login.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="wrapBox">
		<div class="wrap">
			<div class="loginBox">
				<div class="loginBoxIn">
					<form id="validateForm" method="post" action="${pageContext.request.contextPath}/login" name="loginForm">
						<dl>
							<dt>欢迎光临服诺学长后台</dt>
						</dl>
						<dl>
							<dt>学&nbsp;&nbsp;&nbsp;长：</dt>
							<dd>
								<input id="username" name="userName"
									class="loginBoxIn-input1 material_geren_input" />
							</dd>
						</dl>
						<dl>
							<dt>密&nbsp;&nbsp;&nbsp;码：</dt>
							<dd>
								<input id="password" type="password" name="password"
									class="loginBoxIn-input1 material_geren_input" />
							</dd>
						</dl>
						<dl>
							<dt>验证码：</dt>
							<dd>
								<input name="captcha" class="loginBoxIn-input1 material_geren_input1" />
								<img id="captchaImg" onclick="changeCaptcha();"
									src="${pageContext.request.contextPath}/servlet/captcha.jpg"
									alt="" align="absmiddle" />
							</dd>
							<dd>
								<a href="javascript:void(0);" onclick="changeCaptcha();"
									class="loginBoxIn-link">看不清楚，换一张</a>
							</dd>
						</dl>
						<dl>
							<dt></dt>
							<dd>
								<input onclick="doSubmit()" type="button"
									class="loginBoxIn-button" />
							</dd>
						</dl>
						<div class="cb"></div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/common/footer.jsp" />
	<script type="text/javascript">
		getDepts('${dept.parent.id}');

		function getDepts(defaultSelect) {
			$.ajax({
				type : 'post',
				url : '${pageContext.request.contextPath}/sys/companyNodes',
				dataType : 'json',
				success : function(data) {
					if (data) {
						for ( var i = 0; i < data.length; i++) {
							var op = new Option(data[i].name, data[i].id);
							op.innerHTML = data[i].name;
							if (defaultSelect == data[i].id) {
								op.selected = true;
							}
							$("#company").append(op);
						}
					}
				}
			});
		}
	</script>
	<script type="text/javascript">
	<!--
		if (window.self != window.top) {
			window.open(".", "_top");
		}
		document.forms["loginForm"].elements["username"].focus();

		function changeCaptcha() {
			document.getElementById("captchaImg").src = "${pageContext.request.contextPath}/servlet/captcha.jpg?"
					+ (new Date()).valueOf();
		}

		// -->
		function doSubmit() {
			$("#validateForm").submit();
		}
		
		$(function() {
			message("${msg}");
		});
	</script>
	
</body>
</html>