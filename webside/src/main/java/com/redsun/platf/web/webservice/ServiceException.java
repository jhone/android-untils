package com.redsun.platf.web.webservice;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>Title: com.walsin.platf.service.ServiceException</p>
 * <p>Description: EP系統相關服務例外父類別</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: FreeLance</p>
 * @author Jason Huang
 * @version 1.0
 */
public class ServiceException extends Exception {

    private static final long serialVersionUID = -4010589132568951029L;

    public ServiceException() {
        super();
    }

    public ServiceException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public ServiceException(String detailMessage) {
        super(detailMessage);
    }

    public ServiceException(Throwable throwable) {
        super(throwable);
    }
    
    public ServiceException(ServiceMessage serviceMessage, Throwable throwable, Object[] values) {
        super(serviceMessage.getMessage(values), throwable);
    }

    public ServiceException(ServiceMessage serviceMessage, Object[] values) {
        super(serviceMessage.getMessage(values));
    }
    
    public ServiceException(ServiceMessage serviceMessage, Throwable throwable) {
        super(serviceMessage.getMessage(ArrayUtils.EMPTY_OBJECT_ARRAY), throwable);
    }

    public ServiceException(ServiceMessage serviceMessage) {
        super(serviceMessage.getMessage(ArrayUtils.EMPTY_OBJECT_ARRAY));
    }
}
