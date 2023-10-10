package com.lion.bonobono.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
//                .components(new Components().addSecuritySchemes("bearer-key",
//                        new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
//                .info(apiInfo())
//                .addSecurityItem(new SecurityRequirement().addList("bearer-key"));



    }
    private Info apiInfo() {
        return new Info()
                .title("BonoBono API 명세서")
                .description("Springdoc을 사용한 명세서")
                .version("1.0.0");
    }




}
