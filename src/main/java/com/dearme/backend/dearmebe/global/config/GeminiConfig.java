package com.dearme.backend.dearmebe.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class GeminiConfig {

    @Bean
    public RestTemplate geminiRestTemplate() {
        var factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(3000);   // 연결 타임아웃 3초
        factory.setReadTimeout(7000);      // 응답 타임아웃 7초

        return new RestTemplate(factory);
    }
}
