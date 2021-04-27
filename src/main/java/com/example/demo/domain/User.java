package com.example.demo.domain;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private int userId;

    private String userName;

    private String avatarUrl;

    private String phone;

    private Date regTime;

    private Date loginTime;
}
