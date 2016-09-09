/**
*说明：简单的BlockUI进入与退出函数
*使用方法：需要引用jquery-1.2.6.pack.js与jquery.blockUI.js
*		  需要弹出模态窗口的地方调用 Block(divId,divWidth,divHeight)，解除模态调用unBlock()
*参数说明： Block(divId,divWidth,divHeight)，（需要模态显示的DIV的id，DIV的宽，DIV的高）
**/
function Block(divId,divWidth,divHeight){
	divWidth = $("#"+divId).width();
	divHeight = $("#"+divId).height();
	$.blockUI({ 
			message: $('#'+divId),
			 css: {  
                top:  '50px', 
                left: ($(window).width() - divWidth) /2 + 'px', 
                 //width: divWidth+'px' ,
                 //height:divHeight+'px',
				cursor:'default'
            } 
	}); 
}
function unBlock(){
	$.unblockUI(); 
}