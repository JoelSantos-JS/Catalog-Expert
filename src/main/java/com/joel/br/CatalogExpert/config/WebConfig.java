package com.joel.br.CatalogExpert.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {


    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }
}
