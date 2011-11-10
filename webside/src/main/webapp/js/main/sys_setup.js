/**
 * created : 2011/02/16
 * author  : pyc
 * version : 1.0
 * desc    : get system language list  from server 
 * 
 */
var sys_languages = new Array();
var sys_themes = new Array();

jQuery(document).ready(function() {

	getSysLanaugaes();
	getThemes();

})

/**
 * get languages list  from server
 */
function getSysLanaugaes() {

	$.ajax( {
		type : "GET",
		url : "list_languages.do",
		data : {},
		datatype : "json",
		success : function(msg, textstatus) {

			sys_languages = msg;
			addLanguage();

		}//success~
	});//ajax~

}//fun getLanguages~

/**
 * get themes list from server 
 */
function getThemes() {

	$.ajax( {
		type : "GET",
		url : "list_themes.do",
		data : {},
		datatype : "json",
		success : function(msg, textstatus) {

			sys_themes = msg;
			addTheme();
		}//success~
	});//ajax~

}//fun getThemes~

/**
 * add to ui
 */
function addLanguage() {

	var now_language = $("#_language").val();
	$("#change_language").remove();
	//动态增加select控件的option项
	$.each(sys_languages, function(i, object) {

		var temp = document.createElement("OPTION");
		$("#sel_language").append(temp);
		temp.text = object.description;
		temp.value = object.language;
		if (object.language == now_language) {
			temp.selected = true;
		}
		// alert(object.description);
		});

	/** 
	 var bb = document.getElementById("sel_language");
	alert(bb);
	for(var i=0;i<sys_languages.length;i++)
	 { 
	    var cc = document.createElement("OPTION");
	    bb.appendChild(cc);
	    cc.value=sys_languages[i].language;
	    cc.text = sys_languages[i].description;
	  }

	for (x in sys_languages){
	   alert(sys_languages[x].language);
	   var oOption = document.createElement("OPTION");
	   
	   oOption.text=sys_languages[x].language;
	   oOption.value=sys_languages[x].description;
	   }


	 **/

}
/**
 * add theme list to ui
 */
function addTheme() {

	var now_theme = $("#_theme").val();
	//delete the default option
	$("#change_theme").remove();

	//动态增加select控件的option项
	$.each(sys_themes, function(i, object) {

		var temp = document.createElement("OPTION");
		$("#sel_theme").append(temp);
		temp.text = object.description;
		temp.value = object.theme;
		//  alert(object.description);
			if (object.theme == now_theme) {
				temp.selected = true;
			}
		});
}

//object language define
function Language(language, description) {
	this.language = language;
	this.description = description;
}
function Theme(theme, description) {
	this.theme = theme;
	this.description = description;
}
