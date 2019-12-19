package com.drivingtalking.util;

import com.drivingtalking.model.member.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;


public class ContextManager {

    private static final ThreadLocal<HttpSession> context = new ThreadLocal<>();

    public static final String LOGIN_MEMBER = "member";

    public static final String ROOM_ID = "room_id";

    private ContextManager () {

    }

    public static void initSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        context.set(session);
    }

    public static void setSessionMember(Member member){
        context.get().setAttribute(LOGIN_MEMBER,member);
    }

    public static Member getSessionMember(){
        return (Member)context.get().getAttribute(LOGIN_MEMBER);
    }

    public static HttpSession getSession(){
        return  context.get();
    }

    public static  void setSessionRoomId(String room_id){
        context.get().setAttribute(ROOM_ID,room_id);
    }

    public static String getSessionRoomId() {
        return Optional.ofNullable((String)context.get().getAttribute(ROOM_ID)).orElse(null) ;
    }

}