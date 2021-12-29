package com.example.sbtickets.service.impl;

import com.example.sbtickets.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserServiceImplement {
    public  List<User> findAll();
    public boolean checkUserName(String userName);
    public Optional<User> findById(int id);
    public boolean add(User user);
    public boolean delete(int id);
    public void update(Integer id, User user);
    public boolean checkLogin(User user);
    public User loadUserByUsername(String userName);
}
