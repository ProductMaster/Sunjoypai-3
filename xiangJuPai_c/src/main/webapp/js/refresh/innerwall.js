/*******数据交互************/	
$(function(){
	getHouseModifyInfo();
})		
	
	//从享居派获取房屋修改信息
/*	function getHouseModifyInfo(){
		
		 $.ajax({   
			    type : "get",   
			   // url : "http://www.sunjoypai.com/api/userapp/paintwall/getHouseModify.json", 
			    url : "http://192.168.1.164:8080/api/refresh/getHouseModify.json",			    
			    dataType: "jsonp",
			    jsonp:"callback",
			    success : function(result) {   
			    	alert(result);
			    }   
		 })		 		 
	}*/
	var str = "";
	function getHouseModifyInfo(){
		
		 $.ajax({   
			    type : "post",   
			    url : ctx+"/info/getHouseModify",	
			    dataType:"json",
			    success : function(result) {   
			    	if(result.status == 1)  { //请求成功
			    		var fitmentArray = result.data.fitment; // 新房装修
			    		
			    		var modifyArray = result.data.modify; // 旧房改造 
			    					    		
			    		for(var i=0;i<fitmentArray.length;i++) {
			    			var fit = fitmentArray[i];
			    		/*	str += "<li>";
			    			str += "<img src=http://www.sunjoypai.com/"+fit.pictureUrl+" alt='新房装修' class='newImg'>";
			    			str += "<p>"+fit.modifyName+"</p>"
			    			str += "<em></em>";
			    			str += "<label style='display:none'>"+fit.id+"</label>";
			    			str += "</li>"
			    			var init="<ul class='newList clearfix'>"*/
			    			str += "<dl class='new'>";
			    			str += "<dd>";
			    			str += "<img src=http://www.sunjoypai.com/"+fit.pictureUrl+" alt='新房装修'>";
			    			str += "<em></em>";
			    			str += "<label style='display:none'>"+fit.id+"</label>";
			    			str += "</dd>";
			    			str += "<dt><span>"+fit.modifyName+"</span></dt>";
			    			str += "</dl>";	
			    		}
			    		//alert(str);
			    		$("#newHouse").html(str);
			    		
			    		var mstr = "";
			    		for(var i=0;i<modifyArray.length;i++) {
			    			var mod = modifyArray[i];
			    			mstr += '<dl class="old">';
			    			mstr += '<dd class="oldList">';
			    			mstr += '<img src=http://www.sunjoypai.com/'+mod.pictureUrl+' alt="新房装修" class="degree">';
			    			mstr+='<span class="name">'+mod.modifyName+'</span>';
			    			mstr+='<span class="num"><i class="sub">-</i><input type="text" class="areaNum" value="0"><i class="add">+</i> ㎡</span>';
                            mstr += "<em></em>";
                            mstr += "<label style='display:none'>"+mod.id+"</label>";
                            mstr += "</dd></dl>";
			    			
			    			/*mstr += "<dl>";
			    			mstr += "<dd>";
			    			mstr += "<img src=http://www.sunjoypai.com/"+mod.pictureUrl+" alt='新房装修'>";
			    			mstr += "<img src=http://www.sunjoypai.com/"+mod.pictureUrl+" alt='新房装修'>";
			    			mstr += "<em></em>";
			    			mstr += "<label style='display:none'>"+mod.id+"</label>";
			    			mstr += "</dd>";
			    			mstr += "<dt><span>"+mod.modifyName+"</span></dt>";
			    			mstr += "</dl>";	*/
			    		}
			    		
			    		$("#oldHouse").html(mstr);
			    		
			    		$(function(){
			    			  $(".add").click(function(){
			    			      var num=$(this).siblings(".areaNum").val();
			    			      $(this).siblings(".areaNum").val(++num);
			    			    })
			    			    $(".sub").click(function(){
			    			      var num=$(this).siblings(".areaNum").val();
			    			      if (num>1) {
			    			        $(this).siblings(".areaNum").val(--num);
			    			      }else{
			    			        $(this).siblings(".areaNum").val(1);
			    			      }
			    			    })
			    			 })
			    	
			    		/*******动态效果**********/		

			    		$(function(){
			    			//墙面情况选择
			    			$(".houseProblems span").click(function(){
			    				$(this).toggleClass("on");
			    				if($(".problemNoneFlag").hasClass("on")){
			    					$(this).addClass("on").siblings().removeClass("on");
			    				}
			    			});
			    			//新旧房改造
			    			$(".renovation li").click(function(){
			    				$(this).addClass("active").siblings().removeClass("active");
			    				$("#decorationContent div").hide().eq($(this).index()).show();
			    			});
			    			//选择新旧墙的墙面问题
			    			$("#newHouse dl").last().addClass('Decoration_last');
			    			$("#oldHouse dl").last().addClass('Decoration_last');
			    			$("#decorationContent dl").click(function(){
			    				if($(this).hasClass('Decoration_last')){
			    					$("#decorationContent dl").find('em').hide();
			    					$(this).find('em').toggle();
			    				}
			    				else{
			    					$('.Decoration_last').find('em').hide();
				    				$(this).find('em').toggle();
			    				}
			    			});
			    			

			    		});

			    	}
 			    }   
		 })		 		 
	}
	//智能报价
	function autoPrice(){
				
		var spaceType = 1; //1.家用 2商用
		var metopeType = 1;   //家用
		var roomType = '1,2'; //房间类型 
		var materialType = '1,1';	//涂料种类 
		var colorId = '1,1'; //涂料颜色
		var saloonNum = 1;
		var roomNum = 1;
		
		var forecastCoveredArea = $("#area1").val(); //面积
		if($.trim(forecastCoveredArea)==''){
			alert("请输入建筑面积");
			return;
		}
		if(!(/^\d{1,5}$/.test(forecastCoveredArea))){
			alert("建筑面积格式不正确,请输入5位以下正整数");
			return;
		}
		if(parseFloat(forecastCoveredArea)<10){
			alert("输入面积不能小于10");
			return;
		}
		//房间类型
/*			$("#keting>section>div>p").each(function(){ //客厅选中数量 客厅为1
				if($(this).hasClass("bg1")){ //是否选中
					roomType+='1,';
				
					//所用材料   涂料1 墙纸2
					var material = $(this).parent().next().find(".style_list");
					var mtype = material.children(".active").html()=="涂料"?1:2;
					materialType+= mtype+',';
					
					//获取选中的颜色
				    var selectColor = material.parent().next().children(".ul_select:visible");
					var selectColorId = selectColor.find(".colorId").html();
					if(selectColorId!=''&&typeof(selectColorId)!="undefined")
						colorId+=selectColorId+',';
					
				}				
			});
			$("#woshi>section>div>p").each(function(){ //卧室选中数量  卧室为2
				if($(this).hasClass("bg1")){ //是否选中
					roomType+='2,';
				
					//所用材料
					var material = $(this).parent().next().find(".style_list");
					var mtype = material.children(".active").html()=="涂料"?1:2;
					materialType+= mtype+',';
					
					//获取选中的颜色
				    var selectColor = material.parent().next().children(".ul_select:visible");
					var selectColorId = selectColor.find(".colorId").html();
					if(selectColorId!=''&&typeof(selectColorId)!="undefined")
						colorId+=selectColorId+',';
				}
				
			});*/
			//roomType = roomType.substr(0,roomType.length-1);
			//materialType = materialType.substr(0,materialType.length-1);
			//colorId = colorId.substr(0,colorId.length-1);
			//alert(roomType+"&"+materialType+"&"+colorId);
			//var saloonNum = document.getElementById("ting").value; //客厅数量
			//var roomNum = document.getElementById("shi").value; //卧室数量
			//remark = document.getElementById("remark1").value;
			

			//房屋改造情况 暂且写死 
			var houseModify = ''; 
			if($(".old").is(":visible")){
				$(".old").find("em:visible").next("label").each(function(){
					houseModify+=$(this).text()+','+$(this).siblings(".num").find("input.areaNum").val()+'#';	
				});
			}else{
				$(".new").find("em:visible").next().each(function(){
					houseModify+=$(this).text()+',';						
				});
			}
			var num=houseModify.length-1;
			houseModify = houseModify.substring(0,num);
			console.log(houseModify);
/*			$("#decorationContent").children(".houseDecoration:visible").find("em:visible").next().each(function(){
				houseModify+=$(this).text()+',';	
			});
			houseModify = houseModify == ''?'':houseModify.substring(0,houseModify.length-1);*/
			
			
			
			if(houseModify == ''){                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
				alert("请选择房屋装修情况");
				return;
			}
			if($.trim(roomType)==''){
				alert("请选择房间");
				return;
			}	
			if(saloonNum==0&&roomNum==0){
				alert("请选择房型");
				return;
			}			
			
			
			/* 		 {"spaceType":'${spaceType }',"metopeType":$("#metopeType").val(),"roomType":roomType,"materialType":materialType,
    		"forecastCoveredArea":area,"saloonNum":$("#saloonNum").val(),"roomNum":$("#roomNum").val(),"metopeCase1":$("input[name='metopeCase1']").val(),
    		"metopeCase2":$("input[name='metopeCase2']").val(),"metopeCase3":$("input[name='metopeCase3']").val(),"metopeCaseNoneFlag":$("input[name='metopeCaseNoneFlag']").val(),
    		"scaffoldFlag":$("input[name='scaffoldFlag']").val(),"houseModify":$("#houseModify").val()} */
    	
		var param = {"spaceType":spaceType,"metopeType":metopeType,"roomType":roomType,"materialType":materialType,
				"forecastCoveredArea":forecastCoveredArea,"saloonNum":saloonNum,"roomNum":roomNum,"metopeCase1":0,"metopeCase2":0,"metopeCase3":0,"metopeCaseNoneFlag":1,
	    		"scaffoldFlag":0,"houseModify":houseModify}	
		
		
		 $.ajax({   
			    type : "post",   
			    url : ctx+"/info/calculatecost",	
			    dataType:"json",
			    data:param,
			    success : function(result) {  
			    	
			    	if(result.status == 1)  { //请求成功			    		
			    		var data = result.data;
			    		console.log(result);
			    		alert("基础价格:"+data.baseTotalCost+",墙面处理费:"+data.houseModifyPrice+",总金额:"+data.userForecastTotalCost);			    		
			    	}
			    	else {
			    		alert(result.msg);
			    	}
			    }   
		 })		
			
  }		
		
	//家居焕新的tab选项卡 
	$(".title_tab>li").click(function(){
		$(this).addClass('select').siblings().removeClass('select');
		$("#myTabContent>article").hide().eq($(this).index()).show();
	});
	

	

		
		
	
	
	

	
	
	
	
