package com.redsun.platf.temp;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

/**
 * <p>Title        : com.webapp        </p>
 * <p>Description  :                   </p>
 * <p>Copyright    : Copyright (c) 2011</p>
 * <p>Company      : FreedomSoft       </p>
 * 
 */

/**
 * @author dick pan
 * @version 1.0
 * @since 1.0
 *        <p>
 *        <H3>Change history</H3>
 *        </p>
 *        <p>
 *        2011/1/12 : Created
 *        </p>
 * 
 */
@ParentPackage(value = "webmain")
public class JQdemoAccordion extends ActionSupport {

    private static final long serialVersionUID = -3066791113091431706L;
    private Map<String, String> accordion;
    private String saveMessage ="";

    
    @Actions( {
	    @Action(value = "/accordion-list", results = { @Result(location = "menu/accordion-list.jsp", name = "success") })
		    
	    })
    public String execute() throws Exception {
	
	//config.init();
	
	accordion = new HashMap<String, String>();
	accordion
		.put(
			"Section 4",
			"<a href='http://www.163.com'>go 163</a> <p>Cras dictum. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aenean lacinia mauris vel est. Suspendisse eu nisl. Nullam ut libero. Integer dignissim consequat lectus. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.");
	accordion
		.put(
			"Section 3",
			"Nam enim risus, molestie et, porta ac, aliquam ac, risus. Quisque lobortis. Phasellus pellentesque purus in massa. Aenean in pede. Phasellus ac libero ac tellus pellentesque semper. Sed ac felis. Sed commodo, magna quis lacinia ornare, quam ante aliquam nisi, eu iaculis leo purus venenatis dui.");
	accordion
		.put(
			"Section 2",
			"Sed non urna. Donec et ante. Phasellus eu ligula. Vestibulum sit amet purus. Vivamus hendrerit, dolor at aliquet laoreet, mauris turpis porttitor velit, faucibus interdum tellus libero ac justo. Vivamus non quam. In suscipit faucibus urna.");
	accordion
		.put(
			"Section 1",
			"Mauris mauris ante, blandit et, ultrices a, suscipit eget, quam. Integer ut neque. Vivamus nisi metus, molestie vel, gravida in, condimentum sit amet, nunc. Nam a nibh. Donec suscipit eros. Nam mi. Proin viverra leo ut odio. Curabitur malesuada. Vestibulum a velit eu ante scelerisque vulputate.");
	return SUCCESS;
    }

    public Map<String, String> getAccordion() {
	return accordion;
    }

    @Actions( {
	    @Action(value = "/success_message", results = { @Result(location = "/success.jsp", name = "success") })
	    })
    public String ajax() {
	return SUCCESS;
    }
    
    @Actions( {
	@Action(value = "/menu", results = { @Result(location = "menu/apymenu.jsp", name = "success") })
    })
    public String ajaxmenu() {
	saveMessage =getText("springapp.title");
	
	System.out.println(getLocale()+":"+ saveMessage);
	
	return SUCCESS;
    }

    public String getSaveMessage() {
        return saveMessage;
    }

    public void setSaveMessage(String saveMessage) {
        this.saveMessage = saveMessage;
    }

}
