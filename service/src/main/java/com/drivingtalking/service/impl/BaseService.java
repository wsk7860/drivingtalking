package com.drivingtalking.service.impl;

import com.drivingtalking.dao.BaseDAO;

import com.drivingtalking.model.base.BaseModel;
import com.drivingtalking.service.IBaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class BaseService<T extends BaseModel,D extends BaseDAO<T>> implements IBaseService<T>{
    @Autowired
    protected D dao;

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

    @Override
    public List<T> findAll(Class<T> t) {
        return dao.findPyQuery(t,new Query());
    }

    @Override
    public List<T> findByIds(Class<T> t,List<String> ids) {
        return dao.findByIds(t,ids);
    }

    @Override
    public int count(Class<T> t) {
        return dao.count(t);
    }


}
