package com.example.sbtickets.controller;

import com.example.sbtickets.bean.TripBusBean;
import com.example.sbtickets.bean.WagesDriverBean;
import com.example.sbtickets.bean.WagesTimeBean;
import com.example.sbtickets.bean.WrapperResponse;
import com.example.sbtickets.common.UrlConst;
import com.example.sbtickets.service.TripBusCustomerService;
import com.example.sbtickets.service.TripBusService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StatisticalController {
    private static final Logger logger = Logger.getLogger(StatisticalController.class);

    @Autowired
    TripBusService tripBusService;


    @Autowired
    TripBusCustomerService tripBusCustomerService;

    @RequestMapping(value = UrlConst.HOMEADIM.GET_WAGES_DRIVER, method = RequestMethod.POST)
    public ResponseEntity<WrapperResponse> getListWages(@RequestBody WagesTimeBean wagesTimeBean) {
        WrapperResponse result = new WrapperResponse();
        try {
            List<WagesDriverBean> listData = tripBusCustomerService.getListWagesDriver(wagesTimeBean.getId(), wagesTimeBean.getScrapTime());
            result.setStatus(HttpStatus.OK.value());
            result.setBody(listData);
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
            result.setMsg(ex.getMessage());
            result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<WrapperResponse>(result, HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
        return new ResponseEntity<WrapperResponse>(result, HttpStatus.valueOf(HttpStatus.OK.value()));
    }

    @RequestMapping(value = UrlConst.HOMEADIM.COUNT_TRIP_BY_WEEK, method = RequestMethod.GET)
    public ResponseEntity<WrapperResponse> getCountTrip(){
        WrapperResponse result = new WrapperResponse();
        try {
            result.setMsg("get CountTrip booked successfully");
            result.setStatus(HttpStatus.OK.value());
            result.setBody(tripBusCustomerService.getCountTripBusForMonth());
        } catch (Exception ex){
            logger.error(ex.getMessage());
            result.setMsg(ex.getMessage());
            result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<WrapperResponse>(result, HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
        return new ResponseEntity<WrapperResponse>(result, HttpStatus.valueOf(HttpStatus.OK.value()));
    }

    @RequestMapping(value = UrlConst.HOMEADIM.GET_TRIPBUS_CUSTOMER, method = RequestMethod.GET)
    public ResponseEntity<WrapperResponse> getTripBusCustomer(@PathVariable("id") Integer id){
        WrapperResponse result = new WrapperResponse();
        try {
            result.setMsg("Get ListCustomer booked successfully");
            result.setStatus(HttpStatus.OK.value());
            result.setBody(tripBusCustomerService.getListTripBusByCustomer(id));
        } catch (Exception ex){
            logger.error(ex.getMessage());
            result.setMsg(ex.getMessage());
            result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<WrapperResponse>(result, HttpStatus.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        }
        return new ResponseEntity<WrapperResponse>(result, HttpStatus.valueOf(HttpStatus.OK.value()));
    }

}
