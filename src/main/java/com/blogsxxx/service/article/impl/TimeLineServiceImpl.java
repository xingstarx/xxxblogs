package com.blogsxxx.service.article.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogsxxx.dao.TimeLineDao;
import com.blogsxxx.model.TimeLine;
import com.blogsxxx.service.article.TimeLineService;
@Service
public class TimeLineServiceImpl implements TimeLineService{
	@Autowired
private TimeLineDao timeLineDao;
	@Override
	public void addTimeLine(TimeLine timeLine) {
		// TODO Auto-generated method stub
		timeLineDao.insert(timeLine);
	}

	@Override
	public void updateTimeLine(TimeLine timeLine) {
		// TODO Auto-generated method stub
		timeLineDao.updateTimeLine(timeLine);
	}

	@Override
	public List<TimeLine> findAllTimeLineList() {
		// TODO Auto-generated method stub
		return timeLineDao.findAllTimeLineList();
	}

}
