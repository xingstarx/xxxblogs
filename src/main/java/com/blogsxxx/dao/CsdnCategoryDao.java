package com.blogsxxx.dao;

import java.util.List;

import com.blogsxxx.model.CsdnCategory;

public interface CsdnCategoryDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CsdnCategory record);

    int insertSelective(CsdnCategory record);

    CsdnCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsdnCategory record);

    int updateByPrimaryKey(CsdnCategory record);
/**
 * @author xiongxingxing
 * @desc 查询所有的类别记录
 * @return
 */
	List<CsdnCategory> findAllCsdnCategory();
}