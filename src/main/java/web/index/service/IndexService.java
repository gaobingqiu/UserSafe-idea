package web.index.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.Define;
import com.base.HttpUtils;
import com.table.limit.entity.PassType;
import com.table.limit.service.LimitService;
import com.table.securityCode.entity.SecurityCode;
import com.table.securityCode.service.SecurityCodeService;
import com.table.user.entity.User;
import com.table.user.service.UserService;

@Service
public class IndexService {
	@Autowired
	SecurityCodeService securityCodeService;
	@Autowired
	LimitService limitService;
	@Autowired
	UserService userService;

	public boolean setTelCode(String tel,Integer code) {
		try {
			User user = userService.getUserByPhone(tel);
			SecurityCode securityCode = new SecurityCode();
			if(null!=user){
				String userId = user.getId();
				securityCode.setUserId(userId);
			}
			securityCode.setCode(code);
			securityCode.setTel(tel);
			securityCode.setType(Define.telPhone);
			securityCodeService.saveOrUpdate(securityCode);
			return true;		
		} catch (Exception e) {
			// TODO: handle exception
			return false;		
		}
	}
	
	public boolean setEmailCode(String email,Integer code) {
		try {
			User user = userService.getUserByEmail(email);
			String userId = user.getId();
			SecurityCode securityCode = new SecurityCode();
			securityCode.setCode(code);
			securityCode.setEmail(email);
			securityCode.setType(Define.telPhone);
			securityCode.setUserId(userId);
			securityCodeService.saveOrUpdate(securityCode);
			return true;		
		} catch (Exception e) {
			// TODO: handle exception
			return false;		
		}
	}
	
	public boolean checkCode(HttpServletRequest request, Integer code) {
		HttpSession session = HttpUtils.getSession(request);
		String userId = (String) session.getAttribute("userId");
		SecurityCode securityCode = securityCodeService.getSecurityCodeByUserId(userId);
		if (securityCode.getCode() == code) {
			return true;
		}
		return false;
	}
	
	public boolean checkCodeByTel(String tel, Integer code) {
		SecurityCode securityCode = securityCodeService.getSecurityCodeByTel(tel);
		if (securityCode.getCode() == code) {
			return true;
		}
		return false;
	}
	
	public boolean resetCode(String userId, Integer code) {
		SecurityCode securityCode = securityCodeService.getSecurityCodeByUserId(userId);
		if (securityCode.getCode() == code) {
			return true;
		}
		return false;
	}

	public boolean limitAble(HttpServletRequest request, Integer type) {
		HttpSession session = HttpUtils.getSession(request);
		String userId = (String) session.getAttribute("userId");
		Integer maxCount = PassType.getCount(type);
		int currentCount = limitService.getCurrentCount(userId, type);
		if (maxCount < currentCount) {
			limitService.disable(userId, type);
			return false;
		}
		return true;
	}
}
