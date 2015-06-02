package com.blogsxxx.dao;

import java.util.List;

import com.blogsxxx.model.CsdnArticle;

public interface CsdnArticleDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CsdnArticle record);

    int insertSelective(CsdnArticle record);

    CsdnArticle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsdnArticle record);

    int updateByPrimaryKeyWithBLOBs(CsdnArticle record);

    int updateByPrimaryKey(CsdnArticle record);
/**
 * @author xiongxingxing
 * @desc 查询所有的csdn文章
 * @return
 */
	List<CsdnArticle> findAllCsdnArticle();
}