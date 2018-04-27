package com.kittycoder.util;

import com.kittycoder.po.Student;

import java.util.List;

/**
 * Created by shucheng on 2018/4/25.
 */
public interface Dao {

    public List<Student> findAll();

    public Student findById(int id);

    public void insertStudent(Student student);

    public void updateStudent(Student student);

    public void deleteStudent(int id);
}
