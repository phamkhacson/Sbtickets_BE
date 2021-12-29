package com.example.sbtickets.service.impl;

import com.example.sbtickets.bean.RevenueBusBean;

import java.util.List;


public interface RevenueBusImplement {
    public List<RevenueBusBean> listBusRevenue();
    public List<RevenueBusBean> getDriverById(Integer id);
}
