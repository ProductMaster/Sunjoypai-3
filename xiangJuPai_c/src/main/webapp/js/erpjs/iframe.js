function reinitIframe(){
	var iframe = document.getElementById("frame_content");
	try{
		var bHeight = iframe.contentWindow.document.body.scrollHeight;
		var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;		
		var height = Math.max(bHeight, dHeight);
		iframe.height = height;
	}catch (ex){}
}

function hideSbar(){
	var jt_all = document.getElementById('jt_all');
	var sLeft = document.getElementById('slideBar');
	var right = document.getElementById('right');
	var jt_hide = document.getElementById('jt_hide');
	var jt_show = document.getElementById('jt_show');
	var frame = document.getElementById("frame_content");
	jt_hide.style.display="none";
	jt_show.style.display="block";
	sLeft.style.display = 'none';
	right.style.width = '100%';
	if(document.all){	
		right.style.marginLeft="-5px";		
	}
}
function showSbar(){
	var jt_all = document.getElementById('jt_all');
	var sLeft = document.getElementById('slideBar');
	var right = document.getElementById('right');
	var jt_hide = document.getElementById('jt_hide');
	var jt_show = document.getElementById('jt_show');
	var frame = document.getElementById("frame_content");
	jt_hide.style.display="block";
	jt_show.style.display="none";
	sLeft.style.display = 'block';
	right.style.width = '79%';
	if(document.all){
		right.style.marginLeft="0px";		
	}	
}
