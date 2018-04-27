package com.kittycoder.studentMgmt.service;

import com.kittycoder.studentMgmt.po.Student;

import java.util.List;

/**
 * Created by shucheng on 2018/4/26.
 */
public interface StudentService {

    /**
     * 查询所有学生
     * @param student
     * @return
     */
    List<Student> findAll(Student student);

    /**
     * 根据id查找学生
     * @param id
     * @return
     */
    Student findById(int id);

    /**
     * 新增学生
     * @param student
     */
    void insertStudent(Student student);

    /**
     * 修改学生
     * @param student
     */
    void updateStudent(Student student);

    /**
     * 删除学生
     * @param id
     */
    void deleteStudent(int id);
}
