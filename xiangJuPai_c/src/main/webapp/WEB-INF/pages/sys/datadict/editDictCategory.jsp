<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>编辑系统属性类别</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
	
		<div class="container">
			<div class = "title">
				<b>编辑系统属性类别</b>
			</div>		
			<div>
					
				<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath }/sys/updateDictCategory/${dictCategory.id}">	
					<table class="table table-bordered table-striped">
						<tbody>
							<tr class="th">
								<th class="rth">类型编号：</th>
								<td class="ltd">
									<input name="id" value="${dictCategory.id}" type="hidden"/>
									<input class="input-xlarge required" id="categoryCode" name="code" value="${dictCategory.code}" type="text"/>
								</td>
							</tr>
							<tr class="th">
								<th class="rth">类型名称：</th>
								<td class="ltd">
									<input class="input-xlarge required" id="title" name="title" value="${dictCategory.title}" type="text"/>
								</td>
							</tr>
							<tr class="th">
								<th class="rth">类型说明：</th>
								<td class="ltd">
									<input class="input-xlarge" id="descr" name="descr" value="${dictCategory.descr}" type="text"/>
								</td>
							</tr>
							<tr>
								<td colspan="2" align="center">
									<div align="center">
										<button type="submit" class="btn btn-primary">提&nbsp;&nbsp;&nbsp;交</button>
										<button type="button" class="btn btn-primary historyBackClass">返&nbsp;&nbsp;&nbsp;回</button>
									</div>
								</td>
							</tr>							
						</tbody>
					</table>
				</form>	
						
			</div>
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
		<script type="text/javascript">
			$(document).ready(function() {
				$("#validateForm").validate();
			});
		</script>
		
	</body>

</html>