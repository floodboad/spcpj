package com.ocean;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;

@Configuration
@EnableLoadTimeWeaving
@EnableSpringConfigured
public class Config {

    // 这个Bean将会被注入到Account的属性中
    @Bean
    Dog dog(){
        Dog d = new Dog();
        d.setId(1);
        d.setName("dog");
        return d;
    }

    @Bean
    public InstrumentationLoadTimeWeaver loadTimeWeaver() throws Throwable {
        InstrumentationLoadTimeWeaver loadTimeWeaver = new InstrumentationLoadTimeWeaver();
        return loadTimeWeaver;
    }
}