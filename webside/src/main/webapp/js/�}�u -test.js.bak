/* 
 * 模板基类 
 */
Tbi.Template = Class.create();
Tbi.Template.prototype = {
	initialize : function() {
		this._init.apply(this, arguments);
	},

	_init : function(wrap, items, catchUrl) {
		this.wrap = $(wrap);
		this.items = items;
		this.catchUrl = catchUrl;
		this.ajax = null; //全局异步对象  
},

// 显示等待信息  
	_showMessage : function(text) {
		this.wrap.innerHTML = text;
	},

	// 显示模板默认页面  
	show : function() {
		this.beforeShow(); //显示前事件处理入口  
	this._display();
	this.afterShow(); //显示后时间处理入口  
},

// 显示前事件处理入口  
	beforeShow : function() {
	},

	_display : function() {
		this._showMessage("正在获取数据，请稍等……");
		this._catchContent();
	},

	//取得默认页面内容，由两个模板公用  
	_catchContent : function() {
		if (this.ajax) {
			this.ajax.transport.abort();
		}
		this.ajax = new Ajax.Request(this.catchUrl, {
			method : "get",
			parameters : {
				"mode" : this.mode,
				"items" : this.items
			},
			onComplete : this.addContent.bind(this)
		});
	},

	// 添加页面内容  
	addContent : function(xmlhttp) {
		this.addPage(xmlhttp); //抽象方法，添加页面内容  
	this.addNavigation(); //抽象方法，添加分页导航  
},

// 显示后事件处理入口          
	afterShow : function() {
	}
}

/* 
 * 静态模板，一次请求全部数据 
 */
Tbi.StaticTemplate = Class.create();
Tbi.StaticTemplate.prototype = Object.extend(new Tbi.Template(), {
	/* 
	 * 构造函数，实例化静态模板 
	 * 参数：父容器，每页显示条目数，数据获取地址
	 */
	initialize : function(wrap, items, catchUrl) {
		this._init(wrap, items, catchUrl);
		this.mode = "static";
	},

	// 实现父类抽象方法，向页面添加默认分页  
	addPage : function(xmlhttp) {
		this.wrap.innerHTML = xmlhttp.responseText;
		this.pageTotal = this.wrap.getElementsByTagName("tbody").length;
		var table = $("pages");
		var pages = $A(table.getElementsByTagName("tbody"));
		displayPage = pages[0];
		displayPage.className = "";
	},

	// 实现父类抽象方法，向页面添加分页导航  
	addNavigation : function() {
		if (this.pageTotal > 1) {
			var navigation = document.createElement("div");
			navigation.id = "navigation";
			var context = this;
			$R(1, this.pageTotal, false).each(
					function(item) {
						var link = document.createElement("a");
						link.href = "#";
						link.onclick = context._changePage.bindAsEventListener(
								context, link);
						link.appendChild(document.createTextNode(item));
						if (item == 1) {
							link.className = "active";
						}
						navigation.appendChild(link);
					});
			this.wrap.appendChild(navigation);
		}
	},

	// 导航链接点击事件处理函数，切换页面内容,同时改变导航链接样式（突出显示当前页）  
	_changePage : function(event, link) {
		var activeLink = $$('#TMPwrap div a[class="active"]')[0];
		if (activeLink != link) {
			var pages = $$("#TMPwrap tbody");
			var oldPage = pages.find(function(item) {
				return item.className == "";
			});
			var newPage = pages[link.firstChild.nodeValue - 1]; //inner系列属性不兼容ff
			oldPage.className = "hidden";
			newPage.className = "";
			activeLink.className = "";
			link.className = "active";
		}
		Event.stop(event);
	}
});

/* 
 * 异步模板，每次请求一个页面，并缓存浏览过的页面(可选) 
 */
