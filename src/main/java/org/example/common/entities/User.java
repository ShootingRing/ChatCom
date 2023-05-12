package org.example.common.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class User {
    public String username;
    public String account;
    public String password;
    public Integer groupId;
    public String uuid;
}
