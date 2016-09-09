define(function(require){
	$ = jQuery = require('jquery');
	
	$('.content').on('click','.head',function(){
		var _t = $(this);
		if(_t.attr('data-to')){
			location.href = './' +_t.attr('data-to') +'.html';
		}
	})
	
});
