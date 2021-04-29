package com.example.demo.controller;

import com.example.demo.common.config.RedissonManager;
import org.apache.ibatis.annotations.Param;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.BitOP;
import redis.clients.jedis.Jedis;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RequestMapping("/Test")
@RestController
public class TestController {
    /**
     * （1）登录：uid 日期
     * （2）获取几天内活跃的用户数：天数
     * （3）获取几天内登录的用户：天数
     * （4）获取某一个用户在几天内登陆的次数：uid 天数
     */
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private Redisson redisson;

    private String lock = "LAF";

    @GetMapping("/add/{userId}")
    public void addInfo(@PathVariable("userId") Integer userId){

        LocalDate localDate = LocalDate.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMdd");
        String dateStr = localDate.format(fmt);
        System.out.println(userId);
        redisTemplate.opsForValue().setBit(dateStr,userId,true);
        System.out.println(dateStr);
        redisTemplate.opsForValue().setBit(String.valueOf(userId),Long.valueOf(dateStr),true);
        System.out.println(redisTemplate.opsForValue().getBit(dateStr,userId));
    }

    @GetMapping("/userCount/{day}")
    public Long getUserCount(@PathVariable("day")Integer day){
        RLock rLock = redisson.getLock(lock);

        rLock.lock();
        try{
            LocalDate localDate = LocalDate.now();
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMdd");
            String dateStr = localDate.format(fmt);
            Long time = Long.valueOf(dateStr);
            String[] str = new String[day];
            int idx = 0;
            while (day > 0){
                str[idx] = String.valueOf(time - idx);
                day--;
                idx++;
            }
            Jedis jedis = new Jedis("127.0.0.1",6379);
            System.out.println(jedis.bitop(BitOP.OR, "dest", str));
            Long count = jedis.bitcount("dest");

            System.out.println(count);
            return count;
        }finally {
            if (rLock.isHeldByCurrentThread() && rLock.isLocked()){
                rLock.unlock();
            }
        }

    }

    @GetMapping("/loginCount/{day}/{userId}")
    public Integer getLoginCount(@PathVariable("day")Integer day,@PathVariable("userId") Integer userId){
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMdd");
        String dateStr = localDate.format(fmt);
        Long time = Long.valueOf(dateStr);
        Jedis jedis = new Jedis("127.0.0.1",6379);
        int count = 0;
        while (day>0){
            if (jedis.getbit(String.valueOf(userId),time-day+1)){
                count++;
            }
            day--;
        }

        System.out.println(count);
        return count;
    }
}
