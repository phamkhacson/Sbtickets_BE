package com.example.sbtickets.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class TripBusByCusomer {
    private String fullName;
    private String cmt;
    private String address;
    private Date birthday;
    private Integer bookseat;
    private Integer tripBus;
}
