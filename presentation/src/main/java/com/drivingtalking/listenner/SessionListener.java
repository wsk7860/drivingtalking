package com.drivingtalking.listenner;

import com.drivingtalking.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionActivationListener {

    private static Logger logger = LoggerFactory.getLogger(SessionListener.class);

    @Autowired
    private RedisUtil redisUtil;


    @Override
    public void  sessionCreated(HttpSessionEvent event) {
        logger.info("session created");
        redisUtil.set("1","123123");

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        logger.info("session destroyed");
        System.out.print(redisUtil.get("1"));
        redisUtil.del("1");
    }
}
