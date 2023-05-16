package org.example.server;

import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.FileUtils;
import org.example.common.entities.User;
import org.example.common.utils.DataBuffer;
import org.example.common.utils.Response;
import org.example.server.threads.ServerThread;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.example.common.utils.DataBuffer.*;
import static org.example.common.utils.ResponseCode.SUCCESS;

public class SocketService {
    public static List<Socket> socketList = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(5208);
        System.out.println(
                """           
                     ██████╗ ██╗  ██╗  █████╗  ████████╗  ██████╗  ██████╗  ███╗   ███╗
                    ██╔════╝ ██║  ██║ ██╔══██╗ ╚══██╔══╝ ██╔════╝ ██╔═══██╗ ████╗ ████║
                    ██║      ███████║ ███████║    ██║    ██║      ██║   ██║ ██╔████╔██║
                    ██║      ██╔══██║ ██╔══██║    ██║    ██║      ██║   ██║ ██║╚██╔╝██║
                    ╚██████╗ ██║  ██║ ██║  ██║    ██║    ╚██████╗ ╚██████╔╝ ██║ ╚═╝ ██║
                     ╚═════╝ ╚═╝  ╚═╝ ╚═╝  ╚═╝    ╚═╝     ╚═════╝  ╚═════╝  ╚═╝     ╚═╝
                """
        );
        System.err.println("Socket Service Launch Successfully!!");

        initData();

        while(true){
            Socket socket = serverSocket.accept();
            System.err.println("user " + socket.getInetAddress() + ":" + socket.getPort() + " linked!");

            socketList.add(socket);

            new Thread(new ServerThread(socket)).start();
        }

        //serverSocket.close();
    }
}
