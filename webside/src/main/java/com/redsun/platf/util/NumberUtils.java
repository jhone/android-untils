package com.redsun.platf.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.apache.commons.lang.StringUtils;

/**
 * <p>Title: com.walsin.platf.util.NumberUtils</p>
 * <p>Description: 數字相關共用方法</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: FreeLance</p>
 * @author Jason Huang
 * @version 1.0
 */
public class NumberUtils extends org.apache.commons.lang.math.NumberUtils {
    
    private final static DecimalFormat df = new DecimalFormat("#,###.##############################");
    
    /**
     * 格式化數字
     * @param value 值
     * @return 返回格式化數字ex:123,321.123
     */
    public static String format(BigDecimal value) {
        return value == null ? StringUtils.EMPTY : df.format(value.doubleValue());
    }
}
