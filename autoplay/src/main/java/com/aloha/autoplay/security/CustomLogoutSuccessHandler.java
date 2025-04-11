package com.aloha.autoplay.security;

import java.io.IOException;

import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    /**
     * 로그아웃 성공 시 호출되는 메소드
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param authentication Authentication 객체
     * @throws IOException, ServletException
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, 
                                org.springframework.security.core.Authentication authentication) 
                                throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // 세션 무효화
        }
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("Logout successful");

        response.sendRedirect(request.getContextPath() + "/login?logout");
        
    }
}
