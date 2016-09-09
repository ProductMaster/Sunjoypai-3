<%@ page language="java" pageEncoding="UTF-8"%>

<script type="text/javascript">
<!--
	var basePath = "${pageContext.request.contextPath}";
//-->
</script>

<script type="text/javascript" src="${pageContext.request.contextPath}/console/js/jquery.js"></script>

<script src="${pageContext.request.contextPath}/console/js/layer/jquery-1.8.0.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/console/js/layer/layer.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/console/js/layer/extend/layer.ext.js" type="text/javascript"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/console/js/twitter/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/console/js/twitter/bootstrap-modal.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/console/js/twitter/bootstrap-tooltip.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/console/js/plugins/validate/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/console/js/plugins/validate/jquery.metadata.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/console/js/plugins/validate/messages_cn.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/console/js/hubSpot/build/js/messenger.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/console/js/date/WdatePicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/console/js/impromptu/jquery-impromptu.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("#validateForm").validate();
		$("button").filter(".historyBackClass").each(function(i){
			$(this).bind("click", function(){
				//window.history.go(-1);
				javascript:history.back(); 
			});
		});
	});
		var msg;
		function message(msg) {
			if (msg.length > 0) {
				msg = $.globalMessenger().post({
					message : msg,
					actions : {
						cancel : {
							label : '关闭',
							action : function() {
								return msg.cancel();
							}
						}
					}
				});
			}
		}
		
</script>
