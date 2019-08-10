package com.drivingtalking.filter;

import com.drivingtalking.model.member.Member;
import com.drivingtalking.util.ContextManager;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AccessSessionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final String uri = request.getRequestURI();
        ContextManager.initSession(request);
        Member member = ContextManager.getSessionMember();
        if (getWhiteList().stream().noneMatch(uri::contains) && member == null) {
            response.setStatus(Response.Status.UNAUTHORIZED.getStatusCode());
            return;
        }
        filterChain.doFilter(request, response);
    }

    private List<String> getWhiteList() {
        List<String> whiteList = new ArrayList<>();
        whiteList.add("/login");
        whiteList.add("/register");
        whiteList.add("/file");
        return whiteList;
    }
}
