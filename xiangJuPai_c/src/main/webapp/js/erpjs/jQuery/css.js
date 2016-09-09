$(document).ready(function() {
		$(":input").hover(
		  function () {
		    $(this).addClass("input_move");
		  },
		  function () {
		    $(this).removeClass("input_move");
		  }
		); 
		$(":input").focus(function() {
		    $(this).addClass("input_on");
		  })
		  $(":input").blur(function () {
		    $(this).removeClass("input_on");
		  }); 
});