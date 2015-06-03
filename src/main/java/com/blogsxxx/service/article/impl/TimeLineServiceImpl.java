package com.blogsxxx.service.article.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogsxxx.dao.TimeLineDao;
import com.blogsxxx.model.TimeLine;
import com.blogsxxx.service.article.TimeLineService;

@Service
public class TimeLineServiceImpl implements TimeLineService {
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

	@Override
	public void addOrUpdateTimeLineByTimestr(Integer temp) {
		// TODO Auto-generated method stub
		TimeLine timeLine = timeLineDao.findTimelineByTimestr(temp);
		if (timeLine != null) {
			timeLine.setArticlecount(timeLine.getArticlecount()+1);
			timeLineDao.updateTimeLine(timeLine);
		} else {
			timeLine = new TimeLine();
			timeLine.setArticlecount(1);
			String createTime=temp+"";
			timeLine.setDescr(createTime.substring(0, 4) + "年"
					+ createTime.substring(4) + "月");
			timeLine.setTimestr(temp);
			timeLineDao.insert(timeLine);
		}
	}

}
