package com.example.sbtickets.controller;

import com.example.sbtickets.bean.UserRegisterBean;
import com.example.sbtickets.bean.WrapperResponse;
import com.example.sbtickets.common.UrlConst;
import com.example.sbtickets.entity.Bus;
import com.example.sbtickets.entity.Customer;
import com.example.sbtickets.entity.User;
import com.example.sbtickets.service.CustomerService;
import com.example.sbtickets.service.LineBusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@RestController
@CrossOrigin
public class CustomerController {

    @Autowired
    CustomerService customerService;

    private static final Logger logger = Logger.getLogger(BusController.class);
    @RequestMapping(value = UrlConst.HOME_USER.GET_USER, method = RequestMethod.GET)
    public ResponseEntity<WrapperResponse> getBus() {
        WrapperResponse response = new WrapperResponse();
        List<User> result = new ArrayList<>();
        try {
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

    @RequestMapping(value = UrlConst.HOME_USER.UPDATE_CUSTOMER, method = RequestMethod.PUT)
    public ResponseEntity<WrapperResponse> updateCustomer(@PathVariable("id") Integer id, @RequestBody UserRegisterBean customer){
        WrapperResponse response = new WrapperResponse();
        Customer updatingCustomer;
        try{
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date dateTime = df.parse(customer.getBirthDay());
            updatingCustomer = new Customer(
                    customer.getFullName(),
                    customer.getCmt(),
                    customer.getAddress(),
                    customer.getEmail(),
                    dateTime
            );
            customerService.updateCustomer(id, updatingCustomer);
            response.setMsg("Updated successfully");
            response.setStatus(HttpStatus.OK.value());
        } catch (Exception ex){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMsg("Updated fail");
            return new ResponseEntity<WrapperResponse>(response, HttpStatus.FAILED_DEPENDENCY);
        }
        return new ResponseEntity<WrapperResponse>(response, HttpStatus.OK);
    }

    @RequestMapping(value = UrlConst.HOME_USER.GET_CUSTOMER_DETAIL, method = RequestMethod.GET)
    public ResponseEntity<WrapperResponse> getDetailCustomer(@PathVariable("id") Integer id){
        WrapperResponse response = new WrapperResponse();
        try{
            Customer foundCustomer = customerService.getCustomerDetail(id);
            if(foundCustomer == null){
                response.setStatus(HttpStatus.BAD_REQUEST.value());
                response.setMsg("Not found customer");
                return new ResponseEntity<WrapperResponse>(response, HttpStatus.BAD_REQUEST);
            }
            response.setStatus(HttpStatus.OK.value());
            response.setBody(foundCustomer);
        } catch (Exception ex){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setMsg("Can not get customer detail");
            return new ResponseEntity<WrapperResponse>(response, HttpStatus.FAILED_DEPENDENCY);
        }
        return new ResponseEntity<WrapperResponse>(response, HttpStatus.OK);
    }

}
