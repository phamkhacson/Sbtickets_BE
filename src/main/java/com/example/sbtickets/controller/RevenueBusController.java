package com.example.sbtickets.controller;
import com.example.sbtickets.bean.RevenueBusBean;
import com.example.sbtickets.bean.WrapperResponse;
import com.example.sbtickets.common.UrlConst;
import com.example.sbtickets.service.RevenueBusService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RevenueBusController {
    private static final Logger logger = Logger.getLogger(StatisticalController.class);
    @Autowired
    RevenueBusService revenueBusService;
    @RequestMapping(value = UrlConst.HOMEADIM.GET_REVENUE_BUS, method = RequestMethod.GET)
    public ResponseEntity<WrapperResponse> listBusRevenue() {
        WrapperResponse response = new WrapperResponse();
        List<RevenueBusBean> result = new ArrayList<>();
        try {
            result = revenueBusService.listBusRevenue();
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
    @RequestMapping(value = UrlConst.HOMEADIM.GET_REVENUE_BUS_BY_ID, method = RequestMethod.GET)
    public ResponseEntity<WrapperResponse> getBusRevenue(@PathVariable("busId") Integer id){
        WrapperResponse response = new WrapperResponse();
        List<RevenueBusBean> revenueBus;
        try {
            revenueBus = revenueBusService.getDriverById(id);
            response.setBody(revenueBus);
            response.setStatus(HttpStatus.OK.value());
        } catch (Exception ex){
            logger.error(ex);
            response.setMsg("");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<WrapperResponse>(response, HttpStatus.FAILED_DEPENDENCY);
        }
        return new ResponseEntity<WrapperResponse>(response, HttpStatus.OK);
    }
}
