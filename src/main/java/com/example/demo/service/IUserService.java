package com.example.demo.service;

import com.example.demo.domain.User;

import java.util.List;

public interface IUserService {
    List<User> listUsers();

    List<User> selectByUserId(Integer UserId);

    void insertUser(User user);

    void delUserById(User user);

    void updateUserById(User user);
}
