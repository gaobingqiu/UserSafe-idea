package com.table.advise.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.base.BaseService;
import com.base.dao.PageBean;
import com.base.dao.Pager;
import com.table.advise.entity.Advise;
import com.table.advise.service.AdviseService;

@Service("adviseService")
public class AdviseServiceImpl extends BaseService implements AdviseService {
	
	@Override
	public Advise getAdvise(Integer id) {
		// TODO Auto-generated method stub
		return dao.getObject(Advise.class, id);
	}

	@Override
	public List<Advise> getAllAdvise() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(Advise advise) {
		// TODO Auto-generated method stub
		if (null==advise.getId()) {
			dao.saveObject(advise);
		}
		else {
			dao.updateObject(advise);
		}
	}

	@Override
	public boolean deleteAdvise(Integer id) {
		// TODO Auto-generated method stub
		Advise advise = this.getAdvise(id);
		dao.delete(advise);
		return true;
	}

	@Override
	public Pager<Advise> getByPage(PageBean pageBean) {
		// TODO Auto-generated method stub
		String hql="from Advise";
		int page = pageBean.getPageNo();
		int rows = pageBean.getPageSize();
		return dao.findPager(hql, page, rows);
	}

}
