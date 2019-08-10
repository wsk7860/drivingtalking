package com.drivingtalking.util;

import lombok.Data;

@Data
public class ResponseModel<T> {

    private int status = 1;

    private String errorMessage;

    private  T  result;

    public ResponseModel(){

    }

    private ResponseModel(String errorMessage){
        this.status = 0;
        this.errorMessage = errorMessage;
    }


    public ResponseModel(T t){
        this.result =  t;
    }

    public ResponseModel(int status,String errorMessage){
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public static ResponseModel buildForErr(String errorMessage) {
        return  new ResponseModel(errorMessage);
    }

}
