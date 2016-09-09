/*
 * tablePaging 1.0.2 - New Wave Javascript
 * 表格分页AJAX控件
 * Copyright (c) 2008  (rome360.com)
 *
 * $Date: 2008-11-13 10:11:00 -0500  $
 * 调用时 至少传入url 、 tabId 
 * pageNo、pageSize不传入 ,则使用默认值


 * 在jsp页面任何位置加入如下form"<form  id="pagingForm" name="pagingForm" method="post"></form>",把查询条件封装进去,如没有条件则加空form
 * 如果需要编辑 初始化增加函数调用setEditorForm("需要编辑的form id","数据表主键标识id",editOption,saveOption);
 */ var bg1 = "#fff";//默认奇数行背景色
 	var bg2 = "#f5fafa";//默认偶数行背景色
 	var bg3 = "#f5f5f5";//鼠标移上去时行背景色
 	var bg4 = "#fffdd7";//双击鼠标行背景色
	var TabId; //操作表格的id
  	var PageNo = 1;//默认当前页数为1
  	var PageSize = 10;//默认每页大小为10
  	var totalCount = 0;//默认所有条数为0
  	var SubmitFlag = 0;//是否表单提交后跳至状态 
  	var Url;//请求的url
  	var IsEditor =false;
  	var EditorForm="";//需要编辑的form
  	var SymbolId="";//主键标识id 与xml内名字对应
  	var SubmitIsDisabled = false;


  	var EditOption;
  	var SaveOption;
  	var ResetForm="";//需要编辑的form
  	var StatId="";//显示统计数据的文本框id列表，形式为：id|id|id
  	var RunNo="";
  	//用于统计分析图

  	var ImgId="../../images/wait_img.gif";//图片域的id
	function setStatId(statId,runNo){
		StatId = statId;
		RunNo = runNo;
	}
	function setSrc(imgId){
		if(imgId!=""){
	    	$('#'+imgId).attr('src',"../../images/wait_img.gif");
	    }
		ImgId =imgId
	}
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
  	function setItemId(symbolId){
  		SymbolId = symbolId;
  	}
  	function setPageSize(pageSize){
  		PageSize = pageSize;
  	}
	function initTable(url,tabId,pageNo,pageSize)
	{
		
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
	
	function clickTable(url,tabId,pageNo,pageSize){
		
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
	//回调函数(返回一个XML数组文件)
	function setTable(data){
		//加载tbody内容
		$('#loading').hide();
		var tBodyLength = $('#'+TabId+' tbody').length;
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
				thName =  $(this).attr('id');
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
				html += '<td>';
			}
				html += $entity.find(thName).text();
				html += "</td>";
			})
			html += "</tr>";
			
		})
		if(html!=""){
			tBody.append($(html));
		}else{
			tBody.append("<tr id='nochange'><td colspan='"+$('#'+TabId+' thead th').length+"'>暂无记录</td></tr>");
		}
		html="";
		var colspan = $('#'+TabId+' thead th').length;
		var trLength = $('#'+TabId+' tbody tr').length;//加载后tr的行数

		if(trLength<PageSize){
			for(var i =0;i<PageSize-trLength;i++){
				html +="<tr id='nochange'><td colspan='"+colspan+"'>&nbsp;</td></tr>";
			}
		}
		if(html!=""){
			tBody.append($(html));
		}
		//下面加载tfoot分页
		var tFootLength = $('#'+TabId+' tfoot tr').length;
		var tFoot;
		if(tFootLength == 0){
				$('#'+TabId).append("<tFoot></tFoot>");
		}
		var footHtml;
		var totalPage = $(data).find('totalPage').text();
		var currPageNo = $(data).find('pageNo').text();
		var totalSize = $(data).find('totalSize').text();
		totalCount = $(data).find('totalCount').text();
		var prePage = PageNo -1;
		var nextPage= PageNo +1;
		var colspan = $('#'+TabId+' thead th').length;
		if(prePage<1){
			prePage =1;
		}
		if(nextPage>totalPage){
			nextPage = totalPage;
		}
		//如果是提交表单，移至最后一页
		if(SubmitFlag == 1){
			initTable(Url,TabId,totalPage);
			SubmitFlag = 0;
		}else if (SubmitFlag == -1){
			initTable(Url,TabId,1);
			SubmitFlag = 0;
		}
		tFoot = $('#'+TabId+' tfoot');
		tFoot.empty();
		footHtml += '<tr id="span_all">';
		footHtml += '<th colspan="'+colspan+'" style="border-top:solid #a4bfae 1px">';
		footHtml += '<div class="div_left"></div>';
		footHtml += '<div class="div_all">';
		footHtml += '<div class="div_center" style="float:right">';
		if(totalPage==1){
			footHtml += '<a href="javascript:void(0)" ><span id="img_first"></span></a>';
			footHtml += '<a href="javascript:void(0)" ><span id="img_prev"></span></a>';
			footHtml += '<div>当前第'+currPageNo+'页/共'+totalPage+'页</div>';
			footHtml += '<a href="javascript:void(0)"><span id="img_next"></span></a>';
			footHtml += '<a href="javascript:void(0)" ><span id="img_last"></span></a>';
		}else if(currPageNo==1){
			footHtml += '<a href="javascript:void(0)" ><span id="img_first"></span></a>';
			footHtml += '<a href="javascript:void(0)" ><span id="img_prev"></span></a>';
			footHtml += '<div>当前第'+currPageNo+'页/共'+totalPage+'页</div>';
			footHtml += '<a href="javascript:void(0)" onclick="clickTable(Url,TabId,'+nextPage+')"><span id="img_next"></span></a>';
			footHtml += '<a href="javascript:void(0)" onclick="clickTable(Url,TabId,'+totalPage+')"><span id="img_last"></span></a>';
		
		} else if(currPageNo==totalPage){
			footHtml += '<a href="javascript:void(0)" onclick="clickTable(Url,TabId,1)"><span id="img_first"></span></a>';
			footHtml += '<a href="javascript:void(0)" onclick="clickTable(Url,TabId,'+prePage+')"><span id="img_prev"></span></a>';
			footHtml += '<div>当前第'+currPageNo+'页/共'+totalPage+'页</div>';
			footHtml += '<a href="javascript:void(0)"><span id="img_next"></span></a>';
			footHtml += '<a href="javascript:void(0)" ><span id="img_last"></span></a>';
		
		}else if(currPageNo==0){
			footHtml += '<a href="javascript:void(0)" ><span id="img_first"></span></a>';
			footHtml += '<a href="javascript:void(0)" ><span id="img_prev"></span></a>';
			footHtml += '<div>当前第'+currPageNo+'页/共'+totalPage+'页</div>';
			footHtml += '<a href="javascript:void(0)"><span id="img_next"></span></a>';
			footHtml += '<a href="javascript:void(0)"><span id="img_last"></span></a>';
		} 
		else{
			footHtml += '<a href="javascript:void(0)" onclick="clickTable(Url,TabId,1)"><span id="img_first"></span></a>';
			footHtml += '<a href="javascript:void(0)" onclick="clickTable(Url,TabId,'+prePage+')"><span id="img_prev"></span></a>';
			footHtml += '<div>当前第'+currPageNo+'页/共'+totalPage+'页</div>';
			footHtml += '<a href="javascript:void(0)" onclick="clickTable(Url,TabId,'+nextPage+')"><span id="img_next"></span></a>';
			footHtml += '<a href="javascript:void(0)" onclick="clickTable(Url,TabId,'+totalPage+')"><span id="img_last"></span></a>';
		}
		footHtml += '<div id="pageNoWait" class="nowait"></div>';
		footHtml += '<div id="pageWaiting" class="waiting"></div>';
		footHtml += '<div>检索到'+totalCount+'记录</div>';
		footHtml += '</div>';
		footHtml += '</div>';
		footHtml += '<div class="div_right"></div>';
		footHtml += '</th>';
		footHtml += '</tr>';
		var imgFirst = 'page-first2';
		var imgPrev= 'page-prev2';
		var imgNext= 'page-next2';
		var imgLast= 'page-last2';
		if(currPageNo==1){
			imgFirst = 'page-first1';
			imgPrev= 'page-prev1';
			
		}
		if(currPageNo == 0){
			imgFirst = 'page-first1';
			imgPrev= 'page-prev1';
		}
		if(currPageNo==totalPage){
			imgNext= 'page-next1';
			imgLast= 'page-last1';
		}
		tFoot.append($(footHtml));
		$('#img_first').attr('class',imgFirst);
		$('#img_prev').attr('class',imgPrev);
		$('#img_next').attr('class',imgNext);
		$('#img_last').attr('class',imgLast);
		//$("#tab1").tablesorter();//0为升序    1降。
		
		if(RunNo=="1"){
			var StatIds = new Array();
			if(StatId!=""){
		    	StatIds = StatId.split("|");
		    }
		    for(var i=0;i<StatIds.length;i++){
		    	$('#'+StatIds[i]).attr('value',$(data).find(StatIds[i]).text());
		    }
	    }
	    if(ImgId!="../../images/wait_img.gif"){
	    	if($(data).find("statImgSrc").text()==''){
	    		$('#'+ImgId).attr('src','../../images/statis_empty.jpg');
	    	}else{
	    		$('#'+ImgId).attr('src',$(data).find("statImgSrc").text());
	    	}
	    	
	    	ImgId="";
	    }
		if(IsEditor){
			setEditor(data);
		}
		changColor(TabId,bg1,bg2,bg3);
		ejiaA1(TabId,bg1,bg2,bg3,bg4);
		//初始化明细列表

		var tBody = $('#'+ItemTabId+' tbody');
		tBody.empty();
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
			//alert(SubmitIsDisabled)
		}
		$('#'+ResetForm).resetForm();
   	}
	function ejiaA1(o,a,b,c,d){
	var t=document.getElementById(o).getElementsByTagName("tr");
	for(var i=0;i<t.length;i++){
		t[i].ondblclick = function (){
			changColor(o,a,b,c);
			if(this.y!="1"){
				this.y="1";//本来打算直接用背景色判断，FF获取到的背景是RGB值，不好判断
				this.style.backgroundColor=d;
			}else{
				this.y="0";
				changColor(o,a,b,c);
			}
			
		}
			
	}
}

