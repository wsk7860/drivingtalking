package com.drivingtalking.dao;

import com.drivingtalking.model.base.BaseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BaseDAO <T extends BaseModel> {

    @Autowired
    private MongoTemplateProxy<T> proxy;

    public void save(T t) {
        proxy.save(t);
    }

    public T getById(Class<T> t,String id){
        Query query =  new Query(Criteria.where("id").is(id));
        return  proxy.findOne(t,query);
    }

    public T getByParam(Class<T> t ,Map<String, Object> params){
        return proxy.findOne(t,getQuery(params));
    };

    public List<T> findByParams(Class<T> t, Map<String,Object> params){
     return  proxy.findList(t,getQuery(params));
    }

    public List<T> findPyQuery(Class<T> t,Query query){
        return proxy.findList(t,query);
    }

    private Query getQuery(Map<String,Object> params) {
        Query query = new Query();
        params.keySet().forEach(key->
                query.addCriteria(Criteria.where(key).is(params.get(key)))
        );
        return  query;
    }




}
