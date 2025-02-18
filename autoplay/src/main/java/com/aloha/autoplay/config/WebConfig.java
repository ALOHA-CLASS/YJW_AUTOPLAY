package com.aloha.autoplay.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**", "/css/**", "/js/**", "/fonts/**")
                .addResourceLocations("classpath:/static/img/", "classpath:/static/css/", "classpath:/static/js/", "classpath:/static/fonts/")
                .setCachePeriod(3600 * 24 * 30); // 30일 동안 캐시
    }
}