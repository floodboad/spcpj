package com.ocean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableTurbine
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "turbine!" );
        SpringApplication.run(App.class, args);
    }
}
