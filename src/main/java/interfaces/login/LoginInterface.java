package interfaces.login;

import interfaces.entity.BaseResponse;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import web.index.service.IndexService;
import web.interfaces.service.InterfaceService;
import web.interfaces.vo.RegisterVo;
import web.login.service.LoginService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.BaseController;
import com.base.random.RandomSecret;
import com.table.user.entity.User;
import com.table.user.service.UserService;

@Controller
@RequestMapping("/loginInterface")
public class LoginInterface extends BaseController {
	@Autowired
	private LoginService loginService;
	@Autowired
	private IndexService indexService;
	@Autowired
	private UserService userService;
	@Autowired
	private InterfaceService interfaceService;
	private String phoneMac;

	@RequestMapping("/login")
	@ResponseBody
	public BaseResponse<User> login(HttpServletRequest request,
			HttpServletResponse response, String password, String userName,
			String token) throws ServletException, IOException {
		User user = loginService.userLogin(request, userName, password, token);
		BaseResponse<User> baseResponse = new BaseResponse<User>();
		baseResponse.setCode(0);
		baseResponse.setData(user);
		return baseResponse;
	}

	@RequestMapping(value = "/reset", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String reset(HttpServletRequest request,
			HttpServletResponse response, String password, String tel,
			String token) throws ServletException, IOException {
		User user = userService.getUserByPhone(tel);
		phoneMac = user.getMac();
		if (null == phoneMac || !phoneMac.equals(token)) {
			return "你无权进行此操作！";
		}
		if (!loginService.resetPassword(user, password)) {
			return "error";
		}
		return "success";
	}

	@RequestMapping("/getUser")
	@ResponseBody
	public String getUser(HttpServletRequest request,
			HttpServletResponse response, String userName, String token)
			throws ServletException, IOException {
		User user = userService.getUserByName(userName);
		JSONObject json = (JSONObject) JSON.toJSON(user);
		HttpSession session = request.getSession();
		session.setAttribute("mac", token);
		session.setAttribute("userId", user.getId());
		session.setMaxInactiveInterval(30 * 60);
		return json.toJSONString();
	}

	@RequestMapping("/getCode")
	@ResponseBody
	public BaseResponse<String> getCode(HttpServletRequest request,
			HttpServletResponse response, String tel) throws ServletException, IOException {
		Integer code = Integer.valueOf(RandomSecret.getNum());
		BaseResponse<String> baseResponse = new BaseResponse<String>();
		if (indexService.setTelCode(tel, code)) {
			baseResponse.setCode(0);
			baseResponse.setData("验证码已经成功发送到了你的手机，请查收");
		}
		else {
			baseResponse.setCode(1003);
			baseResponse.setMsg("验证码发送失败");
		}
		return baseResponse;
	}

	@RequestMapping("/register")
	@ResponseBody
	public BaseResponse<String> register(HttpServletRequest request,
			HttpServletResponse response, String userName, String tel,
			String password, Integer code) throws Exception {
		BaseResponse<String> baseResponse = new BaseResponse<String>();
		if (null == code) {
			baseResponse.setCode(1001);
			baseResponse.setMsg("请获取验证码");
		} else if (!indexService.checkCodeByTel(tel, code)) {
			baseResponse.setCode(1002);
			baseResponse.setMsg("验证码错误");
		} else if (loginService.register(userName, tel, password)) {
			baseResponse.setCode(0);
			baseResponse.setData(userName);
		}
		return baseResponse;
	}

	@RequestMapping("/quickRegister")
	@ResponseBody
	public BaseResponse<RegisterVo> quickRegister(HttpServletRequest request,String userName, HttpServletResponse response)
			throws ServletException, IOException {
		String verifyCode = "";
		String webName ="nubia";
		RegisterVo registerVo = interfaceService.quickRegister(request,
				verifyCode, webName, userName);
		User user = new User();
		user.setUserName(userName);
		user.setPassword(registerVo.getPassword());
		userService.saveOrUpdate(user);
		BaseResponse<RegisterVo> baseResponse = new BaseResponse<RegisterVo>();
		baseResponse.setCode(0);
		baseResponse.setData(registerVo);
		return baseResponse;
	}
}
