/*
 * ajaxNotice 1.0.0 - New Wave Javascript
 * 默认ajax调用用户提示
 * Copyright (c) 2008  (rome360.com)

 * $Date: 2008-11-15 10:11:00 -0500  $
 */
function initAjaxNotice(){
	if(window.top.document.getElementById('ajBgDiv')){
		var load = document.body;
		$(load).ajaxStart(function(){//开始时加载
			if(document.body){
				window.top.document.getElementById('ajBgDiv').style.width = window.top.document.body.offsetWidth+"px";
				window.top.document.getElementById('ajBgDiv').style.height = (window.top.document.body.scrollTop+window.top.document.body.offsetHeight)+"px";
			}if(document.documentElement){
				window.top.document.getElementById('ajBgDiv').style.height = (window.top.document.documentElement.scrollTop+window.top.document.documentElement.offsetHeight)+"px";
			}
			//$(window.top.document.getElementById('ajBgDiv')).show();
	   		$(window.top.document.getElementById('loadingImg')).show();
	   	});
	   	$(load).ajaxSuccess(function(){//结束时加载	
	   		//$(window.top.document.getElementById('ajBgDiv')).hide()
	   		$(window.top.document.getElementById('loadingImg')).hide();
	   	});
	   	$(load).ajaxError(function(){//结束时加载	
	   		//$(window.top.document.getElementById('ajBgDiv')).hide()
	   		$(window.top.document.getElementById('loadingImg')).hide();
	   		alert("由于网络原因，请求操作失败，请稍后再试！");
	   	});
   	}
}
initAjaxNotice();
