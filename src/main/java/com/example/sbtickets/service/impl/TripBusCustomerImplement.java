package com.example.sbtickets.service.impl;

import com.example.sbtickets.bean.*;
import com.example.sbtickets.entity.TripBusCustomer;

import java.util.List;

public interface TripBusCustomerImplement {
    public List<ObjectByTripBus> findByFirtLastPointObject(AllTripBusByLastPointBean allTripBusByLastPointBean);
    public boolean checkIfCustomerHadTicket(Integer tripBusId ,Integer customerId);
    public List<CountTripBusForMonth> getCountTripBusForMonth();
    public List<TripBusByCusomer> getListTripBusByCustomer(Integer id);
    public void insertTripBusCustomer(TripBusCustomer tripBusCustomer);
    public List<WagesDriverBean> getListWagesDriver(Integer driverId, String scrapTime);
    public void deleteTripBusCustomerById(Integer TripBusId);
}
