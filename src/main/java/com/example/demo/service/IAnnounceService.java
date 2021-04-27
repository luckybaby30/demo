package com.example.demo.service;

import com.example.demo.domain.Announce;

import java.util.List;

public interface IAnnounceService {

    List<Announce> listAnnounces();

    void insertAnnounce(Announce announce);

    void delAnnounceById(Integer announceId);

    void updateAnnounceById(Announce announce);

}
