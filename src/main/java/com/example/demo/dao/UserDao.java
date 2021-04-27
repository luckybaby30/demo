package com.example.demo.dao;

import com.example.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {

    List<User> listUsers();

    List<User> selectByUserId(Integer userId);

    void insertUser(User user);

    void delUserById(User user);

    void updateUserById(User user);

}
