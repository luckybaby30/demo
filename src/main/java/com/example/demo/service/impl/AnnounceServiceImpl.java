package com.example.demo.service.impl;

import com.example.demo.dao.AnnounceDao;
import com.example.demo.domain.Announce;
import com.example.demo.service.IAnnounceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnounceServiceImpl implements IAnnounceService {

    @Autowired
    private AnnounceDao announceDao;

    public List<Announce> listAnnounces(){
        return announceDao.listAnnounces();
    }

    public void insertAnnounce(Announce announce){
        announceDao.insertAnnounce(announce);
    }

    public void delAnnounceById(Integer announceId){
        announceDao.delAnnounceById(announceId);
    }

    public void updateAnnounceById(Announce announce){
        announceDao.updateAnnounceById(announce);
    }
}
