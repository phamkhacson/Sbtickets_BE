package com.example.sbtickets.controller;

import com.example.sbtickets.bean.LineBusBean;
import com.example.sbtickets.bean.WrapperResponse;
import com.example.sbtickets.common.UrlConst;
import com.example.sbtickets.entity.LineBus;
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
@CrossOrigin
public class LineBusController {
    private static final Logger logger = Logger.getLogger(LineBusController.class);

    @Autowired
    LineBusService lineBusService;

    @Autowired
    TripBusAddressService tripBusAddressService;

    @RequestMapping(value = UrlConst.HOMEADIM.GET_LINE_BUS, method = RequestMethod.GET)
    public ResponseEntity<WrapperResponse> getLineBus() {
        WrapperResponse response = new WrapperResponse();
        List<LineBus> result = new ArrayList<>();
        try {
            result = lineBusService.getLineBus();
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

    @RequestMapping(value = UrlConst.HOMEADIM.GET_LINE_BUS_BY_ID, method = RequestMethod.GET)
    public ResponseEntity<WrapperResponse> getlineBusById(@PathVariable("id") Integer id){
        WrapperResponse response = new WrapperResponse();
        LineBus lineBus;
        try {
            lineBus = lineBusService.getLineBusById(id);
            response.setBody(lineBus);
            response.setStatus(HttpStatus.OK.value());
        } catch (Exception ex){
            response.setMsg("Cannot find line bus");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<WrapperResponse>(response, HttpStatus.FAILED_DEPENDENCY);
        }
        return new ResponseEntity<WrapperResponse>(response, HttpStatus.OK);
    }

    @RequestMapping(value = UrlConst.HOMEADIM.CREATE_LINE_BUS, method = RequestMethod.POST)
    public ResponseEntity<WrapperResponse> createLineBus(HttpServletRequest request, @RequestBody LineBusBean lineBus){
        WrapperResponse response = new WrapperResponse();
        LineBus newLineBus, createdLineBus;
        try {

            newLineBus = new LineBus(
                    tripBusAddressService.findTripBusAddress(lineBus.getFirstId()),
                    tripBusAddressService.findTripBusAddress(lineBus.getLastId()),
                    lineBus.getLength(),
                    lineBus.getComplexity()
            );
            createdLineBus = lineBusService.createLineBus(newLineBus);
            response.setBody(createdLineBus);
            response.setStatus(HttpStatus.OK.value());
            response.setMsg("Created new line bus successfully");
        } catch (Exception ex){
            logger.error(ex);
            response.setMsg("Cannot create new line bus");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<WrapperResponse>(response, HttpStatus.FAILED_DEPENDENCY);
        }
        return new ResponseEntity<WrapperResponse>(response, HttpStatus.OK);
    }

    @RequestMapping(value = UrlConst.HOMEADIM.UPDATE_LINE_BUS, method = RequestMethod.PUT)
    public ResponseEntity<WrapperResponse> updateLineBus(@PathVariable("id") Integer id, @RequestBody LineBusBean lineBus){
        WrapperResponse response = new WrapperResponse();
        LineBus updatingLineBus;
        try{
            updatingLineBus = new LineBus(
                    id,
                    tripBusAddressService.findTripBusAddress(lineBus.getFirstId()),
                    tripBusAddressService.findTripBusAddress(lineBus.getLastId()),
                    lineBus.getLength(),
                    lineBus.getComplexity()
            );
            lineBusService.updateLineBus(updatingLineBus);
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

    @RequestMapping(value = UrlConst.HOMEADIM.DELETE_LINE_BUS, method = RequestMethod.DELETE)
    public ResponseEntity<WrapperResponse> deleteLineBus(@PathVariable("id") Integer id){
        WrapperResponse response = new WrapperResponse();
        try{
            lineBusService.deleteLineBus(id);
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

    @RequestMapping(value = UrlConst.HOMEADIM.DELETE_LINE_BUSES, method = RequestMethod.POST)
    public ResponseEntity<WrapperResponse> deleteLineBuses(@RequestBody Integer[] ids){
        WrapperResponse response = new WrapperResponse();
        try{
            List<Integer> list = Arrays.asList(ids);
            lineBusService.deleteLineBuses(list);
            response.setStatus(HttpStatus.OK.value());
            response.setMsg("Deleted successfully");
        } catch (Exception ex){
            response.setMsg(ex.getMessage());
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<WrapperResponse>(response, HttpStatus.FAILED_DEPENDENCY);
        }
        return new ResponseEntity<WrapperResponse>(response, HttpStatus.OK);
    }

}
