package com.example.sbtickets.controller;

import com.example.sbtickets.bean.*;
import com.example.sbtickets.common.UrlConst;
import com.example.sbtickets.entity.*;
import com.example.sbtickets.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RestController
public class TripBusController {

    private static final Logger logger = Logger.getLogger(TripBusController.class);

    @Autowired
    BusService busService;

    @Autowired
    LineBusService lineBusService;

    @Autowired
    TripBusService tripBusService;

    @Autowired
    TripBusCustomerService tripBusCustomerService;

    @Autowired
    CustomerService customerService;

    @Autowired
    DriverService driverService;

    @Autowired
    SendToEmailService sendToEmailService;

    @Autowired
    TripBusDriverService tripBusDriverService;

    @RequestMapping(value = UrlConst.HOMEADIM.CREATE_TRIP_BUS, method = RequestMethod.POST)
    public ResponseEntity<WrapperResponse> creatTripBus(@RequestBody TripBusBean tripBusBean) {
        WrapperResponse result = new WrapperResponse();
        try {
            tripBusBean.setNumberGuest(0);
            Bus bus = busService.findBus(tripBusBean.getBusId());
            LineBus lineBus = lineBusService.getLineBusById(tripBusBean.getLineBusId());
            TripBus tripBus = new TripBus();
            tripBus.setBus(bus);
            tripBus.setLineBus(lineBus);
            tripBus.setNumberGuest(tripBusBean.getNumberGuest());
            tripBus.setTimeTrip(tripBusBean.getTimeTrip());
            tripBus.setPriceTrip(tripBusBean.getPriceTrip());
            tripBus = tripBusService.createTripBus(tripBus);
            if(tripBus != null){
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                TripBusDriver driverBus = new TripBusDriver();
                driverBus.setDriver(driverService.getDriverById(tripBusBean.getDriverId()));
                driverBus.setTripbus(tripBusService.findTripBus(tripBus.getId()));
                driverBus.setWages((double) (lineBus.getComplexity()*lineBus.getLength()*20000));
                driverBus.setDate(tripBus.getTimeTrip());
                driverBus.setRoleCar("1");
                driverBus.setScrapDateTime(df.format(tripBus.getTimeTrip()));
                tripBusDriverService.insertTripBusDriver(driverBus);

                TripBusDriver assistantDriver = new TripBusDriver();
                assistantDriver.setDriver(driverService.getDriverById(tripBusBean.getDriverId()));
                assistantDriver.setTripbus(tripBusService.findTripBus(tripBus.getId()));
                assistantDriver.setWages((double) (lineBus.getComplexity()*lineBus.getLength()*10000));
                assistantDriver.setDate(tripBus.getTimeTrip());
                assistantDriver.setScrapDateTime(df.format(tripBus.getTimeTrip()));
                assistantDriver.setRoleCar("0");
                tripBusDriverService.insertTripBusDriver(assistantDriver);

                result.setMsg("Creat TripBus Sucessfull");
                result.setStatus(HttpStatus.OK.value());
            }
            else{
                return new ResponseEntity<WrapperResponse>(result, HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            result.setMsg(ex.getMessage());
            result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<WrapperResponse>(result, HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
        return new ResponseEntity<WrapperResponse>(result, HttpStatus.valueOf(HttpStatus.OK.value()));
    }

    @RequestMapping(value = UrlConst.HOMEADIM.DELETE_TRIP_BUS, method = RequestMethod.POST)
    public ResponseEntity<WrapperResponse> deleteTripBus(@RequestBody Integer[] ids) {
        WrapperResponse result = new WrapperResponse();
        try {
            List<Integer> list = Arrays.asList(ids);
            for(Integer id: list){
                tripBusCustomerService.deleteTripBusCustomerById(id);
                tripBusDriverService.deleteTripBusDriver(id);
            }
            tripBusService.deleteTripBus(list);
            result.setMsg("Delete TripBus Sucessfull");
            result.setStatus(HttpStatus.OK.value());
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
            result.setMsg(ex.getMessage());
            result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<WrapperResponse>(result, HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
        return new ResponseEntity<WrapperResponse>(result, HttpStatus.valueOf(HttpStatus.OK.value()));
    }

    @RequestMapping(value = UrlConst.HOMEADIM.FIND_TRIP_BUS, method = RequestMethod.GET)
    public ResponseEntity<WrapperResponse> findTripBus(@PathVariable("id") Integer id) {
        WrapperResponse result = new WrapperResponse();
        try {
            result.setMsg("Delete TripBus Sucessfull");
            result.setStatus(HttpStatus.OK.value());
            result.setBody(tripBusService.findTripBus(id));
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
            result.setMsg(ex.getMessage());
            result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<WrapperResponse>(result, HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
        return new ResponseEntity<WrapperResponse>(result, HttpStatus.valueOf(HttpStatus.OK.value()));
    }

    @RequestMapping(value = UrlConst.HOMEADIM.EDIT_TRIP_BUS, method = RequestMethod.POST)
    public ResponseEntity<WrapperResponse> editTripBus(@RequestBody TripBusBean tripBusBean) {
        WrapperResponse result = new WrapperResponse();
        try {
            if(tripBusBean.getNumberGuest() == null)  tripBusBean.setNumberGuest(0);
            Bus bus = busService.findBus(tripBusBean.getBusId());
            LineBus lineBus = lineBusService.getLineBusById(tripBusBean.getLineBusId());
            TripBus tripBus = new TripBus();
            tripBus = tripBusService.findTripBusById(tripBusBean.getTripBusId());
            tripBus.setBus(bus);
            tripBus.setLineBus(lineBus);
            tripBus.setNumberGuest(tripBusBean.getNumberGuest());
            tripBus.setTimeTrip(tripBusBean.getTimeTrip());
            tripBus.setPriceTrip(tripBusBean.getPriceTrip());
            tripBusService.updateTripBus(tripBus); // update TripBus

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            // update lai tai xe
            TripBusDriver driverBus = new TripBusDriver();
            driverBus.setDriver(driverService.getDriverById(tripBusBean.getDriverId()));
            driverBus.setTripbus(tripBusService.findTripBus(tripBus.getId()));
            driverBus.setWages((double) (lineBus.getComplexity()*60000));
            driverBus.setDate(tripBus.getTimeTrip());
            driverBus.setRoleCar("1");
            driverBus.setScrapDateTime(df.format(tripBus.getTimeTrip()));
            tripBusDriverService.editTripBusDriver(driverBus);

            // update lai phu xe
            TripBusDriver assistantDriver = new TripBusDriver();
            assistantDriver.setDriver(driverService.getDriverById(tripBusBean.getAssistantBusId()));
            assistantDriver.setTripbus(tripBusService.findTripBus(tripBus.getId()));
            assistantDriver.setWages((double) (lineBus.getComplexity()*30000));
            assistantDriver.setDate(tripBus.getTimeTrip());
            assistantDriver.setRoleCar("0");
            assistantDriver.setScrapDateTime(df.format(tripBus.getTimeTrip()));
            tripBusDriverService.editTripBusDriver(assistantDriver);
            result.setMsg("Update TripBus Sucessfull");
            result.setStatus(HttpStatus.OK.value());
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
            result.setMsg(ex.getMessage());
            result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<WrapperResponse>(result, HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
        return new ResponseEntity<WrapperResponse>(result, HttpStatus.valueOf(HttpStatus.OK.value()));
    }

    @RequestMapping(value = UrlConst.HOMEADIM.GET_ALL_TRIP_BUS, method = RequestMethod.GET)
    public ResponseEntity<WrapperResponse> getAllTripBus() {
        WrapperResponse result = new WrapperResponse();
        try {
            result.setMsg("Get All TripBus Sucessfull");
            result.setStatus(HttpStatus.OK.value());
            HashMap<String, Object> listData = new HashMap<>();
            listData.put("listTripBus", tripBusService.listTripBus());
            listData.put("listTripBusDriver", tripBusDriverService.getListBusDriver());
            result.setBody(listData);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<WrapperResponse>(result, HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
        return new ResponseEntity<WrapperResponse>(result, HttpStatus.valueOf(HttpStatus.OK.value()));
    }

    @RequestMapping(value = UrlConst.HOME_USER.BOOK_SEAT, method = RequestMethod.POST)
    public ResponseEntity<WrapperResponse> bookSeat(@RequestBody TripBusCustomerBean tripBusCustomerBean){
        WrapperResponse result = new WrapperResponse();
        try {
            TripBus tripBus = tripBusService.findTripBusById(tripBusCustomerBean.getTripBusId());
            if(tripBus.getBus().getNumberSeats() - 2 <= tripBus.getNumberGuest()){
                result.setMsg("This trip bus is full of guests");
                result.setStatus(HttpStatus.FORBIDDEN.value());
            }
            else if(tripBusDriverService.checkRoleCar(tripBusCustomerBean.getSeatBooked())){
                result.setMsg("Number car is booked");
                result.setStatus(HttpStatus.FORBIDDEN.value());
            }
            else{
                TripBusCustomer newSeat = new TripBusCustomer();
                newSeat.setTripbus(tripBusService.findTripBus(tripBusCustomerBean.getTripBusId()));
                newSeat.setCustomer(customerService.getCustomerDetail(tripBusCustomerBean.getCustomerId()));
                newSeat.setRoleCar(tripBusCustomerBean.getSeatBooked());
                tripBusCustomerService.insertTripBusCustomer(newSeat);
                Integer currentPassengerNum = tripBus.getNumberGuest();
                tripBus.setNumberGuest(currentPassengerNum + 1);
                tripBusService.updateTripBus(tripBus);
                sendToEmailService.sendToEmail(tripBusCustomerBean.getSeatBooked(),tripBusService.findTripBus(tripBusCustomerBean.getTripBusId()),customerService.getCustomerDetail(tripBusCustomerBean.getCustomerId()));
                result.setMsg("Seats booked successfully");
                result.setStatus(HttpStatus.OK.value());
            }
        } catch (Exception ex){
            logger.error(ex.getMessage());
            result.setMsg(ex.getMessage());
            result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<WrapperResponse>(result, HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
        return new ResponseEntity<WrapperResponse>(result, HttpStatus.valueOf(HttpStatus.OK.value()));
    }

    @RequestMapping(value = UrlConst.HOME_USER.FIND_BY_TRIPBUS, method = RequestMethod.POST)
    public ResponseEntity<WrapperResponse> findTripBus(@RequestBody AllTripBusByLastPointBean tripBusByLastPointBean){
        WrapperResponse result = new WrapperResponse();
        try {
            result.setMsg("Seats booked successfully");
            result.setStatus(HttpStatus.OK.value());
            result.setBody(tripBusCustomerService.findByFirtLastPointObject(tripBusByLastPointBean));
        } catch (Exception ex){
            logger.error(ex.getMessage());
            result.setMsg(ex.getMessage());
            result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<WrapperResponse>(result, HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
        return new ResponseEntity<WrapperResponse>(result, HttpStatus.valueOf(HttpStatus.OK.value()));
    }
}
