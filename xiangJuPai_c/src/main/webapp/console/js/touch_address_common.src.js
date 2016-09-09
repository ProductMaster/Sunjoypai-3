//order/address_tuanembed

	var teamPrice,num,address,teamType,sid;
	var isUseJingCoupons,isUseDongCoupons,isUseGiftCards;

	function initVar(outteamPrice,outNum,outAddress,outTeamType,outSid){
		teamPrice=outteamPrice;
		num=outNum;
		address=outAddress;
		teamType=outTeamType;
		sid=outSid;
	}

	function initVarForSecurityPay(isUseJingCouponsP,isUseDongCouponsP,isUseGiftCardsP){
		isUseJingCoupons=isUseJingCouponsP;
		isUseDongCoupons=isUseDongCouponsP;
		isUseGiftCards=isUseGiftCardsP;
	}

    var showSecurityPayUrl = "";
    function showSecurityPayBlock(doFlag, isChecked){
        if(doFlag){
           if(isChecked){
               if(showSecurityPayUrl.indexOf("usedBalance=false")!=-1){
                  showSecurityPayUrl = showSecurityPayUrl.replace("usedBalance=false", "usedBalance=true");
               }
           }else{
               if(showSecurityPayUrl.indexOf("usedBalance=true")!=-1){
                  showSecurityPayUrl = showSecurityPayUrl.replace("usedBalance=true", "usedBalance=false");
               }
           }
        }
        jQuery.get(showSecurityPayUrl,{},function(data){
            if(data!=null){
               var obj = eval('(' + data + ')');
               if(obj.showSecurityPayBlock){
                  if(data.indexOf("securityPayBlocks")!=-1){
                     for(var i=0;i<obj.securityPayBlocks.length;i++){
                    	 if($("#showSecurityPayBlockId").children().is("ul") && $("#showSecurityPayBlockId").children().attr("class")=="html5"){ //html5and androidpad
                    		 
                    		 
                    		 //$("#showSecurityPayBlockId").children().empty();
                             //$("#showSecurityPayBlockId").children().append("<li class='last'><label>"+obj.securityPayBlocks[i].title+"</label><p><input type='password' name='order."+obj.securityPayBlocks[i].submitKey+"'  class='common-input paypwd'></p></li>");
                             
                             $("#showSecurityPayBlockId").children().empty();
                             $("#showSecurityPayBlockId").children().append("<li class='last'><label>"+obj.securityPayBlocks[i].title+"</label>&nbsp;&nbsp;<div  style='font-size:0.9em;color:#cc0000;text-adivgn:center;margin-bottom:0.6em;margin-left:0.6em'><img src='/images/html5/help.png'>&nbsp\u4F7F\u7528\u8D26\u53F7\u4F59\u989D\u6216\u4F18\u60E0\u5238\u65F6\uFF0C\u8F93\u5165\u652F\u4ED8\u5BC6\u7801\u53EF\u4EE5\u4FDD\u969C\u60A8\u7684\u8D44\u4EA7\u5B89\u5168;</div><div><input type='password' name='order."+obj.securityPayBlocks[i].submitKey+"'  class='common-input paypwd'></div></li>");
                             
                             //\u6DFB\u52A0\u652F\u4ED8\u5BC6\u7801\u5F00\u542F\u6216\u8005\u627E\u56DE\u7684\u8FDE\u63A5;
                             addPayPasswordLink();
                             
                             
                    	 }else if($("#showSecurityPayBlockId").children().is("ul") && $("#showSecurityPayBlockId").children().attr("class")=="two"){ //tuanembed
                            $("#showSecurityPayBlockId").children().empty();
                            $("#showSecurityPayBlockId").children().append("<li>"+obj.securityPayBlocks[i].title+"<input type='password' name='order."+obj.securityPayBlocks[i].submitKey+"' class='common-input paypwd'></li>");
                         }else if($("#showSecurityPayBlockId").children().is("ul")){ //TV
                            $("#showSecurityPayBlockId").children().empty();
                            $("#showSecurityPayBlockId").children().append("<li><b>"+obj.securityPayBlocks[i].title+"</b></li>");
                            $("#showSecurityPayBlockId").children().append("<li><input type='password' name='order."+obj.securityPayBlocks[i].submitKey+"' class='common-input paypwd'></li>");
                         }else{ //\u5176\u5B83;
                            $("#showSecurityPayBlockId").children().empty();
                            $("#showSecurityPayBlockId").children().append("<li class='last'><label>"+obj.securityPayBlocks[i].title+"</label><p><input type='password' name='order."+obj.securityPayBlocks[i].submitKey+"'  class='common-input'></p></li>");
                         }
                     }
                  }
                  $("#showSecurityPayBlockId").show();
               }else{
                  $("#showSecurityPayBlockId").hide();
                  $('#findPayPassword').hide();
                  $('#openPayPassword').hide();
               }
            }
        });
    }

    function processYunFeeResultList(data){
        var strVal = "";
        jQuery.each(data,function(i,item){
            if(item.label.indexOf("\u8FD0\u8D39")!=-1 || item.label.indexOf("\u4F18\u60E0\u5238")!=-1 || item.label.indexOf("\u793C\u54C1\u5361")!=-1 || item.label.indexOf("\u8FD4\u73B0")!=-1 || item.label.indexOf("\u4F59\u989D")!=-1){
               strVal += item.operator + " ";
            }
            strVal += item.label + "\uFF1A";
            if(item.label.indexOf("\u5E94\u4ED8\u603B\u989D")!=-1){
               strVal += "<span style='color:red;'>\uFFE5"+item.value+" \u5143;</span><br/>"
            }else{
               strVal += item.value +" \u5143;<br/>"
            }
        });
        return strVal;
    }

	var citytemp;
	var areatemp;
	var towntemp;
	function provinceData(idprovince){
  		if(typeof(idprovince)=='undefined'){
			idprovince=1;
		}
  	
		jQuery.get("/order/area.action?sid="+sid,
		{idProvince:0},
			function(data){ 
				var data= eval('(' + data + ')'); 
				if(data!=null){
						$("#address_province").empty();
						for(var i=0;i<data.length;i++){
							if(idprovince==data[i].id){
								$("#province_text").html( data[i].name );
							}
						$("#address_province").append("<option "+(idprovince==data[i].id?"selected":"")+" id='option_add_"+data[i].id+"' value="+data[i].id+">"+data[i].name+"</option>");
						}
					}  
						//$("#address_province").val(idprovince);
						$("#address_province").change();
			});
	}
	
	var citychange = function cityData(){
		var idprovince=$("#address_province").val();
		
		$("#province_text").html( $("#address_province").find("option:selected").text());
		
//		var idcity=$("#address_city").val();
		var idcity=citytemp;
		jQuery.get("/order/area.action?sid="+sid,
		{idProvince:idprovince},
			function(data){ 
				var data= eval('(' + data + ')');  
				if(data!=null){
						$("#address_city").empty();
						for(var i=0;i<data.length;i++){
						$("#address_city").append("<option "+(idcity==data[i].id?"selected":"")+" id='option_add_"+data[i].id+"' value="+data[i].id+">"+data[i].name+"</option>");
				        }
					}  
						//$("#address_city").val(idcity);
						$("#address_city").change();
			});
	}
	
	var areachange = function areaData(){
		var idcity=$("#address_city").val();
		$("#city_text").html( $("#address_city").find("option:selected").text());
		var idarea=areatemp;
		if(idcity==null){
			$("#address_area").empty();
			$("#address_area").change();
			return;
		}
		jQuery.get("/order/area.action?sid="+sid,
		{idCity:idcity},
			function(data){ 
				var data= eval('(' + data + ')');  
				if(data!=null){
						$("#address_area").empty();
						for(var i=0;i<data.length;i++){
						$("#address_area").append("<option "+(idarea==data[i].id?"selected":"")+" id='option_add_"+data[i].id+"' value="+data[i].id+">"+data[i].name+"</option>");
						}
					}  	
						//$("#address_area").val(idarea);
						$("#address_area").change();
			});
	}
	//
	var townchange = function townData(){
		var idarea=$("#address_area").val();
		$("#area_text").html( $("#address_area").find("option:selected").text());
		var idtown=towntemp;
		if(idarea==null){
			$("#address_town").empty();
			$("#address_town").change();
			return;
		}
		jQuery.get("/order/area.action?sid="+sid,
		{idArea:idarea},
			function(data){ 
				var data= eval('(' + data + ')');  
				  if(!!data && data.length > 0){
					    $("#townaddress").show();
						$("#address_town").empty();
						for(var i=0;i<data.length;i++){
						$("#address_town").append("<option "+(idtown==data[i].id?"selected":"")+" id='option_add_"+data[i].id+"' value="+data[i].id+">"+data[i].name+"</option>");
				       }
					}else{
						$("#address_town").empty();
						$("#townaddress").hide();					
					}				
						//$("#address_town").val(idtown);
						$("#address_town").change();
			});
	}
	
	
	function getData(id,name,where,mobile,email,zip,idprovince,idcity,idarea,idtown){
    	$("#address_name").val(name);	
    	$("#address_where").val(where);	
    	$("#address_mobile").val(mobile);	
    	$("#address_email").val(email);	
    	$("#address_zip").val(zip);	
		citytemp=idcity;
		areatemp=idarea;
		towntemp=idtown;
		provinceData(idprovince);
	}
	
	var labelchange=function setLabel(){
		
		
		//城镇名称
		
		$("#town_text").html( $("#address_town").find("option:selected").text());
		//街道信息（过滤掉省、市、地区）
		var provinceorigin=$("#address_province").find("option:selected").text();
		var cityorigin=$("#address_city").find("option:selected").text();
		var areaorigin=$("#address_area").find("option:selected").text();
		var townorigin=$("#address_town").find("option:selected").text();
		var address=$("#address_where").val().replace(provinceorigin,'');
		address=address.replace(cityorigin,'');
		address=address.replace(areaorigin,'');
		address=address.replace(townorigin,'');
		$("#address_where").val(address);
	}

	
