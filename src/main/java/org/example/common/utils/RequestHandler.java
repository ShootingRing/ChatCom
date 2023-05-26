package org.example.common.utils;

import org.example.common.entities.Login;
import org.example.common.entities.OnlineSocketStream;
import org.example.common.entities.Register;
import org.example.common.entities.User;

import java.io.IOException;
import java.net.Socket;

import static org.example.common.utils.DataBuffer.gson;

public class RequestHandler {
    public static void login(Object content, OnlineSocketStream currentSocket) {
        Login log = (Login) content;
        log.login(currentSocket);
//        gson.fromJson(content, Login.class).login(socket);
    }

    public static void register(Object content, OnlineSocketStream currentSocket) throws IOException {
        Register register = (Register) content;
        register.register(currentSocket);
    }

    public static void getFriends(Object content, OnlineSocketStream currentSocket) {
        User user = (User) content;
        user.getFriends(currentSocket);
    }

    public static void getOnlineFriends(Object content, OnlineSocketStream currentSocket) {
        User user = (User) content;
        user.getOnlineFriends(currentSocket);
    }

    public static void sendMessage(Object content, OnlineSocketStream currentSocket) throws IOException { //这个currentSocket传入的是chatSocket
        Message message = (Message) content;
        message.sendMessage(currentSocket);
    }
}
