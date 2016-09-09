//版本1.0
//最后修改日期：12.31
//说明：1、initProzone()直接初始化省市区2、编辑initProArea(100200300)

//====================================================================================================
//====================================================================================================
/**
*通用的初始化省市区JS代码
*TONY
*供参考
*最后修改：2011-12-26
*/
//====================================================================================================
//====================================================================================================


//====================================================================================================
// 类型：      通用函数
// 功能描述：  默认动态初始化省、市、区【未登录的时候】
// 参数：      cityDiv 显示的相关id
// 作者:      tony
// 时间：      2011-12-26 
//====================================================================================================
function initProzone()
{   
    initProzoneByDIVID("cityDiv","","","");
}
function initProzoneNo()
{    
    initProzoneByProDiv("proDiv","","","");
}
function initProzoneByProDiv(divID,prozoneNo,cityNo,zoneNo)
{
	$.getScript(basePath+"/sys/initZone?disType=json&"+Math.random()+"&jsoncallback=?",
    	function(data){
    		var selectHtml = '<select name="prozoneNo" id="prozoneNo" class="required new-input" title="请选择省份" onchange="initCityZoneNo(this);" id="provinceNo">';
    			selectHtml += '<option value="">---请选择---</option>';
    		var json  = eval(jinit.options);
    		for(var i=0; i<json.length; i++)
			{
				var strSelected = prozoneNo == json[i].zoneNo ? 'selected="selected"' : '';
				selectHtml += '<option value="'+json[i].zoneNo+'" title= "'+json[i].zoneName+'" '+strSelected+'>'+json[i].zoneName+'</option>';
			}
			selectHtml += '</select>';//省
			selectHtml += '<select name="cityzoneNo" id="cityzoneNo" onchange="InitZoneNo(this);" class="required new-input" title="请选择城市"  >';
			selectHtml += '<option value="">---请选择---</option></select>';//市
			$("#"+divID).append(selectHtml);
			if(cityNo!=""){
				//初始化城市
				initCityInfoOnly(prozoneNo,cityNo,zoneNo);
			}
				
    	}
    );
}
function chooseCustomer(customer){
	$('#customerId').val(customer.value);
}
function initDealer(prozoneNo)
{	
	initDealerInfo(prozoneNo.value);
}
function initDealerInfo(prozoneNo)
{
	var strdb = "zoneNo="+prozoneNo;
		strdb += "&type=1";
	$.getScript(basePath+"/sys/getZoneCustomer?"+strdb+"&disType=json&"+Math.random()+"&jsoncallback=?",
    	function(data){
    		//获取JSON
		   	var json  = eval(jinitcity.options);
			var strOption = '<option value="">---请选择---</option>';
		 	for(var i=0; i<json.length; i++)
			{
				var strSelected = cityNo == json[i].zoneNo ? 'selected="selected"' : '';
			    strOption += '<option value="'+json[i].customerId+'" '+strSelected+'>'+json[i].customerName+'</option>';
			}
			//加载经销商
	   		$("#customerList").empty();
			$(strOption).appendTo("#customerList");
   	}); 
}

//====================================================================================================
// 类型：      通用函数
// 功能描述：  根据用户的zoneNo（城市）直接生成省、市zoneNo,动态初始化省、市、区
// 参数：      zoneNo 城市
// 作者:      tony
// 时间：      2011-12-26 
//====================================================================================================
function initProArea(zoneNo)
{	
	//转省份编号zoneNo
	var prozoneNo = zoneNo.substring(0,3) + "000000";
	//转城市zoneNo
	var cityNo = zoneNo.substring(0,6) + "000";
	//初始化省份（在ID为：cityDiv中显示）
	initProzoneByDIVID("cityDiv",prozoneNo,cityNo,zoneNo);
}

//==============================================================================
// 类型：      通用函数【手动选择时加载】
// 功能描述：  根据省份zoneNo动态初始化（【城市】）
// 参数：      prozoneNo 当前选择的省份zoneNo
// 作者:      tony
// 时间：      2011-12-26 
//==============================================================================
function initCityZoneNo(prozoneNo)
{	
	$("#province_text").text(prozoneNo.title);
	initCityInfo(prozoneNo.value,"");
}
//==============================================================================
// 类型：      通用函数【手动选择时加载】
// 功能描述：  根据城市zoneNo动态初始化（【区域】）
// 参数：      cityNo 当前选择的城市zoneNo
// 作者:      tony
// 时间：      2011-12-26 
//==============================================================================
function InitZoneNo(cityNo)
{
	initZoneInfo(cityNo.value,"");
}


