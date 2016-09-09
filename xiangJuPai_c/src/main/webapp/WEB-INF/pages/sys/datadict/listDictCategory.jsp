<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" uri="ueye"%>

<!DOCTYPE html>
<html>

	<head>
		<title>系统属性类别表</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
		
		<div class="container">
			<div class = "title">
				<b>系统属性类别表</b>
			</div>
			<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/sys/listDictCategory">
				<div class="search">
					<div class="row">
						<div class="span12">
							<div class="control-group">
					           	<span style="margin-left: 10px;">类型名称 </span>
					            <input id="title" class="input-large" name="filter_LIKES_title" value="${filter_LIKES_title}" style="width: 100px;" placeholder="类型名称"/>
							  	<button type="submit" class="btn btn-primary">查询</button>
					        </div>
						</div>
					  </div>
				</div>
				
				<div>
					<table class="table table-bordered table-striped">
						<tbody>
							<tr>
								<th  class="th" colspan="7" align="right">
									<div align=right style="margin-right:10px;">
										<a href="${pageContext.request.contextPath}/sys/newDictCategory">
											新增类别
										</a>
									</div>
								</th>
							</tr>
							<tr class="tableth">
								<th width="10%">类型编码</th>
								<th width="15%">类型名称</th>
								<th width="50%">说明</th>
								<th width="15%">字典数据</th>
								<th width="10%">操 作</th>
							</tr>
							<c:forEach var="dictCategory" items="${page.datas}">
								<tr>
									<td>${dictCategory.code}</td>
									<td>${dictCategory.title}</td>
									<td>${dictCategory.descr}</td>
									<td>
										<a href="${pageContext.request.contextPath}/sys/listDataDict/${dictCategory.id}">字典数据</a>
									</td>
									<td>
										<a href="${pageContext.request.contextPath}/sys/editDictCategory/${dictCategory.id}">修改</a>
									</td>
								</tr>
							</c:forEach>
							<jsp:include page="/common/no-data.jsp">
								<jsp:param value="6" name="colspan"/>
							</jsp:include>								
						</tbody>
					</table>
					
					<jsp:include page="/common/page.jsp">
						<jsp:param name="actionURL" value="dict-category"/>
					</jsp:include>
						
				</div>
				
			</form>
				
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
	</body>

</html>