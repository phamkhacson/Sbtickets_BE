package com.example.sbtickets.service;

import com.example.sbtickets.entity.Driver;
import com.example.sbtickets.repository.DriverRepository;
import com.example.sbtickets.service.impl.DriverImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService implements DriverImplement {
    private static final Logger logger = Logger.getLogger(DriverService.class);

    @Autowired
    DriverRepository driverRepository;
    @Override
    public List<Driver> getDriver() {
       try{
           List<Driver> listDriver = driverRepository.findAll();
           return  listDriver;
       }
       catch (Exception ex){
           logger.error(ex);
       }
       return  null;
    }


    @Override
    public Driver getDriverById(Integer id) {
        try {
            Optional<Driver> dbDriver = driverRepository.findById(id);
            Driver foundDriver = dbDriver.get();
            return foundDriver;
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
        }
        return null;
    }

    @Override
    public Driver findDriver(String name) {
        try {
            return null;
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
        }
        return null;
    }

    @Override
    public Driver createDriver(Driver driver) {
        try {
            Driver newDriver = driverRepository.save(driver);
            return newDriver;
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
        }
        return null;
    }

    @Override
    public void updateDriver(Integer id, Driver driver) {
        try {
            Optional<Driver> dbDriver = driverRepository.findById(id);
            Driver foundDriver = dbDriver.get();
            foundDriver.setAddress(driver.getAddress());
            foundDriver.setCodeLicense(driver.getCodeLicense());
            foundDriver.setDob(driver.getDob());
            foundDriver.setImage(driver.getImage());
            foundDriver.setSeniority(driver.getSeniority());
            foundDriver.setNationalId(driver.getNationalId());
            foundDriver.setTypeLicense(driver.getTypeLicense());
            foundDriver.setName(driver.getName());
            driverRepository.save(foundDriver);
            return;
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
        }
    }

    @Override
    public void deleteDriver(Integer id) {
        try{
            driverRepository.deleteById(id);
            return;
        }
        catch(Exception ex){
            logger.error(ex.getMessage());
        }
    }

    @Override
    public void deleteDrivers(List<Integer> ids) {
        try{
            driverRepository.deleteAllById(ids);
            return;
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
        }
    }
}
