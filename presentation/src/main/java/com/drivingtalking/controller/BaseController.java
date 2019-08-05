package com.drivingtalking.controller;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseController {

    @Autowired
    private Mapper mapper;

   protected <T> T map(Object source,Class<T> result) {
    return mapper.map(source,result);
   }
}
