package com.example.demo.dao;

import com.example.demo.domain.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TagDao {

     List<Tag> listTags();

     List<Tag> selectByTagId(Integer tagId);

     void insertTag(Tag tag);

     void delTagById(Tag tag);

     void updateTagById(Tag tag);
}
