package com.example.sbtickets.controller;

import com.example.sbtickets.bean.DriverBean;
import com.example.sbtickets.bean.WrapperResponse;
import com.example.sbtickets.common.UrlConst;
import com.example.sbtickets.entity.Driver;
import com.example.sbtickets.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.log4j.Logger;

@RestController
@CrossOrigin
public class DriverController {
    private static final Logger logger = Logger.getLogger(DriverController.class);

    @Autowired
    DriverService driverService;


    @RequestMapping(value = UrlConst.HOMEADIM.GET_DRIVER, method = RequestMethod.GET)
    public ResponseEntity<WrapperResponse> getDriver() {
        WrapperResponse response = new WrapperResponse();
        List<Driver> result = new ArrayList<>();
        try {
            result = driverService.getDriver();
            response.setBody(result);
            response.setStatus(HttpStatus.OK.value());
        }
        catch (Exception ex){
            logger.error(ex);
            response.setMsg("Not found");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<WrapperResponse>(response, HttpStatus.FAILED_DEPENDENCY);
        }
        return new ResponseEntity<WrapperResponse>(response, HttpStatus.OK);
    }

    @RequestMapping(value = UrlConst.HOMEADIM.GET_DRIVER_BY_ID, method = RequestMethod.GET)
    public ResponseEntity<WrapperResponse> getDriverById(@PathVariable("id") Integer id){
        WrapperResponse response = new WrapperResponse();
        Driver driver;
        try {
            driver = driverService.getDriverById(id);
            response.setBody(driver);
            response.setStatus(HttpStatus.OK.value());
        } catch (Exception ex){
            logger.error(ex);
            response.setMsg("Cannot find driver");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<WrapperResponse>(response, HttpStatus.FAILED_DEPENDENCY);
        }
        return new ResponseEntity<WrapperResponse>(response, HttpStatus.OK);
    }

    @RequestMapping(value = UrlConst.HOMEADIM.CREATE_DRIVER, method = RequestMethod.POST)
    public ResponseEntity<WrapperResponse> createDriver(HttpServletRequest request, @RequestBody DriverBean driver){
        WrapperResponse response = new WrapperResponse();
        Driver newDriver, createdDriver;
        try {
            newDriver = new Driver(
                    driver.getNationalId(),
                    driver.getName(),
                    driver.getCodeLicense(),
                    driver.getTypeLicense(),
                    driver.getAddress(),
                    driver.getDob(),
                    driver.getSeniority(),
                    driver.getImage(),
                    driver.getFixedSalary()
            );
            createdDriver = driverService.createDriver(newDriver);
            response.setBody(createdDriver);
            response.setStatus(HttpStatus.OK.value());
            response.setMsg("Created new driver successfully");
        } catch (Exception ex){
            logger.error(ex);
            response.setMsg("Cannot create new driver");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<WrapperResponse>(response, HttpStatus.FAILED_DEPENDENCY);
        }
        return new ResponseEntity<WrapperResponse>(response, HttpStatus.OK);
    }

    @RequestMapping(value = UrlConst.HOMEADIM.UPDATE_DRIVER, method = RequestMethod.PUT)
    public ResponseEntity<WrapperResponse> updateDriver(@PathVariable("id") Integer id, @RequestBody DriverBean driver){
        WrapperResponse response = new WrapperResponse();
        Driver updatingDriver;
        try{
            updatingDriver = new Driver(
                    driver.getNationalId(),
                    driver.getName(),
                    driver.getCodeLicense(),
                    driver.getTypeLicense(),
                    driver.getAddress(),
                    driver.getDob(),
                    driver.getSeniority(),
                    driver.getImage(),
                    driver.getFixedSalary()
            );
            driverService.updateDriver(id, updatingDriver);
            response.setMsg("Updated successfully");
            response.setStatus(HttpStatus.OK.value());
        } catch (Exception ex){
            logger.error(ex);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMsg("Updated fail");
            return new ResponseEntity<WrapperResponse>(response, HttpStatus.FAILED_DEPENDENCY);
        }
        return new ResponseEntity<WrapperResponse>(response, HttpStatus.OK);
    }

    @RequestMapping(value = UrlConst.HOMEADIM.DELETE_DRIVER, method = RequestMethod.DELETE)
    public ResponseEntity<WrapperResponse> deleteDriver(@PathVariable("id") Integer id){
        WrapperResponse response = new WrapperResponse();
        try{
            driverService.deleteDriver(id);
            response.setStatus(HttpStatus.OK.value());
            response.setMsg("Deleted successfully");
        } catch (Exception ex){
            logger.error(ex);
            response.setMsg(ex.getMessage());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<WrapperResponse>(response, HttpStatus.FAILED_DEPENDENCY);
        }
        return new ResponseEntity<WrapperResponse>(response, HttpStatus.OK);
    }

    @RequestMapping(value = UrlConst.HOMEADIM.DELETE_DRIVERS, method = RequestMethod.POST)
    public ResponseEntity<WrapperResponse> deleteDrivers(@RequestBody Integer[] ids){
        WrapperResponse response = new WrapperResponse();
        try{
            List<Integer> list = Arrays.asList(ids);
            driverService.deleteDrivers(list);
            response.setStatus(HttpStatus.OK.value());
            response.setMsg("Deleted successfully");
        } catch (Exception ex){
            logger.error(ex);
            response.setMsg(ex.getMessage());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<WrapperResponse>(response, HttpStatus.FAILED_DEPENDENCY);
        }
        return new ResponseEntity<WrapperResponse>(response, HttpStatus.OK);
    }

    @RequestMapping(value = UrlConst.HOMEADIM.FIND_DRIVER, method = RequestMethod.GET)
    public ResponseEntity<Driver> findDriver(@RequestBody String name) {
        Driver result = new Driver();
        try {
            result = driverService.findDriver(name);
        }
        catch (Exception ex){
            logger.error(ex);
            return new ResponseEntity<Driver>(result, HttpStatus.FAILED_DEPENDENCY);
        }
        return new ResponseEntity<Driver>(result, HttpStatus.OK);
    }

}