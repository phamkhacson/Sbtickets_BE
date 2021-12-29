package com.example.sbtickets.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TripBusCustomerBean {
    private Integer tripBusId;
    private Integer customerId;
    private Integer seatBooked;
}
