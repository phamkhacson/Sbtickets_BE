package com.example.sbtickets.service;

import com.example.sbtickets.entity.Customer;
import com.example.sbtickets.repository.CustomerRepository;
import com.example.sbtickets.service.impl.CustomerImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements CustomerImplement {
    private static final Logger logger = Logger.getLogger(BusService.class);

    @Autowired
    CustomerRepository customerRepository;
    @Override
    public boolean addCustomer(Customer customer) {
       try {
           customerRepository.save(customer);
       }
       catch (Exception ex){
           logger.error(ex);
           return false;
       }
       return true;
    }

    @Override
    public Integer findCustomerId(Integer accountId) {
        List<Customer> allCustomer = customerRepository.findAll();
        for (Customer cus: allCustomer){
            if(cus.getUser().getId() == accountId){
                return cus.getId();
            }
        }
        return null;
    }


    @Override
    public void updateCustomer(Integer id, Customer customer) {
        Optional<Customer> dbCustomer = customerRepository.findById(id);
        Customer foundCustomer = dbCustomer.get();
        foundCustomer.setFullName(customer.getFullName());
        foundCustomer.setCmt(customer.getCmt());
        foundCustomer.setAddress(customer.getAddress());
        foundCustomer.setBirthDay(customer.getBirthDay());
        foundCustomer.setEmail(customer.getEmail());
        customerRepository.save(foundCustomer);
        return;
    }

    @Override
    public Customer getCustomerDetail(Integer id) {
        try {
            Optional<Customer> dbCustomer = customerRepository.findById(id);
            Customer foundCustomer = dbCustomer.get();
            return foundCustomer;
        } catch (Exception ex){
            logger.error(ex);
            return null;
        }
    }
}
