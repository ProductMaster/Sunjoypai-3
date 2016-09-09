/*
 * tablePaging 1.0.2 - New Wave Javascript
 * 表格分页AJAX控件
 * Copyright (c) 2009  (rome360.com)
 *
 * $Date: 2009-04-21 14:26:00 -0500  $
 * 调用时 至少传入url 、 pagingId 
 * pageNo、pageSize不传入 ,则使用默认值
 */
var PagingId_; //操作表格的id
var PageNo_ = 1;//默认当前页数为1
var PageSize_ = 10;//默认每页大小为10
var Url_;//请求的url

/**
 **初始化paging,传入分页分界点的id
 **如<div id='pagingId'>内容</div>
 **出来的效果:<div id='pagingId'>内容</div><div id='pagingId'>内容</div>
 **/
function initPaging(pagingId,url){
	Url_ = url;//请求的url
	PagingId_ = pagingId;
	showWait();//显示加载的小图像
	getData();
}
function getData(){
	TabId = tabId;
	if(url.indexOf('?')>0){
		url +="&";
	}else{
		url +="?";
	}
	url +="pageNo="+PageNo_+"&pageSize="+PageSize_+"&"+Math.random();
    $.get(url,setData);
}
function setData(data){
	var totalPage = $(data).find('totalPage').text();
	var currPageNo = $(data).find('pageNo').text();
	var totalSize = $(data).find('totalSize').text();
	var totalCount = $(data).find('totalCount').text();
	var prePage = PageNo -1;
	var nextPage= PageNo +1;	
	var html_=""
	$(data).find('entity').each(function(j){
		var $entity = $(this);
		$("#"+PagingId_).children("[id]").each(function(i){
			$(this).html($entity.find($(this).attr('id')).text());
		})
		html_ += $("#"+PagingId_).html();
	})
	$("#"+PagingId_).html(html_)
}
function showWait(){

}
function stopWait(){

}
function setPageSize_(pageSize){
	PageSize_ = pageSize;
}
