package com.jnshu.dreamteam.config.exception;

/**
 * 自定义异常
 * service层关于更删改查异常
 */
public class ServiceDaoException extends Exception {
    public ServiceDaoException() {
        super();
    }

    public ServiceDaoException(String message) {
        super(message);
    }

    public ServiceDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceDaoException(Throwable cause) {
        super(cause);
    }

    protected ServiceDaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}