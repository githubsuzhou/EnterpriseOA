package com.zhao.oa.controller;

import com.zhao.oa.entity.Department;
import com.zhao.oa.entity.Employee;
import com.zhao.oa.global.Contant;
import com.zhao.oa.service.DepartmentService;
import com.zhao.oa.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by zhao
 * 2019/6/2 15:31
 * 员工管理控制器
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/list")
    public  String list(Map<String,Object> map){
        map.put("list",employeeService.getAll());
        return "employee_list";

    }
    @RequestMapping("/to_add")
    public  String to_add(Map<String,Object> map){
        map.put("employee",new Employee());
        //传递部门
        map.put("dlist",departmentService.getAll());
        //传递职位
        map.put("plist",Contant.getPost());
        return "employee_add";
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public  String add(Employee employee){
        employeeService.add(employee);
        //直接访问页面是没有值的，重定向到list
        return "redirect:list";
    }
    //params = "sn"   必须传递一个参数过来
    @RequestMapping(value = "/to_update")
    public  String to_updata(Map<String,Object> map, @RequestParam("sn") String sn){
        map.put("employee",employeeService.get(sn));
        //传递部门
        map.put("dlist",departmentService.getAll());
        //传递职位
        map.put("plist",Contant.getPost());
        return "employee_update";
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public  String updata(Employee employee){

        employeeService.edit(employee);
        //直接访问页面是没有值的，重定向到list
        return "redirect:list";
    }
    @RequestMapping(value = "/remove",params = "sn")
    public  String delete(String sn){
        employeeService.remove(sn);
        //直接访问页面是没有值的，重定向到list
        return "redirect:list";
    }
}
