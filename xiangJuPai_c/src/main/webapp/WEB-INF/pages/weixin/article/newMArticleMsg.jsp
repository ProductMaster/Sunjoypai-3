<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>新增多图文</title>
		<jsp:include page="/common/header.jsp" />
	</head>

	<body>

		<div class="container">
			<div>
				<div class="title">
					<b>新增多图文</b>
				</div>
				<form id="validateForm" class="form-horizontal" method="post"
					action="${pageContext.request.contextPath }/weixin/article/addMArticleMsg">
					<table class="table table-bordered table-striped">
						<tr class="th">
								<th class="rth" style="width: 20%;">
									图文类别：
								</th>
								<td class="ltd" colspan="3">
									<select class="required" name="articleCategoryId" >
									<option value="">请选择</option>
									<c:forEach items="${articleCategoryList}" var="aticleCategory">
									<option value="${aticleCategory.id}">${aticleCategory.title}</option>
									</c:forEach>
									</select>
								</td>
							</tr>
							<tr class="th">
								<th class="rth" style="width: 20%;">
									关键字：
								</th>
								<td class="ltd" colspan="3">
									<input class="input-xlarge" name="key"
										value="${articleMsg.key}" type="text" size="12" />
								</td>
							</tr>
							<tr><th colspan="4"></th></tr>
							<tr class="th">
								<th class="rth" style="width: 20%;">
									标题：
								</th>
								<td class="ltd" colspan="3">
									<input class="input-xlarge required" name="title"
										value="${articleMsg.title}" type="text" size="12" />
								</td>
							</tr>
							<tr class="th">
								<th class="rth" style="width: 20%;">
									图文封面：
								</th>
								<td class="ltd" colspan="3">
									<input class="input-xlarge required" name="picUrl"
										value="${articleMsg.picUrl}" type="text" size="12" /><br />
								</td>
							</tr>
							<tr class="th">
								<th class="rth">
									简介：
								</th>
								<td class="ltd" colspan="3">
									<textarea rows="5" cols="10" class="input-xlarge required"
										name="description">${articleMsg.description}</textarea>
								</td>
							</tr>
							<tr class="th" id="nextview1">
								<th class="rth" >
									正文：
								</th>
								<td class="ltd" colspan="3">
									 <input class="input-xlarge required" name="url"
										value="weixin/article/showArticleMsg/" type="hidden" />
									<textarea id="content_id" name="content"
										style="width: 700px; height: 300px;">
									</textarea>
								</td>
							</tr>
							<tr><th colspan="4"></th></tr>
							<tr class="th">
								<th class="rth" style="width: 20%;">
									标题：
								</th>
								<td class="ltd" colspan="3">
									<input class="input-xlarge required" name="title_m"
										value="${articleMsg.title}" type="text" size="12" />
								</td>
							</tr>
							<tr class="th">
								<th class="rth" style="width: 20%;">
									图文封面：
								</th>
								<td class="ltd" colspan="3">
									<input class="input-xlarge required" name="picUrl_m"
										value="${articleMsg.picUrl}" type="text" size="12" /><br />
								</td>
							</tr>
							<tr class="th">
								<th class="rth">
									简介：
								</th>
								<td class="ltd" colspan="3">
									<textarea rows="5" cols="10" class="input-xlarge required"
										name="description_m">${articleMsg.description}</textarea>
								</td>
							</tr>
							<tr class="th" id="nextview">
								<th class="rth" >
									正文：
								</th>
								<td class="ltd" colspan="3">
									 <input class="input-xlarge required" name="url_m"
										value="weixin/article/showArticleMsg/" type="hidden" />
									<textarea id="content_id" name="content_m"
										style="width: 700px; height: 300px;">
									</textarea>
								</td>
							</tr>
							<tbody id="tbody">
							</tbody>
							<tr>
							<td colspan="4" align="left">
							<input type="button" onclick="addMsg();" value="新增图文"/>
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
					</table>
				</form>
				
			</div>
		</div>

		<jsp:include page="/common/footer.jsp" />
	
		<script type="text/javascript">
		var globalK;
		KindEditor.ready(function(K) {
			globalK = K;
			window.editor = K.create('#content_id',{cssData: "body {font-family:'微软雅黑'}"	});
		});
		
		</script>
		
<script type="text/javascript">
	$(document).ready(function() {
		$("#validateForm").validate();
	});
	function addMsg(){
	$("#tbody").append('<tr><th colspan="4"></th></tr><tr class="th"><th class="rth" style="width: 20%;">标题：</th>'+
					 '<td class="ltd" colspan="3"><input class="input-xlarge required" name="title_m" value="${articleMsg.title}" type="text" size="12" />'+
					 '</td></tr><tr class="th"><th class="rth" style="width: 20%;">图文封面：</th><td class="ltd" colspan="3">'+
					 '<input class="input-xlarge required" name="picUrl_m" value="${articleMsg.picUrl}" type="text" size="12" /><br />'+
					 '</td></tr><tr class="th"><th class="rth">简介：</th><td class="ltd" colspan="3"><textarea rows="5" cols="10" '+
					 'class="input-xlarge required"	name="description_m">${articleMsg.description}</textarea></td></tr><tr class="th" '+
					 'id="nextview"><th class="rth" >正文：</th>	<td class="ltd" colspan="3"><input class="input-xlarge required" name="url_m" '+
					 'value="weixin/article/showArticleMsg/" type="hidden" /><textarea id="content_id" name="content_m"	style="width: 700px; height: 300px;">'+
					 '</textarea></td></tr>');
	globalK.create('#content_id',{cssData: "body {font-family:'微软雅黑'}"	});
	}
</script>

	</body>

</html>