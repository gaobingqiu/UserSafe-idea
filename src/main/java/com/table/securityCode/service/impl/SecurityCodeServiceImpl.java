package com.table.securityCode.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.base.BaseService;
import com.base.dao.PageBean;
import com.base.dao.Pager;
import com.table.securityCode.entity.SecurityCode;
import com.table.securityCode.service.SecurityCodeService;

@Service("securityCodeService")
public class SecurityCodeServiceImpl extends BaseService implements SecurityCodeService {
	
	@Override
	public SecurityCode getSecurityCode(Integer id) {
		// TODO Auto-generated method stub
		return dao.getObject(SecurityCode.class, id);
	}

	@Override
	public List<SecurityCode> getAllSecurityCode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(SecurityCode securityCode) {
		// TODO Auto-generated method stub
		if (null==securityCode.getId()) {
			dao.saveObject(securityCode);
		}
		else {
			dao.updateObject(securityCode);
		}
	}

	@Override
	public boolean deleteSecurityCode(Integer id) {
		// TODO Auto-generated method stub
		SecurityCode securityCode = this.getSecurityCode(id);
		dao.delete(securityCode);
		return true;
	}

	@Override
	public Pager<SecurityCode> getByPage(PageBean pageBean) {
		// TODO Auto-generated method stub
		String hql="from SecurityCode";
		int page = pageBean.getPageNo();
		int rows = pageBean.getPageSize();
		return dao.findPager(hql, page, rows);
	}

	public SecurityCode getSecurityCodeByUserId(String userId){
		String hql="from SecurityCode where userId=? order by id desc";
		return dao.findObject(hql, userId);
	}
	
	public SecurityCode getSecurityCodeByTel(String tel){
		String hql="from SecurityCode where tel=? order by id desc";
		return dao.findObject(hql, tel);
	}
	
	public SecurityCode getSecurityCodeByEmail(String email){
		String hql="from SecurityCode where tel=? order by id desc";
		return dao.findObject(hql, email);
	}
}
