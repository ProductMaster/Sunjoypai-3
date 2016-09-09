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
					action="${pageContext.request.contextPath }/weixin/article/updateArticleMsg/${articleMsg.id}">
					<input type="hidden" name="id" value="${articleMsg.id}"/>
					<table class="table table-bordered table-striped">
						<tbody>
							<tr class="th">
								<th class="rth" style="width: 20%;">
									关键字：
								</th>
								<td class="ltd" colspan="3">
									<input class="input-xlarge " name="key"
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
										value="${articleMsg.picUrl}" type="text" />
								</td>
							</tr>
							<tr class="th">
								<th class="rth">
									简介：
								</th>
								<td class="ltd" colspan="3">
									<textarea rows="5" cols="10" class="input-xlarge required"
										name="Description">${articleMsg.description}</textarea>
								</td>
							</tr>
							<tr class="th">
								<th class="rth">
									图文链接：
								</th>
								<td class="ltd" colspan="3">
									<input type="radio" name="nextViewType" value="0" ${viewType== 0 ?'checked':''} onclick="changeType(this.value);"/>图文详情 &nbsp;&nbsp;&nbsp;
									<input type="radio" name="nextViewType" value="1" ${viewType== 1 ?'checked':''} onclick="changeType(this.value);" />指定链接
								</td>
							</tr>
							<tr class="th" id="nextview0">
								<th class="rth" >
									正文：
								</th>
								<td class="ltd" colspan="3">
									<input class="input-xlarge required" id="url0"
										value="weixin/article/showArticleMsg/${articleMsg.id}" type="hidden" />
									<textarea id="content_id" 
										style="width: 700px; height: 300px;">
										${articleMsg.content}
									</textarea>
								</td>
							</tr>
							<tr class="th" id="nextview1">
								<th class="rth" >
									指定URL：</th>
								<td class="ltd" colspan="3">
									 <input class="input-xlarge required" id="url1" type="text" value="${articleMsg.url}"/>
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

		<jsp:include page="/common/footer.jsp" />
		<script type="text/javascript">
		var globalK;
		var editor;
		KindEditor.ready(function(K) {
			globalK=K;
			editor = K.create('#content_id',{cssData: "body {font-family:'微软雅黑'}"	});
		});
		function articleMsgList(id){
			var href='${pageContext.request.contextPath}/weixin/article/articleMsgList/'+id;
			$.layer({
			    type: 2,
			    title: false,
			    iframe: {src: href},
			    area : ['1050px' , '566px'],
			    offset : ['100px','']
			});
		}
		$(function(){
			if('${viewType}'=='0'){
				$("#nextview0").show();
				$("#nextview1").hide();
				$("#url0").attr("name","url");
				$("#content_id").attr("name","content");
				$("#url1").removeAttr("name");
			}else{
				$("#nextview0").hide();
				$("#nextview1").show();
				$("#url0").removeAttr("name");
				$("#content_id").removeAttr("name");
				$("#url1").attr("name","url");
			}
		});
		function changeType(value){
			if(value==0){
				$("#nextview0").show();
				$("#nextview1").hide();
				$("#url0").attr("name","url");
				$("#content_id").attr("name","content");
				$("#url1").removeAttr("name");
			}
			else if(value==1){
				$("#nextview0").hide();
				$("#nextview1").show();
				$("#url0").removeAttr("name");
				$("#content_id").removeAttr("name");
				$("#url1").attr("name","url");
			}
		}
		</script>
		
		<script type="text/javascript">
$(document).ready(function() {
	$("#validateForm").validate();
});
</script>

	</body>

</html>