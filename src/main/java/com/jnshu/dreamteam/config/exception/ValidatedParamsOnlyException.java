package com.jnshu.dreamteam.config.exception;


/**
 * 自定义异常类
 * 校验参数是否唯一，不唯一则抛出
 */
public class ValidatedParamsOnlyException extends Exception {

    public ValidatedParamsOnlyException() {
        super();
    }

    public ValidatedParamsOnlyException(String message) {
        super(message);
    }

    public ValidatedParamsOnlyException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidatedParamsOnlyException(Throwable cause) {
        super(cause);
    }

    protected ValidatedParamsOnlyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
