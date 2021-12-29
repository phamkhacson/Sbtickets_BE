package com.example.sbtickets.bean;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;

@Getter
@Setter
public class CountTripBusForMonth {
    private Integer count;
    private Integer week;

    public CountTripBusForMonth(Integer count, Integer week) {
        this.count = count;
        this.week = week;
    }
}
