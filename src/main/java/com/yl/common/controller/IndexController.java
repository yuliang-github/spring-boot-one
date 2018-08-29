package com.yl.common.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Alex
 * @since 2018/8/28 14:03
 * @des 主页控制层
 */
@RestController
public class IndexController {

    @GetMapping("/index")
    public JSONObject index(){
        JSONObject ret = new JSONObject();
        ret.put("name","小爱");
        ret.put("sex","man");
        ret.put("birthday",new Date());
        return  ret;
    }

    @GetMapping("/getMsg")
    public String getMsg(){
        return "热加载Devtools";
    }
}