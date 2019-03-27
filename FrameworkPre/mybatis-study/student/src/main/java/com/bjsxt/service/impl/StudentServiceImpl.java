package com.bjsxt.service.impl;

import com.bjsxt.mapper.StudentMapper;
import com.bjsxt.mapper.TeacherMapper;
import com.bjsxt.pojo.PageInfo;
import com.bjsxt.pojo.Student;
import com.bjsxt.service.StudentService;
import com.bjsxt.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Create by Administrator on 2019/3/26
 */
public class StudentServiceImpl implements StudentService {
    @Override
    public PageInfo showPage(String sname, String tname, String pageSizeStr, String pageNumberStr) {
        int pageSize = 2;
        if (pageSizeStr != null && !pageSizeStr.equals("")) {
            pageSize = Integer.parseInt(pageSizeStr);
        }
        int pageNumber = 1;
        if (pageNumberStr != null && !pageNumberStr.equals("")) {
            pageNumber = Integer.parseInt(pageNumberStr);
        }
        SqlSession session = MyBatisUtil.getSession();
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);

        // 设置前端传来的参数
        PageInfo pi = new PageInfo();
        pi.setPageNumber(pageNumber);
        pi.setPageSize(pageSize);
        pi.setPageStart((pageNumber-1)*pageSize);
        pi.setTname(tname);
        pi.setSname(sname);

        List<Student> list = studentMapper.selectPageList(pi);
        // 查询出每个学生对应的老师信息
        TeacherMapper teacherMapper = session.getMapper(TeacherMapper.class);
        for (Student student : list) {
            student.setTeacher(teacherMapper.selectInfoById(student.getTid()));
        }
        pi.setList(list);

        long count = studentMapper.selectPageCount(pi);
        pi.setTotal(count%pageSize==0 ? count/pageSize : count/pageSize+1);

        return pi;
    }
}
