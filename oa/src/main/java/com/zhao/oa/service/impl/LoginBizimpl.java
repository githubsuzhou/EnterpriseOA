package com.zhao.oa.service.impl;

import com.zhao.oa.dao.EmployeeDao;
import com.zhao.oa.entity.Employee;
import com.zhao.oa.service.LoginBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhao
 * 2019/6/2 21:02
 * 用户登陆修改密码
 */
@Service("LoginBiz")
public class LoginBizimpl implements LoginBiz {
    @Autowired
    private EmployeeDao employeeDao;
    public LoginBizimpl() {
        super();
    }
    //登陆
    public Employee login(String sn, String password) {
        Employee  employee = employeeDao.select(sn);
        if (employee != null&&employee.getPassword().equals(password)) {
            return employee;
        }else {
            return null;
        }

    }

    public void changPaaaword(Employee employee) {
        employeeDao.update(employee);
    }
}
