package com.example.demo.controller;

import com.example.demo.domain.Announce;
import com.example.demo.service.IAnnounceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/Announce")
public class AnnounceController {

    @Autowired
    private IAnnounceService iAnnounceService;

    @GetMapping("/announces")
    public List<Announce> listAnnounces(){
        System.out.println("执行中。。。");
        return iAnnounceService.listAnnounces();
    }

    @GetMapping("/insertannounce")
    public void insertAnnounce(@RequestBody Announce announce){
        System.out.println(announce.getContact());
        //iAnnounceService.insertAnnounce(announce);
        System.out.println("执行成功");
    }

    @GetMapping("/delannounceById")
    public void delAnnounceById(Integer announceId){
        iAnnounceService.delAnnounceById(announceId);
        System.out.println("执行完毕");
    }

    @GetMapping("/updateannounceById")
    public void updateAnnounceById(Announce announce){
        iAnnounceService.updateAnnounceById(announce);
        System.out.println("执行完毕");
    }
}
