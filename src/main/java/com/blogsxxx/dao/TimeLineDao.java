package com.blogsxxx.dao;

import com.blogsxxx.model.TimeLine;

public interface TimeLineDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TimeLine record);

    int insertSelective(TimeLine record);

    TimeLine selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TimeLine record);

    int updateByPrimaryKey(TimeLine record);
}