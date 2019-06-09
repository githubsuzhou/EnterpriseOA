package com.zhao.oa.service.impl;

import com.zhao.oa.dao.DepartmentDao;
import com.zhao.oa.dao.EmployeeDao;
import com.zhao.oa.entity.Department;
import com.zhao.oa.entity.Employee;
import com.zhao.oa.service.DepartmentService;
import com.zhao.oa.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhao
 * 2019/6/2 15:09
 */
@Service("employeeService")
public class EmployeeServiceimpl implements EmployeeService {
    @Autowired
    private  EmployeeDao employeeDao;


    public void add(Employee employee) {
        //添加员工时设置默认密码
        employee.setPassword("0000");
        employeeDao.insert(employee);
    }

    public void edit(Employee employee) {
        employeeDao.update(employee);
    }

    public void remove(String sn) {
        employeeDao.delete(sn);
    }

    public Employee get(String sn) {
        return employeeDao.select(sn);
    }

    public List<Employee> getAll() {
        return employeeDao.selectAll();
    }
}
