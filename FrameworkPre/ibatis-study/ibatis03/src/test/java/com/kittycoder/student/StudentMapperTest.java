package com.kittycoder.student;

import com.kittycoder.BaseSqlMapTest;
import com.kittycoder.student.pojo.Student;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by shucheng on 2020/8/9 11:52
 */
public class StudentMapperTest extends BaseSqlMapTest {

    @Before
    public void setUp() throws Exception {
        initSqlMap("SqlMapConfig.xml", null);
        initScript("sql/createAndData.sql");
    }

    @Test
    public void testQueryAll() throws SQLException {
        List<Student> list = sqlMap.queryForList("student.queryAll");
        assertEquals(3, list.size());
    }

    @Test
    public void testInsertStudent() throws SQLException {
        Student student = new Student(0, "赵六", 40);
        sqlMap.insert("student.insertStudent", student); // 这里返回值就是插入时生成的主键

        assertEquals(4, student.getId());
    }

    @Test
    public void testUpdateStudent() throws SQLException {
        Student student = new Student(3, "王五2", 32);
        sqlMap.update("student.updateStudent", student);
    }

    @Test
    public void testDeleteStudent() throws SQLException {
        sqlMap.delete("student.deleteStudent", 3);
        List<Student> list = sqlMap.queryForList("student.queryAll");
        assertEquals(2, list.size());
    }

    // 利用ibatis自带的executeBatchDetailed实现批量插入，这个批量插入不能返回主键
    @Test
    public void testBatchInsertStudent() throws Exception {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(0, "赵六", 40));
        studentList.add(new Student(0, "田七", 50));
        studentList.add(new Student(0, "郑八", 60));

        sqlMap.startTransaction();
        sqlMap.startBatch();

        for (int i = 0; i < studentList.size(); i++) {
            sqlMap.insert("student.insertStudent", studentList.get(i));
        }

        List result = sqlMap.executeBatchDetailed(); // 批量操作，但是没有返回主键
        sqlMap.commitTransaction();
        System.out.println(result);
    }

    // 利用数据库insert本身的语法支持，这个批量插入不能返回主键
    @Test
    public void testBatchInsert2Student() throws Exception {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(0, "赵六", 40));
        studentList.add(new Student(0, "田七", 50));
        studentList.add(new Student(0, "郑八", 60));

        sqlMap.insert("student.insertStudent2", studentList);

        List<Student> list = sqlMap.queryForList("student.queryAll"); // 批量操作，但是没有返回主键
        assertEquals(6, list.size());
    }

    // 这个批量插入可以返回主键，但效率较低
    @Test
    public void testBatchInsert3Student() throws Exception {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(0, "赵六", 40));
        studentList.add(new Student(0, "田七", 50));
        studentList.add(new Student(0, "郑八", 60));

        // 插入后会返回主键，但效率比较低
        for (int i = 0; i < studentList.size(); i++) {
            sqlMap.insert("student.insertStudent", studentList.get(i));
        }

        List<Student> list = sqlMap.queryForList("student.queryAll"); // 批量操作，但是没有返回主键
        assertEquals(6, list.size());
    }
}
