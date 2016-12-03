package com.table.limit.controller;

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
import com.table.limit.entity.ErrorLimit;
import com.table.limit.service.LimitService;

@Controller
@RequestMapping("/limit")
public class LimitController extends BaseController{

    /*
     * 在配置文件中， userManager会绑定bean中id为userManager的类 有注解就不需要set方法
     */
    @Autowired
    private LimitService limitService;

    @RequestMapping("/goList")
    public String goList(HttpServletRequest request) {
    	log.debug("跳转到限制管理页面");
        return "table/limit/list";
    }

    @RequestMapping("/getAllManager")
    @ResponseBody
    public Pager<ErrorLimit> getAllLimit(HttpServletRequest request,Integer page,Integer rows) {
        PageBean pageBean = new PageBean();
        pageBean.setPageNo(page);
        pageBean.setPageSize(rows);
        Pager<ErrorLimit> pager = limitService.getByPage(pageBean);
        return pager;
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public boolean SaveOrUpdate(ErrorLimit limit, HttpServletRequest request) {
        limitService.saveOrUpdate(limit);
        return true;
    }

    /**
     *
     * @param id
     * @param response
     */
    @RequestMapping("/delLimit")
    public void delLimit(Integer id, HttpServletResponse response) {

        String result = "{\"result\":\"error\"}";

        if (limitService.deleteLimit(id)) {
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

