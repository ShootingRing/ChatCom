package org.example.common.entities;

import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.FileUtils;
import org.example.common.utils.Response;

import java.io.File;
import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.net.Socket;
import java.util.List;
import java.util.Objects;

import static org.example.common.utils.DataBuffer.*;
import static org.example.common.utils.ResponseCode.SUCCESS;
import static org.example.common.utils.ResponseCode.WRONG_PASSWORD_OR_ACCOUNT;

public class Login implements Serializable {
    @Serial
    private static final long serialVersionUID = -8504915906614662338L;
    public String acc;
    public String pwd;

    public User findUser(Login user, List<User> userList){
        for (User item : userList) {
//            System.out.println(item.account + "--" + user.acc);
            if(Objects.equals(user.acc, item.account) && Objects.equals(user.pwd, item.password)){
                return item;
            }
        }
        return null;
    }

    public Login(String acc, String pwd) {
        this.acc = acc;
        this.pwd = pwd;
    }

    /**
     * 服务端的登录请求处理
     */
    public void login(OnlineSocketStream currentSocket) {
        User user = findUser(this, userList);

        if(user != null){
            onlineList.add(user);
        }

        new Response(
                user==null ? WRONG_PASSWORD_OR_ACCOUNT : SUCCESS,
                user==null ? "wrong account or password" : "login success",
                user
        ).response(currentSocket);

    }

}
