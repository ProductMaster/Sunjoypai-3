<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" uri="ueye"%>

<!DOCTYPE html>
<html>

	<head>
		<title>${dictCategory.title}-属性值列表</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
		
		<div class="container">
			<div class="title" >
			     <b>属性值列表</b>
			</div>				
			<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/sys/listDataDict">
				<div>
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th  class="th" colspan="7" align="right">
									<div align=left style="margin-left:5px;display:inline;float:left">
										<a href="${pageContext.request.contextPath}/sys/listDictCategory">
											返回分类
										</a>
									</div>								
									<div align=right style="margin-right:10px;display:inline;float:right">
										<a href="${pageContext.request.contextPath}/sys/newDataDict/${dictCategory.id}">
											新增
										</a>
									</div>
								</th>
							</tr>
							<tr  class="tableth">
								<th style="width: 20%;">名称</th>
								<th style="width: 10%;">缺省</th>
								<th style="width: 10%;">状态</th>
								<th style="width: 30%;">描述</th>								
								<th style="width: 20%;">操 作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="dataDict" items="${page.datas}">
								<tr>
									<td>${dataDict.title}</td>
									<td>${dataDict.defaultValue == 0? '否': '是'}</td>
									<td>${dataDict.status == 0? '禁用': '启用'}</td>
									<td>${dataDict.memo}</td>									
									<td>
										<a href="${pageContext.request.contextPath}/sys/editDataDict/${dataDict.id}">修改</a>
										<a href="${pageContext.request.contextPath}/sys/destroyDataDict/${dictCategory.id}/${dataDict.id}">删除</a>
									</td>
								</tr>
							</c:forEach>
							<jsp:include page="/common/no-data.jsp">
								<jsp:param value="6" name="colspan"/>
							</jsp:include>								
						</tbody>
					</table>
					
				</div>
				
			</form>
				
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
	</body>

</html>