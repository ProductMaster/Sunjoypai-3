//====================================================================================================
// 类型：      通用函数
// 功能描述：  执行完后计算总价
// 作者:      tony
// 最后修改时间：      2011-12-23 
//====================================================================================================
function ZONE_CODE_CALLBACK(){
	getCostListPrice();//计算总价
}
//====================================================================================================
// 类型：      通用函数
// 功能描述：  执行完后加载用户优惠券和会员地址
// 作者:      tony
// 最后修改时间：      2011-12-23 
//====================================================================================================
function WEBOPENUSER_CALLBACK(){
	getUserCouponList();//优惠券
	getUserAddrs();//加载会员地址
}

//====================================================================================================
// 类型：      		通用函数
// 功能描述：  		根据选中商品ID动态加载(用户优惠券)
// 参数：      		catalogId ： 商品ID type 
// 作者:      		tony
// 最后修改时间：      2011-12-19 
//====================================================================================================
function getUserCouponList(){
	
	$.getScript(DOMAIN+"/couponVoucher/getWebOpenUserCouponVoucher.html?disType=json&"+Math.random()+"&jsoncallback=?",
    	function(data){
    		str_html = '';
            var json  = eval(jcoupon.options);
				str_html += '<ul>';
				str_html += '<li>';
					str_html += '<div class="floatleft">';
						str_html += '<input value="" name="voucherNo_" fd="0"  id="voucherNo_notuse" onclick="doCoupon(this,0)" type="radio"  />';
						str_html += '<span>';
						str_html += '不使用现金券'
						str_html += '</span>';
					str_html += '</div>';
				str_html += '</li>';
				
				var best_voucher = 'voucherNo_notuse'; 
				var best_voucher_money = 0;
				
				for(var i=0; i<json.length; i++){
					var freeDelivery = json[i].freeDelivery; 
					var voucherId = json[i].voucherId; 
					var	voucherNo= json[i].voucherNo; 
					var	couponName= json[i].couponName; 
					var	discountValue	= json[i].discountValue; 
					var	memberRestrict= json[i].memberRestrict;
					var	amountRestrict= json[i].amountRestrict;
					var	strStartDate= json[i].strStartDate;
					var	strEndDate= json[i].strEndDate;
					var	killMoney= parseInt(json[i].killMoney);
					var	strUseRestrict= json[i].strUseRestrict;
					var dis_str = killMoney <= 0 ? "disabled=\"disabled\"" : "";
					var kill_str = killMoney <= 0 ? "面值:"+discountValue+"元" : "可抵扣:"+killMoney+"元";
				
					var	couponCount= json[i].couponCount; 
						
					if(killMoney > best_voucher_money ){
						best_voucher = "voucherNo_"+i;
						best_voucher_money = killMoney;
					}
					
					if(killMoney > 0 ){
					str_html += '<li>';
						str_html += '<div class="floatleft">';
							str_html += '<input  name="voucherNo_" fd="'+freeDelivery+'" id="voucherNo_'+i+'" onclick="doCoupon(this,'+killMoney+')" type="radio" value="'+voucherNo+'" '+dis_str+' />';
							str_html += '<span>';
							str_html += '['+couponName+'：';
							str_html += '<font color=red>'+couponCount+'</font>';
							str_html += '张]';		
								str_html += '<span class="coupon_num">'
								str_html += kill_str
								str_html += '</span>';
								str_html += '&nbsp;&nbsp;[有效期：'+strEndDate+']';
							str_html += '</span>';
						str_html += '</div>';
					str_html += '</li>';
					}
				}
				
				str_html += '</ul>';
				
				$("#couponPanel").html(str_html);
				$("#"+best_voucher).click()
				
			
	});
}





//====================================================================================================
// 类型：      通用函数
// 功能描述：  绑定现金券
// 参数：      现金券号
// 作者:      tony
// 时间：      2011-12-19 
//====================================================================================================
var coupondiscount = 0;
function bindWebOpenVoucherNo(){
	var voucherNo = $("#bind_voucher_no").val();
	$.getScript(basePath+"/couponVoucher/bindCouponVoucher?voucherNo="+voucherNo+"&disType=json&"+Math.random()+"&jsoncallback=?",
    	function(data){
            var status = jcoupon.status;
          	switch(status){
          		case 'VOUCHER_NO_ERROR':
          		$("#bindRes").html("<label style='color:red;' >现金券号不存在或已绑定!</label>");
          		break;
          		case 'BIND_SUCCESS':
          		$("#bindRes").html("<label style='color:green;' >绑定成功!</label>");
          		$("#coupon_count").text('1');
          		coupondiscount = jcoupon.discountValue;
          		$("#coupon_discount").text(jcoupon.discountValue);
          		getCostListPrice();//重新计算价格明细
          		break;
          		case 'USER_NOT_LOGIN':
          		$("#bindRes").html("<label style='color:red;' >请重新登录!</label>");
          		break;
          	}
          	
	});
}

