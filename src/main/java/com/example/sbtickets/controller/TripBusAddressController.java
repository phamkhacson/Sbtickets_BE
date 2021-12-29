package com.example.sbtickets.controller;
import com.example.sbtickets.bean.TripBusAddressBean;
import com.example.sbtickets.bean.UserBean;
import com.example.sbtickets.bean.WrapperResponse;
import com.example.sbtickets.common.UrlConst;
import com.example.sbtickets.entity.TripBusAddress;
import com.example.sbtickets.entity.User;
import com.example.sbtickets.service.LineBusService;
import com.example.sbtickets.service.TripBusAddressService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class TripBusAddressController {
    private static final Logger logger = Logger.getLogger(LineBusController.class);

    @Autowired
    LineBusService lineBusService;

    @Autowired
    TripBusAddressService tripBusAddressService;

    @RequestMapping(value = UrlConst.HOMEADIM.GET_TRIPBUS_ADDRESS, method = RequestMethod.GET)
    public ResponseEntity<WrapperResponse> getTripBusAddress() {
        WrapperResponse response = new WrapperResponse();
        List<TripBusAddress> result = new ArrayList<>();
        try {
            result = tripBusAddressService.getTripBusAddress();
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

    @RequestMapping(value = UrlConst.HOMEADIM.GET_TRIPBUS_ADDRESS_BY_ID, method = RequestMethod.GET)
    public ResponseEntity<WrapperResponse> getDriverById(@PathVariable("id") Integer id){
        WrapperResponse response = new WrapperResponse();
        TripBusAddress tripBusAddress;
        try {
            tripBusAddress = tripBusAddressService.getTripBusAddressById(id);
            response.setBody(tripBusAddress);
            response.setStatus(HttpStatus.OK.value());
        } catch (Exception ex){
            logger.error(ex);
            response.setMsg("Cannot find trip bus address");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<WrapperResponse>(response, HttpStatus.FAILED_DEPENDENCY);
        }
        return new ResponseEntity<WrapperResponse>(response, HttpStatus.OK);
    }

    @RequestMapping(value = UrlConst.HOMEADIM.CREATE_TRIPBUS_ADDRESS, method = RequestMethod.POST)
    public ResponseEntity<WrapperResponse> createTripBusAddress(HttpServletRequest request, @RequestBody TripBusAddressBean tripBusAddress){
        WrapperResponse response = new WrapperResponse();
        TripBusAddress newTripBusAddress, createdTripBusAddress;
        try {

            newTripBusAddress = new TripBusAddress(

                    tripBusAddress.getAddress()
            );
            tripBusAddressService.createTripBusAddress(newTripBusAddress);
            response.setStatus(HttpStatus.OK.value());
            response.setMsg("Created new trip bus address successfully");
        } catch (Exception ex){
            logger.error(ex);
            response.setMsg("Cannot create trip bus address bus");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<WrapperResponse>(response, HttpStatus.FAILED_DEPENDENCY);
        }
        return new ResponseEntity<WrapperResponse>(response, HttpStatus.OK);
    }

    @RequestMapping(value = UrlConst.HOMEADIM.UPDATE_TRIPBUS_ADDRESS, method = RequestMethod.PUT)
    public ResponseEntity<WrapperResponse> updateTripBusAddress(@PathVariable("id") Integer id, @RequestBody TripBusAddress tripBusAddress){
        WrapperResponse response = new WrapperResponse();
        TripBusAddress updatingTripBusAddress;
        try{
            updatingTripBusAddress = new TripBusAddress(

                    tripBusAddress.getAddress()
            );
            tripBusAddressService.updateTripBusAddress(id, updatingTripBusAddress);
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

    @RequestMapping(value = UrlConst.HOMEADIM.DELETE_TRIPBUS_ADDRESS, method = RequestMethod.DELETE)
    public ResponseEntity<WrapperResponse> deleteTripBusAddress(@PathVariable("id") Integer id){
        WrapperResponse response = new WrapperResponse();
        try{
            tripBusAddressService.deleteTripBusAddress(id);
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

    @RequestMapping(value = UrlConst.HOMEADIM.DELETE_TRIPBUS_ADDRESSES, method = RequestMethod.POST)
    public ResponseEntity<WrapperResponse> deleteTripBusAddresses(@RequestBody Integer[] ids){
        WrapperResponse response = new WrapperResponse();
        try{
            List<Integer> list = Arrays.asList(ids);
            tripBusAddressService.deleteTripBusAddresses(list);
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
}
