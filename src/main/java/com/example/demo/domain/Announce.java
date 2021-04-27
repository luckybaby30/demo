package com.example.demo.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Announce {

    private int announceId;

    private int userId;

    private String itemName;

    private int announceType;

    private String itemPhoto;

    private String tagName;

    private String itemDescription;

    private Date itemTime;

    private String itemPlace;

    private String contact;

    private int isStatus;
}
