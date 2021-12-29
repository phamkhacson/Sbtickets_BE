package com.example.sbtickets.service.impl;

import com.example.sbtickets.bean.BusBean;
import com.example.sbtickets.entity.Bus;
import java.util.List;
import java.util.Optional;

public interface BusImplement {
    public List<Bus> getBus();
    public Bus getBusById(Integer id);
    public Bus findBus(Integer carNumber);
    public Bus createBus(Bus bus);
    public void updateBus(Integer id, Bus bus);
    public void deleteBus(Integer id);
    public void deleteBuses(List<Integer> ids);
}