function changColor(o,a,b,c){
	var t=document.getElementById(o).getElementsByTagName("tr");
	for(var i=0;i<t.length;i++){
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
	
	//分录列表
	
	function refreshSubTable(){
		doRest();
		initSubTable(Url,TabId);
	}
	function initSubTable(url,tabId){
		TabId = tabId;
		Url=url;
		var options = {
		    url: url,
		    success: setSubTable
	    };
		
		$('#pagingForm').ajaxForm(options);//指明ajaxform
		$('#pagingForm').submit();
	}
	
	function setSubTable(data){
		//加载tbody内容
		var tBodyLength = $('#'+TabId+' tbody').length;
		var tBody;
		
		if(tBodyLength == 0){
			if(!document.all){//判断不是ie 创建tbody
				$('#'+TabId).append("<tbody></tbody>");
			}	
		}
		tBody = $('#'+TabId+' tbody');
		tBody.empty();//清空数据
		var html= "";
		$(data).find('entity').each(function(j){
			var $entity = $(this);
			var thName;
			html += "<tr char='"+$entity.find(SymbolId).text()+"'>";
			$('#'+TabId+' thead th').each(function(i){
				thName =  $(this).attr('id');
				html += "<td>";
				html += $entity.find(thName).text();
				html += "</td>";
			})
			html += "</tr>";
			
		})
		if(html != ""){
			tBody.append($(html));
		}
		
		//添加统计text
		var StatIds = new Array("checkQty","totalQty","totalPrice");
		if(StatId!=""){
	    	StatIds = StatId.split("|");
	    }
	    for(var i=0;i<StatIds.length;i++){
	    	$('#'+StatIds[i]).attr('value',$(data).find(StatIds[i]).text());
	    }
		//添加总数
	//	$('#totalQty').attr('value',$(data).find("totalQty").text());
		//添加总金额

	//	$('#totalPrice').attr('value',$(data).find("totalPrice").text());
		if(IsEditor){
			setEditor(data);
		}
		changColor(TabId,bg1,bg2,bg3);
		ejiaA1(TabId,bg1,bg2,bg3,bg4);
	}
	
	
	
   /**
	*	显示记录子项目列表，不分页

	*	使用方法：调用initItem(dataId)。

	*	初始设置：需要显示明细表的页面，需要在页面onload函数中执行setItemUrl(url,tabId)，传入明细表的ajax请求URL和显示明细表的tableId
	*
	**/	
	var ItemUrl="";//明细表请求链接

	var ItemTabId="";//显示明细表table的ID
	var ItemFatId;//需要显示明细表的记录ID
	var ItemSymbolId="";//明细表ID
	var ItemEditorForm;
	var IsItemEditor=false;
	function setItemUrl(url,tabId){
		ItemUrl = url;
		ItemTabId = tabId;
	}
	function doItem(obj,id){
		document.location.href="#"+ItemTabId;
		initItem(id);
	}
	//初始化需要显示明细列表的所属Id
	function initItem(dataId,url,itemTabId){
		document.location.href="#"+ItemTabId;
		ItemFatId=dataId;
		getItem(url,itemTabId);
	}
	//ajax请求明细列表
	function getItem(url,itemTabId){
//		$.get(ItemUrl,{dataId:ItemFatId}, function(data){
//			  setItemTable(data)
//		});
		if(ItemUrl!=""){
			url=ItemUrl;
		}
		if(ItemTabId!=""){
			itemTabId=ItemTabId;
		}
		$.ajax({
		   type: "POST",
		   url: url,
		   data: SymbolId+"="+ItemFatId,
		   success: function(data){
		     setItemTable(data,itemTabId);
		   }
		}); 
		
	}
	function setItemTable(data,itemTabId){
		var tBodyLength = $('#'+itemTabId+' tbody').length;
		var tBody;
		if(tBodyLength == 0){
			if(!document.all){//判断不是ie 创建tbody
				$('#'+itemTabId).append("<tbody></tbody>");
			}	
		}
		tBody = $('#'+itemTabId+' tbody');
		tBody.empty();//清空数据
		var html="";
		$(data).find('entity').each(function(j){
			var $entity = $(this);
			var thName;
			html += "<tr char='"+$entity.find(ItemSymbolId).text()+"'>";
			$('#'+itemTabId+' thead th').each(function(i){
				thName =  $(this).attr('id');
				html += "<td>";
				html += $entity.find(thName).text();
				html += "</td>";
			})
			html += "</tr>";
			
		})
		if(html != ""){
			tBody.append($(html));
		}
		if(IsItemEditor){
			setItemEditor(data);
		}
		changColor(itemTabId,bg1,bg2,bg3);
		ejiaA1(itemTabId,bg1,bg2,bg3,bg4);
	}
	//绑定点击编辑功能的方法，仅适合本项目使用，暂不做通用组件
	//使用时需要传入ItemEditorForm、ItemSymbolId
	function setItemEditorForm(editorForm,symbolId){
  		IsItemEditor = true;
  		ItemEditorForm = editorForm;
  		ItemSymbolId = symbolId;
  	}
	function setItemEditor(data){
		$('#'+ItemTabId+' tbody tr').bind('dblclick',function(){
			var symbolId = $(this).attr('char');
			$(data).find(ItemSymbolId).each(function(){
				if(symbolId == $(this).text()){
					var entity  = $(this).parent();//得到该位置的xml entity
					var inputName;
					$('#'+ItemEditorForm+' input[id='+ItemSymbolId+']')[0].value = symbolId;
			//		$('#'+ItemEditorForm+' input[type=submit]')[0].value = "修改";
			//		$('#'+ItemEditorForm+' input[type=reset]').bind('click',doRest);
			//		$('#'+ItemEditorForm).ajaxForm(ItemEditOption);
					$('#'+ItemEditorForm+' input[type=text]').each(function(){//遍历表单 文本类型
						inputName =  $(this).attr('name');
						$(this).attr("value",(entity.find(inputName).text()))
					})
					$('#'+ItemEditorForm+' textarea').each(function(){//遍历表单 textarea类型
						inputName =  $(this).attr('name');
						$(this).text(entity.find(inputName).text())
					})
					$('#'+ItemEditorForm+' input[type=radio]').each(function(){//遍历表单 radio类型
						inputName =  $(this).attr('name');
						if($(this).attr("value")==entity.find(inputName).text()){
							$(this).attr("checked","checked");
							if(!$(this).attr("disabled")){
								$(this).click();
							}
						}
					})
					$('#'+ItemEditorForm+' input[type=checkbox]').each(function(){//遍历表单 checkbox类型
						inputName =  $(this).attr('name');
						var options = entity.find(inputName).text().split(',')
						for(var i in options){
							if(options[i] == $(this).attr("value")){
								$(this).attr("checked","checked");
							}
						}
					})
					$('#'+ItemEditorForm+' select').each(function(){//遍历表单 select类型
						inputName =  $(this).attr('name');
						$(this).attr("value",entity.find(inputName).text());
						if(!$(this).attr("disabled")){
							$(this).change();
						}
						setTimeout(function(){},3000)
						
					})
				}
			})
		})
	}