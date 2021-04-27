package com.example.demo.service;



import com.example.demo.domain.Wx;

import java.util.Map;

public interface IWxService {

    Wx getUserId(String openId);

    void insertUserId(Wx wx);

    Map login(String code)throws Exception;
}
