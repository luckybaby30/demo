package com.example.demo.service.impl;

import com.example.demo.dao.TagDao;
import com.example.demo.domain.Tag;
import com.example.demo.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements ITagService {

    @Autowired
    private TagDao tagDao;

    public List<Tag> listTags(){ return tagDao.listTags(); }

    public List<Tag> selectByTagId(Integer tagId){
        return tagDao.selectByTagId(tagId);
    }

    public void insertTag(Tag tag){
        tagDao.insertTag(tag);
    }

    public void delTagById(Tag tag){
        tagDao.delTagById(tag);
    }

    public void updateTagById(Tag tag){
        tagDao.updateTagById(tag);
    }

}
