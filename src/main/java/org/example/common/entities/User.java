package org.example.common.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.common.utils.UUIDGenerator;

import java.io.Serial;
import java.io.Serializable;

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
}
