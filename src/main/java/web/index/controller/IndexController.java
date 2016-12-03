package web.index.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.BaseController;
import com.base.HttpUtils;
import com.base.random.RandomSecret;
import com.table.advise.entity.Advise;
import com.table.advise.service.AdviseService;
import com.table.user.entity.User;
import com.table.user.service.UserService;

import web.index.service.IndexService;

@Controller
@RequestMapping("/index")
public class IndexController extends BaseController {
	@Autowired
	AdviseService adviseService;
	@Autowired
	UserService userService;
	@Autowired
	IndexService indexService;

	@RequestMapping("/getCode")
	@ResponseBody
	public String getCode(HttpServletRequest request, String email, String tel) {
		Integer code = Integer.valueOf(RandomSecret.getNum());
		if (null == email) {
			if (indexService.setTelCode(tel, code)) {
				return "success";
			} else {
				return "error";
			}

		}
		if (null == tel) {
			if (indexService.setEmailCode(email, code)) {
				return "success";
			} else {
				return "error";
			}
		}
		return "success";
	}

	@RequestMapping("/addAdvise")
	@ResponseBody
	public String addAdvise(HttpServletRequest request, String userName, String tel, String content, String email) {
		Advise advise = new Advise();
		HttpSession session = HttpUtils.getSession(request);
		String userId = (String) session.getAttribute("userId");
		if(null!=userId){
			advise.setUserId(userId);
		}
		advise.setDescrible(content);
		advise.setEmail(email);
		advise.setTel(tel);
		advise.setUserName(userName);
		try {
			adviseService.saveOrUpdate(advise);
			return "success";
		} catch (Exception e) {
			// TODO: handle exception
			return "error";
		}
	}

	@RequestMapping("/loginOut_manager")
	public String loginOut(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		log.debug("管理员退出登录！");
		session.setAttribute("managerName", null);
		return "manage/login";
	}

	@RequestMapping("/goManager")
	public String goList(HttpServletRequest request) {
		return "manage/login";
	}

	@RequestMapping("/404")
	public String go404(HttpServletRequest request) {
		return "404";
	}

	@RequestMapping("/interfaces")
	public String interfaces(HttpServletRequest request) {
		return "index/interfaces";
	}

	@RequestMapping("/safeKnow")
	public String safeKnow(HttpServletRequest request) {
		return "index/safeKnow";
	}

	@RequestMapping("/advise")
	public String advise(HttpServletRequest request, Model model) {
		HttpSession session = HttpUtils.getSession(request);
		String userId = (String) session.getAttribute("userId");
		if (null != userId) {
			User user = userService.getUser(userId);
			model.addAttribute("userName", user.getUserName());
		}
		return "index/advise";
	}

	@RequestMapping("/toUpload")
	// @ResponseBody 返回json数据
	public String toUpload() {
		log.error("到了");
		return "test/upload/upload";
	}
}