//order/submit_tuanembed
	$("#submit_success_button").click(function(){
	});
	$("#submit_back_button").click(function(){
	});
	
	
//total////////////////////////////////////////////////////////////////////////////////////////////
	$(document).ready(function(){
        if($("input")){
           $("input").attr("autocomplete","off"); // \u6D88\u9664\u8868\u5355\u4E2D\u7684\u8F93\u5165\u6846\u7684\u7F13\u5B58;
        }

//tuan_order
		$("#tuan_order_totalPrice").text("\uFFE5"+teamPrice*num);
		$("#tuan_order_num").change(function(){
			if(validateTuanOrderNum()){
    			var totalPrice = teamPrice*parseInt($("#tuan_order_num").val());
    			totalPrice = totalPrice.toFixed(2);
    			$("#tuan_order_totalPrice").text("\uFFE5"+totalPrice);
			}
		});
		
		var validateTuanOrderNum = function(){
			$("#tuan_order_num_error").text("");
			var num = $("#tuan_order_num").val();
			if(num==null || num ==''){
				$("#tuan_order_num_error").text("\u6570\u91CF\u4E0D\u80FD\u4E3A\u7A7A");
				return false;
			}else if(!/^(\d)+$/.exec(num)){
				$("#tuan_order_num_error").text("\u975E\u6CD5\u6570\u91CF");
				return false;
			}else{
				return true
			}
		};
		
		var validateTuanOrderMobile=function(){
			$("#tuan_order_mobile_error").text("");
			var mobile = $("#tuan_order_mobile").val();
			if(mobile==null || mobile ==''){
				$("#tuan_order_mobile_error").text("\u624B\u673A\u53F7\u7801\u4E0D\u80FD\u4E3A\u7A7A");
				return false;
			}else if(!/^1(\d){10}$/.exec(mobile)){
				$("#tuan_order_mobile_error").text("\u8BF7\u8F93\u5165\u6B63\u786E\u7684\u624B\u673A\u53F7");
				return false;
			}else{
				return true
			}
		};

		var checkTuanOrderAddress=function(){
		if( teamType == "lottery" ||  teamType == "normal" )  return true;
    		if(address==""){
    			window.location.href="#address";
    			return false;
    		}
			return true;
		}
		
		$("#order_form").submit(function(){
    		if(checkTuanOrderAddress()&&validateTuanOrderMobile()&&validateTuanOrderNum()){   //\u5982\u679C\u5730\u5740\u68C0\u67E5\u548C\u6570\u91CF\u68C0\u67E5\u901A\u8FC7;
    			$("#tuan_order_submit").attr("disabled", "disabled");
				return true;
			}
			return false;
		});
	
		$("#tuan_order_mobile").change(validateTuanOrderMobile);
		$("#tuan_order_num").change(validateTuanOrderNum);
		
//order_order///////////////////////////////////////////////////////////////////////////////////////////
        var oldIsUseBalance = "undefined";
        if($("#isUseBalance").attr("checked")  == "checked"){
            oldIsUseBalance = "checked";
        }
		var calcYunfee = function(){
			if(validateNum()){
				var num = $("#order_tuan_num").val();
				var orderYunFeeOperate = $("#orderYunFeeOperate").val();
				var isUseBalance = $("#useBalance").attr("checked") == "checked";
                if(orderYunFeeOperate == "html5"){
                    if(oldIsUseBalance == "checked"){
                       isUseBalance = false;
                    }else{
                       isUseBalance = true;
                    }
                }
				jQuery.get("/order/calcYunfee.action",{wareId:$("#order_tuan_size").val(),sid:sid,quantity:num,useBalance:isUseBalance},function(data){
					if(data!=null){
    					$("#order_tuan_yunfeeList").html("");
                        if(orderYunFeeOperate == "html5"){
                        	var balanceobj = document.getElementById("isUseBalance");
                            if(oldIsUseBalance == "checked"){
                               //$("#isUseBalance").removeAttr("checked");
                            	balanceobj.checked=false;
                               oldIsUseBalance = "undefined";
                               showSecurityPayBlock(true, false);
                            }else{
                            	balanceobj.checked=true;
                               //$("#isUseBalance").attr("checked", "true");
                               oldIsUseBalance = "checked";
                               showSecurityPayBlock(true, true);
                            }
                            var strVal = processYunFeeResultList(data);
                            $("#order_tuan_yunfeeList").html(strVal);
                        }else{
                            if(isUseBalance){
                                showSecurityPayBlock(true, true);
                            }else{
                                showSecurityPayBlock(true, false);
                            }
                            jQuery.each(data,function(i,item){
                                $("#order_tuan_yunfeeList").append(function(){
                                    return "<li>"+item.label+" <b>"+(item.operator==null?"":item.operator)+" "+ item.value+" \u5143;</b> </li>";
                                });
                            });
                        }
					}
				},'json');
			}
	}

    //\u5224\u65AD\u662F\u5426\u663E\u793A\u652F\u4ED8\u5BC6\u7801\u6846;
    var showSecurityPayIsDo = false;
    showSecurityPayUrl = "/order/showSecurityPayBlock.action?sid="+sid;
    if((jQuery('#isUseBalance').length > 0 && $("#isUseBalance").attr("checked")=="checked") || (jQuery('#useBalance').length > 0 && $("#useBalance").attr("checked")=="checked")){
        showSecurityPayUrl += "&order.usedBalance=true";
        showSecurityPayIsDo = true;
    }else{
        showSecurityPayUrl += "&order.usedBalance=false";
    }
    if(isUseJingCoupons  == "true"){
        showSecurityPayUrl += "&order.usedJingCoupons=true";
        showSecurityPayIsDo = true;
    }else{
        showSecurityPayUrl += "&order.usedJingCoupons=false";
    }
    if(isUseDongCoupons  == "true"){
        showSecurityPayUrl += "&order.usedDongCoupons=true";
        showSecurityPayIsDo = true;
    }else{
        showSecurityPayUrl += "&order.usedDongCoupons=false";
    }
    if(isUseGiftCards  == "true"){
        showSecurityPayUrl += "&order.usedGiftCards=true";
        showSecurityPayIsDo = true;
    }else{
        showSecurityPayUrl += "&order.usedGiftCards=false";
    }
    if(showSecurityPayIsDo){
       showSecurityPayBlock(false, false);
    }
	
	var colorChange = function sizeData(){
		var color=$("#order_tuan_color").val();
		jQuery.get("/order/size.action?sid="+sid,
		{wareId:color},
			function(data){ 
				var data= eval('(' + data + ')');  
				if(data!=null){
						$("#order_tuan_size").empty();
						for(var i=0;i<data.length;i++)
						$("#order_tuan_size").append("<option id='option_add_"+data[i].id+"' value="+data[i].id+">"+data[i].name+"</option>");
					}  	
			});
	}
	
	$("#order_tuan_num").change(calcYunfee);
	$("#order_tuan_color").change(colorChange);
	$("#order_tuan_size").change(calcYunfee);
	$("#useBalance").click(calcYunfee);
		
		var validateNum = function(){
			$("#order_tuan_num_error").text("");
			if(!document.getElementById("order_tuan_num")) 
				return true;
			var num = $("#order_tuan_num").val();
			if(num==null || num ==''){
				$("#order_tuan_num_error").text("\u6570\u91CF\u4E0D\u80FD\u4E3A\u7A7A");
				return false;
			}else if(!/^(\d)+$/.exec(num)){
				$("#order_tuan_num_error").text("\u8BF7\u8F93\u5165\u6B63\u786E\u6570\u91CF");
				return false;
			}else{
				return true;
			}
		};
		
		var checkAddress=function(){
    		if(address==""){
    			$("#errorMessage").text("\u8BF7\u8F93\u5165\u6536\u8D27\u4EBA\u5730\u5740");
    			window.location.href="#address";
    			return false;
    		}
			return true;
		};
		//\u65E0\u6CE8\u518C\u4E0B\u5355\u7684\u624B\u673A\u53F7\u9A8C\u8BC1;
		var checkNoRegisterOrder=function(){
    		var loginflag=$("#loginflag").val();  		
    		if(loginflag=="true"){
    			return true;
    			
    		}
    		var registermobile=$("#mobile").val();
    		var registerpwd=$("#password").val();
    		if(loginflag=="false"){
    		  if(registermobile==""  || registerpwd==""){
    		   $("#nameNull").show();
    		   $("#nameNull").text("\u8BF7\u8F93\u5165\u7535\u8BDD\u53F7\u7801\u6216\u5BC6\u7801");
      		   return false;
      	      } 
    		}
			return true;
		};
		var checkSecurityPayPassword=function(){
            var securityPayPassword = $("input[name='order.securityPayPassword']").val();
            if(securityPayPassword.trim() == "" && $("#showSecurityPayBlockId").attr("style").indexOf("none") == -1){
               alert("\u652F\u4ED8\u5BC6\u7801\u4E0D\u80FD\u4E3A\u7A7A");
               $("input[name='order.securityPayPassword']").focus()
               return false;
            }
			return true;
		};

		var checkRemarkStringLength=function(){
            var remark = $("input[name='order.remark']").val();
            if(remark != null && remark.trim() != "" && remark.length > 15){
               alert("\u8BA2\u5355\u5907\u6CE8\u9650\u5236\u5728;15\u4E2A\u5B57\u4EE5\u5185");
               $("input[name='order.remark']").focus()
               return false;
            }
			return true;
		};
		var orderDate=new Array(); 
		$("#order_tuan_form").submit(function(){
			//\u91CD\u590D\u63D0\u4EA4\u5224\u65AD;
			orderDate.push(new Date()); 
			if (orderDate.length > 1 && (orderDate[orderDate.length - 1].getTime() - orderDate[orderDate.length - 2].getTime() < 5000)) {  //\u5C0F\u4E8E;5\u79D2\u5219\u8BA4\u4E3A\u91CD\u590D\u63D0\u4EA4;
			    return false; 
			} 
    		if(checkAddress()&&checkNoRegisterOrder()&&validateNum()&&checkSecurityPayPassword()&&checkRemarkStringLength()){   //\u5982\u679C\u5730\u5740\u68C0\u67E5\u548C\u6570\u91CF\u68C0\u67E5\u901A\u8FC7;
//    			$("#order_tuan_submit").attr("disabled", "disabled");
				return true;
			}
			return false;
		});
		
		$("#order_tuan_num").change(validateNum);
	
//order_address	///////////////////////////////////////////////////////////////////////////////////////////////////////////////
		var validateName=function(){
			var name = $("#address_name").val();
			if(name==null || name.trim() ==''){
				$("#name_error").text("\u6536\u8D27\u4EBA\u4E0D\u80FD\u4E3A\u7A7A");
				return false;
			}else{
			$("#name_error").text("");
				return true
			}
		};
	$("#address_name").blur(validateName);
		
	var validateWhere=function(){
			var where = $("#address_where").val();
			if(where==null || where.trim() ==''){
				$("#where_error").text("\u5730\u5740\u4E0D\u80FD\u4E3A\u7A7A");
				return false;
			}else{
			   $("#where_error").text("");	 
				return true;
			}
		};
	$("#address_where").blur(validateWhere);
	
	var validateAddressMobile=function(){
			var mobile = $("#address_mobile").val();
			if(mobile==null || mobile.trim() ==''){
				$("#mobile_error").text("\u624B\u673A\u53F7\u4E0D\u80FD\u4E3A\u7A7A");
				return false;
			}else if(!/^1(\d){10}$/.exec(mobile)){
				$("#mobile_error").text("\u8BF7\u8F93\u5165\u6B63\u786E\u7684\u624B\u673A\u53F7");
				return false;
			}else{
			$("#mobile_error").text("");
				return true
			}
		};
		
	$("#address_mobile").blur(validateAddressMobile);

	var validateSubmit=function(){	
		
        var resultVal = true;
//      var initSign=$("#address_label").html();		
//		if(initSign==null||initSign==''){
//			$("#errorMessage1").html("\u5730\u5740\u52A0\u8F7D\u4E2D...");
//			resultVal = false;
//		}
        if(!validateWhere()){
           resultVal = false;
        }
        if(!validateName()){
           resultVal = false;
        }
        if(!validateAddressMobile()){
           resultVal = false;
        }
        if(resultVal){
            
        	$("#addressForm").submit();
        }
        
	};
	//$("#addressForm").submit(validateSubmit);
	$("#address_submit").click(validateSubmit);
	$("#address_province").change(citychange);
	$("#address_city").change(areachange);
	$("#address_area").change(townchange);
	$("#address_town").change(labelchange);
//	idprovince=1;
//	provinceData(idprovince);
	
//	$("#firstradio").click();
		
	//order/shipment_tuanembed
	    
	$("#back").click(function(){
		  return history.go(-1);
	});	
	
	
	//\u652F\u4ED8\u5BC6\u7801\u63D0\u793A\u9690\u85CF;
	$('#btnTip').click(function(){
		  $('.pop-prompt').hide();
    });
	
	
});
	
	
	//\u652F\u4ED8\u5BC6\u7801\u63D0\u793A;
	function showpayPasswordTip(){
		 $('.pop-prompt').show();
	}

	//\u6DFB\u52A0\u652F\u4ED8\u5BC6\u7801\u4F7F\u7528\u7684\u8FDE\u63A5;
	function addPayPasswordLink(){
		jQuery.get('/user/payPasswordLink.json?',
				{},function(data){
					if( data.isSuccess == "T" ){
						$('#findPayPassword').show();
					}else{
						$('#openPayPassword').show();
					}
				},'json');
	}