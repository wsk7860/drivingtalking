package com.drivingtalking.service.impl;

import com.drivingtalking.dao.BaseDAO;

import com.drivingtalking.model.base.BaseModel;
import com.drivingtalking.service.IBaseService;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseService<T extends BaseModel,D extends BaseDAO<T>> implements IBaseService<T>{
    @Autowired
    private D dao;

    @Override
    public T getById(Class<T> t, String id) {
        return dao.getById(t,id);
    }

    @Override
    public void save(T t) {
        dao.save(t);
    }

    @Override
    public void deleteById(String id) {

    }
}
