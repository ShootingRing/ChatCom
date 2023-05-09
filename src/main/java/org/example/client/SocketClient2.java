package org.example.client;

import org.example.client.threads.ClientGetThread;
import org.example.client.threads.ClientSendThread;

import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient2 {
    public static String name = "client 2";
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 5208);
        System.out.println(name + " linking...");

        new Thread(new ClientSendThread(socket, name)).start();
        new Thread(new ClientGetThread(socket)).start();

        Thread t = new Thread(() -> {
            try  {
                System.err.println(name + " exit");
                PrintWriter pw = new PrintWriter(socket.getOutputStream());
                pw.println(name + " exit");
                pw.flush();
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        Runtime.getRuntime().addShutdownHook(t);
    }
}
