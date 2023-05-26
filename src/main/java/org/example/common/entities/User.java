package org.example.common.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.common.utils.Response;
import org.example.common.utils.ResponseCode;
import org.example.common.utils.UUIDGenerator;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.example.common.utils.DataBuffer.*;

@Getter
@Setter
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = -1234915906614662334L;

    public String username;
    public String account;
    public String password;
    public Integer groupId;
    public String uuid;

    public User(String username, String account, String password, Integer groupId) {
        this.username = username;
        this.account = account;
        this.password = password;
        this.groupId = groupId;

        this.uuid = UUIDGenerator.generateUUID();
    }

    public void getFriends(OnlineSocketStream currentSocket){
        Map<String, String> myFriendsMap = new HashMap<>();

        for (String itUuid : friendsMap.get(uuid)){
            myFriendsMap.put(itUuid, userMap.get(itUuid).username);
        }

        new Response(
                ResponseCode.SUCCESS,
                "get friends map",
                myFriendsMap
        ).response(currentSocket);
    }

    public void getOnlineFriends(OnlineSocketStream currentSocket){
        List<String> myOnlineList = new ArrayList<>();

        for (User user : onlineList){
            if(friendsMap.get(uuid).contains(user.uuid))
                myOnlineList.add(user.uuid);
        }

        new Response(
                ResponseCode.SUCCESS,
                "get online friends list",
                myOnlineList
        ).response(currentSocket);
    }
}
