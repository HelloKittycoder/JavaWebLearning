package com.kittycoder.mapper;

import com.kittycoder.bean.Department;
import org.apache.ibatis.annotations.*;

/**
 * Created by shucheng on 2020/2/11 17:23
 */
// @Mapper
public interface DepartmentMapper {

    @Select("select * from department where id=#{id}")
    Department getDeptById(Integer id);

    @Delete("delete from department where id=#{id}")
    int deleteDeptById(Integer id);

    // 将department的departmentName列的名称改成department_name
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into department(department_name) values(#{departmentName})")
    int insertDept(Department department);

    @Update("update department set department_name=#{departmentName} where id=#{id}")
    int updateDept(Department department);
}
