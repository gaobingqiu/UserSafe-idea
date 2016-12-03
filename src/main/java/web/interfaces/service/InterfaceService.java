package web.interfaces.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.BaseService;
import com.table.records.entity.Records;
import com.table.records.service.RecordsService;
import com.table.user.entity.User;
import com.table.user.service.UserService;
import com.table.web.entity.Web;
import com.table.web.service.WebService;

import web.interfaces.vo.PassVo;
import web.interfaces.vo.RegisterVo;

/**
 * 用户登录模块功能类
 * @author gbq
 * 2016年4月19日
 */
@Service
public class InterfaceService extends BaseService{
	@Autowired 
	private RecordsService recordsService;
	
	@Autowired 
	private UserService userService;
	
	@Autowired 
	private WebService webService;

	public PassVo quickPass(HttpServletRequest request,String verifyCode, String webName, String userName) {
		// TODO Auto-generated method stub
		Web web = webService.getWebByName(webName);
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		User user = userService.getUser(userId);
		if(null==user){
			return null;
		} 
		if(web.getTitle().equals(recordsService.getWeb(userName, webName))){
			return new PassVo(request,userName);
		}
		return null; 
	}

	public RegisterVo quickRegister(HttpServletRequest request,String verifyCode, String webName, String userName) {
		// TODO Auto-generated method stub
		Records records = new Records();
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		if(null == userId){
			userId = "15";
		}
		User user = userService.getUser(userId);
		records.setUserId(userId);
		return new RegisterVo(user,userName);
	}
	
}
