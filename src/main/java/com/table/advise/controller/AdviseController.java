package com.table.advise.controller;

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
import com.table.advise.service.AdviseService;
import com.table.advise.entity.Advise;

@Controller
@RequestMapping("/advise")
public class AdviseController extends BaseController{

    /*
     * 在配置文件中， userManager会绑定bean中id为userManager的类 有注解就不需要set方法
     */
    @Autowired
    private AdviseService adviseService;

    @RequestMapping("/goList")
    public String goList(HttpServletRequest request) {
        return "table/advise/list";
    }

    @RequestMapping("/getAllAdvise")
    @ResponseBody
    public Pager<Advise> getAllAdvise(HttpServletRequest request,Integer page,Integer rows) {
        PageBean pageBean = new PageBean();
        pageBean.setPageNo(page);
        pageBean.setPageSize(rows);
        Pager<Advise> pager = adviseService.getByPage(pageBean);
        return pager;
    }

    /**
     * 
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/getAdvise")
    public String getAdvise(Integer id, HttpServletRequest request) {
        request.setAttribute("advise", adviseService.getAdvise(id));
        return "/editAdvise";
    }

    @RequestMapping("/toAddAdvise")
    public String toAddAdvise() {
        return "/addAdvise";
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public boolean SaveOrUpdate(Advise advise, HttpServletRequest request) {
        adviseService.saveOrUpdate(advise);
        return true;
    }

    /**
     *
     * @param id
     * @param response
     */
    @RequestMapping("/delAdvise")
    public void delAdvise(Integer id, HttpServletResponse response) {

        String result = "{\"result\":\"error\"}";

        if (adviseService.deleteAdvise(id)) {
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

