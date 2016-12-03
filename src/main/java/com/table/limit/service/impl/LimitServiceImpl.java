package com.table.limit.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.base.BaseService;
import com.base.dao.PageBean;
import com.base.dao.Pager;
import com.table.limit.entity.ErrorLimit;
import com.table.limit.entity.PassType;
import com.table.limit.service.LimitService;

/**
 * 错误次数限制
 * @author gbq
 * 2016年4月24日
 */
@Service("limitService")
public class LimitServiceImpl extends BaseService implements LimitService {

	@Override
	public ErrorLimit getLimit(Integer id) {
		// TODO Auto-generated method stub
		return dao.getObject(ErrorLimit.class, id);
	}

	@Override
	public List<ErrorLimit> getAllLimit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(ErrorLimit limit) {
		// TODO Auto-generated method stub
		if (null == limit.getId()) {
			dao.saveObject(limit);
		} else {
			dao.updateObject(limit);
		}
	}

	@Override
	public boolean deleteLimit(Integer id) {
		// TODO Auto-generated method stub
		ErrorLimit limit = this.getLimit(id);
		dao.delete(limit);
		return true;
	}

	@Override
	public Pager<ErrorLimit> getByPage(PageBean pageBean) {
		// TODO Auto-generated method stub
		String hql = "from ErrorLimit";
		int page = pageBean.getPageNo();
		int rows = pageBean.getPageSize();
		return dao.findPager(hql, page, rows);
	}

	@Override
	public int PassLimit(String userId, Integer type) {
		int currentCount = this.getCurrentCount(userId, type);
		int maxCount = PassType.getCount(type);
		String hql = "from ErrorLimit where userId=? and type=?";
		ErrorLimit limit = dao.findObject(hql, userId, type);
		Long time = System.currentTimeMillis();
		if (currentCount >= maxCount) {
			if (time >= Integer.parseInt(limit.getEnableTime())) {
				this.reSet(userId, type);
				return 1;
			} else {
				this.disable(userId, type);
				return 0;
			}
		}
		return 0;
	}

	public void reSet(String userId, Integer type) {
		String hql = "from ErrorLimit where userId=? and type=?";
		ErrorLimit limit = dao.findObject(hql, userId, type);
		limit.setEnableTime(""+0);
		limit.setEnable(1);
		this.saveOrUpdate(limit);
	}

	public void disable(String userId, Integer type) {
		String hql = "from ErrorLimit where userId=? and type=?";
		ErrorLimit limit = dao.findObject(hql, userId, type);
		limit.setEnableTime(this.refFormatNowDate(PassType.getTime(type)));
		limit.setEnable(0);
		this.saveOrUpdate(limit);
	}

	@Override
	public int getCurrentCount(String userId, Integer type) {
		String hql = "from ErrorLimit where userId=? and type=?";
	
		String currentTime = this.refFormatNowDate();
		int count = dao.countObjects(hql, userId, type);
		if (count == 0) {
			ErrorLimit limit = new ErrorLimit();
			limit.setUserId(userId);
			limit.setType(type);
			limit.setEnable(1);
			limit.setCurrentCount(0);
			limit.setEnableTime(this.refFormatNowDate(PassType.getTime(type)));	
			limit.setCurrentTime(currentTime);
			this.saveOrUpdate(limit);
			return 1;
		} else {
			ErrorLimit limit = dao.findObject(hql, userId, type);
			if(limit.getEnable()==0){
				return 100;
			}
			this.countAdd(userId, type);
			return ++count;
		}
	}

	@Override
	public int countAdd(String userId, Integer type) {
		try {
			String hql = "from ErrorLimit where userId=? and type=?";
			ErrorLimit limit = dao.findObject(hql, userId, type);
			limit.setCurrentCount(limit.getCurrentCount() + 1);
			dao.updateObject(limit);
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}
	
	public String refFormatNowDate(Long time) {
		Date nowTime = new Date(System.currentTimeMillis()+time*60);
		SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String retStrFormatNowDate = sdFormatter.format(nowTime);
		return retStrFormatNowDate;
	}
	
	public String refFormatNowDate() {
		Date nowTime = new Date(System.currentTimeMillis());
		SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String retStrFormatNowDate = sdFormatter.format(nowTime);
		return retStrFormatNowDate;
	}
}
