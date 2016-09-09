
//初始化省份信息,默认的插入HTML标签在<span>ID=cityDiv里面
//插入里面的标签为select标签
function initProzone()
{    
    initProzoneByDIVID("cityDiv","");
}
//省初始化的时候定位操作
/**
** divID 将select标签插入的位置,divID为DIV的ID
** prozoneNo省编号,如果是初次加载输入""值
**/
function initProzoneByDIVID(divID,prozoneNo)
{
	
	$.ajax({
     type: "get",       
     url: SERVER_PATH+"/base/initZone",       
     data: "s="+new Date(),   
     success: function(data){
        var selectHtml = '<select name="prozoneNo" id="prozoneNo" class="required" title="请选择省份" onchange="getZoneNo(this,1)" id="provinceNo"><option value="">---请选择---</option>';
		$(data).find("entity").each(function(){
			var entityZoneNo = $(this).find('zoneNo').text();
			
			//begin 定位select标签
			var strSelected = "";
			if(prozoneNo==entityZoneNo)
				strSelected = 'selected="selected"';
			//end 定位select标签	
			selectHtml += '<option value="'+entityZoneNo+'" '+strSelected+'>'+$(this).find('zoneName').text()+'</option>';
		})
		selectHtml += '</select>';//省
		selectHtml += '<select name="cityzoneNo" id="cityzoneNo" onchange="getZoneNo(this,2)" class="required" title="请选择城市"  ><option value="">---请选择---</option></select>';
		selectHtml += '<select name="zoneNo" id="zoneNo" class="required" title="请选择区域"><option value="">---请选择---</option></select>';
		$("#"+divID).append(selectHtml);
	 }             
    });  
}
function initArea(zoneNo)
{
	var prozoneNo = zoneNo.substring(0,3) + "000000";//省编号
	var cityNo = zoneNo.substring(0,6) + "000";//城市
	initProzoneByDIVID("cityDiv",prozoneNo);
	selectedCity(prozoneNo,cityNo,1);
	selectedCity(cityNo,zoneNo,2);
}
function selectedCity(zoneNo,zoneCodeNo,type)
{
	$.ajax({
     type: "get",       
     url: SERVER_PATH+"/base/getZone",       
     data: "s="+new Date()+"&zoneNo="+zoneNo+"&type="+type,   
     success: function(data){
     	if(type==1)
     		$("#cityzoneNo").empty();
     	else
     		$("#zoneNo").empty();
		$(data).find("entity").each(function(){
		    var entityZoneNo = $(this).find('zoneNo').text();
		    var entityZoneName = $(this).find('zoneName').text();
		    var strSelected = "";
		    if(zoneCodeNo==entityZoneNo)
		    {
		    	strSelected = 'selected="selected"';
		    }
		    var strOption = "<option value='"+entityZoneNo+"' "+strSelected+">"+entityZoneName+"</option>";
			if(type==1)
				$(strOption).appendTo("#cityzoneNo");
		    else
		    	$(strOption).appendTo("#zoneNo");
		})
	 } 
    });  
}

function getZoneNo(zoneNo,type)
{
	$.ajax({
     type: "get",       
     url: SERVER_PATH+"/base/getZone",       
     data: "s="+new Date()+"&zoneNo="+zoneNo.value+"&type="+type,   
     success: function(data){
     	if(type==1)
     		$("#cityzoneNo").empty();
     	else
     		$("#zoneNo").empty();
		$(data).find("entity").each(function(){
			var entityZoneName = $(this).find('zoneName').text();
			var entityZoneNo = $(this).find('zoneNo').text();
			var strOption = "<option value='"+entityZoneNo+"'>"+entityZoneName+"</option>";
			if(type==1)
				$(strOption).appendTo("#cityzoneNo");
		    else
		    	$(strOption).appendTo("#zoneNo");
		    	
		})
	 } 
	            
    });  
}