package com.ocean;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Configurable(preConstruction = true,autowire = Autowire.BY_TYPE)
@Component
public class MqttPushClient {
    private static final Logger logger = LoggerFactory.getLogger(MqttPushClient.class);
    private String url = "tcp://127.0.0.1:61613";
    private String clientId = "mqttProducer";

    @Autowired
    MqttConfig config;
    private static final byte[] WILL_DATA;

    static {
        WILL_DATA = "offline".getBytes();
    }

    private static volatile MqttPushClient mqttPushClient = null;

    @Bean
    public static MqttPushClient getInstance() throws MqttException {

        if (null == mqttPushClient) {
            synchronized (MqttPushClient.class) {
                if (null == mqttPushClient) {
                    mqttPushClient = new MqttPushClient();
                }
            }
        }
        return mqttPushClient;
    }


    public MqttPushClient() throws MqttException {
        connect();
    }

    public MqttPushClient(int a){

    }

    private MqttClient client;

    private void connect() throws MqttException {
        System.out.println(this.config);
        try {
            client = new MqttClient(url, clientId, new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，
            // 这里设置为true表示每次连接到服务器都以新的身份连接
            options.setCleanSession(true);
            // 设置连接的用户名
            options.setUserName("admin");
            // 设置连接的密码
            options.setPassword("password".toCharArray());
            String[] uris = new String[]{"tcp://182.92.68.73:1883"};
            options.setServerURIs(uris);
            // 设置超时时间 单位为秒
            options.setConnectionTimeout(100);
            // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送心跳判断客户端是否在线，但这个方法并没有重连的机制
            options.setKeepAliveInterval(20);
            // 设置“遗嘱”消息的话题，若客户端与服务器之间的连接意外中断，服务器将发布客户端的“遗嘱”消息。
            options.setWill("willTopic", WILL_DATA, 2, false);
            client.setCallback(new PushCallback());
            IMqttToken tok = client.connectWithResult(options);
            System.out.println(tok);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发布，默认qos为0，非持久化
     *
     * @param topic
     * @param pushMessage
     */
    public void publish(String topic, String pushMessage) {
        publish(0, false, topic, pushMessage);
    }

    /**
     * 发布
     *
     * @param qos
     * @param retained
     * @param topic
     * @param pushMessage
     */
    public void publish(int qos, boolean retained, String topic, String pushMessage) {
        MqttMessage message = new MqttMessage();
        message.setQos(qos);
        message.setRetained(retained);
        message.setPayload(pushMessage.getBytes());
        MqttTopic mTopic = client.getTopic(topic);
        if (null == mTopic) {
            logger.error("topic not exist");
        }
        MqttDeliveryToken token;
        try {
            token = mTopic.publish(message);
            token.waitForCompletion();
        } catch (MqttPersistenceException e) {
            e.printStackTrace();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    /**
     * 订阅某个主题，qos默认为0
     *
     * @param topic
     */
    public void subscribe(String topic) {
        subscribe(topic, 0);
    }

    /**
     * 订阅某个主题
     *
     * @param topic
     * @param qos
     */
    public void subscribe(String topic, int qos) {
        try {
            client.subscribe(topic, qos);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
