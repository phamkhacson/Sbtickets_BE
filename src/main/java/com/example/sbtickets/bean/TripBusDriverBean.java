package com.example.sbtickets.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class TripBusDriverBean {
    private Integer tripBusId;
    private Integer busId;
    private Integer lineBusId;
    private Integer numberGuest;
    private Double priceTrip;
    private Date timeTrip;
    private Integer driverId;
    private String driverName;
    private Integer assistantBusId;
    private String assistantName;

    public TripBusDriverBean() {
    }
}
