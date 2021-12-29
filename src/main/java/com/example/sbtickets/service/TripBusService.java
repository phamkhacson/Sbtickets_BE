package com.example.sbtickets.service;

import com.example.sbtickets.bean.AllTripBusByLastPointBean;
import com.example.sbtickets.bean.CountTripBusForMonth;
import com.example.sbtickets.bean.ObjectByTripBus;
import com.example.sbtickets.bean.TripBusByCusomer;
import com.example.sbtickets.entity.Customer;
import com.example.sbtickets.entity.TripBusCustomer;
import com.example.sbtickets.repository.TripBusCustomerRepository;
import org.apache.log4j.Logger;
import com.example.sbtickets.entity.TripBus;
import com.example.sbtickets.repository.TripBusRepository;
import com.example.sbtickets.service.impl.TripBusImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service("TripBusService")
public class TripBusService implements TripBusImplement {
    private static final Logger logger = Logger.getLogger(TripBusService.class);
    @Autowired
    TripBusRepository tripBusRepository;


    @Override
    public TripBus createTripBus(TripBus tripBus) {
        try {
            return tripBusRepository.save(tripBus);
        }
        catch (Exception ex){
            logger.error(ex.getMessage(), ex);
            return null;
        }
    }

    @Override
    public void deleteTripBus(List<Integer> ids) {
        try {
            tripBusRepository.deleteAllById(ids);
        }
        catch (Exception ex){
            logger.error(ex.getMessage(), ex);
        }
    }

    @Override
    public void updateTripBus(TripBus tripBus) {
        try {
            tripBusRepository.save(tripBus);
        }
        catch (Exception ex){
            logger.error(ex.getMessage(), ex);
        }
    }

    @Override
    public TripBus findTripBusById(Integer id) {
        try{
            return tripBusRepository.findById(id).get();
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<TripBus> listTripBus() {
        try{
            return tripBusRepository.findAll();
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
        }
        return null;
    }

    @Override
    public TripBus findTripBus(Integer id) {
        try{
            return tripBusRepository.findById(id).get();
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
        }
        return null;
    }
}
