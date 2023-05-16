package org.example.common.utils;

import org.example.common.entities.Login;
import org.example.common.entities.OnlineSocketStream;
import org.example.common.entities.Register;

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
}
