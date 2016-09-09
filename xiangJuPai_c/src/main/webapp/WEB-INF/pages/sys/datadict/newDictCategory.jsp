<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>新增系统属性类别</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
	
		<div class="container">
			<div class = "title">
				<b>新增系统属性类别</b>
			</div>
			<div>
					
				<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath }/sys/addDictCategory">	
					<table class="table table-bordered table-striped">
						<tbody>
							<tr class="th">
								<th class="rth">类型编号：</th>
								<td class="ltd">
									<input class="input-xlarge required" name="code" type="text"/>
								</td>
							</tr>
							<tr class="th">
								<th class="rth">类型名称：</th>
								<td class="ltd">
									<input class="input-xlarge required" id="title" name="title" type="text"/>
								</td>
							</tr>
							<tr class="th">
								<th class="rth">类型说明：</th>
								<td class="ltd">
									<input class="input-xlarge" id="descr" name="descr" type="text"/>
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
		
	</body>

</html>