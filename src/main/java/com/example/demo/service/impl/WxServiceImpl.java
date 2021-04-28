package com.example.demo.service.impl;

import com.example.demo.dao.WxDao;
import com.example.demo.domain.UserWx;
import com.example.demo.service.IWxService;
import com.example.demo.utils.HttpsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class WxServiceImpl implements IWxService {

    @Autowired
    private WxDao wxDao;

    public UserWx getUserId(String openId) {
        return wxDao.getUserId(openId);
    }

    public void insertUserId(UserWx userWx){
        wxDao.insertUserId(userWx);
    }

    public Map login(String code) throws Exception {
        return HttpsClient.httpsGet("https://api.weixin.qq.com/sns/jscode2session?appid=wx59936b46d1c19718&secret=bf488f38df58159e8b978087482b02ac&js_code="+ code +"&grant_type=authorization_code");
    }
}
