package com.kittycoder.controller;

import com.kittycoder.bean.Employee;
import com.kittycoder.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by shucheng on 2020/2/13 21:04
 */
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // http://localhost:8080/emp/2
    @GetMapping("/emp/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id) {
        Employee emp = employeeService.getEmp(id);
        return emp;
    }

    // http://localhost:8080/emp?id=2&lastName=ccc
    @GetMapping("/emp")
    public Employee updateEmployee(Employee employee) {
        Employee emp = employeeService.updateEmp(employee);
        return emp;
    }

    // http://localhost:8080/delemp?id=2
    @GetMapping("/delemp")
    public String deleteEmployee(Integer id) {
        employeeService.deleteEmp(id);
        return "success";
    }

    // http://localhost:8080/emp/lastName/aaa
    @GetMapping("/emp/lastName/{lastName}")
    public Employee getEmpByLastName(@PathVariable("lastName") String lastName) {
        return employeeService.getEmpByLastName(lastName);
    }
}
