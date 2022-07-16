package com.azarot.homework.hw17.configuration;

import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    @Bean
    public RestTemplate createRestTemplate() {
        var requestFactory = new HttpComponentsClientHttpRequestFactory();
        var httpClient = HttpClientBuilder.create()
                .setRedirectStrategy(new LaxRedirectStrategy())
                .build();
        requestFactory.setHttpClient(httpClient);
        return new RestTemplate(requestFactory);
    }

//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(
//            ServerHttpSecurity http) {
//        return http.authorizeExchange()
//                .pathMatchers("/actuator/**").permitAll()
//                .anyExchange().authenticated()
//                .and().build();
//    }
}
