/*
 * tablePaging 2.5.0 - New Wave Javascript
 * 表格分页AJAX控件
 * Copyright (c) 2008  (rome360.com)
 *
 * $CreateDate: 2008-11-13 10:11:00   $
 * $LastModifyDate: 2010-05-27 16:00:00   $
 * 调用时 至少传入url 、 tabId 
 * pageNo、pageSize不传入 ,则使用默认值

 * 在jsp页面任何位置加入如下form"<form  id="pagingForm" name="pagingForm" method="post"></form>",把查询条件封装进去,如没有条件则加空form
 * 如果需要编辑 初始化增加函数调用setEditorForm("需要编辑的form id","数据表主键标识id",editOption,saveOption);
 */ var bg1 = "#fff";//默认奇数行背景色
 	var bg2 = "#F7F7F7";//默认偶数行背景色
 	var bg3 = "#D9EBF5";//鼠标移上去时行背景色
 	var bg4 = "#D9EBF5";//双击鼠标行背景色
	var TabId; //操作表格的id
  	var PageNo = 1;//默认当前页数为1
  	var PageSize = 10;//默认每页大小为10
  	var totalCount = 0;//默认所有条数为0
  	var SubmitFlag = 0;//是否表单提交后跳至状态 
  	var TotalPage;
  	var Url;//请求的url
  	var IsEditor =false;
  	var EditorForm="";//需要编辑的form
  	var SymbolId="";//主键标识id 与xml内名字对应  	var EditOption;
  	var SaveOption;
  	var ResetForm="";//需要编辑的form
  	var SubmitIsDisabled = false;
  	var XmlData="";
  	var IsFormat = true;
  	var FormatToken = "-";
  	var ORDER_TOKEN = 0;
  	
  	
  	
  	function setResetForm(resetForm){
	  	ResetForm = resetForm;
  	}
  	function setEditorForm(editorForm,symbolId,editOption,saveOption){
  		IsEditor = true;
  		EditorForm = editorForm;
  		SymbolId = symbolId;
  		EditOption = editOption;
  		SaveOption = saveOption;
  		SubmitIsDisabled = $('#'+EditorForm+' input[type=submit]').attr("disabled");
  	}
  	function openEditorCell(symbolId,editOption){
  		SymbolId = symbolId;
  		EditOption = editOption;
  	}
  	function setPageSize(pageSize){
  		PageSize = pageSize;
  	}
	function initTable(url,tabId,pageNo,pageSize)
	{
		$('#loading').show();
		var CK = $('#ck');
		if(CK){
			CK.html("<input type='checkbox' id='ck_all' onclick='ck_checked(this)' />");
		}
		clickTable(url,tabId,pageNo,pageSize);
		
		//排序
		if(ORDER_TOKEN == 0){
		$('#'+TabId+' thead th').each(function(i){
				if($(this).attr("sortable")== "true"){
					$(this).append("<div class='ico_px'></div>");
					$(this).bind('click',function(){
						
						if($("#orderBy").val() == $(this).attr("sortname")){//没有更换排序列
							if($("#orderSuffix").val() == "desc"){
								$("#orderSuffix").val("asc");
								$($(this).children()[1]).attr("class","ico_asc");
								
							}else{
								$("#orderSuffix").val("desc");
								$($(this).children()[1]).attr("class","ico_desc");
							}
						}else{
							reOrder();
							$("#orderBy").val($(this).attr("sortname"));
							$("#orderSuffix").val("desc");
							$($(this).children()[1]).attr("class","ico_desc");
						}
						$('#pagingForm').submit();
					})
				}
			})
			ORDER_TOKEN = 1 ;
		}
	}
	function reOrder(){
		$('#'+TabId+' thead th').each(function(i){
				if($(this).attr("sortable")== "true"){
					$($(this).children()[1]).attr("class","ico_px");
				}
			})
	}
	function clickTable(url,tabId,pageNo,pageSize){
		$("#ck_all").attr("checked","")
		$('#pageNoWait').hide();//分页等待图片处理
		$('#pageWaiting').show();
		if(pageNo!=null){
			PageNo = pageNo;
		}
		if(pageSize!=null){
			PageSize = pageSize;
		}
		
		TabId = tabId;
		Url = url;
		if(url.indexOf('?')>0){
			url +="&";
		}else{
			url +="?";
		}
		url +="pageNo="+PageNo+"&pageSize="+PageSize+"&"+Math.random();
		//form表单接收回调函数
		var options = {
	    url: url,
	    success: setTable
	    };
		
		$('#pagingForm').ajaxForm(options);//指明ajaxform
		$('#pagingForm').submit();
	}
	function initTableToEndPage(){//移至最后一页		SubmitFlag = 1;
		doRest();
		initTable(Url,TabId);
		
	}
	function initTableToFirstPage(){//移至第一页		SubmitFlag = -1;
		doRest();
		initTable(Url,TabId);
	}
	function refreshTable(){//刷新表格，页数不动		SubmitFlag = 0;
		doRest();
		initTable(Url,TabId);
	}
	
	//回调函数(返回一个XML数据文件)
	function setTable(data){
		XmlData = data;
		//加载tbody内容
		$('#loading').hide();
		var tBodyLength = $('#'+TabId+' tbody tr').length;
		var tBody;
		
		if(tBodyLength == 0){
			if(!document.all){//判断不是ie 创建tbody
				$('#'+TabId).append("<tbody></tbody>");
			}	
		}
		tBody = $('#'+TabId+' tbody');
		tBody.empty();//清空数据
		var html="";
			
		$(data).find('entity').each(function(j){
			var $entity = $(this);
			var thName;
			html += "<tr char='"+$entity.find(SymbolId).text()+"'>";
			$('#'+TabId+' thead th').each(function(i){
			//可见性
				if($(this).attr("visiable")== "false"){
					$(this).hide();
					return true;
				}else{
					$(this).show();
				}
			
			
				
				thName =  $(this).attr('id');
			
			//处理字体颜色
			if($entity.find('flag').text()!= ""){
				if($entity.find('flag').text() == 0){//0代表锁定状态
					html += '<td class="lock">';
				}
				else if($entity.find('flag').text() == 1){//字为红色
					html += '<td class="text_red">';
				}
				else if($entity.find('flag').text() == 2){//字为绿色
					html += '<td class="text_green">';
				}
				else{
				
					html += '<td>';
				}
			}else{
				if($(this).attr("edit")){
					html += '<td dataType="'+$(this).attr("dataType")+'" ondblclick="editCell(this,\''+$(this).attr("edit")+'\')">';
				}else{
					html += '<td>';
				}
			}
				if(IsFormat){//修正内容显示
					if($entity.find(thName).text()==""||$entity.find(thName).text()=="-99"||$entity.find(thName).text()=="-99.00"||$entity.find(thName).text()=="1970-01-01"){
						html +=  FormatToken;
					}else{
						html += $entity.find(thName).text();
					}
				}else{
					html += $entity.find(thName).text();
				}
				html += "</td>";
			})
			html += "</tr>";
			
		}); 
		if(html!=""){ 
			tBody.append($(html));
		}else{
			
			tBody.append("<tr id='nochange'><td colspan='"+$('#'+TabId+' thead th').length+"'>暂无记录</td></tr>");
		}
		
		html="";
		if(html!=""){
			tBody.append($(html));
		}
		//下面加载tfoot分页		
		var footHtml;
		var totalPage = parseInt($(data).find('totalPage').text(),10);
		TotalPage =totalPage;
		var currPageNo = parseInt($(data).find('pageNo').text(),10);
		var totalSize = parseInt($(data).find('totalSize').text(),10);
		
		totalCount = parseInt($(data).find('totalCount').text(),10);
		var prePage = PageNo -1;
		var nextPage= PageNo +1;
		
		if(prePage<1){
			prePage =1;
			currPageNo =1;
		}
		if(nextPage>totalPage){
			nextPage = totalPage;
			
		}
		if(PageNo>TotalPage){initTableToEndPage()}
		//如果是提交表单，移至最后一页
		if(SubmitFlag == 1){
			initTable(Url,TabId,totalPage);
			SubmitFlag = 0;
		}else if (SubmitFlag == -1){
			initTable(Url,TabId,1);
			SubmitFlag = 0;
		}
		
		$('#head_page').remove();
		
		footHtml += '<div class="page_all" id="head_page">';
		footHtml += '<div class="show_th"><div class="show_th_text" onclick="choose_th()"></div><div class="show_div" id="_chooseDiv" onmouseover="show_th()" onmouseout="close_th()"></div></div>';
		footHtml += '<div class="page_size"><select id="page_size" onchange="change_pageSize(this)"><option value="10">10</option><option value="20">20</option><option value="30">30</option><option value="50">50</option><option value="100">100</option></select></div>';
		footHtml += '<div class="page_img">'
		if(totalCount==0){
			footHtml += '<a href="javascript:void(0)" ><span class="page_first"></span></a>';
			footHtml += '<a href="javascript:void(0)"><span class="page_prev"></span></a>';
			footHtml += '<div>Page<input onkeyup="javascript:if(event.keyCode==13){jumpPage()}" type="text" class="page_text" id="page_jump" value="'+currPageNo+'" />of&nbsp;&nbsp;'+totalPage+'</div>';
			footHtml += '<a href="javascript:void(0)" ><span class="page_next"></span></a>';
			footHtml += '<a href="javascript:void(0)" ><span class="page_last"></span></a>';
		}
		else if(totalPage==1){
			footHtml += '<a href="javascript:void(0)" ><span class="page_first"></span></a>';
			footHtml += '<a href="javascript:void(0)"><span class="page_prev"></span></a>';
			footHtml += '<div>Page<input onkeyup="javascript:if(event.keyCode==13){jumpPage()}" type="text" class="page_text" id="page_jump" value="'+currPageNo+'" />of&nbsp;&nbsp;'+totalPage+'</div>';
			footHtml += '<a href="javascript:void(0)" ><span class="page_next"></span></a>';
			footHtml += '<a href="javascript:void(0)" ><span class="page_last"></span></a>';
		}else if(currPageNo==1){
			footHtml += '<a href="javascript:void(0)" ><span class="page_first"></span></a>';
			footHtml += '<a href="javascript:void(0)" ><span class="page_prev"></span></a>';
			footHtml += '<div>Page<input onkeyup="javascript:if(event.keyCode==13){jumpPage()}" type="text" class="page_text" id="page_jump" value="'+currPageNo+'" />of&nbsp;&nbsp;'+totalPage+'</div>';
			footHtml += '<a href="javascript:void(0)" onclick="clickTable(Url,TabId,'+nextPage+')"><span class="page_next"></span></a>';
			footHtml += '<a href="javascript:void(0)" onclick="clickTable(Url,TabId,'+totalPage+')"><span class="page_last"></span></a>';
		} else if(currPageNo==totalPage){
			footHtml += '<a href="javascript:void(0)" onclick="clickTable(Url,TabId,1)"><span class="page_first"></span></a>';
			footHtml += '<a href="javascript:void(0)" onclick="clickTable(Url,TabId,'+prePage+')"><span class="page_prev"></span></a>';
			footHtml += '<div>Page<input onkeyup="javascript:if(event.keyCode==13){jumpPage()}" type="text" class="page_text" id="page_jump" value="'+currPageNo+'" />of&nbsp;&nbsp;'+totalPage+'</div>';
			footHtml += '<a href="javascript:void(0)"><span class="page_next"></span></a>';
			footHtml += '<a href="javascript:void(0)" ><span class="page_last"></span></a>';
		} else{
			footHtml += '<a href="javascript:void(0)" onclick="clickTable(Url,TabId,1)"><span class="page_first"></span></a>';
			footHtml += '<a href="javascript:void(0)" onclick="clickTable(Url,TabId,'+prePage+')"><span class="page_prev"></span></a>';
			footHtml += '<div>Page<input onkeyup="javascript:if(event.keyCode==13){jumpPage()}" type="text" class="page_text" id="page_jump" value="'+currPageNo+'" />of&nbsp;&nbsp;'+totalPage+'</div>';			
			footHtml += '<a href="javascript:void(0)" onclick="clickTable(Url,TabId,'+nextPage+')"><span class="page_next"></span></a>';
			footHtml += '<a href="javascript:void(0)" onclick="clickTable(Url,TabId,'+totalPage+')"><span class="page_last"></span></a>';
		}
		footHtml += '</div>';
		//footHtml += '<div><input onclick="jumpPage()" type="button"  style="height:18px;line-height:18px;margin:4px 5px 0px;" class="btn" value="跳转" /></div>';
		footHtml += '<div id="pageWaiting" class="waiting"></div>';
		footHtml += '<div id="pageNoWait" class="nowait"></div>';
		//footHtml += '<div>&nbsp;&nbsp;&nbsp;页号：'+currPageNo+'/'+totalPage+'&nbsp;]</div>';
		footHtml += '<div class="page_totle">总记录数：'+totalCount+'</div>';
		footHtml += '</div>';
		
		$('#'+TabId).before($(footHtml));
		var page_num = parseInt($("#page_jump")[0].value,10);
		
		if(IsEditor){
			setEditor(data);
		}
		changColor(TabId,bg1,bg2,bg3);
		clickChange(TabId,bg1,bg2,bg3,bg4);
		$("select[id=page_size] option[value="+PageSize+"]").attr("selected",true)
	}
	function change_pageSize(obj){
		PageSize = obj.value;
		initTableToFirstPage();
	}
	
	function jumpPage(){
		var page_num =  parseInt($("#page_jump")[0].value,10);
		if(page_num>TotalPage){
			page_num = TotalPage;
		}
		clickTable(Url,TabId,page_num);
		
	}
	function setEditor(data){
		$('#'+TabId+' tbody tr').bind('dblclick',function(){
			var symbolId = $(this).attr('char');
			$(data).find(SymbolId).each(function(){
				if(symbolId == $(this).text()){
					var entity  = $(this).parent();//得到该位置的xml entity
					var inputName;
					$('#'+EditorForm+' input[id='+SymbolId+']')[0].value = symbolId;
					$('#'+EditorForm+' input[type=submit]')[0].value = "修改";
					$('#'+EditorForm+' input[type=reset]').bind('click',doRest);
					$('#'+EditorForm).ajaxForm(EditOption);
					$('#'+EditorForm+' input[type=text]').each(function(){//遍历表单 文本类型
						inputName =  $(this).attr('name');
						$(this).attr("value",(entity.find(inputName).text()))
					})
					$('#'+EditorForm+' textarea').each(function(){//遍历表单 textarea类型
						inputName =  $(this).attr('name');
						$(this).text(entity.find(inputName).text())
					})
					$('#'+EditorForm+' input[type=radio]').each(function(){//遍历表单 radio类型
						inputName =  $(this).attr('name');
						if($(this).attr("value")==entity.find(inputName).text()){
							$(this).attr("checked","checked");
							if(!$(this).attr("disabled")){
								$(this).click();
							}
						}
					})
					$('#'+EditorForm+' input[type=checkbox]').each(function(){//遍历表单 checkbox类型
						inputName =  $(this).attr('name');
						var options = entity.find(inputName).text().split(',')
						for(var i in options){
							if(options[i] == $(this).attr("value")){
								$(this).attr("checked","checked");
							}
						}
					})
					$('#'+EditorForm+' select').each(function(){//遍历表单 select类型
						inputName =  $(this).attr('name');
						$(this).attr("value",entity.find(inputName).text());
						if(!$(this).attr("disabled")){
							if($(this).attr('changeId')!=null){
								$(this).trigger("change", [entity.find($(this).attr('changeId')).text()]); 
							} else{
								$(this).change();
							}
							
						}
				//		setTimeout(function(){},3000)
						
					})
				}
			})
			$('#'+EditorForm+' input[type=submit]').attr("disabled",false);
		})
		
	}
