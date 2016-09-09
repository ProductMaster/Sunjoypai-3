var msg=new Object();
msg.TEXT_FORMAT = 1;
msg.XML_FORMAT = 2;



/*
 * Returns a new XMLHttpRequest object, or false if this browser
 * doesn't support it
 */
function createXMLHttpRequest(){
    var xmlreq = false;
    if (window.XMLHttpRequest)
    {
        xmlreq = new XMLHttpRequest();
    }else if(window.ActiveXObject)
    {
        try
        {
        	xmlreq = new ActiveXObject("Msxml2.XMLHTTP");
    		}
    		catch (e1)
    		{
        		try
        		{
        				xmlreq = new ActiveXObject("Microsoft.XMLHTTP");
        		}
        		catch (e2)
        		{
        				alert("Your browser does not permit the use of all "+
              "of this application's features!");
        		}
    		}
  	}
  	return xmlreq;
}

//onreadystatechange：可以指定一个事件处理函数来处理XMLHttpRequest对象的执行结果

function sendRequest(request, handler, reqType, url, isAsyn){
	try{
		url +="&timeStamp="+new Date().getTime();
		request.onreadystatechange=responseHandler(request, handler);
		request.open(reqType,url,isAsyn);
		request.send(null);
		//alert("sendRequest");
	}catch(e){
		alert("The application cannot contact the server "+
          "at the moment. "+
          "Please try again in a few seconds." );
  }
}

/*****************************************************************************/


/**	request: 指定请求的类型，一般为get和Post
	url：	 指定请求的地址，可以是绝对地址和相对地址，可以附带查询字符串	
	isAsyn：  可选参数，表示请求是同步还是异步，默认为true
	
*/
/** send Get Request **/	
function sendGetRequest(request, handler, url, isAsyn, oSource, oTarget)
{
	try
	{
		url +="&timeStamp="+new Date().getTime();
		request.onreadystatechange=responseHandler(request, handler, oSource, oTarget);
		request.open("GET", url, isAsyn);//创建一个请求，并想服务器发送该请求
		//logger.append("[send request][URL]"+url);
		request.send(null);
	}catch(e){
		alert("The application cannot contact the server "+ "at the moment. "+"Please try again in a few seconds." );
  }
}

/** send Post Request **/
function sendPostRequest(request, handler, url, post_data, isAsyn, oSource, oTarget){
	try{
		url += "?timeStamp="+new Date().getTime();
		request.onreadystatechange=responseHandler(request, handler, oSource, oTarget);
		request.open("POST", url, isAsyn);
		request.setRequestHeader("Content-type","application/x-www-form-urlencoded;");
		//logger.append("[send request][URL]"+url);
		request.send(post_data);

	}catch(e){
		alert("无法与服务器进行连接，请过一会再试，或与客服联系! ");
  }
}

/** XML Format data handler**/
function responseHandler(req, responseHandler, oSource, oTarget) {

	// Return an anonymous function that listens to the
	// XMLHttpRequest instance
  	return function(){
    	try{
	    // If the request's status is "complete"
  	  	if(req.readyState == 4){//数据已接收成功
    	    // Check that a successful server response was received
      	   	if (req.status == 200) {//请求成功
        		// Pass the XML payload of the response to the
        		// handler function
        		// logger.append("[return Ajax][responseXMLHandler]");
        		//var jsonTextObj=eval("("+req.responseText+")");
        		//logger.append("Flag:"+jsonTextObj.flag);
        		//logger.append("returnData:"+jsonTextObj.returnData);
        		//logger.append("go back");
        		//logger.append(req.responseText);
        		checkXMLParseError(req.responseXML);
          		responseHandler(req, oSource, oTarget);
      	   	}else {
          		// An HTTP problem has occurred
          		alert("与服务器通讯时发生错误，错误码："+req.status);
      	   	}
    	}
    } catch (err) {
        alert("It does not appear that the server is available "+
              "for this application. Please"+
              " try again very soon. \\nError: "+err.message);
		}

  }
}
function getFormAsString(formName){

	returnString = "timeStamp="+new Date().getTime();
	formElements=document.forms[formName].elements;

	for(var i=formElements.length-1;i>=0; --i ){
		returnString+="&"
		+formElements[i].name+"="
		+formElements[i].value;
	}
	return returnString;
}

function checkXMLParseError(oDoc){
    var parseErrorText = "OK";
    var errFlag = 0;
    if(!oDoc.documentElement){
        parseErrorText = "";
    } else if(oDoc.documentElement.tagName == "parsererror"){
        parseErrorText = oDoc.documentElement.firstChild.data;
        parseErrorText += "\n" +  oDoc.documentElement.firstChild.nextSibling.firstChild.data;
        errFlag = 1;
    } else if(oDoc.getElementsByTagName("parsererror").length > 0){
        var parsererror = oDoc.getElementsByTagName("parsererror")[0];
        parseErrorText = Sarissa.getText(parsererror, true)+"\n";
        errFlag = 1;
    } else if(oDoc.parseError && oDoc.parseError.errorCode != 0){
        parseErrorText = "UNKNOWN_ERROR";
        errFlag = 1;
    };
    if(errFlag == 1){
	    alert(parseErrorText);
    }
}


/** TEXT Format data handler**/
//function responseTextHandler(req, handler) {

	// Return an anonymous function that listens to the
	// XMLHttpRequest instance
 // 	return function(){
//    	try{
	    // If the request's status is "complete"
//  	  	if(req.readyState == 4){
    	    // Check that a successful server response was received
//      	   	if (req.status == 200) {
        		// Pass the XML payload of the response to the
        		// handler function
//        		logger.append("[return Ajax][responseTEXTHandler]");
//        		var jsonTextObj=eval("("+req.responseText+")");
//        		logger.append("Flag:"+jsonTextObj.flag);
//        		logger.append("returnData:"+jsonTextObj.returnData);


//          		handler(req);
//      	   	}else {
          		// An HTTP problem has occurred
//          		alert("A problem occurred with communicating between the "+
//               	"XMLHttpRequest object and the server program.");
//      	   	}
//    	}
//    } catch (err) {
//        alert("It does not appear that the server is available "+
//              "for this application. Please"+
//              " try again very soon. \\nError: "+err.message);
//		}
//  }
//}
