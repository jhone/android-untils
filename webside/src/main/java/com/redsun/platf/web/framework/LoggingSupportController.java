package com.redsun.platf.web.framework;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.mvc.AbstractController;

import com.redsun.platf.util.LogUtils;


/**
 * <p>Title: com.walsin.platf.web.framework.LoggingSupportController</p>
 * <p>Description: 共用Log物件</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: FreeLance</p>
 * @author Jason Huang
 * @version 1.0
 */
public abstract class LoggingSupportController extends AbstractController{
    
    protected Logger _logger = LogUtils.getLogger(getClass());

    /**
     * Log 等級為 DEBUG.
     * @param message 要 log 的訊息
     */
    protected void debug(String message) {
        if (_logger.isDebugEnabled()) {
            _logger.debug(message);
        }
    }

    /**
     * Log 等級為 DEBUG.
     * @param message 要 log 的訊息
     * @param cause 錯誤的 exception.
     */
    protected void debug(String message, Throwable cause) {
        if (_logger.isDebugEnabled()) {
            _logger.debug(message, cause);
        }
    }

    /**
     * Log 等級為 INFO.
     * @param message 要 log 的訊息
     */
    protected void info(String message) {
        _logger.info(message);      
    }

    /**
     * Log 等級為 INFO.
     * @param message 要 log 的訊息
     * @param cause 錯誤的 exception.
     */
    protected void info(String message, Throwable cause) {
        _logger.info(message, cause);
    }

    /**
     * Log 等級為 WARN.
     * @param message 要 log 的訊息
     */
    protected void warn(String message) {
        _logger.warn(message);
    }

    /**
     * Log 等級為 WARN.
     * @param message 要 log 的訊息
     * @param cause 錯誤的 exception.
     */
    protected void warn(String message, Throwable cause) {
        _logger.warn(message, cause);
    }

    /**
     * Log 等級為 ERROR.
     * @param message 要 log 的訊息
     */
    protected void error(String message) {
        _logger.error(message);
    }

    /**
     * Log 等級為 ERROR.
     * @param ex
     */
    protected void error(Throwable ex) {
        _logger.error(ex.getMessage(), ex);
    }
    
    /**
     * Log 等級為 ERROR.
     * @param message 要 log 的訊息
     * @param cause 錯誤的 exception.
     */
    protected void error(String message, Throwable cause) {
        _logger.error(message, cause);
    }
    
    /**
     * Log 等級為 FATAL.
     * @param message 要 log 的訊息
     */
    protected void fatal(String message) {
        _logger.fatal(message);
    }

    /**
     * Log 等級為 FATAL.
     * @param message 要 log 的訊息
     * @param cause 錯誤的 exception.
     */
    protected void fatal(String message, Throwable cause) {
        _logger.fatal(message, cause);
    }
}
