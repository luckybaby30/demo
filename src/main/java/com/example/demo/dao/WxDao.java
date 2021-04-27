package com.example.demo.dao;

import com.example.demo.domain.Wx;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper

public interface WxDao {

    Wx getUserId(String openId);

    void insertUserId(Wx wx);

}
