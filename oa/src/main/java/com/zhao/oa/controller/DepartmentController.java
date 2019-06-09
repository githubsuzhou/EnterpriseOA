package com.zhao.oa.controller;

import com.zhao.oa.entity.Department;
import com.zhao.oa.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhao
 * 2019/6/2 15:31
 * 部门管理控制器
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @RequestMapping("/list")
    public  String list(Map<String,Object> map){
        map.put("list",departmentService.getAll());
        return "department_list";

    }
    @RequestMapping("/to_add")
    public  String to_add(Map<String,Object> map){
        map.put("department",new Department());
        return "department_add";
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public  String add(Department department){
        departmentService.add(department);
        //直接访问页面是没有值的，重定向到list
        return "redirect:list";
    }
    //params = "sn"   必须传递一个参数过来
    @RequestMapping(value = "/to_update")
    public  String to_updata(Map<String,Object> map, @RequestParam("sn") String sn){
        map.put("department",departmentService.get(sn));
        return "department_update";
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public  String updata(Department department){
        departmentService.edit(department);
        //直接访问页面是没有值的，重定向到list
        return "redirect:list";
    }
    @RequestMapping(value = "/remove",params = "sn")
    public  String delete(String sn){
        departmentService.remove(sn);
        //直接访问页面是没有值的，重定向到list
        return "redirect:list";
    }
}
