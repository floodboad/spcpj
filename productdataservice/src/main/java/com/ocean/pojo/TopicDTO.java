package com.ocean.pojo;

import javax.validation.constraints.NotNull;

/**
 * @author yangjingsheng
 * @version 1.0
 * @date 2021/4/13 14:21
 */
public class TopicDTO {
    @NotNull
    private Long deviceId;
    @NotNull
    private Object message;
    @NotNull
    private Integer qos;
    @NotNull
    private Integer retain;
    @NotNull
    private String topic;

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Integer getQos() {
        return qos;
    }

    public void setQos(Integer qos) {
        this.qos = qos;
    }

    public Integer getRetain() {
        return retain;
    }

    public void setRetain(Integer retain) {
        this.retain = retain;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "TopicDTO{" +
                "deviceId=" + deviceId +
                ", message=" + message +
                ", qos=" + qos +
                ", retain=" + retain +
                ", topic='" + topic + '\'' +
                '}';
    }
}
