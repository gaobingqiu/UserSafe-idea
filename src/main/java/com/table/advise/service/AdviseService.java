package com.table.advise.service;

import java.util.List;

import com.base.dao.PageBean;
import com.base.dao.Pager;
import com.table.advise.entity.Advise;

public interface AdviseService {
	public Advise getAdvise(Integer id) ;
	
	public List<Advise> getAllAdvise();
	
	public void saveOrUpdate(Advise advise);
	
	public boolean deleteAdvise(Integer id);
	
	public Pager<Advise> getByPage(PageBean pageBean);
	
}
