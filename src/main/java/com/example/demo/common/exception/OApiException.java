package com.example.demo.common.exception;

import lombok.Data;

/**
 * @PROJECT_NAME: demo01
 * @PACKAGE_NAME: com.example.demo01.common.exception
 * @Class_NAME: OApiException
 * @Author: zhangyongjiang
 * @DATE_TIME: 2021-4-21 下午 02:24
 * @Description:
 * @version:
 **/
@Data
public class OApiException extends Exception {
    private Integer code;
    public OApiException(Integer code, String msg){
        super(msg);
        this.code = code;
    }
}
