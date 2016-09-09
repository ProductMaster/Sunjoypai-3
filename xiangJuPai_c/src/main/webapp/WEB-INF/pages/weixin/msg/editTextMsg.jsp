<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>编辑文本自定义内容</title>
		<jsp:include page="/common/header.jsp" />
	</head>

	<body>

		<div class="container">
			<div>
				<div class="title">
					<b>编辑文本自定义内容</b>
				</div>
				<form id="validateForm" class="form-horizontal" method="post"
					action="${pageContext.request.contextPath }/weixin/msg/updateTextMsg/${textMsg.id}">
					<table class="table table-bordered table-striped">
						<tbody>
							<tr class="th">
								<th class="rth" style="width: 20%;">
									关键字：
								</th>
								<td class="ltd" colspan="3">
									<input class="input-xlarge required" name="key"
										value="${textMsg.key}" type="text" size="12" />
								</td>
							</tr>
							<tr class="th">
								<th class="rth">
									自动回复内容:
								</th>
								<td class="ltd" colspan="3">
									<textarea rows="5" cols="10" class="input-xlarge required"
										name="content">${textMsg.content}</textarea>
								</td>
							</tr>
							<tr>
								<td colspan="4" align="center">
									<div align="center">
										<button type="submit" class="btn btn-primary">
											提&nbsp;&nbsp;&nbsp;交
										</button>
										<button type="button" class="btn btn-primary historyBackClass">
											返&nbsp;&nbsp;&nbsp;回
										</button>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</form>

			</div>
		</div>

		<jsp:include page="/common/weixinfooter.jsp" />

		<script type="text/javascript">
$(document).ready(function() {
	$("#validateForm").validate();
});
</script>

	</body>

</html>