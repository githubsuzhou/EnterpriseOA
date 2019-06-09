package com.zhao.oa.service;

import com.zhao.oa.entity.Department;
import com.zhao.oa.entity.Employee;

import java.util.List;

/**
 * Created by zhao
 * 2019/6/2 15:09
 */
public interface EmployeeService {
    void add(Employee employee);
    void edit(Employee employee);
    void remove(String sn);
    Employee  get(String sn);
    List<Employee> getAll();

}
