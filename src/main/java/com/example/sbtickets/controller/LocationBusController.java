package com.example.sbtickets.controller;

import com.example.sbtickets.bean.WrapperResponse;
import com.example.sbtickets.common.UrlConst;
import com.example.sbtickets.entity.LineBus;
import com.example.sbtickets.service.TripBusAddressService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LocationBusController {
    private static final Logger logger = Logger.getLogger(LocationBusController.class);

    @Autowired
    TripBusAddressService tripBusAddressService;

    @RequestMapping(value = UrlConst.HOME_USER.GET_LOACTION, method = RequestMethod.GET)
    public ResponseEntity<WrapperResponse> getLineBus() {
        WrapperResponse response = new WrapperResponse();
        try {
            response.setBody(tripBusAddressService.listAddress());
            response.setStatus(HttpStatus.OK.value());
        } catch (Exception ex) {
            logger.error(ex);
            response.setMsg("Not found");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<WrapperResponse>(response, HttpStatus.FAILED_DEPENDENCY);
        }
        return new ResponseEntity<WrapperResponse>(response, HttpStatus.OK);
    }

        @RequestMapping(value = UrlConst.HOMEADIM.GET_LOACTION, method = RequestMethod.GET)
        public ResponseEntity<WrapperResponse> getLoaction() {
            WrapperResponse response = new WrapperResponse();
            try {
                response.setBody(tripBusAddressService.listAddress());
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
}
