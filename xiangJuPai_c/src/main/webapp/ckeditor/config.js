/*
Copyright (c) 2003-2011, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config )
{
	config.language= 'zh-cn'; 
	config.skin = 'kama';
	config.font_names = '微软雅黑;宋体;黑体;Arial';
	// config.uiColor = '#AADC6E';
	
	/* 定义工具栏 */ 
	config.toolbar = 'Define'; 
    config.toolbar_Define = [
       ['Source', '-', 'NewPage', 'Preview', '-', 'Templates'],
       ['Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'Print', 'Image'],
       ['Undo', 'Redo', '-', 'Find', 'Replace', '-', 'SelectAll', 'RemoveFormat'],
       '/',
       ['Bold', 'Italic', 'Underline', 'Strike', '-', 'Subscript', 'Superscript'],
       ['NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', 'Blockquote'],
       ['JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock'],
       ['Link', 'Unlink', 'Anchor'],
       ['Table', 'HorizontalRule', 'Smiley', 'SpecialChar', 'PageBreak'],
       '/',
       ['Styles', 'Format', 'Font', 'FontSize'],
       ['TextColor', 'BGColor']
    ];
    
    /* 上传配置 */  
    config.filebrowserUploadUrl 	 = '/social/albumPic/upload?type=File';				// 文件上传Url  
    config.filebrowserImageUploadUrl = '/social/albumPic/upload?type=Image';  		// 图片上传Url
    config.filebrowserFlashUploadUrl = '/social/albumPic/upload?type=Flash';  		// flash上传Url
    // config.filebrowserImageBrowseUrl = '/social/albumPic/browerServer?type=image';		// 图片浏览
};
