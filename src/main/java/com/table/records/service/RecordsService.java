package com.table.records.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.base.dao.PageBean;
import com.base.dao.Pager;
import com.table.records.entity.Records;

public interface RecordsService {
	public Records getRecords(String id) ;
	
	public List<Records> getAllRecords();
	
	public void saveOrUpdate(Records manager);
	
	public boolean deleteRecords(String id);
	
	public Pager<Records> getByPage(HttpServletRequest request, PageBean pageBean);
	
	public List<Records> getByList(HttpServletRequest request, PageBean pageBean);

	public String getWeb(String userName, String title);
}
