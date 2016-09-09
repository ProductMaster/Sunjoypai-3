define(function(require){
	$ = jQuery = require('jquery');
	
	$('.sexBox').on('click','.sex',function(){
		var _t = $(this);
		if(_t.hasClass('active'))return;
		$('.sex.active').removeClass('active');
		_t.addClass('active');
	})
	
});
