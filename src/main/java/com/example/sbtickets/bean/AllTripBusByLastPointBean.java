package com.example.sbtickets.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class AllTripBusByLastPointBean {
    private Integer firstPoint;
    private Integer lastPoint;
    private String dateTime;
}
