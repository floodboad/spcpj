package com.ocean;

import brave.sampler.Sampler;
import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import com.ocean.socketio.handler.ExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class App
{
    public static void main( String[] args )
    {
        System.out.println( "socket io service start!" );
        SpringApplication.run(App.class,args);
    }

    /**
     * 注册netty-socketio服务端
     * @author liangxifeng 2018-07-07
     * @return
     */
    @Bean
    public SocketIOServer socketIOServer() {
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();

        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")){   //在本地window环境测试时用localhost
            System.out.println("this is  windows");
            config.setHostname("192.168.10.109");
        } else {
            config.setHostname("0.0.0.0");
        }
        config.setPort(9093);
//        解决端口占用问题
        SocketConfig socketConfig=config.getSocketConfig();
        if(!socketConfig.isReuseAddress()){
            socketConfig.setReuseAddress(true);
            System.out.println("是否绑定了: "+socketConfig.isReuseAddress());
        }
        config.setSocketConfig(socketConfig);
        config.setExceptionListener(new ExceptionHandler());

        /*config.setAuthorizationListener(new AuthorizationListener() {//类似过滤器
            @Override
            public boolean isAuthorized(HandshakeData data) {
                //http://localhost:8081?username=test&password=test
                //例如果使用上面的链接进行connect，可以使用如下代码获取用户密码信息，本文不做身份验证
                // String username = data.getSingleUrlParam("username");
                // String password = data.getSingleUrlParam("password");
                return true;
            }
        });*/

        final SocketIOServer server = new SocketIOServer(config);
        return server;
    }

    /**
     * tomcat启动时候，扫码socket服务器并注册
     * @param socketServer
     * @return
     */
    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketServer) {
        return new SpringAnnotationScanner(socketServer);
    }
}
