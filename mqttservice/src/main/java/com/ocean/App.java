package com.ocean;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

/**
 * Hello world!
 *
 */
//@EnableAutoConfiguration(exclude = {
//        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
//})
@SpringBootApplication
@EnableEurekaClient
//@EnableSpringConfigured
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "mqtt server start!" );
        SpringApplication.run(App.class,args);
        //订阅主题，之后控制台打印消息。证明整合成功
        try {
            MqttPushClient.getInstance().subscribe("topic1");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
