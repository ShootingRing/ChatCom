package org.example.client.threads;

import org.example.server.tools.ColorOutput;
import org.fusesource.jansi.Ansi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Objects;

public class ClientGetThread implements Runnable {
    public Socket socket;

    public ClientGetThread(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            while(true) {
                String str = br.readLine();
                if(Objects.equals(str.split(" ")[2], "exit")) ColorOutput.output(str, Ansi.Color.RED);
                else System.out.println(str);
            }
        }catch (IOException e){
            System.out.println("error");
        }
    }
}
