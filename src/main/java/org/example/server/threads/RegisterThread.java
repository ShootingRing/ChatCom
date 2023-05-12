package org.example.server.threads;

import java.io.*;
import java.net.Socket;

public class RegisterThread implements Runnable {

    public Socket socket;

    public RegisterThread(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        System.out.println("someone is registering...");
        try(
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter pw = new PrintWriter(socket.getOutputStream());
        ) {

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
