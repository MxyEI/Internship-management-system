function setCookie(name, value) {
	var Days = 30;
	var exp = new Date();
	exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
	document.cookie = name + "=" + escape(value) + ";expires="
			+ exp.toGMTString();
}

function getCookie(name) {
	var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
	if (arr = document.cookie.match(reg))
		return unescape(arr[2]);
	else
		return null;
}

function delCookie(name) {
	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
	var cval = getCookie(name);
	if (cval != null)
		document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
}

function checkCookie() {
	if (getCookie("username") == null || getCookie("id") == null) {
		alert("未登录!");
		window.location.href = "login.jsp";
	}
}

function clearCookie() {
	delCookie("username");
	delCookie("id");
	window.location.href = "login.jsp";
}

function getNowFormatDate() {
	var date = new Date();
	var seperator1 = "-";
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var strDate = date.getDate();
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}
	if (strDate >= 0 && strDate <= 9) {
		strDate = "0" + strDate;
	}
	var currentdate = year + seperator1 + month + seperator1 + strDate;
	return currentdate;
}

function StringBuffer() {
	this.__strings__ = new Array;
}

StringBuffer.prototype.append = function(str) {
	this.__strings__.push(str);
	return this;
};

StringBuffer.prototype.toString = function() {
	return this.__strings__.join("");
};

function getQueryStringByName(name) {
	var result = location.search.match(new RegExp(
			"[\?\&]" + name + "=([^\&]+)", "i"));
	if (result == null || result.length < 1) {
		return "";
	}
	return result[1];
};

function isNull(id) {
	if (id == null || (typeof (id) == "string" && id.trim().length == 0)) {
		return true;
	}
	return false;
}

function isNotNull(id) {
	return !isNull(id);
}