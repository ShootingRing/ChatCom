package org.example.server.threads;

import org.example.common.entities.OnlineSocketStream;
import org.example.common.utils.Request;
import org.example.common.utils.RequestType;

import java.io.*;
import java.net.Socket;

import static org.example.common.utils.DataBuffer.gson;
import static org.example.common.utils.DataBuffer.socketMap;
import static org.example.common.utils.RequestHandler.*;

public class ChatThread implements Runnable {

    public Socket socket;

    public ChatThread(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            System.out.println("creating ChatSocketStream...");
            OnlineSocketStream currentSocket = OnlineSocketStream.builder()
                    .oos(new ObjectOutputStream(socket.getOutputStream()))
                    .ois(new ObjectInputStream(socket.getInputStream()))
                    .build();

            Request hello = (Request)currentSocket.getOis().readObject(); //建立连接后首先由客户端发送HELLO请求并附带UUID信息，不返回响应
            if(hello.type != RequestType.HELLO){
                System.err.println("Something Wrong!");
                System.err.flush();
                return;
            }
            socketMap.put((String)hello.content, currentSocket); //建立socketMap(UUID -> OnlineSocketStream)

            while (socket.isConnected()){
                try{
                    Request req = (Request)currentSocket.getOis().readObject();
                    System.out.println("request:" + gson.toJson(req));
                    switch (req.type){
                        case SEND_MESSAGE:
                            sendMessage(req.content, currentSocket);
                            break;
                        default:
                            break;
                    }
                }catch (Exception e){
                    System.err.println("chatter " + socket.getInetAddress() + ":" + socket.getPort() + " disconnected!");
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
