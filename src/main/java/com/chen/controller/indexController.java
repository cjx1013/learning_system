package com.chen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {
    @GetMapping("/index")
    public String toIndex(){
        return "pages/Home";
    }

    @GetMapping("/home")
    public String toHome(){
        return "pages/Home2";
    }
    @GetMapping("/login")
    public String toLogin(){
        return "pages/Login";
    }

    @GetMapping("/register")
    public String toRegister(){
        return "/pages/Regist";
    }

    @GetMapping("/admin")
    public String toAdmin(){
        return "pages/Admin";
    }

    @GetMapping("/toSave")
    public  String toSave(){return "pages/addUser";}

    @GetMapping("/toSaveQue")
    public  String toSaveQue(){return "pages/addQuestion";}
}
