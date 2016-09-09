<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" uri="ueye"%>

<!DOCTYPE html>
<html>

	<head>
		<title>自定义图文回复信息</title>
		<jsp:include page="/common/header.jsp" />
	</head>

	<body>

		<div class="container">
			<div class="title">
				<b>自定义图文回复信息</b>
			</div>
			<form id="validateForm" class="form-horizontal" method="post"
				action="${pageContext.request.contextPath}/weixin/article/listArticleMsg">
				<div class="search">
					<div class="row">
						<div class="span10">
							<div class="control-group">
								<span style="margin-left: 10px;">关键字 </span>
								<input id="categoryCode" class="input-large"
									name="filter_LIKES_key" value="${filter_LIKES_key}"
									style="width: 100px;" />
								<span style="margin-left: 10px;">标题 </span>
								<input class="input-large"
									name="filter_LIKES_title" value="${filter_LIKES_title}"
									style="width: 100px;" />
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
										<a href="${pageContext.request.contextPath}/weixin/article/newArticleMsg">
											新增单图文 </a>&nbsp;&nbsp;
										<a href="${pageContext.request.contextPath}/weixin/article/newMArticleMsg">
											新增多图文</a>
										<a href="${pageContext.request.contextPath}/weixin/article/newMArticleMsgMenu">
											新增多图文菜单</a>	
									</div>
								</th>
							</tr>
							<tr class="tableth">
								<th>
									关键字
								</th>
								<th>标题</th>
								<th>
									操 作
								</th>
							</tr>
							<c:forEach var="articleMsg" items="${page.datas}">
								<tr>
									<td>
										${articleMsg.key}
									</td>
									<td>
										${articleMsg.title}
									</td>
									<td>
										<a href="${pageContext.request.contextPath}/weixin/article/editArticleMsg/${articleMsg.id}">修改</a>
										<a href="#" onclick="return confirmEvent('${pageContext.request.contextPath}/weixin/article/destroyArticleMsg/${articleMsg.id}');" class="deletePromptClass">删除</a>
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
		<script type="text/javascript">
			$(function(){
				var msg='${errorMsg}';
				if(msg!=null&&msg!='')
					alert(msg);
			});
			function confirmEvent(url){
				var isDel=false;
				$.prompt('是否确认删除？',{
					buttons:{'确认':true,'取消':false},
					submit:function(e,v,m,f){
						if(v)
							window.location.href=url;
					}
					
				});
				return false;
			}
		</script>

	</body>

</html>