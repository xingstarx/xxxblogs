package com.blogsxxx.service.article;

import java.util.List;

import org.ietf.jgss.Oid;

import com.blogsxxx.model.TimeLine;

/**
 * @desc  时间线
 * @author xiongxingxing
 *
 */
public interface TimeLineService {
	/**
	 * @desc  添加一条时间线
	 * @param timeLine
	 */
	void addTimeLine(TimeLine timeLine);
	/**
	 * @desc  修改一条时间线
	 * @param timeLine
	 */
	void updateTimeLine(TimeLine timeLine);
	/**
	 * @desc  根据时间倒序排列出所有的时间线
	 * @param timeLine
	 */
	List<TimeLine> findAllTimeLineList();
	/**
	 * @desc 根据timestr生成一条时间线
	 * @param temp
	 */
	void addOrUpdateTimeLineByTimestr(Integer temp);
}
