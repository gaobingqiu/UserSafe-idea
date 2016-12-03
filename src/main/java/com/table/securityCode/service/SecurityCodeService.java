package com.table.securityCode.service;

import java.util.List;

import com.base.dao.PageBean;
import com.base.dao.Pager;
import com.table.securityCode.entity.SecurityCode;

public interface SecurityCodeService {
	public SecurityCode getSecurityCode(Integer id) ;
	
	public List<SecurityCode> getAllSecurityCode();
	
	public void saveOrUpdate(SecurityCode securityCode);
	
	public boolean deleteSecurityCode(Integer id);
	
	public Pager<SecurityCode> getByPage(PageBean pageBean);

	public SecurityCode getSecurityCodeByUserId(String userId);

	public SecurityCode getSecurityCodeByTel(String tel);
	
	public SecurityCode getSecurityCodeByEmail(String email);
	
}
