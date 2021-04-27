package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.IUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/User")
@RestController
public class UserController {

    @Autowired
    private IUserService iUserService;

    @GetMapping("/users")
    public List<User> listUser(){
        return iUserService.listUsers();
    }

    @GetMapping("/userById")
    public List<User> selectByUserId(@Param("userId") Integer userId){
        return iUserService.selectByUserId(userId);
    }

    @GetMapping("/adduser")
    public String insertUser(@RequestBody User user){
        iUserService.insertUser(user);
        System.out.println("执行完毕");
        return "成功添加";
    }

    @GetMapping("/deluser")
    public String delUserById(@RequestBody User user){
        iUserService.delUserById(user);
        System.out.println("执行完毕");
        return "成功删除";
    }

    @GetMapping("/updateuser")
    public List<User> updateUserById(@RequestBody User user){
        iUserService.updateUserById(user);
        System.out.println("执行完毕");
        return listUser();
    };
}
