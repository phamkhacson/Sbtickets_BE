package com.example.sbtickets.service;

import com.example.sbtickets.bean.*;
import com.example.sbtickets.entity.Customer;
import com.example.sbtickets.entity.TripBus;
import com.example.sbtickets.entity.TripBusCustomer;
import com.example.sbtickets.entity.TripBusDriver;
import com.example.sbtickets.repository.TripBusCustomerRepository;
import com.example.sbtickets.repository.TripBusDriverRepository;
import com.example.sbtickets.repository.TripBusRepository;
import com.example.sbtickets.service.impl.TripBusCustomerImplement;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TripBusCustomerService implements TripBusCustomerImplement {

    private static final Logger logger = Logger.getLogger(TripBusCustomerService.class);

    @Autowired
    TripBusCustomerRepository tripBusCustomerRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    TripBusRepository tripBusRepository;

    @Autowired
    TripBusDriverService tripBusDriverService;

    @Autowired
    TripBusDriverRepository tripBusDriverRepository;

    @Override
    public List<ObjectByTripBus> findByFirtLastPointObject(AllTripBusByLastPointBean allTripBusByLastPointBean) {
        try{
            List<ObjectByTripBus> objectByTripBuses = new ArrayList<>();
            List<TripBus> listTripBus = new ArrayList<>();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            for(TripBus item : tripBusRepository.findAll()){
                String dateTime = df.format(item.getTimeTrip());
                if(item.getLineBus().getfirstPoint().getId() == allTripBusByLastPointBean.getFirstPoint() && item.getLineBus().getLastPoint().getId() == allTripBusByLastPointBean.getLastPoint() && dateTime.equals(allTripBusByLastPointBean.getDateTime())){
                    List<TripBusCustomer> listTripBusCustomer = tripBusCustomerRepository.getListByTripBusId(item.getId());
                    List<Integer> dataBooked = new ArrayList<>();
                    for(TripBusCustomer bookItem : listTripBusCustomer){
                        dataBooked.add(bookItem.getRoleCar());
                    }
                    objectByTripBuses.add(new ObjectByTripBus(item, dataBooked));
                }
            }

            return objectByTripBuses;

        }
        catch (Exception ex){
            logger.error(ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean checkIfCustomerHadTicket(Integer tripBusId, Integer customerId) {
        try {
            List<TripBusCustomer> listTripBusCustomer = tripBusCustomerRepository.getListByTripBusId(tripBusId);
            for (TripBusCustomer cus : listTripBusCustomer) {
                if (customerId == cus.getCustomer().getId()) {
                    return true;
                }
            }
            return false;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return false;
    }

    public List<CountTripBusForMonth> getCountTripBusForMonth() {
        List<CountTripBusForMonth> listData = new ArrayList<>();
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Integer count1 = 0;
            Integer count2 = 0;
            Integer count3 = 0;
            Integer count4 = 0;
            Integer count5 = 0;
            for(TripBus item : tripBusRepository.findAll()){
                String dateTime = df.format(item.getTimeTrip());
                if(Integer.valueOf(dateTime.substring(5,7)) == Integer.valueOf(java.time.LocalDate.now().getMonthValue())){
                    if(item.getTimeTrip().getDate() >= 1 && item.getTimeTrip().getDate() <= 7){
                        count1++;
                    }
                    if(item.getTimeTrip().getDate() >= 8 && item.getTimeTrip().getDate() <= 14){
                        count2++;
                    }
                    if(item.getTimeTrip().getDate() >= 15 && item.getTimeTrip().getDate() <= 21){
                        count3++;
                    }
                    if(item.getTimeTrip().getDate() >= 23 && item.getTimeTrip().getDate() <= 28){
                        count4++;
                    }
                    if(item.getTimeTrip().getDate() > 28){
                        count5++;
                    }
                }
            }
            listData.add(new CountTripBusForMonth(count1,1));
            listData.add(new CountTripBusForMonth(count2,2));
            listData.add(new CountTripBusForMonth(count3,3));
            listData.add(new CountTripBusForMonth(count4,4));
            listData.add(new CountTripBusForMonth(count5,5));
            return  listData;
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<TripBusByCusomer> getListTripBusByCustomer(Integer id) {
        try {
            List<TripBusCustomer> listDataBusCustomer = tripBusCustomerRepository.getListByTripBusId(id);
            List<TripBusByCusomer> listData = new ArrayList<>();
            for(TripBusCustomer item : listDataBusCustomer){
                TripBusByCusomer tripBusByCusomer = new TripBusByCusomer();
                Customer customer = customerService.getCustomerDetail(item.getCustomer().getId());
                tripBusByCusomer.setTripBus(id);
                tripBusByCusomer.setAddress(customer.getAddress());
                tripBusByCusomer.setBirthday(customer.getBirthDay());
                tripBusByCusomer.setCmt(customer.getCmt());
                tripBusByCusomer.setBookseat(item.getRoleCar());
                tripBusByCusomer.setFullName(customer.getFullName());
                listData.add(tripBusByCusomer);
            }
            return listData;
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
        }
        return null;
    }

    @Override
    public void insertTripBusCustomer(TripBusCustomer tripBusCustomer) {
        try {
            tripBusCustomerRepository.save(tripBusCustomer);
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
        }
    }

    @Override
    public List<WagesDriverBean> getListWagesDriver(Integer driverId, String scrapTime) {
        try {
            List<TripBusDriver> listTripBusDriver = tripBusDriverRepository.listByDriverId(driverId, scrapTime+"-01", scrapTime+"-30" );
            List<WagesDriverBean> listData = new ArrayList<>();
            for(TripBusDriver v : listTripBusDriver){
                WagesDriverBean wages = new WagesDriverBean();
                wages.setTripBusId(v.getTripbus().getId());
                wages.setWages(v.getWages());
                wages.setDriverName(v.getDriver().getName());
                wages.setFixedSalary(v.getDriver().getFixedSalary());
                wages.setRoleCar(v.getRoleCar());
                wages.setScrapDateTime(v.getScrapDateTime());
                listData.add(wages);
            }
            return listData;
        }
        catch (Exception ex){
            logger.error(ex);
        }
        return null;
    }

    @Override
    public void deleteTripBusCustomerById(Integer TripBusId) {
        try {
            tripBusCustomerRepository.deleteTripBusCustomerById(TripBusId);
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
        }
    }
}
