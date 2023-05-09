package org.example.server.threads;

import org.example.server.SocketService;
import org.example.server.tools.ColorOutput;
import org.fusesource.jansi.Ansi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;

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
                if(Objects.equals(str.split(" ")[2], "exit")) ColorOutput.output(str, Ansi.Color.RED);
                else System.out.println(str);
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
