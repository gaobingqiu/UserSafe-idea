package com.table.web.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.base.BaseService;
import com.base.dao.PageBean;
import com.base.dao.Pager;
import com.table.web.entity.Web;
import com.table.web.service.WebService;

@Service("webService")
public class WebServiceImpl extends BaseService implements WebService {
	
	@Override
	public Web getWeb(Integer id) {
		// TODO Auto-generated method stub
		return dao.getObject(Web.class, id);
	}

	@Override
	public List<Web> getAllWeb() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(Web web) {
		// TODO Auto-generated method stub
		if (null==web.getId()) {
			dao.saveObject(web);
		}
		else {
			dao.updateObject(web);
		}
	}

	@Override
	public boolean deleteWeb(Integer id) {
		// TODO Auto-generated method stub
		Web web = this.getWeb(id);
		dao.delete(web);
		return true;
	}

	@Override
	public Pager<Web> getByPage(PageBean pageBean) {
		// TODO Auto-generated method stub
		String hql="from Web";
		int page = pageBean.getPageNo();
		int rows = pageBean.getPageSize();
		return dao.findPager(hql, page, rows);
	}
	
	@Override
	public List<Web> getWebs(){
		String hql="select top 20 * from Web order by score";
		return dao.findList(hql);
	}

	@Override
	public Web getWebByName(String webName) {
		// TODO Auto-generated method stub
		String hql="from Web where title like ?";
		return dao.findObject(hql, webName);
	}
	
	@Override
	public Web getWebByUrl(String link) {
		// TODO Auto-generated method stub
		String hql="from Web where link = ?";
		return dao.findObject(hql, link);
	}
}
