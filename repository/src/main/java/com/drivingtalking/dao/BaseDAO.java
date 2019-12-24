package com.drivingtalking.dao;

import com.drivingtalking.model.base.BaseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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

    public List<T> findByIds(Class<T> t,List<String> ids) {
        Query query = new Query(Criteria.where("id").in(ids));
        return proxy.findList(t,query);
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

    public List<T> findByCriteriaList(Class<T> t,List<Criteria> criteriaList) {
        Query query = new Query();
        for (Criteria criteria : criteriaList) {
            query.addCriteria(criteria);
        }
        return proxy.findList(t,query);
    }

    private Query getQuery(Map<String,Object> params) {
        Query query = new Query();
        params.keySet().forEach(key->
                query.addCriteria(Criteria.where(key).is(params.get(key)))
        );
        return  query;
    }


    public Criteria le(String propertyName,Object value) {
        return Criteria.where(propertyName).lte(value);
    }

    public Criteria ge(String propertyName,Object value) {
        return Criteria.where(propertyName).gte(value);
    }

    public Criteria lt(String propertyName,Object value) {
        return Criteria.where(propertyName).lt(value);
    }

    public Criteria gt(String propertyName,Object value) {
        return Criteria.where(propertyName).gt(value);
    }

    public Criteria isNull(String propertyName) {
        return Criteria.where(propertyName).is(null);
    }

    public Criteria isNotNull(String propertyName) {
        return  Criteria.where(propertyName).ne(null);
    }

    public Criteria between(String propertyName,Object param1,Object param2){
        return Criteria.where(propertyName).gte(param1).andOperator(Criteria.where(propertyName).lte(param2));
    }

    public Criteria like(String propertyName,String value) {
        Pattern pattern = Pattern.compile("^.*" + value + ".*$");
        return Criteria.where(propertyName).regex(pattern);
    }

    public Criteria in(String propertyName,List<Object> objects)  {
        return Criteria.where(propertyName).in(objects);
    }

    public Criteria nin(String propertyName,List<Object> objects) {
        return Criteria.where(propertyName).nin(objects);
    }

    public Criteria and(Criteria... criteria) {
        return new Criteria().andOperator(criteria);
    }

    public Criteria or(Criteria... criteria) {
        return new Criteria().orOperator(criteria);
    }






}
