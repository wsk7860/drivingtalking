package com.drivingtalking.exception;

/**
 * 业务逻辑异常
 */
public class ServiceException extends BaseException {

    public ServiceException(Throwable throwable) {
        super(throwable);
    }

    public ServiceException(String msg) {
        super(msg);
        this.errMsg = msg;
    }

    public ServiceException(String msg, Throwable throwable) {
        super(msg, throwable);
        this.errMsg = msg;

    }
}