//====================================================================================================
// 类型：      通用函数
// 功能描述：  动态初始化【省份】，加载divID里面
// 参数：      divID,省市对象，prozoneNo,1选中省份2选中城市
// 作者:      tony
// 时间：      2011-12-26
//====================================================================================================
function initProzoneByDIVID(divID,prozoneNo,cityNo,zoneNo)
{
	$.getScript(basePath+"/sys/initZone?disType=json&"+Math.random()+"&jsoncallback=?",
    	function(data){
    		var selectHtml 	= '<select name="prozoneNo" id="prozoneNo" class="required new-input" title="请选择省份" onchange="initCityZoneNo(this);" id="provinceNo">';
    			selectHtml += '<option value="">---请选择---</option>';
    		var json  = eval(jinit.options);
    		for(var i=0; i<json.length; i++)
			{
				var strSelected = prozoneNo == json[i].zoneNo ? 'selected="selected"' : '';
				selectHtml += '<option value="'+json[i].zoneNo+'" '+strSelected+'>'+json[i].zoneName+'</option>';
			}
			selectHtml += '</select>';//省
			selectHtml += '<select name="cityzoneNo" id="cityzoneNo" onchange="InitZoneNo(this);" class="required new-input" title="请选择城市"  >';
			selectHtml += '<option value="">---请选择---</option></select>';//市
			selectHtml += '<select name="zoneNo" id="zoneNo" class="required new-input" title="请选择区域">';
			selectHtml += '<option value="">---请选择---</option></select>';//区
			$("#"+divID).append(selectHtml);
			if(cityNo!=""){
				//初始化城市
				initCityInfo(prozoneNo,cityNo,zoneNo);
			}
				
    	}
    );
}

//==============================================================================
// 类型：      通用函数
// 功能描述：  根据省份zoneNo加载【城市】[自动]
// 参数：      用到参数prozoneNo,cityNo,传参zoneNo
// 作者:      tony
// 时间：      2011-12-26 
//==============================================================================

function initCityInfo(prozoneNo,cityNo,zoneNo)
{
	var strdb = "zoneNo="+prozoneNo;
		strdb += "&type=1";
	$.getScript(basePath+"/sys/getZone?"+strdb+"&disType=json&"+Math.random()+"&jsoncallback=?",
    	function(data){
    		//获取JSON
		   	var json  = eval(jinitcity.options);
			var strOption = '<option value="">---请选择---</option>';
		 	for(var i=0; i<json.length; i++)
			{
				var strSelected = cityNo == json[i].zoneNo ? 'selected="selected"' : '';
			    strOption += '<option value="'+json[i].zoneNo+'" '+strSelected+'>'+json[i].zoneName+'</option>';
			}
			//加载城市
	   		$("#cityzoneNo").empty();
			$(strOption).appendTo("#cityzoneNo");
			if(zoneNo!=""){
				//初始化区域
				initZoneInfo(cityNo,zoneNo);
			}
	   		
   	}); 
}
function initCityInfoOnly(prozoneNo,cityNo,zoneNo)
{
	var strdb = "zoneNo="+prozoneNo;
		strdb += "&type=1";
	$.getScript(basePath+"/sys/getZone?"+strdb+"&disType=json&"+Math.random()+"&jsoncallback=?",
    	function(data){
    		//获取JSON
		   	var json  = eval(jinitcity.options);
			var strOption = '<option value="">---请选择---</option>';
		 	for(var i=0; i<json.length; i++)
			{
				var strSelected = cityNo == json[i].zoneNo ? 'selected="selected"' : '';
			    strOption += '<option value="'+json[i].zoneNo+'" '+strSelected+'>'+json[i].zoneName+'</option>';
			}
			//加载城市
	   		$("#cityzoneNo").empty();
			$(strOption).appendTo("#cityzoneNo");
   	}); 
}
//==============================================================================
// 类型：      通用函数
// 功能描述：  根据城市zoneNo加载【区域】[自动]
// 参数：      cityNo,zoneNo
// 作者:      tony
// 时间：      2011-12-26 
//==============================================================================
function initZoneInfo(cityNo,zoneNo)
{
	var strdb = "zoneNo="+cityNo;
    	strdb += "&type=2";
	$.getScript(basePath+"/sys/getZone?"+strdb+"&disType=json&"+Math.random()+"&jsoncallback=?",
    	function(data){
    		//获取JSON
		   	var json  = eval(jinitcity.options);
			var strOption = '<option value="">---请选择---</option>';
		 	for(var i=0; i<json.length; i++)
			{
				var strSelected = zoneNo == json[i].zoneNo ? 'selected="selected"' : '';
			    strOption += '<option value="'+json[i].zoneNo+'" '+strSelected+'>'+json[i].zoneName+'</option>';
			}
			//加载区域
	   		$("#zoneNo").empty();
			$(strOption).appendTo("#zoneNo");
   		}); 
}
