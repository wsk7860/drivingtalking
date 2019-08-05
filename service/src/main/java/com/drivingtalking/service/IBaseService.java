package com.drivingtalking.service;

public interface IBaseService<T> {

     T getById(Class<T> t,String id);

     void save(T t);

     void deleteById(String id);


}
