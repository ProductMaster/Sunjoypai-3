/**
一级菜单通过map传过来
<select style="width:150px;" name="menuSelect" size="10" id="sele1">
	<c:forEach var="category" items="${sysCategory}" varStatus="status">
		<option value="<c:out value='${category.cateNo}'/>"><c:out value="${category.cateName}"/></option>
	</c:forEach>
</select>
页面使用：调用函数sele1Change();
*/
var selectCateUrl ="/product/category/ajax/getCategory";  //uri地址
function sele1Change(){
	$("#sele1").change(function() { //一级分类change事件
	$.get(selectCateUrl+"?cateNo="+$("#sele1").val()+"&"+Math.random(),
		function(data){
			if($("#sele2")[0]==null){//判断sele2是否已存在
				$("#newSelect").append("<select style='width:150px;margin-left:10px;' id='sele2' size='13'></select>"); //新建二级分类
			}
			$("#cateNo").val(-99) ;
			$("#sele2").empty();
			$("#sele3").empty();
			$(data).find('entity').each(function(j){
				$("#sele2").append("<option value='"+$(this).find('optionValue').text()+"'>"+$(this).find('optionText').text()+"</option>");//为二级分类填充值
			});
			seleChange();//二级分类change事件
		}); 
	});
}
function seleChange(){
	$("#sele2").change(function(){//二级分类change事件
		$.get(selectCateUrl+"?cateNo="+$("#sele2").val(),
			function(data){
			if($("#sele3")[0] == null){ //判断sele3是否已存在
				$("#newSelect").append("<select style='width:150px;margin-left:10px;' id='sele3' size='13'></select>");//新建三级分类
			}
			$("#cateNo").val(-99) ;
			$("#sele3").empty();
			$(data).find('entity').each(function(j){
				$("#sele3").append("<option value='"+$(this).find('optionValue').text()+"'>"+$(this).find('optionText').text()+"</option>");//为三级分类填充值
			});
			$("#sele3").change(function(){//三级分类change事件
				$.get(selectCateUrl+"?cateNo="+$("#sele3").val(),
					function(data){
					$("#cateNo").val($("#sele3").val()) ;  //给cateNo赋值
				});
			});
		});
	});
}