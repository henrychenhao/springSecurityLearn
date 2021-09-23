package com.henry.salary.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenhao
 */
@RestController
@RequestMapping("/salary")
public class SalaryController {

    @GetMapping("/query")
    public String query(){
        return "salary";
    }
}