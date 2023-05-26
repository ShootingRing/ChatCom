package org.example.common.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.FileUtils;
import org.example.common.entities.OnlineSocketStream;
import org.example.common.entities.User;

import java.io.*;
import java.net.Socket;
import java.util.*;

import static org.example.common.utils.JSONUtils.ReadJsonFile;

public class DataBuffer {
    public static Socket socket;

    public static Gson gson = new Gson();

    public static Map<String, User> userMap = new HashMap<>();
    public static List<User> userList = new ArrayList<>();
    public static List<User> onlineList = new ArrayList<>();
    public static Map<String, List<String>> friendsMap = new HashMap<>();

    public static BufferedReader br;
    public static ObjectOutputStream oos;
    public static ObjectInputStream ois;

    public static final String USERS_JSON_ADDRESS = "users_database.json";
    public static final String UUID_JSON_ADDRESS = "uuid.json";
    public static final String FRIENDS_JSON_ADDRESS = "users_friendList.json";

    public static List<String> uuidPool = new ArrayList<>();

    public static Map<String, OnlineSocketStream> socketMap = new HashMap<>();

    public static boolean isUuidExist(String uuid){
        for (String id : uuidPool){
            if(Objects.equals(id, uuid)){
                return true;
            }
        }
        return false;
    }

    public static void initData() throws IOException {
        System.out.println("init DataBuffer...");
        //读取Json文件
        readFromJson();
    }

    public static void updateData() throws IOException {
        System.out.println("updating DataBuffer...");
        //读取Json文件
        readFromJson();
    }

    private static void readFromJson() throws IOException {
        String content = ReadJsonFile(USERS_JSON_ADDRESS);
        userList = gson.fromJson(content, new TypeToken<List<User>>(){}.getType());

        for (User user : userList){
            userMap.put(user.uuid, user);
        }

        //读取uuid
        String uuids = ReadJsonFile(UUID_JSON_ADDRESS);
        uuidPool = gson.fromJson(uuids, new TypeToken<List<String>>(){}.getType());

        String friendsJson = ReadJsonFile(FRIENDS_JSON_ADDRESS);
        friendsMap = gson.fromJson(friendsJson, new TypeToken<Map<String, List<String>>>(){}.getType());
    }
}
