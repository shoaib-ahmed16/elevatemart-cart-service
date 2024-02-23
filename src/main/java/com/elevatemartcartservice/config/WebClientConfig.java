package com.elevatemartcartservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public interface WebClientConfig {
    @Bean
    default WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

}
