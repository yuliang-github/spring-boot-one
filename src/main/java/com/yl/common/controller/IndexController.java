package com.yl.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.yl.common.demo.CustomerGetMapping;
import com.yl.common.demo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
        ret.put("sex","人工智能机器人");
        ret.put("birthday",new Date());
        return  ret;
    }

    @GetMapping("/getMsg")
    public String getMsg(){
        return "热加载Devtools";
    }

    @CustomerGetMapping(value = "/sessionDemo")
    public String sessionGet(){
        return "customeGetMapping测试";
    }

    @CustomerGetMapping(value = "/getUser")
    public User getBody(){
        User user = new User(1,"阿童木");
        return user;
    }
}
