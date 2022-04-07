package com.chen.controller;

import com.chen.entity.Question;
import com.chen.service.QuestionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @ResponseBody
    @PostMapping("/getAnswer")
    public List getAnswer(String qu) throws JsonProcessingException {
        List<Question> ques=questionService.find(qu);
//        System.out.println("hahha");
//        System.out.println(ques);
        List<String> msg=new ArrayList<>();
        msg.add(0,"no");;
        if(!ques.isEmpty()){
//            System.out.println("lalalall");
           return ques;
        }else{
//            System.out.println("yoyoyoyo");
           return msg;
        }

    }

    //查询所有
    @GetMapping("/findAll")
    public String findAll(@RequestParam(value="pn",defaultValue = "1")Integer pn, Model model){
        //引入pageHelper插件
        PageHelper.startPage(pn,4);
        List<Question> ques= questionService.findAll();
        PageInfo page=new PageInfo(ques,3);
        model.addAttribute("pageInfo",page);
        return "pages/QuestionPage";
    }

    //删除问题
    @GetMapping("/delete")
    public String delete(String id){
        questionService.delete(id);
        return "redirect:/question/findAll";
    }

    //按id查找
    @GetMapping("/search")
    public String find(String id,Model model){
        Question question = questionService.search(id);
        model.addAttribute("ques",question);
        return "pages/updateQuestion";
    }

    //更新问题
    @PostMapping("/update")
    public String update(Question question){
        questionService.update(question);
        return "redirect:/question/findAll";
    }

    //添加问题
    @PostMapping("/add")
    public String add(Question question){
        questionService.insert(question);
        return "redirect:/question/findAll";
    }
}
