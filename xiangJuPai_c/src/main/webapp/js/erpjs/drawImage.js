var flag=false;
//调用：<img src="图片" onload="javascript:DrawImage(this,'定义图片宽度','定义图片高度度')">
function DrawImage(ImgD,imgWidth,imgHeight){
	var image=new Image();
	var iwidth = imgWidth; //定义允许图片宽度，当宽度大于这个值时等比例缩小
	var iheight = imgHeight; //定义允许图片高度，当宽度大于这个值时等比例缩小
	image.src=ImgD.src;
	if(image.width>0 && image.height>0){
		flag=true;
		if(image.width/image.height>= iwidth/iheight){
			if(image.width>iwidth){ 
				ImgD.width=iwidth;
				ImgD.height=(image.height*iwidth)/image.width;
			}else{
				ImgD.width=image.width; 
				ImgD.height=image.height;
			}
			ImgD.alt=image.width+"×"+image.height;
		}else{
			if(image.height>iheight){ 
				ImgD.height=iheight;
				ImgD.width=(image.width*iheight)/image.height; 
			}else{
				ImgD.width=image.width; 
				ImgD.height=image.height;
			}
			ImgD.alt=image.width+"×"+image.height;
		}
	}
} 

