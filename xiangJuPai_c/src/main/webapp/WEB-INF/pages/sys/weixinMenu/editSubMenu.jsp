<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>${subMenu.mainMenu.title}编辑属性值</title>
		<jsp:include page="/common/homeheader.jsp"/>
	</head>

	<body>
	
		<div class="container">
			<div >
			<div class="title" >
			     <b>编辑属性值</b>
			</div>
				<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath }/weixinMenu/updateSubMenu/${subMenu.id}">	
					<table class="table table-bordered table-striped">
						<tbody>
							<tr class="th">
								<th class="rth" style="width: 20%;">编号：</th>
								<td class="ltd" style="width: 30%;">
									<input class="input-xlarge required" name="orderBy" value="${subMenu.orderBy}" type="text"/>
								</td>
							</tr>
							<tr class="th">
								<th class="rth" style="width: 20%;">关键字或URL：</th>
								<td class="ltd" style="width: 30%;">
									<input name="id" value="${subMenu.id}" type="hidden"/>
									<input name="mainMenu.id" value="${subMenu.mainMenu.id}" type="hidden"/>
									<input class="input-xlarge required" name="value" value="${subMenu.value}" type="text"/>
								</td>
							</tr>
							<tr class="th">
								<th class="rth">名称：</th>
								<td class="ltd">
									<input class="input-xlarge required" name="title" value="${subMenu.title}" type="text"/>
								</td>
							</tr>
							<tr class="th">
								<th class="rth" style="width: 20%;">标签类型：</th>
								<td class="ltd" style="width: 30%;">
									Click:<input class="input-xlarge required " name="type" value="click" ${subMenu.type=="click"?"checked":""} type="radio"/>
									View:<input class="input-xlarge required " name="type" value="view" ${subMenu.type=="view"?"checked":""} type="radio"/>
								</td>
							</tr>
							<tr>
								<td colspan="4" align="center">
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
		
		<jsp:include page="/common/weixinfooter.jsp"/>
		
	</body>

</html>