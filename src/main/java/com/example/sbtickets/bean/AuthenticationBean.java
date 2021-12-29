package com.example.sbtickets.bean;

import com.example.sbtickets.entity.Customer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationBean {
    private String token;
    private String message;
    private int status;
    private String role;
    private Integer customerId;

    public AuthenticationBean(){

    }
}
