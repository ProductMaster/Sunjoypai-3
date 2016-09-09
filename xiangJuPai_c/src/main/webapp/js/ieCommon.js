/*****************************************************************************
 *
 *                      FORNOW PROPRIETARY INFORMATION
 *
 *          The information contained herein is proprietary to ForNow
 *           and shall not be reproduced or disclosed in whole or in part
 *                    or used for any design or manufacture
 *              without direct written authorization from ForNow.
 *
 *            Copyright (c) 2014 by ForNow.  All rights reserved.
 *
 *****************************************************************************/
/**
 * 公共工具类
 * 
 * @author Jiafa Lv
 * @email simon-jiafa@126.com
 * @date 2014-5-28 下午12:56:19
 */
var IECommon = function() {
	function parseJson(text) {
		try {
			return JSON.parse(text);// ie 89 ff ch
		} catch (e) {
			return eval('(' + text + ')'); // ie7
		}
	}

	function json2str(jsonObj) {
		try {
			return JSON.stringify(jsonObj);// ie 89 ff ch
		} catch (e) {
			return jsonObj.toJSONString(); // ie7
		}
	}

	return {
		/**
		 * AJAX基础访问方法
		 * 
		 * @param args
		 * @returns
		 */
		ajaxCall : function(args) {
			var defaults = {
				"success" : function() {
				},
				"erorr" : function() {
				},
				"data" : {},
				"timeout" : 30000,
				"headers" : {
					"Content-Type" : "application/json",
					"Accept" : "application/json"
				}
			};

			args = $.extend(true, defaults, args);

			var dataStr = "";

			return $.ajax({
				url : args.uri + '?' + Math.floor(Math.random() * 100),
				type : args.type,
				headers : args.headers,
				timeout : args.timeout,
				data : json2str(args.data),
				success : args.success,
				error : args.error
			});
		},

		/**
		 * 获得CHECKBOX集合数据
		 * 
		 * @param ckName
		 * @returns
		 */
		getCheckboxVals : function(ckName) {
			var ckValAry = [];
			$("input[name='" + ckName + "']:checkbox").each(function() {
				if ($(this).attr("checked")) {
					ckValAry.push(parseInt($(this).val()));
				}
			});

			return ckValAry;
		},

		/**
		 * 网址验证
		 * 
		 * @param url
		 * @returns
		 */
		//
		doWebSite : function(website) {
			var reg = /^([hH][tT]{2}[pP]:\/\/|[hH][tT]{2}[pP][sS]:\/\/)(([A-Za-z0-9-~]+)\.)+([A-Za-z0-9-~\/])+$/;
			return reg.test(website);
		},

		/**
		 * 手机固话验证
		 * 
		 * @param phoneNum
		 * @returns
		 */
		doPhoneNum: function(phoneNum){
			//var result = /(^([0-9][0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$)|(^((\(\d{3}\))|(\d{3}\-))?(1[358]\d{9})$)/;
			var phoneRegexp=/^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
			var mobileRegexp=/^0{0,1}(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])[0-9]{8}$/;
			var isCheck = false;
			if (phoneRegexp.test(phoneNum)) {
				isCheck = true;
			} else {
				if (mobileRegexp.test(phoneNum)) {
					isCheck = true;
				}
			}
			return isCheck;
		},

		/**
		 * 数字验证
		 * 
		 * @param num
		 * @returns
		 */
		doNumCheck : function(num) {
			var regs = /^[0-9]+$/;
			return regs.test(num);
		},

		/**
		 * 数字加小数位验证
		 * 
		 * @param num
		 * @returns
		 */
		doDecimalCheck : function(num) {
			var regs = /^\d+\.?\d*$/;
			return regs.test(num);
		},

		/**
		 * 是否超过长度限制
		 * 
		 */
		isMaxLen : function(val, len) {
			val = $.trim(val);
			if (val && val.length > len) {
				return false;
			}

			return true;
		},

		/**
		 * 判断中英文字符长度
		 */
		strlen : function(str) {
			var len = 0;
			for ( var i = 0; i < str.length; i++) {
				var c = str.charCodeAt(i);

				if ((c >= 0x0001 && c <= 0x007e)
						|| (0xff60 <= c && c <= 0xff9f)) {
					// 英文字节加1
					len++;
				} else {
					// 中文字节加2
					len += 2;
				}
			}
			return len;
		},

		/**
		 * 身份证验证
		 * 
		 * @param num
		 * @returns
		 */
		isIDCard : function(str) {
			var regs = /^[1-9]\d{16}[\d|x|X]$/;
			return regs.test(str);
		},

		/**
		 * Email验证
		 * 
		 * @param str
		 * @returns
		 */
		isEmail : function(str) {
			var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
			return reg.test(str);
		},

		/**
		 * 是否含有特殊字符
		 * 
		 * @param str
		 * @returns
		 */
		isSpecial : function(str) {
			var pattern = new RegExp("[~'!@#$%^&*()-+_=:]");
			return pattern.test(str);
		},

		/**
		 * 只能包含数字，大小写字母
		 * 
		 * @param str
		 * @returns
		 */
		isNumberLetter : function(str) {
			var pattern = /^[0-9a-zA-Z]*$/g;
			return pattern.test(str);
		},
		
		/**
		 * 组装院校排名信息
		 * 
		 * @param data
		 * @returns
		 */
		doGetForeignSchoolType : function(data, objId, typeId) {
			if (data.length > 0) {
				$("#" + objId).append($("<option value='-99'>-排名类型-</option>"));
				for ( var i = 0; i < data.length; i++) {
					var optVal = "<option value='" + data[i].fstypeId+"' ";
					if(typeId === data[i].fstypeId){
						optVal += "selected";
					}
					optVal += ">" + data[i].fstypeName + "</option>";
					
					$("#" + objId).append($(optVal));
				}
			} else {
				$("#" + objId).append($("<option value='-99'>-无-</option>"));
			}
		},

		/**
		 * 组装城市信息
		 * 
		 * @param data
		 * @returns
		 */
		doGetCity : function(data, objId) {
			if (data.length > 0) {
				$("#" + objId).append($("<option value=''>-请选择城市-</option>"));
				for ( var i = 0; i < data.length; i++) {
					$("#" + objId).append(
							$("<option value=" + data[i].cityId + " title= "
									+ data[i].chineseName + ">"
									+ data[i].chineseName + "</option>"));
				}
			} else {
				$("#" + objId).append($("<option value='-1'>-无-</option>"));
			}
		},

		/**
		 * 组装国外州信息
		 * 
		 * @param data
		 * @returns
		 */
		doGetState : function(datas, objId) {
			var data = datas.datas;
			if (data.length > 0) {
				$("#" + objId).append($("<option value=''>-请选择州-</option>"));
				for ( var i = 0; i < data.length; i++) {
					$("#" + objId).append(
							$("<option value=" + data[i].id + " title= "
									+ data[i].stateCname + ">"
									+ data[i].stateCname + "</option>"));
				}
			} else {
				$("#" + objId).append($("<option value='-1'>-无-</option>"));
			}
		},

		/**
		 * 组装国外城市信息
		 * 
		 * @param data
		 * @returns
		 */
		doGetForeignCity : function(datas, objId) {
			var data = datas.datas;
			if (data.length > 0) {
				$("#" + objId).append($("<option value=''>-请选择城市-</option>"));
				for ( var i = 0; i < data.length; i++) {
					$("#" + objId).append(
							$("<option value=" + data[i].id + " title= "
									+ data[i].cityCname + ">"
									+ data[i].cityCname + "</option>"));
				}
			} else {
				$("#" + objId).append($("<option value='-1'>-无-</option>"));
			}
		},

		/**
		 * 组装国外学校信息
		 * 
		 * @param data
		 * @returns
		 */
		doGetForeignSchool : function(datas, objId) {
			var data = datas.datas;
			if (data.length > 0) {
				$("#" + objId).append($("<option value=''>-请选择学校-</option>"));
				for ( var i = 0; i < data.length; i++) {
					$("#" + objId).append(
							$("<option value=" + data[i].id + " title= "
									+ data[i].schoolCname + ">"
									+ data[i].schoolCname + "</option>"));
				}
			} else {
				$("#" + objId).append($("<option value='-1'>-无-</option>"));
			}
		},
		
		/**
		 * 组装专业信息
		 * 
		 * @param data
		 * @returns
		 */
		doGetForeignMajor : function(datas, objId) {
			var data = datas.datas;
			if (data.length > 0) {
				$("#" + objId).append($("<option value=''>-请选择-</option>"));
				for ( var i = 0; i < data.length; i++) {
					$("#" + objId).append(
							$("<option value=" + data[i].id + " title= "
									+ data[i].majorCname + ">"
									+ data[i].majorCname + "</option>"));
				}
			} else {
				$("#" + objId).append($("<option value=''>-无-</option>"));
			}
		},
		
		/**
		 * 组装国外学校院系专业信息
		 * 
		 * @param data
		 * @returns
		 */
		doGetForeignDiscipline : function(data, objId) {
			if (data.length > 0) {
				$("#" + objId).append($("<option value=''>-专业-</option>"));
				for ( var i = 0; i < data.length; i++) {
					$("#" + objId).append(
							$("<option value=" + data[i].fdisciplineId + " title= "
									+ data[i].fdisciplineEname + ">"
									+ data[i].fdisciplineEname + "</option>"));
				}
			} else {
				$("#" + objId).append($("<option value=''>-无-</option>"));
			}
		},
		
		/**
		* 组装国外学校院系信息
		* 
		* @param data
		* @returns
		*/
		
		doGetForeignFaculty : function(data, objId) {
			if (data.length > 0) {
				$("#" + objId).append($("<option value=''>-院系-</option>"));
				for ( var i = 0; i < data.length; i++) {
					$("#" + objId).append(
							$("<option value=" + data[i].ffacultyId + " title= "
									+ data[i].ffacultyEname + ">"
									+ data[i].ffacultyEname + "</option>"));
				}
			} else {
				$("#" + objId).append($("<option value='-1'>-无-</option>"));
			}
		},

		/**
		 * 组装机构信息
		 * 
		 * @param data
		 * @returns
		 */
		doGetAgent : function(data, objId) {
			if (data.length > 0) {
				$("#" + objId).append($("<option value=''>-所属机构-</option>"));
				for ( var i = 0; i < data.length; i++) {
					$("#" + objId).append(
							$("<option value=" + data[i].agentId + " title= "
									+ data[i].agentName + ">"
									+ data[i].agentName + "</option>"));
				}
			} else {
				$("#" + objId).append($("<option value=''>-无-</option>"));
			}
		},

		/**
		 * 组装客服信息
		 * 
		 * @param data
		 * @returns
		 */
		doGetCustom : function(data, objId) {
			if (data.length > 0) {
				$("#" + objId).append($("<option value=''>-客服-</option>"));
				for ( var i = 0; i < data.length; i++) {
					$("#" + objId).append(
							$("<option value=" + data[i].fnuserId + " title= "
									+ data[i].nickname + ">" + data[i].nickname
									+ "</option>"));
				}
			} else {
				$("#" + objId).append($("<option value='-1'>-无-</option>"));
			}
		},

		/**
		 * 基本地址访问
		 * 
		 * @param url
		 * @returns
		 */
		doHref : function(url) {
			window.location.href = url;
		},

		/**
		 * 基本PUSH方法
		 * 
		 * @param args
		 * @returns
		 */
		doPush : function(args) {
			$IEC.ajaxCall({
				success : function(data) {
					alert(args.sucMsg);
				},
				error : function(data) {
					alert(args.eroMsg);
				},
				uri : args.uri,
				type : args.type
			});
		},
		doFileName : function(uploadFile, names) {
			var name = $("#" + uploadFile).val();
			name = name.substring(name.lastIndexOf("\\") + 1, name
					.lastIndexOf("."));
			if ($IEC.strlen(name) > 20) {
				name = name.substring(0, 20);
			}
			$("#" + names).val(name);
		},
		/**
		 * 删除之前需要确认
		 * 
		 * @param url
		 * @returns
		 */
		doDelete : function(url) {
			if (confirm("确定要删除该记录？")) {
				window.location.href = url;
			}
		}
	};
}();
window.$IEC = IECommon;