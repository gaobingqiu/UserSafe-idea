package com.table.records.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.BaseService;
import com.base.dao.PageBean;
import com.base.dao.Pager;
import com.base.encrypt.aes.AES;
import com.table.records.entity.Records;
import com.table.records.service.RecordsService;
import com.table.user.entity.User;
import com.table.user.service.UserService;
import com.table.web.entity.Web;
import com.table.web.service.WebService;

@Service("recordsService")
public class RecordsServiceImpl extends BaseService implements RecordsService {
	@Autowired
	WebService webService;
	
	@Autowired
	UserService userService;
	
	@Override
	public Records getRecords(String id) {
		// TODO Auto-generated method stub
		return dao.getObject(Records.class, id);
	}

	@Override
	public List<Records> getAllRecords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(Records records) {
		// TODO Auto-generated method stub
		if (null==records.getId()||records.getId().length()==0) {
			dao.saveObject(records);
		}
		else {
			dao.updateObject(records);
		}
	}

	@Override
	public boolean deleteRecords(String id) {
		// TODO Auto-generated method stub
		Records records = this.getRecords(id);
		dao.delete(records);
		return true;
	}

	@Override
	public Pager<Records> getByPage(HttpServletRequest request,PageBean pageBean) {
		// TODO Auto-generated method stub
		String hql="from Records where userId =?";
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		int page = pageBean.getPageNo();
		int rows = pageBean.getPageSize();
		Pager<Records> pager = new Pager<Records>();
		pager = dao.findPager(hql, page, rows,userId);
		List<Records> list =new ArrayList<Records>();
		List<Records> list2 =new ArrayList<Records>();
		list= pager.getRows();
		String userPassword;
		for (Records records : list) {
			Records record =new Records();
			record.setCreateTime(records.getCreateTime());
			record.setId(records.getId());
			record.setLink(records.getLink());
			record.setName(records.getName());
			record.setUserId(userId);
			try {
				userPassword = AES.Decrypt(records.getPassword(), "1234567890123456");
				record.setPassword(userPassword);
				list2.add(record);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		pager.setRows(list2);
		return pager;
	}
	
	public List<Records> getByList(HttpServletRequest request, PageBean pageBean){
		String hql="from Records where userId =?";
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		int page = pageBean.getPageNo();
		int rows = pageBean.getPageSize();
		return dao.findList(hql,page,rows,userId);
	}
	
	public String getWeb(String userName,String title){
		Web web = webService.getWebByName(title); 
		User user = userService.getUserByName(userName);
		String hql="from Records where userId=? and webId =?";
		Records  records = dao.findObject(hql, user.getId(),web.getId());
		if(null!=records)
		return title;
		return null;
	}
	
	
}
