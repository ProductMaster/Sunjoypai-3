$(function(){
   var oH2=$('.wz_top').height();
	$('.dh_nav_con1').attr('style','top:'+oH2+'px');
	$('.lb_btn').click(function(){
	$('.dh_nav_con1').toggle();
 });
})

$(function(){
	var oH1=$('.dh_nav_con').height();
	$('.dh_nav_con').attr('style','top:'+-oH1+'px');
	$('.dh_btn').click(function(){
		$('.dh_nav_con').toggle();
	 });
})

$(function(){
	var h=$(window).height()-$('.wz_top').height()-110;
	$('.sc_content').attr('style','min-height:'+h+'px');
})
