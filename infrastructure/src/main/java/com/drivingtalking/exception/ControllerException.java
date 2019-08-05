package com.drivingtalking.exception;

/**
 * 参数异常处理
 */
public class ControllerException extends BaseException {

    public ControllerException(Throwable throwable) {
        super(throwable);
    }

    public ControllerException(String msg) {
        super(msg);
        this.errMsg = msg;
    }

    public  ControllerException(int errCode,String errMsg,Throwable throwable) {
        super(errMsg,throwable);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
}
