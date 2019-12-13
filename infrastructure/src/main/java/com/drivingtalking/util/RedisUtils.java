package com.drivingtalking.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public void del(String key) {
        redisTemplate.delete(key);
    }

    public <T> boolean setKeyValue(String redisKey, String mapperKey, T value) {
        try {
            redisTemplate.opsForHash().put(redisKey, mapperKey, JSONObject.toJSONString(value));
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public <T> T getKeyValue(String redisKey, Object mapperKey, Class<T> t) {
        if (redisTemplate.opsForHash().hasKey(redisKey, mapperKey)) {
            return JSONObject.parseObject((String) redisTemplate.opsForHash().get(redisKey, mapperKey), t);
        }
        return null;
    }

    public Long getKeyValueSize(String redisKey, String mapperKey) {
        return redisTemplate.opsForHash().lengthOfValue(redisKey, mapperKey);
    }

    public boolean hasRedisKey(String redisKey) {
        return redisTemplate.opsForHash().size(redisKey) > 0;
    }

    public void deleteKeyValue(String redisKey, String mapperKey) {
        redisTemplate.opsForHash().delete(redisKey, mapperKey);
    }

    public <T> List<T> keyValueList(String redisKey, Class<T> t) {
        List<T> results = new ArrayList<>();
        for (Object mapperKey : redisTemplate.opsForHash().keys(redisKey)) {
            results.add(getKeyValue(redisKey,mapperKey,t));
        }
        return results;
    }
}
