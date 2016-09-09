	function getImgType(oImgName, str){
		if(str.indexOf(".jpg")>0){
			oImgName.value=".jpg";
		}else if(str.indexOf(".jpeg")>0){
			oImgName.value=".jpeg";
		}else if(str.indexOf(".JPG")>0){
			oImgName.value=".jpg";
		}else if(str.indexOf(".gif")>0){
			oImgName.value=".gif";
		}else if(str.indexOf(".GIF")>0){
			oImgName.value=".GIF";
		}else if(str.indexOf(".png")>0){
			oImgName.value=".png";
		}else{
			Dialog.alert("您使用了不支持的图片类型!","body");
			return false;
		}
		return true;
	}