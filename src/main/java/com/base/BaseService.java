package com.base;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.base.dao.BaseDao;

/**
 * 基础的service,主要包含日志和baseDao的调用
 * @author gbq
 * 2016年3月24日
 */
public class BaseService {
	@Resource
	protected BaseDao dao;
	protected Log log = LogFactory.getLog(BaseController.class);
}
