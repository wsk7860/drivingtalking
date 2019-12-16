package com.drivingtalking.interceptor;

import com.drivingtalking.model.member.Member;
import com.drivingtalking.util.ContextManager;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Component
public class BaseInterceptor implements HandlerInterceptor {


    @Override
     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        ContextManager.initSession(request);
        Member member = ContextManager.getSessionMember();
        final String uri = request.getRequestURI();
        if (getWhiteList().stream().noneMatch(uri::contains) && member == null) {
            response.setStatus(Response.Status.UNAUTHORIZED.getStatusCode());
            return false;
        }
        return true;
    }
        private List<String> getWhiteList() {
            List<String> whiteList = new ArrayList<>();
            whiteList.add("/login");
            whiteList.add("/register");
            whiteList.add("/file");
            whiteList.add("/swagger");
            return whiteList;
    }


}
