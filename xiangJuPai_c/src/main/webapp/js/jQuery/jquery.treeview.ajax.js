/*
 * Async Treeview 0.1 - Lazy-loading extension for Treeview
 * 
 * http://bassistance.de/jquery-plugins/jquery-plugin-treeview/
 *
 * Copyright (c) 2007 Jörn Zaefferer
 *
 * Dual licensed under the MIT and GPL licenses:
 *   http://www.opensource.org/licenses/mit-license.php
 *   http://www.gnu.org/licenses/gpl.html
 *
 * Revision: $Id$
 *使用说明：回调xml文档，需要回传text、id、expanded、classes、hasChildren;在调用页面中需要声明函数doAct(id);
 *与json版的功能相似，只是没有添加子列表，即只能回传一级目录
 *页面的调用：
 *$(document).ready(function(){
		$("#black").treeview({
			url: "http://localhost:8087/htmlCreator/ajax.html",
			click: function(event){
			alert(event.data.par);
			}
		})
	});
 */

;(function($) {

function load(settings, root, child, container) {
	//alert(settings.url1);
	$.ajax({
		   url: settings.url,
		   data: "root="+root,
		   success: function(data){
		   		$(data).find('entity').each(function(j){
			   		if($(this).find("id").text()>0){
			   			var current = $("<li/>").attr("id", $(this).find("id").text() || "").html("<span>" + $(this).find("text").text() + "</span>").appendTo(child);
						if ($(this).find("classes").text()) {
							current.children("span").addClass($(this).find("classes").text());
						}
						if ($(this).find("expanded").text()=="true") {
							current.addClass("open");
						}
						if ($(this).find("hasChildren").text()=="true") {
							var branch = $("<ul/>").appendTo(current);
							current.addClass("hasChildren");
						}else{
							current.css("cursor","pointer"); 
						}
						current.children("span").bind("click", {par: $(this).find("id").text()}, settings.click) ;
						
					}else{
						if ( child.parent().filter(":last-child").html()==child.parent().html() ){
						 	child.parent().attr('class','last');
						}else{
							child.parent().attr('class','');
						}
						child.parent().children("div").remove();
						child.parent().children("ul").remove();
					}
		   		})
		   		if(child.html()!=""){
					$(container).treeview({add: child});
				}
		   }
	}); 
	
}

var proxied = $.fn.treeview;
$.fn.treeview = function(settings) {
	if (!settings.url) {
		return proxied.apply(this, arguments);
	}
	var container = this;
	load(settings, "source", this, container);
	var userToggle = settings.toggle;
	return proxied.call(this, $.extend({}, settings, {
		collapsed: true,
		toggle: function() {
			var $this = $(this);
			if ($this.hasClass("hasChildren")) {
				var childList = $this.removeClass("hasChildren").find("ul");
				childList.empty();
				load(settings, this.id, childList, container);
			}
			if (userToggle) {
				userToggle.apply(this, arguments);
			}
		}
	}));
};

})(jQuery);