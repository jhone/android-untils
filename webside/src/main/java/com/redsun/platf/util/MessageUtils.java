package com.redsun.platf.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * <p>Title: com.walsin.platf.util.MessageUtils</p>
 * <p>Description: 訊息相關工具</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: FreeLance</p>
 * @author Jason Huang
 * @version 1.0
 */
public class MessageUtils {
    
    private static final String DEFALUT_ENCODING = "UTF-8";
    
    private static final Pattern pattern = Pattern.compile("[{]{1}[\\d]{1}[}]{1}");
    
    /**
     * 取得訊息 
     * @param message 訊息代碼
     * @param values 值
     * @return 返回訊息字串
     */
    public static String getMessage(String message, Object[] values) {
        int index = 0;
        int size = values.length;
        String _message = message;
        Matcher matcher = pattern.matcher(_message);
        while(matcher.find()) {
            if(index < size) _message = _message.replace(matcher.group(), ((values[index] == null) ? StringUtils.EMPTY : values[index]).toString()) ;
            index++;
        }
        return _message;
    }
    
    /**
     * 限制字串長度(byte)
     * @param str 字串
     * @param size 長度byte
     * @return 返回字串
     */
    public static String getLimitString(String str, int size) {
        try {
            if(StringUtils.isNotBlank(str) && str.getBytes(DEFALUT_ENCODING).length > size) {
                int total = 0;
                StringBuffer sb = new StringBuffer();
                for(int i=0, s=str.length() ; i<s ; i++){
                    total += String.valueOf(str.charAt(i)).getBytes(DEFALUT_ENCODING).length;
                    if(total <= size) {
                        sb.append(str.charAt(i));
                    } else {
                        break;
                    }
                }
                return sb.toString();
            } else {
                return str;
            }
        } catch(Exception ex) {
            return str;
        }
    }
    
    /**
     * 取得字串byte長度
     * @param str 字串 
     * @return 長度
     * @throws UnsupportedEncodingException
     */
    public static int getLength(String str) throws UnsupportedEncodingException {
        return StringUtils.isBlank(str) ? 0 : str.getBytes(DEFALUT_ENCODING).length;
    }
    
    /**
     * 陣列轉換成字串(分隔符號,)
     * @param array 陣列
     * @return 字串
     */
    public static String arrayToString(Object[] array) {
        ToStringBuilder ts = new ToStringBuilder(MessageUtils.class, ToStringStyle.SIMPLE_STYLE);
        for(Object obj : array) ts.append(obj);
        return ts.toString();
    }
}
