package com.zhao.oa.service;

import com.zhao.oa.entity.Employee;

/**
 * Created by zhao
 * 2019/6/2 20:59
 * 用户登陆
 */
public interface LoginBiz {
    Employee login(String sn,String password);
    void changPaaaword(Employee employee);
}
