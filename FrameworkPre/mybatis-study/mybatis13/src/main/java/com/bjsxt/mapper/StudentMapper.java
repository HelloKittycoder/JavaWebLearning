package com.bjsxt.mapper;

import com.bjsxt.pojo.Student;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Create by Administrator on 2019/3/29
 */
public interface StudentMapper {

    // 使用AutoMapping结合别名实现关联单个对象（说明：无法做到关联集合）
    @Select("select s.id,s.name,age,s.tid,t.id `teacher.id`,t.name `teacher.name` from student s left join teacher t on s.tid = t.id")
    List<Student> selectAll();

    @Select("select * from student where tid=#{0}")
    List<Student> selectByTid(int tid);
}
