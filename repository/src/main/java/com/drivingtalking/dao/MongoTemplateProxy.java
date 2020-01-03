package com.drivingtalking.dao;

import com.drivingtalking.util.OrderParam;
import com.drivingtalking.util.PagerManager;
import com.drivingtalking.util.PagerSupporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MongoTemplateProxy<T> {


    @Autowired
    private MongoTemplate mongoTemplate;

    public T findOne(Class<T> t, Query query){
        return  mongoTemplate.findOne(query,t);
    }

    public List<T> findList(Class<T> t,Query query) {
        PagerSupporter pagerSupporter  = PagerManager.getPagerSupport();
        if(pagerSupporter != null) {
            query.limit(pagerSupporter.getPageSize());
            query.skip((pagerSupporter.getPageNo() -1) * pagerSupporter.getPageSize());
            pagerSupporter.setTotal((int) mongoTemplate.count(query,t));

            if(Optional.ofNullable(pagerSupporter.getSorts()).isPresent()){
                List<Sort.Order> orders = new ArrayList<>();
                pagerSupporter.getSorts().forEach(sort -> {
                    Sort.Order order;
                    if(sort.getSequence().equals(OrderParam.Sequence.ASC)){
                        order = new Sort.Order(Sort.Direction.ASC,sort.getProperty());
                    }else{
                        order = new Sort.Order(Sort.Direction.DESC,sort.getProperty());
                    }
                orders.add(order);
                });
                query.with(Sort.by(orders));
            }
        }
        return  mongoTemplate.find(query,t);
    }

    public int count(Class<T> t) {
        return  (int) mongoTemplate.count(new Query(),t);
    }

    public void save(T  t){
        mongoTemplate.save(t);
    }
}
