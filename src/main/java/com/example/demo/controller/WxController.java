package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.domain.UserWx;
import com.example.demo.service.IUserService;
import com.example.demo.service.IWxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Heart❤
 * @Time: 2021/4/21  18:47
 * @purpose:
 */

@RestController
@RequestMapping("/wx")
public class WxController {

    @Autowired
    private IWxService wxService;

    @Autowired
    private IUserService iUserService;

    @GetMapping("/Wx")
    public Object login(@RequestParam(value = "code") String code) throws Exception {
        System.out.println(code);

        Map<String, String> map = wxService.login(code);

        String openId = map.get("openid");
        System.out.println("openId=     "+openId);
        //用openId去微信表查有没有数据，没有插入，在得到userId
        UserWx userWx = wxService.getUserId(openId);

        Map<Object,Object> retmap = new HashMap<>();
        if(userWx != null){
            retmap.put("userId",userWx.getUserId());
            retmap.put("user",iUserService.selectByUserId(userWx.getUserId()));
            return retmap;
        }else{
            userWx = new UserWx();
            userWx.setOpenId(openId);
            wxService.insertUserId(userWx);
            userWx = wxService.getUserId(openId);
            retmap.put("userId",userWx.getUserId());
            return retmap;
        }
    }

    @PostMapping("/addUser")
    public Object addUser(@RequestBody User user){
        System.out.println(user.getUserId());
        System.out.println(user.getUserName());
        System.out.println(user.getAvatarUrl());
        System.out.println(user.getPhone());
        System.out.println(user.getLoginTime());
        System.out.println(user.getRegTime());
        iUserService.insertUser(user);
        Map<String, String> map = new HashMap<>();
        map.put("userid",String.valueOf(user.getUserId()));
        return map;
    }

}
