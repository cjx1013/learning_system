package com.chen.controller;

import com.chen.entity.User;
import com.chen.service.UserService;
import com.chen.utils.ValidateImageCodeUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.Console;
import java.io.IOException;
import java.util.List;

//@Autowired 注释，它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作。 通过 @Autowired的使用来消除 set ，get方法。
// 在使用@Autowired之前，我们对一个bean配置起属性时，是这用用的
//<property name="属性名" value=" 属性值"/>
//        通过这种方式来，配置比较繁琐，而且代码比较多。在Spring 2.5 引入了 @Autowired 注释



@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //登录方法
    @PostMapping("/login")
    public String login(String username,String password,HttpSession session){
        User login = userService.login(username,password);

        if(login!=null){
            session.setAttribute("user",login);
            if(login.getRole().equalsIgnoreCase("用户")){
                return "redirect:/home";//账号密码正确,跳转到用户页面
            }else {
                return "redirect:/user/findAll";//账号密码正确,跳转到管理员页面
            }
        }else{
            session.setAttribute("msgg","账号或密码错误!");
            return "redirect:/index";//跳转回登录，账号密码错误
        }
    }
    //注册方法
    @PostMapping("/register")
    public String register(User user,String code,HttpSession session,Model model){
        String sessionCode=(String)session.getAttribute("code");
//        System.out.println(sessionCode);
//        System.out.println(code);
//        System.out.println(user);
        if(sessionCode.equalsIgnoreCase(code)){
            userService.register(user);
//        System.out.println(131546464);
//        System.out.println(sessionCode);
//        System.out.println(code);
            return "redirect:/index";//跳转到主页面
        }else{
            return "redirect:/register";
        }
    }
    //生成验证码
    @GetMapping("code")
    public void getImage(HttpSession session, HttpServletResponse response)throws IOException {
        //生成验证码
        String securityCode= ValidateImageCodeUtils.getSecurityCode();
        BufferedImage image=ValidateImageCodeUtils.createImage(securityCode);
        session.setAttribute("code",securityCode);//存在session域中
        //响应图片
        ServletOutputStream os = response.getOutputStream();
        ImageIO.write(image,"png",os);
    }

    //查询所有
    @GetMapping("/findAll")
    public String findAll(@RequestParam(value="pn",defaultValue = "1")Integer pn, Model model){
        //引入pageHelper插件
        PageHelper.startPage(pn,10);
        List<User> users= userService.findAll();
        PageInfo page=new PageInfo(users,3);
        model.addAttribute("pageInfo",page);
        return "pages/Admin";
    }

    //管理员添加用户
    @PostMapping("/add")
    public String add(User user){
        userService.register(user);
        return "redirect:/user/findAll";
    }

    //删除用户
    @GetMapping("/delete")
    public String delete(String id){
        userService.delete(id);
        return "redirect:/user/findAll";
    }

    //按id查询用户
    @GetMapping("/find")
    public String find(String id,Model model){
        User user = userService.find(id);
        model.addAttribute("user",user);
        return "pages/updateUser";
    }

    //更新用户
    @PostMapping("/update")
    public String update(User user){
        userService.update(user);
        return "redirect:/user/findAll";
    }
}
