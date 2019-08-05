package com.drivingtalking.exception;

import com.drivingtalking.util.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ServiceException.class)
    protected ResponseEntity<ResponseModel> processServiceException(ServiceException ex){
        ResponseModel responseModel = new ResponseModel(ex.errMsg);
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }

    @ExceptionHandler(ControllerException.class)
    protected ResponseEntity<ResponseModel> processControllerException(ServiceException ex){
        ResponseModel responseModel = new ResponseModel(ex.errMsg);
        return new ResponseEntity<>(responseModel, HttpStatus.OK);
    }
}
