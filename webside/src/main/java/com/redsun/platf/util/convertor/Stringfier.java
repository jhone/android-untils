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
/**
 * 字符串格式接口，用于将某个数据类型转化为指定的字符串格式用以显示和输出。
 * @author CHEN Qiang
 *
 * @param <T> 要处理的数据类型
 */

public interface Stringfier<S> extends Convertor<S, String>{
        /**
         * 根据指定数据类型的实例得到表示字符串
         * @param t 实例
         * @return 字符串
         */
        public String convert(S s);
}

