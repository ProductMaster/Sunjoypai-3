define(function(require){
	$ = jQuery = require('jquery');
	
	$('.delete').on('click',function(){
		$('.main').val('');
		$(this).hide();
	})
	$('.main').on('keyup',function(){
		$('.delete').show();
	});
	
	$('.saveBtn').on('click',function(){
		location.href = './shop_setting.html';
	})
});
