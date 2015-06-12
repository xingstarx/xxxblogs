package com.blogsxxx.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * @author xiongxingxing
 * @desc 存储过程
 *
 */
@Repository(value="logDao")
public interface LogDao {
	/**
	 * 插入日志表通过id
	 */
	public abstract void addLogByOrderId(Map param);
	
}
