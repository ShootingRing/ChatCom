package org.example.server.threads;

import org.example.common.entities.Login;
import org.example.common.entities.OnlineSocketStream;
import org.example.common.utils.Request;
import org.example.server.SocketService;

import java.io.*;
import java.net.Socket;

import static org.example.common.utils.DataBuffer.br;
import static org.example.common.utils.DataBuffer.gson;
import static org.example.common.utils.RequestHandler.login;
import static org.example.common.utils.RequestHandler.register;

public class ServerThread implements Runnable {
    public Socket socket;
    public ServerThread(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            System.out.println("creating SocketStream...");
            OnlineSocketStream currentSocket = OnlineSocketStream.builder()
                    .oos(new ObjectOutputStream(socket.getOutputStream()))
                    .ois(new ObjectInputStream(socket.getInputStream()))
                    .build();

            while (socket.isConnected()){
                try{
                    Request req = (Request)currentSocket.getOis().readObject();
                    System.out.println("request:" + gson.toJson(req));
                    switch (req.type){
                        case LOGIN:
                            login(req.content, currentSocket);
                            break;
                        case REGISTER:
                            System.out.println("registering...");
                            register(req.content, currentSocket);
                            break;
                        default:
                            break;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    System.err.println("user " + socket.getInetAddress() + ":" + socket.getPort() + " disconnected!");
                    break;
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
