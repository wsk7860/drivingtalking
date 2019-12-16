package com.drivingtalking.exception;

import com.drivingtalking.util.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = ServiceException.class)
    @ResponseBody
    protected ResponseModel processServiceException(ServiceException ex){
        return ResponseModel.buildForErr(ex.getErrMsg());
    }

    @ExceptionHandler(value = ControllerException.class)
    @ResponseBody
    protected ResponseModel processControllerException(ControllerException ex){
        return ResponseModel.buildForErr(ex.getErrMsg());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    protected ResponseModel processException(Exception ex) {
        return  ResponseModel.buildForErr(ex.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    protected ResponseModel processConstraintViolationException(MethodArgumentNotValidException ex) {
        return  ResponseModel.buildForErr(ex.getBindingResult().getFieldError().getDefaultMessage());
    }
}
