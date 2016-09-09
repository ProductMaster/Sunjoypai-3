function initCharXML(url,type,tagId)
{
		var chartXML;
		if(type==1)
		{
			chartXML = new FusionCharts("/js/fusioncharts/Charts/MSLine.swf?ChartNoDataText=暂无数据", "myChartId", "100%", "300");
			url =url+'?type='+type+'&'+Math.random(); 
			
		}
		else if(type==2)
		{
			chartXML = new FusionCharts("/js/fusioncharts/Charts/Pie2D.swf?ChartNoDataText=暂无数据", "myChartId", "100%", "300");
			url =url+'?type='+type+'&'+Math.random(); 
		}
		else if(type==3)
		{
			chartXML = new FusionCharts("/js/fusioncharts/Charts/Pie2D.swf?ChartNoDataText=暂无数据", "myChartId", "100%", "300");
			url =url+'?type='+type+'&'+Math.random(); 
		}
	    $.get(url,function(data){
	    	chartXML.setDataXML(data); 
	   		chartXML.render(tagId); 
	    })
	    return false;
}
function initChartPie2D(url,tagId){
	var chartXML = new FusionCharts("/js/fusioncharts/Charts/Pie2D.swf?ChartNoDataText=暂无数据", "myChartId", "100%", "300");;
		 $.get(url,function(data){
	    	chartXML.setDataXML(data); 
	   		chartXML.render(tagId); 
	})
	return false;
}