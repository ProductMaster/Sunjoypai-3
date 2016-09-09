define(function(require){
	$ = jQuery = require('jquery');
	var template = require('template2');
	var layer = require('layer');
	//require('./layer/need/layer.css');
	
	//地板
	var shopDatas = {list:[{image:'../images/test_big_pic.png',name:'HCG和成卫浴',stuff:'主营：卫浴，五金配件，智能卫浴',location:'上海市虬江路1538号',promotion:'整单商品满3000元，即可立减500元现金'}
	,{image:'../images/testPic.jpg',name:'商铺名字',stuff:'主营：各种东西',location:'上海  九星市场',promotion:'有好多好多的优惠'}
	,{image:'../images/testPic.jpg',name:'商铺名字',stuff:'主营：各种东西',location:'上海  九星市场',promotion:'有好多好多的优惠'}
	,{image:'../images/testPic.jpg',name:'商铺名字',stuff:'主营：各种东西',location:'上海  九星市场',promotion:'有好多好多的优惠'}
	,{image:'../images/testPic.jpg',name:'商铺名字',stuff:'主营：各种东西',location:'上海  九星市场',promotion:'有好多好多的优惠'}
	,{image:'../images/testPic.jpg',name:'商铺名字',stuff:'主营：各种东西',location:'上海  九星市场',promotion:'有好多好多的优惠'}
	,{image:'../images/testPic.jpg',name:'商铺名字',stuff:'主营：各种东西',location:'上海  九星市场',promotion:'有好多好多的优惠'}]};
	
		
	//木门	
	var shopData1 = {list:[{image:'../images/test_big_pic.png',name:'HCG和成卫浴',stuff:'主营：卫浴，五金配件，智能卫浴',location:'上海市虬江路1538号',promotion:'整单商品满3000元，即可立减500元现金'}
	,{image:'../images/testPic.jpg',name:'商铺名字',stuff:'主营：木门',location:'上海  九星市场',promotion:'有好多好多的优惠'}
	,{image:'../images/testPic.jpg',name:'商铺名字',stuff:'主营：木门',location:'上海  九星市场',promotion:'有好多好多的优惠'}
	,{image:'../images/testPic.jpg',name:'商铺名字',stuff:'主营：木门',location:'上海  九星市场',promotion:'有好多好多的优惠'}]};
	
	//瓷砖	
	var shopData2 = {list:[{image:'../images/testPic.jpg',name:'商铺名字',stuff:'主营：各种东西',location:'上海  九星市场',promotion:'有好多好多的优惠'}
	,{image:'../images/testPic.jpg',name:'商铺名字',stuff:'主营：瓷砖',location:'上海  九星市场',promotion:'有好多好多的优惠'}
	,{image:'../images/testPic.jpg',name:'商铺名字',stuff:'主营：瓷砖',location:'上海  九星市场',promotion:'有好多好多的优惠'}
	,{image:'../images/testPic.jpg',name:'商铺名字',stuff:'主营：瓷砖',location:'上海  九星市场',promotion:'有好多好多的优惠'}]};
	
	//橱柜	
	var shopData3 = {list:[{image:'../images/testPic.jpg',name:'商铺名字',stuff:'主营：各种东西',location:'上海  九星市场',promotion:'有好多好多的优惠'}
	,{image:'../images/testPic.jpg',name:'商铺名字',stuff:'主营：各种东西',location:'上海  九星市场',promotion:'有好多好多的优惠'}
	,{image:'../images/testPic.jpg',name:'商铺名字',stuff:'主营：各种东西',location:'上海  九星市场',promotion:'有好多好多的优惠'}
	,{image:'../images/testPic.jpg',name:'商铺名字',stuff:'主营：各种东西',location:'上海  九星市场',promotion:'有好多好多的优惠'}]};
	
	//装饰
	var shopData4 = {list:[{image:'../images/testPic.jpg',name:'装饰',stuff:'主营：各种东西',location:'上海  九星市场',promotion:'有好多好多的优惠'}
	,{image:'../images/testPic.jpg',name:'装饰',stuff:'主营：各种东西',location:'上海  九星市场',promotion:'有好多好多的优惠'}
	,{image:'../images/testPic.jpg',name:'商铺名字',stuff:'主营：各种东西',location:'上海  九星市场',promotion:'有好多好多的优惠'}
	,{image:'../images/testPic.jpg',name:'商铺名字',stuff:'主营：各种东西',location:'上海  九星市场',promotion:'有好多好多的优惠'}]};
	
	
	//layer.open({content:'<img src="../images/qr.jpg">'})
	var htmls = template('shopData',shopDatas);
	$(htmls).insertBefore('#foot');
	
	$(window).scroll(function(){
		var scrollTop = $(window).scrollTop();
	　　var scrollHeight = $(document).height();
	　　var windowHeight = $(window).height();
	　　if(scrollTop + windowHeight >= scrollHeight-50){
	　　　　$(htmls).insertBefore('#foot');
	　　}
	})
	
	$('.title').on('click','span',function(){
		var _t = $(this);
		if(_t.hasClass('active'))return;
		_t.parent().find('.active').removeClass('active');
		_t.addClass('active');
		var id = _t.attr('id');
		$('ul li').remove();
		var htmls = null;
		if(id == 'door'){
			htmls = template('shopData',shopData1);
		}
		else if(id == 'tile'){
			htmls = template('shopData',shopData2);
		}
		else if(id == 'cupboard'){
			htmls = template('shopData',shopData3);
		}
		else if(id == 'ornament'){
			htmls = template('shopData',shopData4);
		}
		else {
			htmls = template('shopData',shopDatas);
		}
		$(htmls).insertBefore('#foot');
	})
})
