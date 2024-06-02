package com.restaurant.system.backend_restaurant_system.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;



@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Documentation")
                        .version("1.0.0"))
                .servers(Arrays.asList(
                        new Server().url("https://backend-restaurante.deyvids.dev/api/v1").description("Server") //Configurar la ip local o de servidor
                ));
    }
}
