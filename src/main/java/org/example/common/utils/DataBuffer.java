package org.example.common.utils;

import com.google.gson.Gson;
import org.example.common.entities.User;

import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DataBuffer {
    public static Socket socket;

    public static Gson gson = new Gson();

    public static Map<String, User> userMap = new HashMap<>();

    public static PrintWriter pw;
    public static BufferedReader br;
    public static ObjectOutputStream oos;
    public static ObjectInputStream ois;
}
