<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" uri="ueye"%>

<!DOCTYPE html>
<html>

	<head>
		<title>${weixinMenu.title}</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
		
		<div class="container">
			<div class="title" >
			     <b>子菜单列表</b>
			</div>				
			<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/weixinMenu/listSubMenu">
				<div>
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th  class="th" colspan="4" align="right">
									<div align=left style="margin-left:5px;display:inline;float:left">
										<a href="${pageContext.request.contextPath}/weixinMenu/listMenu">
											返回分类
										</a>
									</div>								
									<div align=right style="margin-right:10px;display:inline;float:right">
										<a href="${pageContext.request.contextPath}/weixinMenu/newSubMenu/${mainMenu.id}">
											新增
										</a>
									</div>
								</th>
							</tr>
							<tr  class="tableth">
								<th width=20%>菜单名称</th>
								<th width=10%>排序</th>
								<th width=10%>菜单类型</th>
								<th width=50%>关键字或URL</th>
								<th width=10%>操 作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="subMenu" items="${page.datas}">
								<tr>
									<td>${subMenu.title}</td>
									<td>${subMenu.orderBy}</td>
									<td>${subMenu.type}</td>
									<td>${subMenu.value}</td>
									<td>
										<a href="${pageContext.request.contextPath}/weixinMenu/editSubMenu/${subMenu.id}">修改</a>
										<a href="${pageContext.request.contextPath}/weixinMenu/destroySubMenu/${mainMenu.id}/${subMenu.id}">删除</a>
									</td>
								</tr>
							</c:forEach>
							<jsp:include page="/common/no-data.jsp">
								<jsp:param value="4" name="colspan"/>
							</jsp:include>								
						</tbody>
					</table>
					
					<jsp:include page="/common/page.jsp">
						<jsp:param name="actionURL" value="weixinMenu/list/${mainMenu.id}"/>
					</jsp:include>
						
				</div>
				
			</form>
				
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
	</body>

</html>