var mixin = new Tbi.StaticTemplate(); //用于mixin（混入）对象方法addNavigation  
Tbi.AsyncTemplate = Class.create();
Tbi.AsyncTemplate.prototype = Object.extend(new Tbi.Template(), {
	initialize : function(wrap, items, catchUrl, option) {
		this._init(wrap, items, catchUrl);
		this.mode = "async";
		//option为可选参数，设置初始显示页面以及是否缓存页面  
	this.option = Object.extend( {
		isCache : true
	}, option);
	if (this.option.isCache) {
		this.cache = new Array(); //缓存数组;  
	}
},

// 实现父类抽象方法，向页面添加默认分页  
	addPage : function(xmlhttp) {
		var original = xmlhttp.responseText;
		var html = original.stripScripts();
		this.wrap.innerHTML = html;
		original.evalScripts();
	},

	// mixin(混入)StaticTemplate类的同名方法，向页面添加分页导航  
	addNavigation : mixin.addNavigation, // 方法劫持，重用addNavigation添加分页导航  

	// 导航链接点击事件处理函数，切换页面内容，同时改变导航链接样式（突出显示当前页）  
	_changePage : function(event, link) {
		var activeLink = $$('#TMPwrap div a[class="active"]')[0];
		if (activeLink != link) {
			link.className = "active";
			activeLink.className = "";
			this._displayPage(link);
		}
		Event.stop(event);
	},

	// 根据缓存标志切换页面内容  
	_displayPage : function(link) {
		var page = link.firstChild.nodeValue;
		// 如果是第一页，且进行缓存的话，直接切换页面元素的可见性  
		if (page == 1 && this.cache) {
			$("default").className = "";
			$("swap").className = "hidden";
		}
		// 否则，根据缓存标志，切换显示其他页（包括非缓存的默认页）  
		else {
			$("default").className = "hidden";
			var oldPage = $("swap");
			swapPage = this._prompt("正在获取数据……");
			$("pages").replaceChild(swapPage, oldPage);
			this._showPage(page);
		}
	},

	// 显示提示信息  
	_prompt : function(text) {
		var swapPage = document.createElement("tbody");
		swapPage.id = "swap";
		var messageTr = document.createElement("tr");
		var messageTd = document.createElement("td");
		messageTd.colSpan = 100;
		messageTd.appendChild(document.createTextNode(text));
		messageTr.appendChild(messageTd);
		swapPage.appendChild(messageTr);
		return swapPage;
	},

	// 根据缓存标志显示页面  
	_showPage : function(page) {
		if (this.cache && this.cache["page" + page]) {
			this._addPageContent(page); //巧妙重用此方法达到添加页面的效果  
		} else {
			this._catchPage(page);
		}
	},

	// 异步获取页面内容  
	_catchPage : function(page) {
		if (this.ajax) {
			this.ajax.transport.abort();
		}
		this.ajax = new Ajax.Request(this.catchUrl, {
			method : "get",
			parameters : {
				"mode" : "page",
				"items" : this.items,
				"page" : page
			},
			onComplete : this._addPageContent.bind(this, page)
		});
	},

	// 添加各分页，并根据缓存标志进行页面缓存  
	_addPageContent : function(page, xmlhttp) {
		var xml = xmlhttp ? xmlhttp.responseXML : this.cache["page" + page];
		if (this.cache && !this.cache["page" + page]) {
			this.cache["page" + page] = xml;
		}
		var oldPage = $("swap");
		var newPage = this._createPage(xml);
		$("pages").replaceChild(newPage, oldPage);
	},

	// 由异步返回的xml，或者缓存数组中的xml信息生成页面内容  
	_createPage : function(xml) {
		var newPage = document.createElement("tbody");
		newPage.id = "swap";
		var trs = $A(xml.getElementsByTagName("tr"));
		var context = this;
		trs.each(function(item, index) {
			var tr = document.createElement("tr");
			var tds = $A(item.getElementsByTagName("td"));
			tds.each(function(item) {
				var value = item.firstChild ? item.firstChild.nodeValue : "";
				var td = document.createElement("td");
				td.appendChild(document.createTextNode(value));
				tr.appendChild(td);
			});
			newPage.appendChild(tr);
		});
		return newPage;
	}
});