function doRest(){
		if(IsEditor){
			$('#'+EditorForm+' input[type=submit]')[0].value = "保存";
			$('#'+EditorForm).ajaxForm(SaveOption);
			$('#'+EditorForm+' textarea').each(function(){//遍历表单 textarea类型
				$(this).text("");//清楚jquery textarea 无法reset bug
			})
			if(SubmitIsDisabled){
				$('#'+EditorForm+' input[type=submit]').attr("disabled",SubmitIsDisabled);
			}
		}
		$('#'+ResetForm).resetForm();
   	}
//双击变色
function clickChange(o,a,b,c,d){
	var t=document.getElementById(o).getElementsByTagName("tr");
	for(var i=0;i<t.length;i++){
		t[i].ondblclick = function (){
			changColor(o,a,b,c);
			if(this.y!="1"){
				this.y="1";
				this.style.backgroundColor=d;
			}else{
				this.y="0";
				changColor(o,a,b,c);
			}
			
		}
			
	}
}
//隔行换色
function changColor(o,a,b,c){
	var t=document.getElementById(o).getElementsByTagName("tr");
	for(var i=0;i<t.length;i++){
		if(t[i].id != 'nochange'){
			t[i].style.backgroundColor=(t[i].sectionRowIndex%2==0)?a:b;
			t[i].y = "0";
			t[i].onmouseover=function(){
				if(this.y!="1"){
					this.style.backgroundColor=c;
				}
			}
			t[i].onmouseout=function(){
				if(this.y!="1"){
					this.style.backgroundColor=(this.sectionRowIndex%2==0)?a:b;
				}
			}
		}
	}
}
function ck_checked(ipt){
	$("#"+TabId+" td input[type='checkbox']").trigger("click") 
}
function doAddCk(ipt){
	var objIpt = document.getElementById("objIds");
	var objNamesIpt = document.getElementById("objNames");
	if(ipt.checked){
		objIpt.value += ipt.value+",";
		if(objNamesIpt){
			objNamesIpt.value += ipt.lang+",";
		}
	}else{
		if(objIpt.value.indexOf(ipt.value+",")!=-1){
			objIpt.value = objIpt.value.replace(","+ipt.value+",",",");
			if(objNamesIpt){
				objNamesIpt.value = objNamesIpt.value.replace(","+ipt.lang+",",",");
			}
		}
	}
}
//选择显示列
function choose_th(){
	if($("#_chooseDiv")[0].style.display=="block"){
		$("#_chooseDiv").hide();
		return;
	}
	var _chooseHtml="<ul>";
	//遍历所有的th
	$('#'+TabId+' thead th').each(function(i){
		if($(this).text()){
			if($(this).attr("visiable")=="false"){
				_chooseHtml+="<li><a href='javascript:void(0)'><input type='checkbox' onclick='click_choose(this,\""+$(this).attr("id")+"\")' />"
			}else{
				_chooseHtml+="<li><a href='javascript:void(0)'><input type='checkbox' onclick='click_choose(this,\""+$(this).attr("id")+"\")' checked='checked' />"
			}
			_chooseHtml+=$(this).text()+"</a></li>";
		}
	})
	_chooseHtml+="</ul>";
	$("#_chooseDiv").html(_chooseHtml);
	$("#_chooseDiv").show();
}
function show_th(){
	$("#_chooseDiv").show();
}
function close_th(){
	if($("#_chooseDiv")[0].style.display=="block"){
		$("#_chooseDiv").hide();
		return;
	}
}
function click_choose(obj,id){
	if(obj.checked){
		$('#'+TabId+' thead th[id='+id+']').attr("visiable","true")
	}else{
		$('#'+TabId+' thead th[id='+id+']').attr("visiable","false")
	}
	setTable(XmlData)
	choose_th();
}
function editCell(tabTd,iptName){
	$(tabTd).attr("ondblclick","");
	var tabHtml = $(tabTd).html();
	if(tabHtml==FormatToken){
		tabHtml=""
	}
	var dataType=$(tabTd).attr("dataType");
	if(dataType=="undefined")dataType="";
	
	$(tabTd).html("<input id='_cell' title='按esc取消选择' class='input_foc' dataType='"+dataType+"' onblur='doCellEdit(this)' type='text' name='"+iptName+"' lang='"+$(tabTd).parent().attr("char")+"'  value='"+tabHtml+"' onkeyup='javascript:if(event.keyCode==13){doCellEdit(this)} else if(event.keyCode == 27){ refreshTable()}' />");
	
	$("#_cell").focus();
	$("#_cell").select();
}

function doCellEdit(ipt){
	if($(ipt).attr("dataType") == "number"){
		if(!isNumber(ipt.value)){
			alert("请输入有效的数字")
			return false;
		}
	}
	if($(ipt).attr("dataType") == "money"){
		if(!isDecimal(ipt.value)){
			alert("请输入有效的金额")
			return false;
		}
	}
	
	var frm = document.editCellFrm;
	frm.objId.value = ipt.lang;
	frm.objName.value = ipt.value;
	frm.objName.name=ipt.name;
	
	$(frm).ajaxSubmit(EditOption); 
}

//是否为空
function isEmpty(_str){
    var tmp_str = $.trim(_str);
    return tmp_str.length == 0;
}

//是否有效的数字;
function isNumber(_str){
	if(isEmpty(_str)) return false;
    var tmp_str = $.trim(_str);
    var pattern = /^(0|[1-9]\d*)$/;
    return pattern.test(tmp_str);
}

//是否有效的小数;
function isDecimal(_str){
	if(isEmpty(_str)) return false;
    var tmp_str = $.trim(_str);
    var pattern = /^\d+(\.\d+)?$/;
    return pattern.test(tmp_str);
}
	