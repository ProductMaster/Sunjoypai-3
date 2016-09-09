var PATH = '../scripts/';

var configData = {
	base:'./scripts/',
	alias:{
		'jquery':'./utils/jquery-1.11.1.min.js',
		'template':'./utils/template-native.js',
		'template2':'./utils/template.js',
		'layer':'./utils/layer/layer.js',
		'upload':'./utils/ajaxfileupload.js'
	}
}
seajs.config(configData);
