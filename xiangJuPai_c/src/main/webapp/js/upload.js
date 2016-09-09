define(function(require){
	$ = jQuery = require('jquery');
	
	var datas;
	var picList = [];
	
	$('#upload').on('click',function(){
		////submit
	})
	
	$('#addBtn').on('click',function(){
		$('input[type="file"]').click();
	});
	
	$('input[type="file"]').change(function(e){
		var reader = new FileReader();
	    reader.onload = function(){
	        var image = new Image();
	        image.onload = function(){
	            addPicToS(image);
	        };
	        datas = this.result;
	        image.src=this.result;
	        picList.push(datas);
	
	    };
	    var file =e.target.files||e.dataTransfer.files;
	    reader.readAsDataURL(file[0]);
	});
	
	function addPicToS(image){
		$(image).insertBefore('#addBtn');
	}
})
