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
public final class ObjectUtil {
    public static <T> boolean nullSafeEqual(T lhs, T rhs) {
            if(lhs == null) {
                    return rhs == null;
            }
            else {
                    return lhs.equals(rhs);
            }
    }
}
