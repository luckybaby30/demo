package com.example.demo.service.impl;

import com.example.demo.utils.HttpsClient;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @PROJECT_NAME: demo01
 * @PACKAGE_NAME: com.example.demo01.service.Impl
 * @Class_NAME: WxService
 * @Author: zhangyongjiang
 * @DATE_TIME: 2021-4-21 上午 11:20
 * @Description:
 * @version:
 **/
@Service
public class WxService {

    /**
     * https://api.weixin.qq.com/sns/jscode2session?
     * appid=APPID
     * &
     * secret=SECRET
     * &
     * js_code=JSCODE
     * &
     * grant_type=authorization_code
     * @return
     */
    public Map login(String code) throws Exception {
        return HttpsClient.httpsGet("https://api.weixin.qq.com/sns/jscode2session?appid=wx59936b46d1c19718&secret=bf488f38df58159e8b978087482b02ac&js_code="+ code +"&grant_type=authorization_code");
    }
}
