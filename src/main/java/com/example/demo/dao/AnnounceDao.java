package com.example.demo.dao;

import com.example.demo.domain.Announce;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AnnounceDao {

    List<Announce> listAnnounces();

    void insertAnnounce(Announce announce);

    void delAnnounceById(Integer announceId);

    void updateAnnounceById(Announce announce);
}
