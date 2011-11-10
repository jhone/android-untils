package com.redsun.platf.web.tld;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * Created by IntelliJ IDEA.
 * User: dick pan
 * Date: 2011/3/7
 * Time: 上午 10:53
 * To change this template use File | Settings | File Templates.
 * @param <T>
 */
public class MyForTag<T> extends BodyTagSupport {
    /**
     * 
     */
    private static final long serialVersionUID = -6422365513223672988L;
   
    private Collection<T> items;
    private String var;
    private int index = 0;
    private Iterator<T> iterator;

    public Collection<T> getItems() {
        return items;
    }

    public void setItems(Collection<T> items) {
        this.iterator = items.iterator();

    }

    public String getVar() {
        return var;
    }

    public void setVar(String var) {
        this.var = var;
    }



    @Override
    public int doStartTag() throws JspException {
//        return super.doStartTag();    //To change body of overridden methods use File | Settings | File Templates.
        this.index = 0;
        if (this.process()) {
            return EVAL_BODY_INCLUDE;
        } else {
            return EVAL_PAGE;
        }


    }



    @Override
    public int doAfterBody() throws JspException {
//        return super.doAfterBody();    //To change body of overridden methods use File | Settings | File Templates.
        if (this.process()) {
            return EVAL_BODY_AGAIN;
        } else {
            return EVAL_PAGE;
        }

    }


    private boolean process() {
        if (this.iterator.hasNext()) {
            String row = this.index % 2 != 0 ? "odd" : "even";
            pageContext.setAttribute(var + "_index", this.index);
            pageContext.setAttribute(var + "_row", row);
            Object item = this.iterator.next();
            pageContext.setAttribute(var, item);
            this.index++;
            return true;
        } else {
            return false;
        }


    }

}