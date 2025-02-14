package com.aloha.autoplay.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.stereotype.Component;

import com.aloha.autoplay.domain.UseTime;
import com.aloha.autoplay.service.UseTimeService;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SessionEventListener implements ApplicationListener<SessionDestroyedEvent>, HttpSessionListener {

    @Autowired
    private UseTimeService useTimeService;

    @Override
    public void onApplicationEvent(SessionDestroyedEvent event) {
        event.getSecurityContexts().forEach(securityContext -> {
            String username = securityContext.getAuthentication().getName();
            log.info("세션 종료 사용자: " + username);
        });
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // 세션 생성 시 로직
        log.info("세션 생성 시간: " + new Date(se.getSession().getCreationTime()));  
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // 세션 종료 시 로직
        long creationTime = se.getSession().getCreationTime();              // 세션 생성 시간 (브라우저 접속 시간)
        long lastAccessedTime = se.getSession().getLastAccessedTime();      // 세션 종료 시간 (브라우저 종료 시간)

        Date stdTime = new Date(creationTime);
        Date endTime = new Date(lastAccessedTime);
        Long useTime = lastAccessedTime - creationTime;                   // 세션 사용 시간 (밀리초 단위)
        Date loginTime = (Date) se.getSession().getAttribute("loginTime");
        if (loginTime == null) {
            loginTime = stdTime; // 기본적으로 세션 생성 시간을 로그인 시간으로 설정
        }

        UseTime useTimeEntity = new UseTime();
        useTimeEntity.setStdTime(stdTime);
        useTimeEntity.setEndTime(endTime);
        useTimeEntity.setUseTime(useTime);
        useTimeEntity.setLoginTime(loginTime);

        String username = (String) se.getSession().getAttribute("username");
        if (username != null) {
            useTimeEntity.setUsername(username);
        }

        boolean result = useTimeService.create(useTimeEntity);       
        if( result ) {
            System.out.println("사용시간 저장 성공");
        } else {
            System.out.println("사용시간 저장 실패");
        }
        System.out.println("세션 종료 시간: " + endTime);
    }
}