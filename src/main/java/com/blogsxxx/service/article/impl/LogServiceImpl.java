package com.blogsxxx.service.article.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.blogsxxx.dao.LogDao;
import com.blogsxxx.service.article.LogService;

@Service(value="logService")
public class LogServiceImpl implements LogService {

	@Resource(name="logDao")
	private LogDao logDao;
	

	@Override
	public void addArticleLog(Integer id) {
		Map param = new HashMap();
		param.put("p_id", id);
		param.put("p_record_table", "tb_article");
		this.logDao.addLogByOrderId(param);
		if(param.get("r_succuss_flag").equals("N")){throw new RuntimeException();}
	}

	
}
