package com.blogsxxx.dao;

import org.springframework.stereotype.Repository;

import com.blogsxxx.model.Article;
public interface ArticleDao {

    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);
}