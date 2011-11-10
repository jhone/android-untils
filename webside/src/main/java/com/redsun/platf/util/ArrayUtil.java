package com.redsun.platf.util;

import java.util.List;

public class ArrayUtil {
    
    public static String[] toString(Object[] data) {
        String[] tmp = new String[data.length];
        for(int i = 0; i < data.length; i++) {
            tmp[i] = data[i].toString();
        }
        return tmp;
    }
    
    public static String[] toString(List<Object> data) {
        String[] tmp = new String[data.size()];
        for(int i = 0; i < data.size(); i++) {
            tmp[i] = data.get(i).toString();
        }
        return tmp;
    }
    
    public static String[] listTransToArray(List<String> als){
        String[] sa=new String[als.size()];
        als.toArray(sa);
        return sa;
    }

}
