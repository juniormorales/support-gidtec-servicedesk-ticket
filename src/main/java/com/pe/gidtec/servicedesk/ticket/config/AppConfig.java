package com.pe.gidtec.servicedesk.ticket.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pe.gidtec.servicedesk.lib.config.HttpHeaderArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;

@Configuration
public class AppConfig implements WebFluxConfigurer {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void configureArgumentResolvers(ArgumentResolverConfigurer configurer) {
        configurer.addCustomResolver(new HttpHeaderArgumentResolver(objectMapper));
    }


}
