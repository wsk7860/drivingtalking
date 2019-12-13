package com.drivingtalking.service;

import java.util.List;

public interface IBaseService<T> {

     T getById(Class<T> t,String id);

     void save(T t);

     void deleteById(String id);

     List<T> findAll(Class<T> t);


}