//====================================================================================================
// 类型：      通用函数
// 功能描述：  默认选中现金券
// 参数：      rad,killMoney 当前选中的券及抵扣金额
// 作者:      tony
// 时间：      2011-12-24 
//====================================================================================================
function doCoupon(rad,killMoney){
	if(rad.checked){
		//重新使用虚拟账户时候，恢复原来总价
		TotalPrice = parseFloat(TotalPrice);
		CouponPrice = killMoney;
		if(killMoney == 0){
			$("#coupon_count").html("0");
			$("#hdfk_pmethod").show();
			$("#hdfk_pmethod_memo").hide();
		}else{
			if($("#voucherNo").val() == rad.value) return;
			$("#coupon_count").html("1");
		}
		if(TotalPrice - CouponPrice < 0){//优惠金额超过总价
			var vaccount = parseFloat($("#vaccount_").val());
			if(vaccount>=(CouponPrice-TotalPrice)){
				$("#vaccount_").val(vaccount-(CouponPrice-TotalPrice));
			}else{
				$("#vaccount_").val(0);
			}
		}
		$("#coupon_discount").html(CouponPrice);
		$("#total_coupon_discount").html(CouponPrice);
		
		$("#voucherNo_").val(rad.value);
		getCostListPrice();//重新计算价格明细
		}
	}
	
	
	
//====================================================================================================
// 类型：      通用函数
// 功能描述：  虚拟账户    
// 作者:      tony
// 时间：      2011-12-24 
//====================================================================================================
	var UserVaccount = 0;//虚拟账户余额
	var TotalPrice =0;//当前需要付款的总价	
	function doVaccount(){
		//重新使用虚拟账户时候，恢复原来总价
		var checkPrice = parseFloat(TotalPrice);
		UserVaccount = parseFloat(UserVaccount);
		var vaccount = $("#vaccount_input").val();
		if(!isMoney(vaccount,2)){
			$("#vaccountMsg").html("提示:请输入正确的金额");
			return false;
		}
		
		if(vaccount < 0){
			$("#vaccountMsg").html("提示:请输入正确的金额");
			return false;
		}
		if(vaccount > UserVaccount){
			$("#vaccountMsg").html("提示:余额不足");
			return false;
		}
		if((checkPrice - vaccount) < 0){
			$("#vaccountMsg").html("提示:金额不得超过总价");
			return false;
		}
		var defaultBank = "";
		
		var checkboxs=document.getElementsByName("onlineDefaultBank");
		for (var i=0;i<checkboxs.length;i++) {
			  if(checkboxs[i].checked){
			  	defaultBank = checkboxs[i].value;
			  }
		}
		if(vaccount > 0 && defaultBank == "ZFBDBJY"){
			$("#vaccountMsg").html("提示:担保交易无法使用虚拟账户");
			return false;
		}
		$("#vaccount_").val(vaccount);//初始化使用的虚拟账户
		$.post("/order/getOrderPrice?vaccount="+vaccount,
		    	function(data){
		            $("#total-price").html(data);
		});
	}

	//====================================================================================================
	// 类型：      通用函数
	// 功能描述：  动态加载价格
	// 参数：      voucherNo 现金券编号orderType 订单类型productPrice 商品总价
	// 参数：      minusPrice 优惠减价(包括现金券抵扣的金额和其他优惠金额)zoneNo 收货地址编号
	// 作者:      tony
	// 时间：      2011-12-19 
	//====================================================================================================
	function getCostListPrice(){
		$.post("/order/getOrderPrice",
	    	function(data){
	            $("#total-price").html(data);
	            TotalPrice=data;
		});
	}
	//====================================================================================================
	// 类型：      通用函数
	// 功能描述：  验证价格是否是价格（虚拟账户）  
	// 作者:      tony
	// 时间：      2011-12-24 
	//====================================================================================================
		function isMoney(strValue,len)
		{
			var objExp = /^(\+|-)?(0|[1-9]\d*)(\.\d*[0-9])?$/;
			var res = objExp.test(strValue);
			var res2 =true;
			if(strValue.indexOf('.')!=-1)
			{
				var strValue2 = strValue.split('.');
				if(strValue2[1].length>len)
				{
					res2 = false ;
				}
				else if(parseFloat(strValue2[0])<0)
				{
					res2 = false;
				}
			}else{
				if(parseFloat(strValue)<0)
				{
					res2 = false;
				}
			}
			return res&&res2;
		}	

	

	
