package com.kittycoder.mapper;

import com.kittycoder.bean.Employee;

/**
 * Created by shucheng on 2020/2/11 22:21
 * @Mapper或者@MapperScan将接口扫描装配到容器中
 */
public interface EmployeeMapper {

    Employee getEmpById(Integer id);

    void insertEmp(Employee employee);
}
