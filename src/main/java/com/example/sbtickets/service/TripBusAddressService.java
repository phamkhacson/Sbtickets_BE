package com.example.sbtickets.service;

import com.example.sbtickets.entity.Driver;
import com.example.sbtickets.entity.TripBusAddress;
import com.example.sbtickets.repository.DriverRepository;
import com.example.sbtickets.repository.TripBusAddressRepository;
import com.example.sbtickets.service.impl.TripBusAddressImplement;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripBusAddressService implements TripBusAddressImplement {
    private static final Logger logger = Logger.getLogger(TripBusAddressService.class);

    @Autowired
    TripBusAddressRepository tripBusAddressRepository;

    @Override
    public List<TripBusAddress> listAddress() {
        try {
            return tripBusAddressRepository.findAll();
        }
        catch (Exception ex){
            logger.error(ex);
        }
        return null;
    }

    @Override
    public TripBusAddress findTripBusAddress(Integer id) {
        try {
            return tripBusAddressRepository.findById(id).get();
        }
        catch (Exception ex){
            logger.error(ex);
        }
        return null;
    }

    @Override
    public List<TripBusAddress> getTripBusAddress() {
        try{
            List<TripBusAddress> listTripBusAddress = tripBusAddressRepository.findAll();
            return  listTripBusAddress;
        }
        catch (Exception ex){
            logger.error(ex);
        }
        return  null;
    }


    @Override
    public TripBusAddress getTripBusAddressById(Integer id) {
        try {
            Optional<TripBusAddress> dbTripBusAddress = tripBusAddressRepository.findById(id);
            TripBusAddress foundTripBusAddress = dbTripBusAddress.get();
            return foundTripBusAddress;
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
        }
        return null;
    }


    @Override
    public void createTripBusAddress(TripBusAddress tripBusAddress) {
        try {
            tripBusAddressRepository.save(tripBusAddress);
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
        }
    }

    @Override
    public void updateTripBusAddress(Integer id, TripBusAddress tripbusAddress) {
        try {
            Optional<TripBusAddress> dbTripBusAddress = tripBusAddressRepository.findById(id);
            TripBusAddress foundTripBusAddress = dbTripBusAddress.get();
            foundTripBusAddress.setAddress(tripbusAddress.getAddress());
            tripBusAddressRepository.save(foundTripBusAddress);
            return;
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
        }
    }

    @Override
    public void deleteTripBusAddress(Integer id) {
        try{
            tripBusAddressRepository.deleteById(id);
            return;
        }
        catch(Exception ex){
            logger.error(ex.getMessage());
        }
    }

    @Override
    public void deleteTripBusAddresses(List<Integer> ids) {
        try{
            tripBusAddressRepository.deleteAllById(ids);
            return;
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
        }
    }

}
