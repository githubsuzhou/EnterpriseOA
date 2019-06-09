package com.zhao.oa.service;

import com.zhao.oa.entity.Department;

import java.util.List;

/**
 * Created by zhao
 * 2019/6/2 15:09
 */
public interface DepartmentService {
    void add(Department department);
    void edit(Department department);
    void remove(String sn);
    Department  get(String sn);
    List<Department> getAll();

}
