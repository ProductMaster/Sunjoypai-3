
Array.prototype.toTRACKERJSONString=function()
{
var jsonStr="[";
for(var i=0;i<this.length;i++)
 {
    if(this[i] instanceof Parameter)
    {
    if(this[i].value instanceof Array)
      {
      jsonStr+="{"+this[i].key+"="+this[i].value.toTRACKERJSONString()+"}"+",";
      }
    else {
      jsonStr+=this[i].toJSONString()+",";
      }
    }
 }
if(jsonStr.indexOf(",")>0)
jsonStr=jsonStr.substring(0,jsonStr.length-1);
return jsonStr+"]";

};

var page_locationurl= window.location.href;
var trackerUrl="";
var URLPrefix={central:'http://www.yhd.com',mall:'http://www.1mall.com',tracker: 'tracker.yhd.com'};
var no3wUrl="m.yhd.com";

var tmpurl= window.location.href;
if(tmpurl.indexOf("1mall.com")!=-1){
	no3wUrl="m.1mall.com"
}

///////////////////�����װ����//////////////////////////
function Parameter(key,value)
{

this.key=key;
if(this.key=="internalKeyword"){
	this.value=encodeURI(value);
}else{
this.value=value;
}

this.toJSONString=function()
{
return "{"+this.key+"="+this.value+"}";
};

}
var linkPosition="";
var buttonPosition="";

///////////////////�����װ����ٿ�����//////////////////////////

function TrackerContainer(url)
{
this.url=url;
this.parameterArray=new Array();
this.stockArray=new Array();
this.commonAttached=new Array();//��ͨ�ĸ��Ӷ���key->value��


this.addParameter=function(parameter)
  {
  
  this.parameterArray.push(parameter);
  };
 this.addStock=function(productId,stockInfo) //���ӿ��
  {
   this.stockArray.push(new Parameter(productId,stockInfo));
  };
  this.addCommonAttached=function(key,value) //������ͨ�ĸ�����Ϣ
  {
  this.commonAttached.push(new Parameter(key,value));
  };



//����������Ϣ
this.buildAttached=function()
{
 if(this.stockArray.length>0)
 this.commonAttached.push(new Parameter("1",this.stockArray));
 if(this.commonAttached.length>0)
 this.addParameter(new Parameter("attachedInfo", this.commonAttached.toTRACKERJSONString("attachedInfo")));//���ӿ����Ϣ
};


this.toUrl=function()
  {
   this.buildAttached();
   for(var i=0;i<this.parameterArray.length;i++)
      {
      var key=this.parameterArray[i].key;
      var value=this.parameterArray[i].value;
      this.url+="&"+key+"="+value;
      }
    trackerUrl=this.url;
    return this.url;  
  };  
}

var trackerUrl=('https:'== document.location.protocol ? 'https://' : 'http://') + URLPrefix.tracker+'/tracker/info.do?1=1';



var trackerContainer=new  TrackerContainer(trackerUrl);

trackerContainer.addParameter(new Parameter("ieVersion",navigator.appVersion));
trackerContainer.addParameter(new Parameter("platform",navigator.platform));
var page_refer=document.referrer?document.referrer:"";
var page_location= window.location.href;



function addTrackPositionToCookie(type,position){

	if(type=="1"){
		document.cookie="linkPosition="+encodeURIComponent(position)+";path=/;domain=."+no3wUrl+";";
		
	}else if(type=="2"){
		document.cookie="buttonPosition="+decodeURIComponent(position)+";path=/;domain=."+no3wUrl+";";
		
	}else if(type=="3"){
		document.cookie="pmId="+decodeURIComponent(position)+";path=/;domain=."+no3wUrl+";";
		
	}
}
function getCookies(name){
    var strCookie=document.cookie;
    var arrCookie=strCookie.split("; ");
    for(var i=0;i<arrCookie.length;i++){
        var arr=arrCookie[i].split("=");
        if(arr[0]==name)return arr[1];
    }
    return null;
}

 



function gotracker(type,position,productId){
	var url=trackerUrl;
	//���֮ǰ�����¼�Ĳ���
	var reg=new RegExp("&linkPosition=\\w*","g"); 
	url=url.replace(reg,'');
	var reg=new RegExp("&buttonPosition=\\w*","g");
	url=url.replace(reg,'');
	var reg=new RegExp("&productId=\\w*","g");
	url=url.replace(reg,'');
	if(type!=null){
		if(type==2){
			url=url+"&buttonPosition="+position;		
		}else if(type==3){
			url=url+"&extField7="+position;
		}else{
			url=url+"&linkPosition="+position;
		}
	}
	if(productId!=null){
		url=url+"&productId="+productId;
	}
	jQuery.ajax({ 
	    async:true, 
	    url: url,  
	    type: 'GET',   
	    dataType: 'jsonp', 
	    jsonp: 'jsoncallback'
	});
}

