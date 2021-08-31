package com.ocean.socketio.handler;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ExceptionListener;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Yang Jingsheng
 * @version 1.0
 * @date 2021/6/11 14:23
 */
public class ExceptionHandler implements ExceptionListener {

    @Override
    public void onEventException(Exception e, List<Object> args, SocketIOClient client) {
        System.out.println("9999999999" + "------------------------");
    }

    @Override
    public void onDisconnectException(Exception e, SocketIOClient client) {
        System.out.println("9999999999" + "------------------------");
    }

    @Override
    public void onConnectException(Exception e, SocketIOClient client) {
        System.out.println("9999999999" + "------------------------");
    }

    @Override
    public void onPingException(Exception e, SocketIOClient client) {
        System.out.println("9999999999" + "------------------------");
    }

    @Override
    public boolean exceptionCaught(ChannelHandlerContext ctx, Throwable e) throws Exception {
        System.out.println("9999999999" + "------------------------");
        return false;
    }
}
