package com.chen.controller;

import com.chen.entity.Test;
import com.chen.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping("/findPart")
    public String findPart(Model model, HttpSession session){
        List<Test> tests= testService.findPart();
        //把得到的几个随机题目存到session中
        session.setAttribute("tests",tests);
        model.addAttribute("tests",tests);
        return "pages/Test";
    }

    @ResponseBody
    @PostMapping("/getResult")
    public List getResult(HttpSession session){
        //获取session中的题目
        List<Test> testList= (List<Test>) session.getAttribute("tests");
        return testList;

    }




}
