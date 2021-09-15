package com.henry.security01.controller;

import com.henry.security01.model.User;
import com.henry.security01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hao.chen
 * @date 2021/9/14 4:42 下午
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    UserService userService;

    @GetMapping("test01")
    @ResponseBody
    public String test01(){
        System.out.println("test01");
        String username = "henry";
        User user = userService.getUserInfoByName(username);
        return user.toString();
    }
}
