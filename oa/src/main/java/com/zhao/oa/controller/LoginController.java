package com.zhao.oa.controller;

import com.zhao.oa.entity.Employee;
import com.zhao.oa.service.LoginBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Created by zhao Ctrl + Shift + Enter 自动补充分号
 * 2019/6/2 21:11
 */
@Controller("loginController")
public class LoginController {
    @Autowired
    LoginBiz loginBiz;
    @RequestMapping("/to_login")
    public String toLogin(){
        return "login";

    }
    @RequestMapping("/login")
    private  String Login(HttpSession session, @RequestParam String sn, @RequestParam String password){
        Employee employee = loginBiz.login(sn, password);
        if(employee==null){
            return  "redirect:to_login";
        }else {
            session.setAttribute("employee",employee);
            return  "redirect:self";
        }

    }
    //登陆成功查看个人信息
    @RequestMapping("/self")
    public  String self(){
        return  "self";
    }
    //退出
    @RequestMapping("/quit")
    private  String quit(HttpSession session){
        session.setAttribute("employee",null);
        return  "redirect:to_login";

    }
    @RequestMapping("/to_change_password")
    public String toChangePassword(){
        return "change_password";

    }
    @RequestMapping("/change_password")
    private  String ChangePassword(HttpSession session, @RequestParam String old, @RequestParam String new1,@RequestParam String new2) {
        Employee employee = (Employee) session.getAttribute("employee");

        if (employee.getPassword().equals(old)) {
            if (new1.equals(new2)) {
                employee.setPassword(new1);
                loginBiz.changPaaaword(employee);
                return "redirect:self";
            }
            return "redirect:to_change_password";
        } else {
            return "redirect:to_change_password";
        }
    }
}
