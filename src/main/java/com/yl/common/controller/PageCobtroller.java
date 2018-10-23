package com.yl.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author yu.alex
 * @Create 2018/10/14 9:21
 */
@Controller
public class PageCobtroller {

    @RequestMapping("/demo")
    public String demoJsp(HttpServletRequest request){
        return "demo";
    }

    @RequestMapping("/test")
    public String testHtml(){
        return "test";
    }
}
