package com.example.sbtickets.service;
import com.example.sbtickets.controller.TripBusController;
import com.example.sbtickets.entity.Bus;
import com.example.sbtickets.repository.BusRepository;
import com.example.sbtickets.service.impl.BusImplement;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusService implements BusImplement {
    private static final Logger logger = Logger.getLogger(BusService.class);
    @Autowired
    BusRepository busRepository;
    @Override
    public List<Bus> getBus() {
       try{
           List<Bus> listBus = busRepository.findAll();
           return  listBus;
       }
       catch (Exception ex){
           logger.error(ex);
       }
        return null;
    }

    @Override
    public Bus getBusById(Integer id) {
        Optional<Bus> dbBus = busRepository.findById(id);
        Bus foundBus = dbBus.get();
        return foundBus;
    }

    @Override
    public Bus findBus(Integer carNumber) {
        try {
            Optional<Bus> dbBus = busRepository.findById(carNumber);
            return dbBus.get();
        } catch (Exception ex){
            logger.error(ex);
        }
        return null;
    }

    @Override
    public Bus createBus(Bus bus) {
        try {
            Bus newBus = busRepository.save(bus);
            return newBus;
        }
        catch (Exception ex){
            logger.error(ex);
        }
        return null;
    }

    @Override
    public void updateBus(Integer id, Bus bus) {
        try{
            Optional<Bus> dbBus = busRepository.findById(id);
            Bus foundBus = dbBus.get();
            foundBus.setColor(bus.getColor());
            foundBus.setCarNumber(bus.getCarNumber());
            foundBus.setDateMantain(bus.getDateMantain());
            foundBus.setManufacturer(bus.getManufacturer());
            foundBus.setLifeCar(bus.getLifeCar());
            foundBus.setYearUse(bus.getYearUse());
            foundBus.setNumberSeats(bus.getNumberSeats());
            busRepository.save(foundBus);
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
        }
    }

    @Override
    public void deleteBus(Integer id) {
        try {
            busRepository.deleteById(id);
            return;
        }
        catch (Exception ex){
            logger.error(ex);
        }
    }

    @Override
    public void deleteBuses(List<Integer> ids) {
        busRepository.deleteAllById(ids);
        return;
    }
}
