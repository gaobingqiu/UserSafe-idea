package com.table.securityCode.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.BaseController;
import com.base.dao.PageBean;
import com.base.dao.Pager;
import com.table.securityCode.service.SecurityCodeService;
import com.table.securityCode.entity.SecurityCode;

@Controller
@RequestMapping("/securityCode")
public class SecurityCodeController extends BaseController{

    /*
     * 在配置文件中， userManager会绑定bean中id为userManager的类 有注解就不需要set方法
     */
    @Autowired
    private SecurityCodeService securityCodeService;

    @RequestMapping("/goList")
    public String goList(HttpServletRequest request) {
        return "table/securityCode/list";
    }

    @RequestMapping("/getAllSecurityCode")
    @ResponseBody
    public Pager<SecurityCode> getAllSecurityCode(HttpServletRequest request,Integer page,Integer rows) {
        PageBean pageBean = new PageBean();
        pageBean.setPageNo(page);
        pageBean.setPageSize(rows);
        Pager<SecurityCode> pager = securityCodeService.getByPage(pageBean);
        return pager;
    }

    /**
     * 
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/getSecurityCode")
    public String getSecurityCode(Integer id, HttpServletRequest request) {
        request.setAttribute("securityCode", securityCodeService.getSecurityCode(id));
        return "/editSecurityCode";
    }

    @RequestMapping("/toAddSecurityCode")
    public String toAddSecurityCode() {
        return "/addSecurityCode";
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public boolean SaveOrUpdate(SecurityCode securityCode, HttpServletRequest request) {
        securityCodeService.saveOrUpdate(securityCode);
        return true;
    }

    /**
     *
     * @param id
     * @param response
     */
    @RequestMapping("/delSecurityCode")
    public void delSecurityCode(Integer id, HttpServletResponse response) {

        String result = "{\"result\":\"error\"}";

        if (securityCodeService.deleteSecurityCode(id)) {
            result = "{\"result\":\"success\"}";
        }
        response.setContentType("application/json");
        try {
            PrintWriter out = response.getWriter();
            out.write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

