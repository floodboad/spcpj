package com.ocean;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MqttController {
    @Autowired
    MqttConfig mqttConfig;
    @Autowired
    TestConfig testConfig;
    @Autowired
    Dog dog;
    /**
     * 发送MQTT消息
     *
     * @param message 消息内容
     * @return 返回
     */

    @ResponseBody
    @GetMapping(value = "/mqtt")
    public ResponseEntity<String> sendMqtt(@RequestParam(value = "msg") String message) throws MqttException {
//        Account account = new Account();
//        account.output();
//        TestConfigurable testConfigurable = new TestConfigurable();
//        testConfigurable.xx();
//        System.out.println(testConfig.id + ";;;;;;;;;========;;;;;");
//        MqttPushClient mqttPushClient = new MqttPushClient(3);
        String kdTopic = "topic1";
        MqttPushClient.getInstance().publish(kdTopic, "稍微来点鸡血");
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
