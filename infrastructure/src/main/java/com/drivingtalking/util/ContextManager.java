package com.drivingtalking.util;

import com.drivingtalking.model.member.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class ContextManager {

    private static final ThreadLocal<HttpSession> context = new ThreadLocal<>();

    private static final String LOGIN_MEMBER = "member";

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


}