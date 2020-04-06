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

    //去上传附图界面
    @RequestMapping("/toaddFuImg")
    public String toaddFuImg(){

        return "product/uploadFuImg";
    }

    // 联系我们
    @RequestMapping("/toConnectUs")
    public String toconnectus(){

        return "oboutMe";
    }



}
