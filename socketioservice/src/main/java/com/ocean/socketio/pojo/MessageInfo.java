package com.ocean.socketio.pojo;

import org.springframework.stereotype.Component;

@Component
public class MessageInfo {

    String msgContent;

    public MessageInfo() {

    }

    public MessageInfo(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    @Override
    public String toString() {
        return "MessageInfo{" +
                "msgContent='" + msgContent + '\'' +
                '}';
    }
}
