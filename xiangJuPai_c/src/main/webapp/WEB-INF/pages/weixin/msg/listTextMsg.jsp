<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" uri="ueye"%>

<!DOCTYPE html>
<html>

	<head>
		<title>信息列表</title>
		<jsp:include page="/common/header.jsp" />
	</head>

	<body>

		<div class="container">
			<div class="title">
				<b>信息列表</b>
			</div>
			<form id="validateForm" class="form-horizontal" method="post"
				action="${pageContext.request.contextPath}/weixin/msg/listTextMsg">
				<div class="search">
					<div class="row">
						<div class="span10">
							<div class="control-group">
								<span style="margin-left: 10px;">关键字 </span>
								<input id="categoryCode" class="input-large"
									name="filter_LIKES_key" value="${filter_LIKES_key}"
									style="width: 100px;" placeholder="信息名称" />
								<button type="submit" class="btn btn-primary">
									查询
								</button>
							</div>
						</div>
					</div>
				</div>

				<div>
					<table class="table table-bordered table-striped">
						<tbody>
							<tr>
								<th class="th" colspan="3" align="right">
									<div align=right style="margin-right: 10px;">
										<a
											href="${pageContext.request.contextPath}/weixin/msg/newTextMsg">
											新增信息 </a>
									</div>
								</th>
							</tr>
							<tr class="tableth">
								<th>
									关键字
								</th>
								<th>
									回复
								</th>
								<th>
									操 作
								</th>
							</tr>
							<c:forEach var="textMsg" items="${page.datas}">
								<tr>
									<td>
										${textMsg.key}
									</td>
									<td>
										${textMsg.content}
									</td>
									<td>
										<a
											href="${pageContext.request.contextPath}/weixin/msg/editTextMsg/${textMsg.id}">修改</a>
										<a
											href="${pageContext.request.contextPath}/weixin/msg/destroyTextMsg/${textMsg.id}"
											class="deletePromptClass">删除</a>
									</td>
								</tr>
							</c:forEach>
							<jsp:include page="/common/no-data.jsp">
								<jsp:param value="3" name="colspan" />
							</jsp:include>
						</tbody>
					</table>

					<jsp:include page="/common/page.jsp">
						<jsp:param name="actionURL" value="dict-category" />
					</jsp:include>

				</div>

			</form>

		</div>

		<jsp:include page="/common/weixinfooter.jsp" />

	</body>

</html>