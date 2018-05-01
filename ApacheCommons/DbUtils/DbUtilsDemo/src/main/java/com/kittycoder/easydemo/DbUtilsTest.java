package com.kittycoder.easydemo;

import com.kittycoder.po.Student;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shucheng on 2018/5/1.
 * 来源：https://blog.csdn.net/qq_34416191/article/details/51884266
 * https://blog.csdn.net/earbao/article/details/44901061
 * http://langgufu.iteye.com/blog/2167077
 */
public class DbUtilsTest {

    /**
     * 获取数据库连接
     * @return
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/guan?useUnicode=true&characterEncoding=UTF8",
                    "root", "123456");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    // 根据id查询
    @Test
    public void findById() {
        Connection conn  = getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "select * from student where id = ?";
        Object[] params = {1};
        try {
            Student student = qr.query(conn, sql, new BeanHandler<Student>(Student.class), params);
            System.out.println("查询成功：\n" + student);
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DbUtils.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 查询所有
    @Test
    public void findAll() {
        Connection conn = getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "select * from student";
        List<Student> studentList = new ArrayList<Student>();
        try {
            studentList = qr.query(conn, sql, new BeanListHandler<Student>(Student.class));
            for (Student student : studentList) {
                System.out.println(student);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DbUtils.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 新增方法
    @Test
    public void testInsert() {
        Connection conn = getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "insert into student(name, age, sex, regtime) values(?,?,?,?)";
        Object[] params = {"小红", "28", "女", "2018-04-25"};
        int i = 0;
        try {
            i = qr.update(conn, sql, params);
            System.out.println("添加成功：受影响的行数为" + i);
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DbUtils.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 修改方法
    @Test
    public void testUpdate() {
        Connection conn = getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "update student set name=?, age=?, sex=?, regtime=? where id=?";
        Object[] params = {"王五", 30, "男", "1997-01-01", 3};
        try {
            int i = qr.update(conn, sql,params);
            System.out.println("修改成功：受影响的行数为" + i);
        }
        catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DbUtils.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 删除方法
    @Test
    public void testDelete() {
        Connection conn = getConnection();
        QueryRunner qr = new QueryRunner();
        String sql = "delete from student where id=?";
        try {
            int i = qr.update(conn, sql, 3);
            System.out.println("删除成功：受影响的行数为" + i);
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                DbUtils.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
