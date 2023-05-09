package org.example.server.threads;

import org.example.server.SocketService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread implements Runnable {
    public Socket socket;
    public ServerThread(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true){
                String str = br.readLine();
                System.out.println(str);
                for (Socket item : SocketService.socketList){
                    PrintWriter pw = new PrintWriter(item.getOutputStream());
                    pw.println(str);
                    pw.flush();
                }
            }
        }catch(IOException e){
//            e.printStackTrace();
        }
    }
}
