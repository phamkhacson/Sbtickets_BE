package com.example.sbtickets.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBean {
    private String username;
    private String password;

    public UserBean(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserBean(){

    }

}
