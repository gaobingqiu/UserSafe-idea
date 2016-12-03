package com.table.limit.service;

import java.util.List;

import com.base.dao.PageBean;
import com.base.dao.Pager;
import com.table.limit.entity.ErrorLimit;

public interface LimitService {
	public ErrorLimit getLimit(Integer id) ;
	
	public List<ErrorLimit> getAllLimit();
	
	public void saveOrUpdate(ErrorLimit manager);
	
	public boolean deleteLimit(Integer id);
	
	public Pager<ErrorLimit> getByPage(PageBean pageBean);
	
	public int getCurrentCount(String userId, Integer type);
	
	public int countAdd(String userId, Integer type);
	
	public int PassLimit(String userId, Integer type);
	
	public void disable(String userId, Integer type);
}
