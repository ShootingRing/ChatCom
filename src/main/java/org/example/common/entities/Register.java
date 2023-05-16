package org.example.common.entities;

import org.example.common.utils.Request;
import org.example.common.utils.Response;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

import static org.example.common.utils.DataBuffer.*;
import static org.example.common.utils.JSONUtils.WriteToJsonFile;
import static org.example.common.utils.RequestType.LOGIN;
import static org.example.common.utils.RequestType.REGISTER;
import static org.example.common.utils.ResponseCode.*;

public class Register implements Serializable {
    @Serial
    private static final long serialVersionUID = -8504915906614662343L;
    public String username;
    public String password;
    public String account;

    public Register(String username, String password, String account){
        this.username = username;
        this.password = password;
        this.account = account;
    }

    public void register(OnlineSocketStream currentSocket) throws IOException {

        //验证用户名或账号是否已存在
        System.out.println("validating register data...");
        for (User item : userList){
            System.out.println("userList viewing...");
            if(Objects.equals(item.username, username)){
                System.out.println("username exists");
                new Response(
                        USERNAME_EXISTS,
                        "username exists",
                        null
                ).response(currentSocket);
                return;
            }

            if(Objects.equals(item.account, account)){
                System.out.println("account exists");
                new Response(
                        ACCOUNT_EXISTS,
                        "account exists",
                        null
                ).response(currentSocket);
                return;
            }
        }

        //默认groupId
        int groupId = 0;

        User user = new User(
                username,
                password,
                account,
                groupId
        );

        System.out.println("updateData...");
        userList.add(user);
        WriteToJsonFile(gson.toJson(userList), USERS_JSON_ADDRESS);
        WriteToJsonFile(gson.toJson(uuidPool), UUID_JSON_ADDRESS);

        updateData();

        System.out.println("register success");
        new Response(
                SUCCESS,
                "register success",
                null
        ).response(currentSocket);
    }
}
