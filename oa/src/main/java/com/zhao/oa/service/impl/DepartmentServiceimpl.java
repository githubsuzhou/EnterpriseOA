package com.zhao.oa.service.impl;

import com.zhao.oa.dao.DepartmentDao;
import com.zhao.oa.entity.Department;
import com.zhao.oa.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resources;
import java.util.List;

/**
 * Created by zhao
 * 2019/6/2 15:09
 */
@Service
public class DepartmentServiceimpl  implements DepartmentService {
    @Autowired
    DepartmentDao  departmentDao;
    public DepartmentServiceimpl() {
        super();
    }

    public void add(Department department) {
        departmentDao.insert(department);
    }

    public void edit(Department department) {
        departmentDao.update(department);
    }

    public void remove(String sn) {
  departmentDao.delete(sn);
    }

    public Department get(String sn) {
        return departmentDao.select(sn);
    }

    public List<Department> getAll() {
        return departmentDao.selectAll();
    }
}
