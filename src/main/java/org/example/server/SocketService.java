package org.example.server;

import org.example.common.utils.DataBuffer;
import org.example.common.utils.Response;
import org.example.server.threads.ServerThread;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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

        while(true){
            Socket socket = serverSocket.accept();
            DataBuffer.socket = socket;
            System.err.println("user " + socket.getInetAddress() + ":" + socket.getPort() + " linked!");

            socketList.add(socket);

            new Thread(new ServerThread(socket)).start();
        }

        //serverSocket.close();
    }
}
