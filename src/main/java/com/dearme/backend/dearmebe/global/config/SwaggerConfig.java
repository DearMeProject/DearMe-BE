package com.dearme.backend.dearmebe.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {

        Server httpsServer = new Server()
                .url("https://43-200-255-137.nip.io")   // <-- HTTPS 주소
                .description("배포 서버");

        return new OpenAPI()
                .info(new Info()
                        .title("DearMe REST API")
                        .description("DearMe - REST API Swagger 문서")
                        .version("v1.0.0"))
                .servers(java.util.List.of(httpsServer));
    }
}
