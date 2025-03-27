package com.aloha.autoplay.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.aloha.autoplay.domain.CustomUser;
import com.aloha.autoplay.domain.Users;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 로그인 성공 처리 이벤트 핸들러
 */
@Slf4j
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    
    /**
     * 로그인 성공 시 호출되는 메소드
     * 🍪 아이디 저장 쿠키 생성
     * 🔐 로그인 후 이전 페이지로 리다이렉트
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request
                                      , HttpServletResponse response
                                      , Authentication authentication) throws ServletException, IOException {


        log.info("로그인 성공...");

        // 아이디 저장
        String rememberId = request.getParameter("remember-id"); // ✅ 아이디 저장 여부
        String username = request.getParameter("id");            // 👩‍💼 아이디
        log.info("rememberId  : " + rememberId);
        log.info("username  : " + username);

        // 아이디 저장 체크 ✅
        if( rememberId != null && rememberId.equals("on") ) {
            Cookie cookie = new Cookie("remember-id", username);  // 쿠키에 아이디 등록
            cookie.setMaxAge(60 * 60 * 24 * 7);                        // 유효기간 : 7일
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        // 아이디 저장 체크 ❌
        else {
            Cookie cookie = new Cookie("remember-id", username);  // 쿠키에 아이디 등록
            cookie.setMaxAge(0);                                // 유효기간 : 0 (삭제)
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        


        // 인증된 사용자 정보
        CustomUser customUser = (CustomUser) authentication.getPrincipal();                     
        Users user = customUser.getUser();

        log.info("아이디 : " + user.getUsername());
        log.info("비밀번호 : " + user.getPassword());
        log.info("권한 : " + user.getAuthList());


        // 세션에 로그인한 시간 Date 객체로 저장
        request.getSession().setAttribute("loginTime", new java.util.Date());
        // 세션에 username 저장
        request.getSession().setAttribute("username", user.getUsername());

        // 리다이렉트 처리
        // boolean autoplay = request.getParameter("autoplay") != null && Boolean.parseBoolean(request.getParameter("autoplay"));
        // if (autoplay) {
        //     getRedirectStrategy().sendRedirect(request, response, "/");
        // } else {
        //     getRedirectStrategy().sendRedirect(request, response, "/auto-x");
        // }

        String redirect = request.getParameter("redirect");
        redirect = redirect == null ? "/" : redirect;
        getRedirectStrategy().sendRedirect(request, response, redirect);

        super.onAuthenticationSuccess(request, response, authentication);
    }

    
    
}
