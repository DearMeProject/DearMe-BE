package com.dearme.backend.dearmebe.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // 배포, 로컬 두 개 만들기.
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE",  "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(false);
    }
}
