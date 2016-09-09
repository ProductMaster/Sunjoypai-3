//表单验证，统一入口
function VaildForm(theForm){
	var succ=true;
	for(var i=0;i<theForm.elements.length>0;i++){
		var oField=theForm.elements[i];
		if(oField.type=="text"){
			var _dataType = oField.getAttribute("dataType");
			if(typeof(_dataType)== "undefined")	continue;
			switch(_dataType){
				case "commonChar"://不能为空白
					succ=commonChar(oField);
					break
				case "email":
					succ=email(oField);
					break;
				case "email2"://可以为空，不为空必须为Email格式
					succ=email2(oField);
					break;
				case "postal":
					succ=postal(oField);
					break;
				case "postal2"://可以为空，不为空必须为六位邮编
					succ=postal2(oField);
					break;
				case "phone":
					succ=phone(oField);
					break;
				case "phone2"://可以为空，不为空必须为固定电话号码格式
					succ=phone2(oField);
					break;
				case "mobile":
					succ=mobile(oField);
					break;
				case "mobile2"://可以为空，不为空必须为手机号码格式
					succ=mobile2(oField);
					break;
				case "number":
					succ=number(oField);
					break;
				case "number2"://可以为空，不为空必须为数字
					succ=number2(oField);
					break;	
				case "decimal":
					succ=decimal(oField);
					break;
				case "url":
					succ=url(oField);
					break;
				
			}
		}
		if(oField.type=="password"){
			var _dataType = oField.getAttribute("dataType");
			if(typeof(_dataType)== "undefined")	continue;
			succ=passwd(oField);
		}
		if(!succ) 
		{
			oField.focus(); 
			break;
		}
	}
	return succ;
}

function disMsg(obj){
	var msgId=obj.getAttribute("msgId");
	if(typeof(msgId)!= "undefined"){
		document.getElementById(msgId).style.display="block";
	}
}

function clsMsg(obj){
	var msgId=obj.getAttribute("msgId");
	if(typeof(msgId)!= "undefined"){
		document.getElementById(msgId).style.display="none";
	}
}

//============================  类型入口
//通用类型判断入口
function commonChar(obj){
	if(IsEmpty(obj.value)){
		disMsg(obj);
		return false;
	}
	var _minLength = obj.getAttribute("minLength");
	if(typeof(_minLength) != "undefined"&&obj.value.length<_minLength){
		disMsg(obj);
		return false;
	}
	return true;
}

//邮件类型判断入口1（必填项验证）function email(obj){
	var mail = obj.value;
	if(IsEmpty(mail) || !IsMail(mail)){
		disMsg(obj);
		return false;
	}
	return true;
}
//邮件类型判断入口2
function email2(obj){
	var mail = obj.value;
	if(!IsMail(mail)){
		disMsg(obj);
		return false;
	}
	return true;
}
//邮政编码类型判断入口1（必填项验证）
function postal(obj){
	var postal = obj.value;
	if(IsEmpty(postal) || !IsPostal(postal)){
		disMsg(obj);
		return false;
	}
	return true;
}
//邮政编码类型判断入口2
function postal2(obj){
	var postal = obj.value;
	if(!IsPostal(postal)){
		disMsg(obj);
		return false;
	}
	return true;
}
//固定电话类型判断入口1（必填项验证）
function phone(obj){
	var phone = obj.value;
	if(IsEmpty(phone) || !IsPhone(phone)){
		disMsg(obj);
		return false;
	}
	return true;
}
//固定电话类型判断入口2
function phone2(obj){
	var phone = obj.value;
	if(!IsPhone(phone)){
		disMsg(obj);
		return false;
	}
	return true;
}
//手机类型判断入口1（必填项验证）
function mobile(obj){
	var mobile = obj.value;
	if(IsEmpty(mobile) || !IsMobile(mobile)){
		disMsg(obj);
		return false;
	}
	return true;
}
//手机类型判断入口2
function mobile2(obj){
	var mobile = obj.value;
	if(!IsMobile(mobile)){
		disMsg(obj);
		return false;
	}
	return true;
}
//密码类型判断入口
function passwd(obj){
	if(IsEmpty(obj.value)){
		disMsg(obj);
		return false;
	}
	return true;
}

//数字验证入口
function number(obj){
	var num = obj.value;
	if(IsEmpty(num) || !IsNumber(num)){
		disMsg(obj);
		return false;
	}
	return true;
}
//小数验证入口
function decimal(obj){
	var num = obj.value;
	if(IsEmpty(num) || !IsDecimal(num)){
		disMsg(obj);
		return false;
	}
	return true;
}


//url验证入口
function url(obj){
	var _url = obj.value;
	if(IsEmpty(_url) || !IsURL(_url)){
		disMsg(obj);
		return false;
	}
	return true;
}

