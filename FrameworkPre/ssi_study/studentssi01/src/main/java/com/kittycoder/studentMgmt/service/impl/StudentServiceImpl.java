package com.kittycoder.studentMgmt.service.impl;

import com.kittycoder.common.dao.CommonDao;
import com.kittycoder.studentMgmt.po.Student;
import com.kittycoder.studentMgmt.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by shucheng on 2018/4/26.
 */
public class StudentServiceImpl implements StudentService {
    @Autowired
    private CommonDao commonDao;

    @Override
    public List<Student> findAll(Student student) {
        return commonDao.getAttributeList(student, "student.findAll");
    }

    @Override
    public Student findById(int id) {
        return (Student) commonDao.getAttributeByPoJo(id, "student.findById");
    }

    @Override
    public void insertStudent(Student student) {
        // commonDao.updateAttributeByPoJo(student, "student.insertStudent");
        // 也可以使用下面这个
        commonDao.insertObject(student, "student.insertStudent");
    }

    @Override
    public void updateStudent(Student student) {
        commonDao.updateAttributeByPoJo(student, "student.updateStudent");
    }

    @Override
    public void deleteStudent(int id) {
        // commonDao.updateAttributeByPoJo(id, "student.deleteStudent");
        // 也可以使用下面这个
        commonDao.deleteObject(id, "student.deleteStudent");
    }
}
