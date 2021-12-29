package com.example.sbtickets.service;

import com.example.sbtickets.entity.LineBus;
import com.example.sbtickets.repository.LineBusRepository;
import com.example.sbtickets.service.impl.LineBusImplement;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LineBusService implements LineBusImplement {
    private static final Logger logger = Logger.getLogger(LineBusService.class);

    @Autowired
    LineBusRepository lineBusRepository;
    @Override
    public List<LineBus> getLineBus() {
        try {
            List<LineBus> listLineBus = lineBusRepository.findAll();
            return  listLineBus;
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
        }
        return  null;
    }

    @Override
    public LineBus getLineBusById(Integer id) {
        Optional<LineBus> dbLineBus = lineBusRepository.findById(id);
        LineBus foundLineBus = dbLineBus.get();
        return foundLineBus;
    }

    @Override
    public LineBus createLineBus(LineBus lineBus) {
        try {
            LineBus newLineBus = lineBusRepository.save(lineBus);
            return newLineBus;
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
        }
        return  null;
    }

    @Override
    public void updateLineBus(LineBus lineBus) {
        try {
            lineBusRepository.save(lineBus);
            return;
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
        }
    }

    @Override
    public void deleteLineBus(Integer id) {
        try {
            lineBusRepository.deleteById(id);
            return;
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
        }
    }

    @Override
    public void deleteLineBuses(List<Integer> ids) {
        lineBusRepository.deleteAllById(ids);
        return;
    }

}