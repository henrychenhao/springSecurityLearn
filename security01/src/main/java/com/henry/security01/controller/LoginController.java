package com.henry.security01.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hao.chen
 * @date 2021/9/14 5:25 下午
 */
@Controller
public class LoginController {
    @RequestMapping("/main")
    public String main(){
        return "redirect:/main.html";
    }
    @RequestMapping("/toerror")
    public String error(){
        return "redirect:/error.html";
    }
}
