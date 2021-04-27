package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.domain.Wx;
import com.example.demo.service.IUserService;
import com.example.demo.service.IWxService;
import com.example.demo.service.impl.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
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
        Wx wx = wxService.getUserId(openId);

        Map<Object,Object> retmap = new HashMap<>();
        if(wx != null){
            retmap.put("userId",wx.getUserId());
            retmap.put("user",iUserService.selectByUserId(wx.getUserId()));
            return retmap;
        }else{
            wx = new Wx();
            wx.setOpenId(openId);
            wxService.insertUserId(wx);
            wx = wxService.getUserId(openId);
            retmap.put("userId",wx.getUserId());
            return retmap;
        }

    }
}
