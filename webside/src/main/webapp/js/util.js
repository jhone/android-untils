function quote(str){
    return str !== null ? '"' + str + '"': '""';
}

function encode(str){
    if(typeof encodeURIComponent == "function") {
        return encodeURIComponent(str);
    }
    else{
        return escape(str);
    }
}

function appendArg(argName, argVal){
    if (argVal) {
        window.req_url += "&" + argName + "=" + argVal;
    }
}

function appendEncodeArg(argName, argVal){
    if (argVal) {
        appendArg(argName, encode(argVal));
    }
}

function outputAdsSrc(src) {
    src = src.substring(0, 2000);
    src = src.replace(/%\w?$/, "");
    document.write('<script language="JavaScript1.1" charset="utf-8" src=' + quote(src) + "><\/script>" );
}

function clear(){
    window.req_url = null;
    window.page_url = null;
    window.ad_num = null;
}

function checkYodaoSearch(num){
    var prob = num % 100;
    if ((prob >= 0) && (prob < 100) ){
		var vid = divIdPrefix + yodaoSearchDivId;
		var ele = document.getElementById(vid);
		if (ele != null) {
			renderAtYodaoSearch = true;
		}
    }
    return renderAtYodaoSearch;
}

function checkValidIds(){
	var validIds = new Array();
    var index = 0;
    for (var v = 11; v < 13; v++) {
        var vid = divIdPrefix + v;
        var ele = document.getElementById(vid);
        if (ele != null){
            validIds[index++]=v;
        }
    }
    return validIds;
}

function genRand(){
    var now = new Date().getTime();
    return now;
}

function getNumFromCookie(){
    var allCookies = document.cookie;
    var pos = allCookies.indexOf(cookieName + "=");
    if (pos == -1) {
        var rand = genRand();
        setCookie(rand);
        return rand;
    } else {
        var start = pos + cookieName.length + 1;
        var end = allCookies.indexOf(";", start);
        if (end == -1) {
            end = allCookies.length;
        }
        var value = allCookies.substring(start, end);
        return value;
    }
}

/*
function getUserId() {
	var search = userIdCookieName + "=";
	var c = document.cookie;
	var userId = null;
	if (c.length > 0) {
		offset = c.indexOf(search);
		if (offset != -1) {
			offset += search.length;
			end = c.indexOf(";",offset);
			if (end == -1)
				end = c.length;
			userId = unescape(c.substring(offset,end));
		}
	}
	if (userId == null) {
		userId = ((new Date().getTime() + Math.random()) + "").replace(/\./g,"");
		var expire = new Date(new Date().getTime() + 24*3600000);
		var ckv = userIdCookieName + "=" + userId + "; expires=" + expire.toGMTString() + "; path=/";
		document.cookie = ckv;
	}
	return userId;
}*/

function setCookie(value){
    var expire = new Date(new Date().getTime() + 24*3600000);
    var ckv = cookieName + "=" + value + "; expires="+expire.toGMTString() + "; path=/";
    document.cookie=ckv;
}

function sendAdRequest() {
    var id = -1;
    if (checkYodaoSearch(getNumFromCookie())) {
    	id = yodaoSearchDivId;
    } else {
		if (document.getElementById(divIdPrefix + defaultDivId) != null){
			id = defaultDivId;
		} else {
			var divs = checkValidIds();
            if (divs.length < 1) {
			    return;
			} else {
				id = divs[0];
			}
		}
    }
    adDivId = divIdPrefix + id;
	//var userId = getUserId();

	var randomNum = Math.ceil(Math.random()*100);
	if(randomNum <= 0){
		//forward qt164
		window.req_url = "http://impservice4.youdao.com/imp/request.s?";
		appendArg("adnum", 4);
	} else {
		window.req_url = "http://impservice.youdao.com/imp/request.s?";
		appendArg("adnum", 3);
	}

    //window.req_url = "http://impservice.youdao.com/imp/request.s?";
    appendEncodeArg("req", document.location);
    appendArg("syndid", id);
    appendArg("siteid", id);
    appendArg("action", "pv");
	//appendArg("userid", userId);

    outputAdsSrc(req_url);
    clear();
}

var cookieName = "__ead_rand_ad_cookie_name__";
var userIdCookieName = "__ead_userid_cookie_name__";
var renderAtYodaoSearch = false;
var divIdPrefix = "ead_ad_";
var defaultDivId = 13;
var yodaoSearchDivId = 14;
var adDivId = "";

sendAdRequest();
