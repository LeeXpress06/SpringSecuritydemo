package com.Camp.SpringSecurity.SpringSecuritydemo.WorkerJavaConfig;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfiguration {

    @Bean
    public ModelMapper getModelMapper ( ){

        return new ModelMapper();
    }





}
