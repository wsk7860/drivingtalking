package com.drivingtalking.exception;

import lombok.Data;

@Data
public class BaseException extends RuntimeException {

    protected int errCode;

    protected String errMsg;

    public BaseException(Throwable throwable) {
        super(throwable);
    }

    public BaseException(String msg) {
        super(msg);
    }

    public BaseException(String msg, Throwable throwable) {
        super(msg, throwable);
    }


}
