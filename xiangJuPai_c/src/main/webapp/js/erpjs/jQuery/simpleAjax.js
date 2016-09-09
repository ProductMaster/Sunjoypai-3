/**
需求描述：主要用于Ajax加载select下拉列表、input表单的填充。页面加载时加载，能够动态响应onchange事件，即对onchange事件是可控的
功能描述：调用即填充，如果传入changeUrl，则onchange处于激活状态，即可触发onchange事件，否则不能触发onchange事件，
使用方法：$('#selectId').selectAjax({url:"url",param:"param='param'&param=param"});,参数的意义请参照$.fn.selectAjax.defaults

**/
(function($) {
    $.fn.selectAjax = function(options) {
		// build main options before element iteration
		var opts = $.extend({}, $.fn.selectAjax.defaults, options);
		var $this = $(this); 
		if(opts.url!=""){
			$.ajax({
					   type: "POST",
					   url: opts.url,
					   data: opts.param,
					   success: function(data){
					     appendOption(data);
					   }
			}); 
		}
		function appendOption(data){
			$(data).find('entity').each(function(j){
				$this.append("<option value='"+$(this).find("optionValue").text()+"'>"+$(this).find("optionText").text()+"</option>");
			})
		}
		 // Bind the related event handlers
		if(opts.changeUrl!=""){      
			$(this).change(onchange);
		} 
	    function onchange(){
	    	$.ajax({
				   type: "POST",
				   url: opts.changeUrl,
				   data: $(this).attr('name')+"="+$(this).attr('value')+opts.changeParam,
				   success: function(data){
				   		appendChange(data);
				   }
		 	}); 
	    }
	    function appendChange(data){
	    	if(opts.changeSelectId!=""){
	    			$('#'+opts.changeSelectId).empty();


	    		$(data).find('entity').each(function(j){
					$('#'+opts.changeSelectId).append("<option value='"+$(this).find("optionValue").text()+"'>"+$(this).find("optionText").text()+"</option>");
				  	
				})
	    	}
	    	if(opts.changeTextId!=""){
	    		var changeTextIds = new Array();
	    		changeTextIds = opts.changeTextId.split("|");
	    		for(var i=0;i<changeTextIds.length;i++){
	    			$('#'+changeTextIds[i]).attr('value',$(data).find(changeTextIds[i]).text());
	    		}
	    	}
	    	
	    	
	    }

	};
	
	$.fn.selectAjax.defaults = {
	        url: '',//必填参数
	        param: '',//可选参数:传递的参数。形如："id=124&name='33'"
	        changeSelectId: '',//可选参数：需要相应onchange事件的select的Id
	        changeTextId: '',//可选参数：需要响应onchange时间的input的Id的可split字符串，形如：id1|id2|id3
	        changeRadioName: '',//可选参数：需要响应onchange的事件的radio的name
	        changeUrl: '',//可选参数：onchange事件的请求链接
	        changeParam: ''//可选参数：onchange事件的传递参数,形如："&id=124&name='33'"
	        
    };
	
})(jQuery);


	function editSubSuccess(data){
		if($(data).find('FLAG').text()==1){
			alert("编辑分录数据成功！");		
			refreshSubTable();
		}
	}
	function delSubSuccess(data){
		if($(data).find('FLAG').text()>0){
			alert("删除分录数据成功！");
			refreshSubTable();
		}else{
			alert("至少有一条分类信息！");
			refreshSubTable();
		}
	}
	function addSubSuccess(data){
		if($(data).find('FLAG').text()==1){
			alert("新增一条分录数据！");
			refreshSubTable();
		}		
		if($(data).find('FLAG').text()==2){
			alert("请勿重复添加分录信息！");
		}
	}
	function editTheFormSuccess(data){
		if($(data).find('FLAG').text()==1){
			alert("修改记录成功！");
			refreshSubTable();
			unBlock();
		}		
	}
/*	
	function editSuccess(data){
		if($(data).find('FLAG').text()==1){
			alert("编辑数据成功！");		
			refreshTable();
		}
	}
	function delSuccess(data){
		if($(data).find('FLAG').text()==1){
			alert("删除数据成功！");
			refreshTable();
		}
	}
	function addSuccess(data){
		if($(data).find('FLAG').text()==1){
			alert("新增一条数据！");
			refreshTable();
		}		
	}
	*/