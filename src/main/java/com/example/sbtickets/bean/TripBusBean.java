package com.example.sbtickets.bean;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
public class TripBusBean {
    private Integer tripBusId;
    private Integer busId;
    private Integer lineBusId;
    private Integer numberGuest;
    private Double priceTrip;
    private Date timeTrip;
    private Integer driverId;
    private Integer assistantBusId;

//    {
//        "tripBusId" : 15,
//            "busId" : 1,
//            "lineBusId": 2,
//            "numberGuest": 20,
//            "priceTrip": 12000,
//            "timeTrip": "2021-12-18",
//            "driverId": 2,
//            "assistantBusId": 1
//    }
}
