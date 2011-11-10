package com.redsun.platf.dao.interceptor;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: com.walsin.platf.system.RequestThreadResourceManager</p>
 * <p>Description: 管理 Request 期間資源物件</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: FreeLance</p>
 * @author Jason Huang
 * @version 1.0
 */
public class RequestThreadResourceManager {
    
    private static final ThreadLocal<Map<Object, Object>> $RESOURCES = new ThreadLocal<Map<Object, Object>>();

    /**
     * 取得指定名稱的 resource
     * @param key
     * @return
     */
    public static final Object getResource(Object key) {
        return getResourceMap().get(key);
    }
    
    /**
     * 設定 resource
     * @param key
     * @param res
     */
    public static final void setResource(Object key, Object res) {
        if (res == null) {
            getResourceMap().remove(key);
        } else {
            getResourceMap().put(key, res);
        }
    }
    
    /**
     * 移除 resource
     * @param key
     */
    public static final void removeResource(Object key) {
        if (key != null) { 
            getResourceMap().remove(key);
        }
    }
    
    /**
     * 取得資源 Map
     * @return
     */
    private static Map<Object, Object> getResourceMap() {
        Map<Object, Object> map = (Map<Object, Object>) $RESOURCES.get();
        
        if (map == null) {
            map = new HashMap<Object, Object>();
            $RESOURCES.set(map);
        }
        
        return map;
    }
}
