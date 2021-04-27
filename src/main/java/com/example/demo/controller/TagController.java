package com.example.demo.controller;

import com.example.demo.domain.Tag;
import com.example.demo.service.ITagService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/Tag")
@RestController
public class TagController {

    @Autowired
    private ITagService iTagService;

    @GetMapping("/tags")
    public List<Tag> listTag(){
        return iTagService.listTags();
    }

    @GetMapping("/tagById")
    public List<Tag> selectByTagId(@Param("tagId") Integer tagId){
        return iTagService.selectByTagId(tagId);
    }

    @GetMapping("/addtag")
    public String insertTag(@RequestBody Tag tag){
        iTagService.insertTag(tag);
        System.out.println("执行完毕");
        return "成功添加";
    }

    @GetMapping("/deltag")
    public String delTagById(@RequestBody Tag tag){
        iTagService.delTagById(tag);
        System.out.println("执行完毕");
        return "成功删除";
    }

    @GetMapping("/updatetag")
    public List<Tag> updateTagById(@RequestBody Tag tag){
        iTagService.updateTagById(tag);
        System.out.println("执行完毕");
        return listTag();
    };


}
