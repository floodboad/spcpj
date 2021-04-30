package com.ocean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.stereotype.Component;

@Component
@EnableSpringConfigured
public class TestConfig {
    public int id = 3;
}
