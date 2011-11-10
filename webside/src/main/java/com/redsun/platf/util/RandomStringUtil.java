package com.redsun.platf.util;

/**
 * <p>Title        : com.webapp        </p>
 * <p>Description  :                   </p>
 * <p>Copyright    : Copyright (c) 2010</p>
 * <p>Company      : FreedomSoft       </p>
 * 
 */

/**
 * @author Dick Pan 
 * @version 1.0
 * @since   1.0
 * <p><H3>Change history</H3></p>
 * <p>2010/10/28   : Created </p>
 *
 */
public class RandomStringUtil {

    static final String charFactory = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String getRandomString(int length){
            StringBuilder str = new StringBuilder();
            int tempLength = charFactory.length();
            for(int i = 0; i < length; i ++){
                    double tempDouble = Math.random() * tempLength;
                    int tempInt = (int) Math.floor(tempDouble);
                    str.append(charFactory.charAt(tempInt));
            }

            return str.toString();
    }
}
