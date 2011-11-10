package com.redsun.platf.util;

/**
 * <p>Title: com.walsin.platf.util.HtmlUtils</p>
 * <p>Description: HTML共用程式庫</p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: FreeLance</p>
 * @author Jason Huang
 * @version 1.0
 */
public class HtmlUtils {
    
    /**
     * text文字轉換文html text文字
     * @param text 來源text
     * @return
     */
    public static String textConvertToHtmlText(String text) {
        return text == null ? text : text.replaceAll("\r\n", "<br>").replaceAll(" ", "&nbsp;");
    }
}
