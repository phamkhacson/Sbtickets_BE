package com.example.sbtickets.service.impl;

import com.example.sbtickets.entity.Customer;
import com.example.sbtickets.entity.TripBus;
import com.example.sbtickets.entity.TripBusCustomer;

public interface SendToEmailImplement{
    public void sendToEmail(int roleCar, TripBus tripBus, Customer customer);
}
