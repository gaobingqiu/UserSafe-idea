package com.table.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.BaseController;
import com.base.dao.PageBean;
import com.base.dao.Pager;
import com.table.web.entity.Web;
import com.table.web.service.WebService;

@Controller
@RequestMapping("/web")
public class WebController extends BaseController{

    /*
     * 在配置文件中， userManager会绑定bean中id为userManager的类 有注解就不需要set方法
     */
    @Autowired
    private WebService webService;

    @RequestMapping("/goList")
    public String goList(HttpServletRequest request) {
        return "table/web/list";
    }

    @RequestMapping("/getAllWeb")
    @ResponseBody
    public Pager<Web> getAllWeb(HttpServletRequest request,Integer page,Integer rows) {
        PageBean pageBean = new PageBean();
        pageBean.setPageNo(page);
        pageBean.setPageSize(rows);
        Pager<Web> pager = webService.getByPage(pageBean);
        return pager;
    }

    /**
     * 
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("/getWeb")
    public String getWeb(Integer id, HttpServletRequest request) {
        request.setAttribute("web", webService.getWeb(id));
        return "/editWeb";
    }

    @RequestMapping("/toAddWeb")
    public String toAddWeb() {
        return "/addWeb";
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public boolean SaveOrUpdate(Web web, HttpServletRequest request) {
        webService.saveOrUpdate(web);
        return true;
    }

    /**
     *
     * @param id
     * @param response
     */
    @RequestMapping("/delWeb")
    public void delWeb(Integer id, HttpServletResponse response) {

        String result = "{\"result\":\"error\"}";

        if (webService.deleteWeb(id)) {
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

    @RequestMapping("/getWebs")
    @ResponseBody
    public List<Web> getWebs(HttpServletRequest request) {
        List<Web> list = webService.getWebs();
        return list;
    }
}

