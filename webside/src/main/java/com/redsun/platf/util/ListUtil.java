package com.redsun.platf.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ListUtil {
    
    @SuppressWarnings("unchecked")
    public static List arrayToList(Object[] data) {
        List tmp = new ArrayList();
//        Arrays.asList(data);
        for(int i = 0; i < data.length; i++) {
            tmp.add(data[i]); 
        }
        return tmp;
    }
    
    public static List<String> toString(List<Object> data) {
        List<String> tmp = new ArrayList<String>();
        for(int i = 0; i < data.size(); i++) {
            tmp.add(data.get(i).toString()); 
        }
        return tmp;
    }
    
    public static List<String> toString(Object[] data) {
        List<String> tmp = new ArrayList<String>();
        for(int i = 0; i < data.length; i++) {
            tmp.add(data[i].toString()); 
        }
        return tmp;
    }
}