function number2(obj){
	var num = obj.value;
	if(!IsNumber(num)){
		disMsg(obj);
		return false;
	}
	return true;
}

//select空判断入口
function selectNull(obj){
	var num = obj.value;
	if(num<0){
		disMsg(obj);
		return false;
	}
	return true;
}

//============================  工具方法
//去左空格;
function ltrim(s){
    return s.replace( /^\s*/, "");
}
//去右空格;
function rtrim(s){
    return s.replace( /\s*$/, "");
}
//去左右空格;
function trim(s){
    return rtrim(ltrim(s));
}
//=============================  有效性测试逻辑

//是否为空
function IsEmpty(_str){
    var tmp_str = trim(_str);
    return tmp_str.length == 0;
}

//是否有效的Email;
function IsMail(_str){
	if(IsEmpty(_str)) return true;
    var tmp_str = trim(_str);
    var pattern = /^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*$/;
    return pattern.test(tmp_str);
}
decimal
//是否有效的数字;
function IsNumber(_str){
	if(IsEmpty(_str)) return true;
    var tmp_str = trim(_str);
    var pattern = /^[0-9]/;
    return pattern.test(tmp_str);
}

//是否有效的小数;
function IsDecimal(_str){
	if(IsEmpty(_str)) return true;
    var tmp_str = trim(_str);
    var pattern = /^\d+(\.\d+)?$/;
    return pattern.test(tmp_str);
}

//是否有效的颜色值;
function IsColor(color){
    var temp=color;
    if (temp=="") return true;
    if (temp.length!=7) return false;
    return (temp.search(/\#[a-fA-F0-9]{6}/) != -1);
}

//是否有效的链接;
function IsURL(_str){
   if(IsEmpty(_str)) return true;
		    var tmp_str = trim(_str);
		    var pattern = /^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/;
		    return pattern.test(tmp_str);
}

//是否有效的手机号码;
function IsMobile(_str){
	if(IsEmpty(_str)){return true}
    var tmp_str = trim(_str);
    var pattern =/^((\(\d{2,3}\))|(\d{3}\-))?13|15\d{9}$/;
    return pattern.test(tmp_str);
}

//是否有效的电话号码;
function IsPhone(_str){
	if(IsEmpty(_str)){return true}
    var tmp_str = trim(_str);
    var pattern =/^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/;
    return pattern.test(tmp_str);
}

//有效的邮政编码function IsPostal(_str){
	if(IsEmpty(_str)){return true}
    var tmp_str = trim(_str);
    var pattern =/^[1-9 ]\d{5}$/;
    return pattern.test(tmp_str);
}

//textarea长度检测function IsOverLength(oInObj)
{
    var iMaxLen = parseInt(oInObj.getAttribute('maxlength'));
    var iCurLen = oInObj.value.length;
	return iCurLen > iMaxLen;
}

//密码强度检测function Evaluate(word)
{
    return word.replace(/^(?:([a-z])|([A-Z])|([0-9])|(.)){5,}|(.)+$/g, "$1$2$3$4$5").length;
}

//是否为数字function isInteger(strValue)    
  {  
      var   objExp     =   /(^-?\d\d*$)/;  
      return   objExp.test(strValue);  
  } 
  
//是否是货币
function isMoney(strValue,len)
{
	var objExp = /^(\+|-)?(0|[1-9]\d*)(\.\d*[0-9])?$/;
	var res = objExp.test(strValue);
	var res2 =true;
	if(strValue.indexOf('.')!=-1)
	{
		var strValue2 = strValue.split('.');
		if(strValue2[1].length>len)
		{
			res2 = false ;
		}
		else if(parseInt(strValue2[0])<0)
		{
			res2 = false;
		}
	}else{
		if(parseInt(strValue)<0)
		{
			res2 = false;
		}
	}
	return res&&res2;
}

//限制textarea的字数
function CountNodeNum(Obj) 
{ 
	var Str = Obj.value; 
	var Len = 0; 
	if (Str == "") 
	{ 
	document.getElementById("NodeInt").innerHTML = "0"; 
	document.getElementById("NodeCount").innerHTML = "2000"; 
	return; 
	} 
	Len = Str.length; 
	if (Len - 2000 > 0) 
	{ 
	document.getElementById("NodeInt").innerHTML = "2000"; 
	document.getElementById("NodeCount").innerHTML = "0"; 
	Obj.value = Str.substring(0,2000); 
	return; 
	} 
	document.getElementById("NodeInt").innerHTML = Len; 
	document.getElementById("NodeCount").innerHTML = 2000 - Len; 
} 
//表单是否重复提交
var SubmitCheck = 0;
function checkRepeat(){
	if (++SubmitCheck>1) 
    { 
        //alert('请勿重复提交！'); 
        return false; 
    }
    return true;
}