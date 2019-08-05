package com.drivingtalking.util;

import lombok.Data;

@Data
public class ResponseModel<T> {

    private int status = 1;

    private String errMsg;

    private  T  result;

    public ResponseModel(){

    }

    private ResponseModel(String errMsg){
        this.status = 0;
        this.errMsg = errMsg;
    }


    public ResponseModel(T t){
        this.result =  t;
    }

    public ResponseModel(int status,String errMsg){
        this.status = status;
        this.errMsg = errMsg;
    }

    public static ResponseModel buildForErr(String errMsg) {
        return  new ResponseModel(errMsg);
    }

}
