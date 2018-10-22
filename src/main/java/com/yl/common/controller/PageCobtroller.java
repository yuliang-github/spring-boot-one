package com.yl.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author yu.alex
 * @Create 2018/10/14 9:21
 */
@Controller
public class PageCobtroller {

    @RequestMapping("/demo")
    public String demoJsp(){
        return "demo";
    }

    @RequestMapping("/test")
    public String testHtml(){
        return "test";
    }
}
