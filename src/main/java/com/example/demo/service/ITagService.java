package com.example.demo.service;

import com.example.demo.domain.Tag;

import java.util.List;

public interface ITagService {

     List<Tag> listTags();

     List<Tag> selectByTagId(Integer tagId);

     void insertTag(Tag tag);

     void delTagById(Tag tag);

     void updateTagById(Tag tag);
}
