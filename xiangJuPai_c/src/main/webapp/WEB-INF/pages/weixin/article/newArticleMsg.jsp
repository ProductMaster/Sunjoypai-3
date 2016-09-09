<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title> 编辑图文自定义内容</title>
		<jsp:include page="/common/header.jsp" />
	</head>

	<body>

		<div class="container">
			<div>
				<div class="title">
					<b> 编辑图文自定义内容</b>
				</div>
				<form id="validateForm" class="form-horizontal" method="post"
					action="${pageContext.request.contextPath }/weixin/article/addArticleMsg">
					<table class="table table-bordered table-striped">
						<tbody>
							<tr class="th">
								<th class="rth" style="width: 20%;">
									图文类别：
								</th>
								<td class="ltd" colspan="3">
									<select class="required" name="articleCategory.id" >
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
							<tr class="th">
								<th class="rth">
									图文链接：
								</th>
								<td class="ltd" colspan="3">
									<input type="radio" name="nextViewType" value="0" checked="checked" onclick="changeType(this.value);"/>图文详情 &nbsp;&nbsp;&nbsp;
									<input type="radio" name="nextViewType" value="1" onclick="changeType(this.value);" />指定链接
								</td>
							</tr>
							<tr class="th" id="nextview">
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
							<%--<tr class="th" id="nextview" style="">
							<th class="rth" >
									指定URL:
							</th>
								<td class="ltd" colspan="3">
									 <input class="input-xlarge required" name="url" type="text" />
								</td>
							</tr>
							--%><tr>
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
	function changeType(value){
		if(value==0){
			$("#nextview").html('<th class="rth" >'+
									'正文：'+'</th>'+
								'<td class="ltd" colspan="3">'+
									 '<input class="input-xlarge required" name="url" value="weixin/article/showArticleMsg/" type="hidden" />'+
									'<textarea id="content_id" name="content" style="width: 700px; height: 300px;">'+
									'</textarea>'+
								'</td>');
			globalK.create('#content_id',{cssData: "body {font-family:'微软雅黑'}"	});
		}
		else if(value==1)
			$("#nextview").html('<th class="rth" >'+
									'指定URL：'+'</th>'+
								'<td class="ltd" colspan="3">'+
									 '<input class="input-xlarge required" name="url" type="text" />'+
								'</td>');
	}
</script>

	</body>

</html>