package com.drivingtalking.listenner;

import com.drivingtalking.model.member.Member;
import com.drivingtalking.util.ContextManager;
import com.drivingtalking.util.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Optional;


@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionActivationListener {

    private static Logger logger = LoggerFactory.getLogger(SessionListener.class);

    @Autowired
    private RedisUtils redisUtils;


    @Override
    public void  sessionCreated(HttpSessionEvent event) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        Optional<String> roomId = Optional.ofNullable(ContextManager.getSessionRoomId());
        String memberId =  Optional.ofNullable(ContextManager.getSessionMember()).map(Member::getId).orElse(null);
        logger.info(roomId +"------"+memberId);
    }
}
