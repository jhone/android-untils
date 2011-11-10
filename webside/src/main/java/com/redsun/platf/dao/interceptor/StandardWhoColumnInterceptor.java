package com.redsun.platf.dao.interceptor;

import java.io.Serializable;
import java.util.Calendar;

import org.apache.commons.lang.StringUtils;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import com.redsun.platf.entity.BaseEntity;
import com.redsun.platf.entity.sys.UserAccount;

/**
 * <p>Title: com.walsin.platf.dao.hibernate.support.StandardWhoColumnIntercepter</p>
 * <p>Description: Hibernate Entity 攔截器 for preset StandardWhoColumn</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: FreeLance</p>
 * @author Jason Huang
 * @version 1.0
 */
public class StandardWhoColumnInterceptor extends EmptyInterceptor{

    private static final long serialVersionUID = 7987230107543037978L;
    
    private static final String  LAST_CREATED_BY_COLUMN= "createdBy";
    
    private static final String LAST_CREATED_DATE_COLUMN = "creationDate";
    
    private static final String LAST_UPDATED_BY_COLUMN = "lastUpdatedBy";
    
    private static final String LAST_UPDATED_DATE_COLUMN = "lastUpdateDate";
    
    /** 系統預設帳號 */
    private static final String EP_SYSTEM_USER = "EPSYS";
    
    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        boolean modified = false;
        if(entity instanceof BaseEntity){
            UserAccount userAccount = (UserAccount)RequestThreadResourceManager.getResource(UserAccount.class);
            String userId = (userAccount == null ? EP_SYSTEM_USER : userAccount.getAccountId());
            for (int i=0, size=propertyNames.length ; i<size ; i++) { 
                String propertyName = propertyNames[i];
                if(StringUtils.equals(LAST_UPDATED_BY_COLUMN, propertyName)) {
                    modified = true;
                    if(currentState[i] == null) currentState[i] = userId;
                }
                if(StringUtils.equals(LAST_UPDATED_DATE_COLUMN, propertyName)) {
                    modified = true;
                    currentState[i] = Calendar.getInstance().getTime();
                }
            }
        }
        return modified;
    }
    
    @Override
    public boolean onSave(Object entity, Serializable id, Object[] currentState, String[] propertyNames, Type[] types) {
        boolean modified = false;
        if(entity instanceof BaseEntity){
            UserAccount userAccount = (UserAccount)RequestThreadResourceManager.getResource(UserAccount.class);
            String userId = (userAccount == null ? EP_SYSTEM_USER : userAccount.getAccountId());
            for (int i=0, size=propertyNames.length ; i<size ; i++) { 
                String propertyName = propertyNames[i];
                if(StringUtils.equals(LAST_CREATED_BY_COLUMN, propertyName)) {
                    modified = true;
                    if(currentState[i] == null) currentState[i] = userId;
                }
                if(StringUtils.equals(LAST_CREATED_DATE_COLUMN, propertyName)) {
                    modified = true;
                    currentState[i] = Calendar.getInstance().getTime();
                }
                if(StringUtils.equals(LAST_UPDATED_BY_COLUMN, propertyName)) {
                    modified = true;
                    if(currentState[i] == null) currentState[i] = userId;
                }
                if(StringUtils.equals(LAST_UPDATED_DATE_COLUMN, propertyName)) {
                    modified = true;
                    currentState[i] = Calendar.getInstance().getTime();
                }
            }
        }
        return modified;
    }
}
