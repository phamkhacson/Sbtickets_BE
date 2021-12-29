package com.example.sbtickets.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WrapperResponse<T>{
    private Integer status;
    private T body;
    private String msg;

    public WrapperResponse() {
        super();
    }

    public WrapperResponse(Integer status, T body) {
        super();
        this.status = status;
        this.body = body;
    }
}
