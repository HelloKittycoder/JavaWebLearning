package com.bjsxt.mapper;

import com.bjsxt.pojo.Teacher;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Create by Administrator on 2019/3/29
 */
public interface TeacherMapper {

    // 查询
    @Select("select * from teacher")
    List<Teacher> selectAll();

    // 新增
    @Insert("insert into teacher values(default,#{name})")
    int insertTeacher(Teacher teacher);

    // 修改
    @Update("update teacher set name=#{name} where id=#{id}")
    int updateTeacher(Teacher teacher);

    // 删除
    @Delete("delete from teacher where id=#{0}")
    int deleteById(int id);
}

