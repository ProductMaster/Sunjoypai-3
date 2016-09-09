<script type="text/javascript">
	$(document).ready(function() {
		$("#validateForm").validate();
		$("button").filter(".historyBackClass").each(function(i) {
			$(this).bind("click", function() {
				// window.history.go(-1);
				history.back();
			});
		});
	});
</script>
<script type="text/javascript">
		$(function() {
			message("${msg}");
		});
</script>
<script type="text/javascript">
	var msg;
	function message(msg) {
		if (msg.length > 0) {
			msg = $.globalMessenger().post({
				message : msg,
				actions : {
					cancel : {
						label : '关闭',
						action : function() {
							return msg.cancel();
						}
					}
				}
			});
		}
	}
	
	function getNowDate(id){
		 var now = new Date();
	     var year = now.getFullYear();       
	     var month = now.getMonth() + 1;    
	     var day = now.getDate();           
	     var strDate = year + "-";
	     if(month < 10)
	    	 strDate += "0";
	     strDate += month + "-";
	     if(day < 10)
	    	 strDate += "0";
	     strDate += day + " ";
		$("#"+id).val(strDate);
	}
	var customerNames;
	function getCustomerName(id, targetId) {
		var from = $("#"+id);
		from.autocomplete("${pageContext.request.contextPath}/customer/getCustomer", {
			matchContains: true,
			dataType : 'json',//返回的数据类型为JSON类型
			matchSubset : false,
			delay : 130,
			parse : function(data) {//解释返回的数据，把其存在数组里
				var parsed = [];
				for ( var i = 0; i < data.length; i++) {
					parsed[parsed.length] = {
						data : data[i],
						value : data[i].customerId,
						result : data[i].customerName
					};
				}
				customerNames = parsed;
				return parsed;
			},
			formatItem : function(item) {//显示下拉列表的内容
				return item.customerName;
			}
		});
	}
	function checkCustomerName(id, targetId){
		var from = $("#" + id);
		var target = $("#" + targetId);
		if (customerNames == null)
			return true;
		for (var i = 0; i < customerNames.length; i++) {
			if (from.val() == customerNames[i].result) {
				target.val(customerNames[i].value);
				return true;
			}
		}
		message("客户名称不存在！");
		return false;
	}
	function getNowDate(id){
		 var now = new Date();
	     var year = now.getFullYear();       
	     var month = now.getMonth() + 1;    
	     var day = now.getDate();           
	     var strDate = year + "-";
	     if(month < 10)
	    	 strDate += "0";
	     strDate += month + "-";
	     if(day < 10)
	    	 strDate += "0";
	     strDate += day + " ";
		$("#"+id).val(strDate);
	}
	
	function ajaxHandler(url, method, data, dataType, callback) {
		$.ajax({
			type : method,
			data : data,
			url : url,
			dataType : dataType,
			success : callback
		});
	}

	function showDialog(msg) {
		if (msg.length < 1) 
			return;
		$("<div>", {
			id : "inline",
			html : msg,
			css : {
				textAlign : "center",
				width : 500,
				height : 360
			}
		}).appendTo("body");
		$("<a>", {
			id : "showdiv",
			href : "#inline"
		}).appendTo("body");
		$("#showdiv").fancybox();
		$("#showdiv").click();
	}
	function prompt(content, title, subId) {
		$.prompt(content, {
			title: title,
			buttons: { "确认": true, "取消": false },
			submit: function(e,v,m,f){
			if(v)
				$("#"+subId).click();
		}
		});
	}
	function createLoading() {
		var container = $(".container");
		$("<div>")
				.attr("id", "loading")
				.css({
					width : container.width(),
					height : container.height(),
					position : "absolute",
					textAlign : "center",
					zIndex : 100,
					lineHeight : $(window).height() / 10
				})
				.append(
						$("<img src='${pageContext.request.contextPath }/images/loading.gif' />"))
				.prependTo(container);
	}
	
	</script>
	<script type="text/javascript">	
	function ismoney(obj){
		var re = /^([1-9]{1}[0-9]{0,2}(\,[0-9]{3})*(\.[0-9]{0,2})?|[1-9]{1}\d*(\.[0-9]{0,2})?|0(\.[0-9]{0,2})?|(\.[0-9]{1,2})?)$/;
        var str = obj.value;

        if (str != "") {
            if(re.test(str) == false) {
                message("格式不正确,请输入有效的金额！");
                }else if("."==str.charAt(str.length - 1)||"."==str.charAt(0)){
            	message("格式不正确,请输入有效的金额！");
            	}
        }
	}
	
	function isqty(obj){
		var re = /^(?:[1-9]\d*|0)$/;
        var str = obj.value;

        if (str != "") {
            if(re.test(str) == false) {
                message("格式不正确,请输入有效的数量！");
                }
        }
	}
	function isnumber(str){
		var re = /^(?:[1-9]\d*|0)$/;
		str = str + "";
        if (str != "") {
            if(re.test(str) == false) {
                message("格式不正确,请输入有效的数量！");
                return false;
                }
            else return true;
        }
        else return false;
	}
	function isprice(str){
		str = str+"";
		var re = /^([1-9]{1}[0-9]{0,2}(\,[0-9]{3})*(\.[0-9]{0,2})?|[1-9]{1}\d*(\.[0-9]{0,2})?|0(\.[0-9]{0,2})?|(\.[0-9]{1,2})?)$/;

        if (str != "") {
            if(re.test(str) == false) {
                message("格式不正确,请输入有效的金额！");
                return false;
                }else if("."==str.charAt(str.length - 1)||"."==str.charAt(0)){
            	message("格式不正确,请输入有效的金额！");
            	return false;
            	}
                else return true;
        }else 
        	return false;

	}
	function isEmpty(v) {
		return !v || v.length == 0 || /^\s+$/.test(v);
	}	
	</script>	