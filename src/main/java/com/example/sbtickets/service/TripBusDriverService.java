package com.example.sbtickets.service;

import com.example.sbtickets.entity.TripBusCustomer;
import com.example.sbtickets.entity.TripBusDriver;
import com.example.sbtickets.repository.TripBusCustomerRepository;
import com.example.sbtickets.repository.TripBusDriverRepository;
import com.example.sbtickets.service.impl.TripBusDriverImplement;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
public class TripBusDriverService implements TripBusDriverImplement {

    private static final Logger logger = Logger.getLogger(TripBusDriverService.class);

    @Autowired
    TripBusDriverRepository tripBusDriverRepository;

    @Autowired
    TripBusCustomerRepository tripBusCustomerRepository;

    @Override
    public void insertTripBusDriver(TripBusDriver tripBusDriver) {
        try {
            tripBusDriverRepository.save(tripBusDriver);
        }
        catch (Exception ex){
            logger.error(ex.getMessage(),ex);
        }
    }

    @Override
    public void deleteTripBusDriver(Integer tripBusId) {
        try {
            tripBusDriverRepository.deleteTripBusDriver(tripBusId);
        }
        catch (Exception ex){
            logger.error(ex.getMessage(),ex);
        }
    }

    @Override
    public void editTripBusDriver(TripBusDriver tripBusDriver) {
        try {
            tripBusDriverRepository.editTripBusDriver(tripBusDriver.getDate(),  tripBusDriver.getWages(), tripBusDriver.getScrapDateTime(),tripBusDriver.getDriver().getId(), tripBusDriver.getTripbus().getId(), tripBusDriver.getRoleCar());
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
        }

    }

    @Override
    public List<TripBusDriver> getListBusDriver() {
        try {
            return  tripBusDriverRepository.findAll();
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean checkRoleCar(Integer roleCar) {
        try {
            for(TripBusCustomer u : tripBusCustomerRepository.findAll()){
                if(u.getRoleCar() == roleCar){
                    return  true;
                }
            }

        }
        catch (Exception ex){
            logger.error(ex.getMessage());
        }
        return false;
    }
}
