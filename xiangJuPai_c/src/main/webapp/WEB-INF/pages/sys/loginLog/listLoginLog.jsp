<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" uri="ueye"%>

<!DOCTYPE html>
<html>

<head>
<title>系统访问列表</title>
<jsp:include page="/common/header.jsp" />
</head>

<body>

	<div class="container">
		<div class="title">
			<b>系统访问列表</b>
		</div>
		<form id="validateForm" class="form-horizontal" method="post"
			action="${pageContext.request.contextPath}/sys/listLoginLog">
			<div>
				<table id="t" class="table table-bordered table-striped">
					<tbody>
						<tr>
							<th>用户名</th>
							<th>登录时间</th>
						</tr>
						<c:forEach var="log" items="${page.datas}">
							<tr>
								<td>${log.loginName}</td>
								<td>${log.loginTimeStr}</td>
							</tr>
						</c:forEach>
						<jsp:include page="/common/no-data.jsp">
							<jsp:param value="3" name="colspan" />
						</jsp:include>
					</tbody>
				</table>

				<jsp:include page="/common/page.jsp">
					<jsp:param name="actionURL" value="sys/listLoginLog" />
				</jsp:include>

			</div>

		</form>

	</div>

	<jsp:include page="/common/footer.jsp" />

</body>

</html>