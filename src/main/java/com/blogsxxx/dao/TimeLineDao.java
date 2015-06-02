package com.blogsxxx.dao;

import java.util.List;

import com.blogsxxx.model.TimeLine;

public interface TimeLineDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TimeLine record);

    int insertSelective(TimeLine record);

    TimeLine selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TimeLine record);

    int updateByPrimaryKey(TimeLine record);
/**
 * @desc 更新
 * @param timeLine
 */
	void updateTimeLine(TimeLine timeLine);
/**
 * @desc 根据时间倒序，取出所有的时间线集合
 * @return
 */
	List<TimeLine> findAllTimeLineList();
/**
 * @desc 根据timeStr查找对应的timeline
 * @param timeStr
 * @return
 */
TimeLine findTimelineByTimestr(Integer timestr);
}