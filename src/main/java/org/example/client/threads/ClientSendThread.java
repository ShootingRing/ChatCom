package org.example.client.threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;

public class ClientSendThread implements Runnable {
    public Socket socket;
    public String clientName;

    public ClientSendThread(Socket socket, String name) {
        this.socket = socket;
        this.clientName = name;
    }
    @Override
    public void run() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter pw = new PrintWriter(socket.getOutputStream())) {
            while(true) {
                String str = br.readLine();
                if(Objects.equals(str, "")) continue;
                pw.println(clientName + ":" + str);
                pw.flush();
            }
        }catch (IOException e){
            System.out.println("error");
        }
    }
}
