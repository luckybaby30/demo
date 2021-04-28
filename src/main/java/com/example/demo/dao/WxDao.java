package com.example.demo.dao;

import com.example.demo.domain.UserWx;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface WxDao {

    UserWx getUserId(String openId);

    void insertUserId(UserWx userWx);

}
