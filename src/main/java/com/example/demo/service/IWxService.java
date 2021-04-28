package com.example.demo.service;



import com.example.demo.domain.UserWx;

import java.util.Map;

public interface IWxService {

    UserWx getUserId(String openId);

    void insertUserId(UserWx userWx);

    Map login(String code)throws Exception;
}
