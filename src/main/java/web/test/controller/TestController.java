package web.test.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import web.interfaces.service.InterfaceService;
import web.interfaces.vo.PassVo;
import web.interfaces.vo.RegisterVo;

@Controller
@RequestMapping("/test")
public class TestController {
	@Autowired
	private InterfaceService interfaceService;
	
	@RequestMapping("/testRegister")
	@ResponseBody
	public String testRegister(HttpServletRequest request,String userName){
		String verifyCode = "gbq123456";
		String webName = "测试";
		RegisterVo registerVo = interfaceService.quickRegister(request, verifyCode, webName, userName);
		return registerVo.toString();
	}
	
	@RequestMapping("/testLogin")
	@ResponseBody
	public String testLogin(HttpServletRequest request){
		String verifyCode = "gbq123456";
		String webName = "测试";
		String userName = "gbq";
		PassVo passVo = interfaceService.quickPass(request, verifyCode, webName, userName);
		return passVo.toString();
	}

}
