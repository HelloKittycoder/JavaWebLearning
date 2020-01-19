package com.kittycoder.student.bo;

import com.kittycoder.common.bo.CommonDao;
import com.kittycoder.common.bo.CommonDaoImpl;
import com.kittycoder.student.pojo.Student;

import java.util.List;

/**
 * Created by shucheng on 2020/1/19 21:57
 */
public class StudentBo {

    private CommonDao commonDao = new CommonDaoImpl();

    /**
     * 查询所有学生
     * @return
     */
    public List<Student> queryAllStudent() {
        return commonDao.queryForList("student.queryAll", null);
    }

    /**
     * 新增学生
     * @param student
     * @return
     */
    public Integer insertStudent(Student student) {
        return commonDao.insert("student.insertStudent", student);
    }

    /**
     * 修改学生
     * @param student
     * @return
     */
    public int updateStudent(Student student) {
        return commonDao.update("student.updateStudent", student);
    }

    /**
     * 删除学生
     * @return
     */
    public int deleteStudentById(Integer id) {
        return commonDao.delete("student.deleteStudent", id);
    }
}
