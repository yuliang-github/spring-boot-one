package com.yl.common.controller;

import com.alibaba.fastjson.JSONObject;
import com.yl.common.demo.CustomerGetMapping;
import com.yl.common.demo.User;
import com.yl.common.service.UserService;
import com.yl.common.service.impl.ImplService;
import com.yl.common.service.impl.UserServiceImpl;
import com.yl.exception.common.MvcException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Alex
 * @since 2018/8/28 14:03
 * @des 主页控制层
 */
@RestController
public class IndexController {

    @Autowired
    private UserService userService;
    @Autowired
    private ImplService implService;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @Value("${server.port}")
    private int port;

    @GetMapping("/")
    public JSONObject index(){
        JSONObject ret = new JSONObject();
        ret.put("port",port);
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
        userService.updateName(2, "说什么呢");
        return "customeGetMapping测试";
    }

    @CustomerGetMapping(value = "/getUser")
    public User getBody(){
        User user = new User(1,"阿童木");
        return user;
    }

    @CustomerGetMapping(value = "/spring-boot/demo")
    public User springboot(){
        User user = new User(1,"阿童木");
        return user;
    }

    @CustomerGetMapping(value = "/spring-boot/exception")
    public User springbootException(int type){
        //User user = new User(1,"阿童木");
        if(type == 0){
            throw new RuntimeException("未知异常");
        }else{
            throw new MvcException("自定义错误返回");
        }
        //return user;
    }

    @CustomerGetMapping(value = "/spring-boot/getList")
    public List<Integer> getList(){
        List<Integer> list = new ArrayList<>();
        list.add(1);list.add(2);list.add(3);list.add(4);
        return list.subList(0, 2);
    }

    @CustomerGetMapping(value = "/spring-boot/auto")
    public String auto(){
        implService.print();
        return "auto";
    }

    @CustomerGetMapping(value = "/spring-boot/tx")
    public String tx(@RequestParam("id") int id, @RequestParam("name") String name){
        userServiceImpl.update(id, name);
        return "tx";
    }
}
