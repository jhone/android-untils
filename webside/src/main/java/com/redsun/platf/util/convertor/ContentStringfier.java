package com.redsun.platf.util.convertor;

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

import com.redsun.platf.util.StringUtil;

/**
 * 实现了IStringfier接口的一个抽象类，增加了一个安全的输出函数toSafeString，在输出为null的时候转为输出空字符串。
 * @author CHEN Qiang
 *
 * @param <E>
 * @see Stringfier
 */
public class ContentStringfier<E> implements Stringfier<E>{
        //public abstract String getContent(E e);
//        @Override
        public String convert(E e)  {
                if(e == null) {
                        return null;
                }
                else {
                        return e.toString();
                }
        }
        public String toSafeString(E e) {
                return StringUtil.avoidNull(this.convert(e));
        }
}

