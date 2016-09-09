<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" uri="ueye"%>

<!DOCTYPE html>
<html>

	<head>
		<title>图文信息</title>
		<jsp:include page="/common/homeheader.jsp" />
	</head>

	<body>

		<div class="container">
			<div class="title">
				<b>图文信息</b>
			</div>
			<form id="validateForm" class="form-horizontal" method="post"
				action="${pageContext.request.contextPath}/weixin/article/articleMsgList/${id}">
				<div class="search">
					<div class="row">
						<div class="span10">
							<div class="control-group">
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
				<button id="a" style="margin-right: 0px;" type="button" class="btn btn-primary">确定选择</button>
				</div>
				<div>
					<table class="table table-bordered table-striped">
						<tbody>
							<tr>
								<th class="th" colspan="3" align="right">
								</th>
							</tr>
							<tr class="tableth">
								<th>
								</th>
								<th>标题</th>
							</tr>
							<c:forEach var="articleMsg" items="${msgMap}">
								<tr>
									<td>
									<input type="checkbox" name="messageIds" value="${articleMsg.key.id}" ${articleMsg.value==true?"checked":""}
										 title="${articleMsg.key.title}" />
									</td>
									<td>
										${articleMsg.key.title}
									</td>
								</tr>
							</c:forEach>
							<jsp:include page="/common/no-data.jsp">
								<jsp:param value="3" name="colspan" />
							</jsp:include>
						</tbody>
					</table>

					<jsp:include page="/common/page.jsp">
						<jsp:param name="actionURL" value="weixin/article/articleMsgList/${id}" />
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
			
			var index = parent.layer.getFrameIndex(window.name);
			$('#a').click(function(){
					parent.$("input[id='choosecatalogId']").val($("input[name='messageIds']:checked").val());
					parent.$('#bnBarcode').val($("input[name='catalog']:checked").attr("bnBarcode"));
					parent.$('#selectedName').val($("input[name='catalog']:checked").attr("catalogName"));
					parent.$('#availableQty').val($("input[name='catalog']:checked").attr("availableQty"));
					parent.$('#price').val($("input[name='catalog']:checked").attr("price"));
					parent.layer.close(index);
			});
		</script>

	</body>

</html>