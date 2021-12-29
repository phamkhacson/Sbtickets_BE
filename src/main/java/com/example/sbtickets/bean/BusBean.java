package com.example.sbtickets.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BusBean {
    private Integer id;
    private Integer carNumber;
    private String color;
    private String manufacturer;
    private String lifeCar;
    private Integer numberSeats;
    private Integer yearUse;
    private Date dateMantain;
}
