package com.junhao.yiqi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JumpController {

    //关于我
    @RequestMapping("/aboutMe")
    public String ddd(){

        return "oboutMe";
    }



}
