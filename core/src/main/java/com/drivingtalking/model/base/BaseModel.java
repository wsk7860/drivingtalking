package com.drivingtalking.model.base;

import lombok.Data;


@Data
public class BaseModel {
    private String id;

    public  BaseModel() {
        id = UUIDGenerator.generateUUID();
    }
}
