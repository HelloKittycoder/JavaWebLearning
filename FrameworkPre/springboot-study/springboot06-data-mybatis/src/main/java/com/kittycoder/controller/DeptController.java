package com.kittycoder.controller;

import com.kittycoder.bean.Department;
import com.kittycoder.bean.Employee;
import com.kittycoder.mapper.DepartmentMapper;
import com.kittycoder.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by shucheng on 2020/2/11 17:28
 */
@RestController
public class DeptController {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @GetMapping("/dept/{id}")
    public Department getDepartment(@PathVariable("id") Integer id) {
        return departmentMapper.getDeptById(id);
    }

    @GetMapping("/dept")
    public Department insertDept(Department department) {
        departmentMapper.insertDept(department);
        return department;
    }

    @GetMapping("/emp/{id}")
    public Employee getEmp(@PathVariable("id") Integer id) {
        return employeeMapper.getEmpById(id);
    }

    // http://localhost:8080/emp?lastName=bbb
    @GetMapping("/emp")
    public Employee insertEmp(Employee employee) {
        employeeMapper.insertEmp(employee);
        return employee;
    }
}
