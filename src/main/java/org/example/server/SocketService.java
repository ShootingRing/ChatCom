package org.example.server;

import org.example.server.threads.ServerThread;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketService {
    public static List<Socket> socketList = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(5208);
        System.out.println(
                """
                          _____ _           _    _____               \s
                         / ____| |         | |  / ____|              \s
                        | |    | |__   __ _| |_| |     ___  _ __ ___ \s
                        | |    | '_ \\ / _` | __| |    / _ \\| '_ ` _ \\\s
                        | |____| | | | (_| | |_| |___| (_) | | | | | |
                         \\_____|_| |_|\\__,_|\\__|\\_____\\___/|_| |_| |_|
                """
        );

        System.err.println("Socket Service Launch Successfully!!");

        while(true){
            Socket socket = serverSocket.accept();
            System.err.println("user " + socket.getInetAddress() + ":" + socket.getPort() + " linked!");
            socketList.add(socket);
            new Thread(new ServerThread(socket)).start();
        }

        //serverSocket.close();
    }
}
