package com.drivingtalking.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public Object get(String key){
      return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    public boolean set(String key,Object value) {
        try {
            redisTemplate.opsForValue().set(key,value);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public void del(String key) {
        redisTemplate.delete(key);
    }
}
