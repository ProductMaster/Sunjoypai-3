define(function(require){
	$ = jQuery = require('jquery');
	var template = require('template2');
	var curType;
	
	$('.channel').on('click','span',function(){
		var _t = $(this);
		if(_t.hasClass('active'))return;
		_t.parent().find('.active').removeClass('active');
		_t.addClass('active');
		var type = _t.attr('data');
		setData(type=="cases"?casesData:productData,type);
	})
	
	var casesData = {list:[{head:'../images/seize3.jpg',name:'HCG和成卫浴',desc:'新产品，欧式百搭',picList:['../images/ts1.png','../images/ts6.png']}
	,{head:'../images/test_big_pic.png',name:'HCG和成卫浴',desc:'高端、大气、上档次，低调、奢华、有内涵',picList:['../images/ts1.png','../images/ts2.png','../images/ts3.png','../images/ts4.png','../images/ts5.png','../images/ts6.png','../images/ts7.png','../images/ts8.png','../images/ts9.png']}
	,{head:'../images/test_big_pic.png',name:'HCG和成卫浴',desc:'高端、大气、上档次，低调、奢华、有内涵',picList:['../images/ts1.png','../images/ts2.png','../images/ts3.png','../images/ts4.png','../images/ts5.png','../images/ts6.png','../images/ts7.png','../images/ts8.png','../images/ts9.png']}
	,{head:'../images/test_big_pic.png',name:'HCG和成卫浴',desc:'高端、大气、上档次，低调、奢华、有内涵',picList:['../images/ts1.png','../images/ts2.png','../images/ts3.png','../images/ts4.png','../images/ts5.png','../images/ts6.png','../images/ts7.png','../images/ts8.png','../images/ts9.png']}
	,{head:'../images/test_big_pic.png',name:'HCG和成卫浴',desc:'高端、大气、上档次，低调、奢华、有内涵',picList:['../images/ts1.png','../images/ts2.png','../images/ts3.png','../images/ts4.png','../images/ts5.png','../images/ts6.png','../images/ts7.png','../images/ts8.png','../images/ts9.png']}
	,{head:'../images/test_big_pic.png',name:'HCG和成卫浴',desc:'高端、大气、上档次，低调、奢华、有内涵',picList:['../images/ts1.png','../images/ts2.png','../images/ts3.png','../images/ts4.png','../images/ts5.png','../images/ts6.png','../images/ts7.png','../images/ts8.png','../images/ts9.png']}
	,{head:'../images/test_big_pic.png',name:'HCG和成卫浴',desc:'高端、大气、上档次，低调、奢华、有内涵',picList:['../images/ts1.png','../images/ts2.png','../images/ts3.png','../images/ts4.png','../images/ts5.png','../images/ts6.png','../images/ts7.png','../images/ts8.png','../images/ts9.png']}
	,{head:'../images/test_big_pic.png',name:'HCG和成卫浴',desc:'descdescdesc',picList:['../images/seize2.jpg','../images/seize2.jpg','../images/seize2.jpg','../images/seize2.jpg']}
	,{head:'../images/test_big_pic.png',name:'HCG和成卫浴',desc:'descdescdesc',picList:['../images/seize2.jpg','../images/seize2.jpg','../images/seize2.jpg','../images/seize2.jpg']}
	,{head:'../images/test_big_pic.png',name:'HCG和成卫浴',desc:'descdescdesc',picList:['../images/seize2.jpg','../images/seize2.jpg','../images/seize2.jpg','../images/seize2.jpg']}]};
	
	var productData = {list:[{image:'../images/ts1.png',name:'names',price:'38.80'},{image:'../images/ts2.png',name:'names',price:'38.80'},{image:'../images/ts3.png',name:'names',price:'38.80'}
	,{image:'../images/ts4.png',name:'names',price:'38.80'},{image:'../images/ts5.png',name:'names',price:'38.80'},{image:'../images/ts6.png',name:'names',price:'38.80'}
	,{image:'../images/ts1.png',name:'names',price:'38.80'},{image:'../images/ts1.png',name:'names',price:'38.80'},{image:'../images/ts1.png',name:'names',price:'38.80'}
	,{image:'../images/ts1.png',name:'names',price:'38.80'},{image:'../images/ts1.png',name:'names',price:'38.80'},{image:'../images/ts1.png',name:'names',price:'38.80'}]}
	
	function setData(datas,type){
		var id = type=="cases"?'case_list':'product_list';
		curType = type;
		var htmls = template(id,datas);
		$('ul li').remove();
		$(htmls).insertBefore('#foot');
		if(type=="cases"){
			$('ul').removeClass('product');
		}else{
			$('ul').addClass('product');
		}
	}

	setData(casesData,'cases');
	
	//$(htmls).insertBefore('#foot');
	
	$(window).scroll(function(){
		var scrollTop = $(window).scrollTop();
	　　var scrollHeight = $(document).height();
	　　var windowHeight = $(window).height();
	　　if(scrollTop + windowHeight >= scrollHeight-50){
	　　　　var id = curType=="cases"?'case_list':'product_list';
			var datas = curType=="cases"?casesData:productData;
			var htmls = template(id,datas);
			$(htmls).insertBefore('#foot');
	　　}
	})
})
