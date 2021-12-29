package com.example.sbtickets.service.impl;

import com.example.sbtickets.entity.TripBusAddress;

import java.util.List;

public interface TripBusAddressImplement {
    public  List<TripBusAddress> listAddress();
    public TripBusAddress findTripBusAddress(Integer id);
    public List<TripBusAddress> getTripBusAddress();
    public TripBusAddress getTripBusAddressById(Integer id);
    public void createTripBusAddress(TripBusAddress tripbusAddress);
    public void updateTripBusAddress(Integer id, TripBusAddress tripbusAddress);
    public void deleteTripBusAddress(Integer id);
    public void deleteTripBusAddresses(List<Integer> ids);
}