//add to cartͳ�Ƽ�pmId
function addcartStatistics(pmId){
	addTrackPositionToCookie("3",pmId);
	var url=trackerUrl;
	
	//���֮ǰ�����¼�Ĳ���
	var reg=new RegExp("&buttonPosition=\\w*","g");
	url=url.replace(reg,'');
	var reg=new RegExp("&extField7=\\w*","g");
	url=url.replace(reg,'');
	
	url=url+"&extField7="+pmId;	
	url=url+"&buttonPosition=cartadd";
	$.ajax({ 
	    async:true, 
	    url: url,  
	    type: 'GET',   
	    dataType: 'jsonp', 
	    jsonp: 'jsoncallback'
	});
}

// ָ��DIV�������������tracker����. textPrefixΪtrackerʶ�������ǰ׺
function bindLinkClickTracker(divId, textPrefix) {
	var links = jQuery("#"+divId+" a");
	links.click(function(){
		var text = jQuery(this).text();
		text = textPrefix +"_"+ encodeURIComponent(jQuery.trim(text));
		//alert(text);
		addTrackPositionToCookie('1', text);
	});
}



function callLoadCookie(){
	
	//���ڵ�tracker��cookie����1���̳�ʱ��1�ŵ겢������ͬ��
	//����ж���һҳΪ1���̳�,��ǰҳΪ1�ŵ�ʱ����������ķ�����1���̳���trackerд���cookieͬ����1�ŵ���
	
	var url="";
	if(page_location.indexOf("yhd.com")!=-1&&page_refer.indexOf("1mall.com")!=-1){
		url=URLPrefix.mall;
	} else if(page_location.indexOf("1mall.com")!=-1&&page_refer.indexOf("yhd.com")!=-1){
		url=URLPrefix.central;
	}
		if(url!=""){
			if('https:'== document.location.protocol){
				url=url.replace("http", "https");
			}
		jQuery.getJSON(url+"/yhd-common/assign-login-api.do?" +
				"cookieNames=unionKey&cookieNames=adgroupKeywordID&cookieNames=unionType" +
				"&cookieNames=uid&cookieNames=websiteid" +
				"&callback=?",
				 function(data){
					if(!data.ERROR){
							jQuery(data.info).each(function(){
								if(this.value!=""){
								jQuery.cookie(this.name,this.value,{domain:no3wUrl,path:"/"});
								
								}
							});
							
					}
					
				});
		}
}


function callLoadlinkCookie(){
	// ���ڵ�tracker��cookie����1���̳�ʱ��1�ŵ겢������ͬ��
	// ����ж���һҳΪ1���̳�,��ǰҳΪ1�ŵ�ʱ����������ķ�����1���̳���trackerд���cookieͬ����1�ŵ���
	var url="";
	if(page_location.indexOf("yhd.com")!=-1){
		url=URLPrefix.mall;
	} else if(page_location.indexOf("1mall.com")!=-1){
		url=URLPrefix.central;
	}
	
	if(url!=""){
//			var killFlagA = true;
//			
//			setTimeout(function() {
//					killAjaxThreadA();
//			}, 20000); 
			if('https:'== document.location.protocol){
				url=url.replace("http", "https");
			}
			
			var ajaxUrl = url +"/yhd-common/assign-login-api.do?"
            				  +"&cookieNames=linkPosition&cookieNames=buttonPosition"
            				  +"&callback=?";
			jQuery.ajax({
				async:true, 
	            url: ajaxUrl,  
	            timeout: 1000,
	            type: 'GET',   
	            dataType: 'jsonp', 
	            jsonp: 'jsoncallback',
	            success: function(data){
	            	if(!data.ERROR){
						jQuery(data.info).each(function(){
//							killFlagA = false;
							if(this.value!=""){
								var cookie="";
								trackerContainer.addParameter(new Parameter(this.name,this.value));
								cookie="cookieDatas="+this.name+',,'+this.value+",,0";
							    callAddCookieApi(url, cookie);
							}	
						});
					}		
	            },
	            complete: function(){
	            	initHijack();
	            },
			    statusCode : {
			      404 : function() {
					      initHijack();
			            }
		        }
			});

	}else{
       initHijack();
    }

}

function callAddCookieApi(url, cookieName){
//	var killFlagB = true;
//	
//	setTimeout(function() {
//			killAjaxThreadB();
//	}, 1); 
	
	jQuery.ajax({
		async:true, 
        url: url+'/yhd-common/cookie-set-api.do?'+ cookieName + "&callback=?",  
        timeout: 1000,
        type: 'GET',   
        dataType: 'jsonp', 
        jsonp: 'jsoncallback',
        success: function(data){
        }
	});
	
//	var ajaxThreadB=jQuery.getJSON(url+'/yhd-common/cookie-set-api.do?'+ cookieName + "&callback=?",
//	function(data){
//		if(!data.ERROR){
//			killFlagB = false;
//		}
//	});		
	
//	function killAjaxThreadB(){
//		if(killFlagB){
//			ajaxThreadB.abort();
//		}
//	}
}


callLoadCookie();
