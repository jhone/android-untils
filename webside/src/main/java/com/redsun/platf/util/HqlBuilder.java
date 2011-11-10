package com.redsun.platf.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * <p>Title: com.walsin.platf.util.HqlBuilder</p>
 * <p>Description: HQL建立器</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: FreeLance</p>
 * @author Jason Huang
 * @version 1.0
 */
public class HqlBuilder {
    
    private StringBuffer hql = null;

    private List<Object> params = null;
    
    private Map<String, Object> nameParams = null;

    /**
     *  建構子
     */
    public HqlBuilder(){
        hql = new StringBuffer();
        params = new ArrayList<Object>();
        nameParams = new LinkedHashMap<String, Object>();
    }
    
    /**
     * 加入HQL
     * @param sql hql字串
     */
    public void append(String sql){
        getHql().append(sql);
    }
    
    /**
     * 加入HQL與相關參數
     * @param sql hql字串
     * @param name 參數名稱
     * @param value 參數內容
     * @param likeFlag 是否加入'%'
     */
    public void append(String sql, String name, String value, boolean likeFlag){
        if(StringUtils.isNotBlank(value)){
            getHql().append(sql);
            if(likeFlag) {
                getNameParams().put(name, "%" + value.trim() + "%");
            } else {
                getNameParams().put(name, value.trim());
            }
        }
    }
    
    /**
     * 加入HQL與相關參數
     * @param sql hql字串
     * @param name 參數名稱
     * @param value 參數內容
     */
    public void append(String sql, String name, Object value){
        if(value != null){
            if(value instanceof String){
                if(StringUtils.isNotBlank((String)value)){
                    getHql().append(sql);
                    getNameParams().put(name, value);
                }
            }else{
                getHql().append(sql);
                getNameParams().put(name, value);
            }
        }
    }
    
    /**
     * 加入HQL與相關參數
     * @param sql hql字串
     * @param value 參數內容
     */
    public void append(String sql, Object value){
        if(value != null){
            if(value instanceof String){
                if(StringUtils.isNotBlank((String)value)){
                    getHql().append(sql);
                    params.add(value);
                }
            }else{
                getHql().append(sql);
                params.add(value);
            }
        }
    }
    
    /**
     * 加入HQL與相關參數
     * @param sql hql字串
     * @param value 參數內容
     * @param likeFlag 是否加入'%'
     */
    public void append(String sql, String value, boolean likeFlag){
        if(StringUtils.isNotBlank(value)){
            getHql().append(sql);
            if(likeFlag) {
                params.add("%" + value.trim() + "%");
            } else {
                params.add(value.trim());
            }
        }
    }
    
    /**
     * 取得HQL
     * @return
     */
    public StringBuffer getHql() {
        return hql;
    }

    /**
     * 取得Map<參數名稱, 參數內容>
     * @return Map<參數名稱, 參數內容>
     */
    public List<Object> getParams() {
        return params;
    }

    /**
     * 去得List<參數內容>
     * @return List<參數內容>
     */
    public Map<String, Object> getNameParams() {
        return nameParams;
    }
}
