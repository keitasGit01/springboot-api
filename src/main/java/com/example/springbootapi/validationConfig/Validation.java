package com.example.springbootapi.validationConfig;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class Validation {


    @Bean
    public ValidatingMongoEventListener eventListener(){
        return new ValidatingMongoEventListener(validatorFactoryBean());
    }

    @Bean
    public LocalValidatorFactoryBean validatorFactoryBean(){
        return new LocalValidatorFactoryBean();
    }
}
