<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

	<head>
		<title>${dataDict.dictCategory.title}编辑属性值</title>
		<jsp:include page="/common/header.jsp"/>
	</head>

	<body>
	
		<div class="container">
			<div >
			<div class="title" >
			     <b>编辑属性值</b>
			</div>
				<form id="validateForm" class="form-horizontal" method="post" action="${pageContext.request.contextPath }/sys/updateDataDict/${dataDict.id}">	
					<table class="table table-bordered table-striped">
						<tbody>
							<tr class="th">
								<th class="rth">名称：</th>
								<td class="ltd">
									<input class="input-xlarge required" name="title" value="${dataDict.title}" type="text"/>
								</td>
								<th class="rth">code：</th>
								<td class="ltd">
									<input class="input-xlarge" name="memo" value="${dataDict.code}" type="text"/>
								</td>
							</tr>
							<tr class="th">								
								<th class="rth">描述：</th>
								<td class="ltd" colspan="3">
									<input class="input-xlarge" name="memo" value="${dataDict.memo}" type="text"/>
								</td>
							</tr>
							<tr class="th">
								<td>是否缺省：</td>
								<td>
									是<input class="input-xlarge required" name="defaultValue" value="1" type="radio" ${dataDict.defaultValue != 0? 'checked': ''}/>
									否<input class="input-xlarge required" name="defaultValue" value="0" type="radio" ${dataDict.defaultValue == 0? 'checked': ''} style="margin-right: 20px;" />
								</td>								
								<th class="rth">数据状态：</th>
								<td class="ltd"> 
									<input class="input-xlarge required" name="status" value="1" type="radio" ${dataDict.status != 0? 'checked': ''}/>启用
									<input class="input-xlarge required" name="status" value="0" type="radio" ${dataDict.status == 0? 'checked': ''} style="margin-left: 20px;"/>禁用
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
					<input name="id" value="${dataDict.id}" type="hidden"/>
					<input name="dictCategory.id" value="${dataDict.dictCategory.id}" type="hidden"/>
				</form>	
						
			</div>
		</div>
		
		<jsp:include page="/common/footer.jsp"/>
		
	</body>

</html>