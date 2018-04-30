package com.kittycoder.easydemo;

import com.kittycoder.util.DbUtil;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * Created by shucheng on 2018/4/30.
 * JDBC操作数据库（使用封装过的DbUtil）
 */
public class JDBCDemo01 {

    public static Connection getConnection() {
        Connection conn = DbUtil.getConnection("com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/guan?useUnicode=true&characterEncoding=UTF8",
                "root", "123456");
        return conn;
    }

    /**
     * 查询所有学生
     */
    public static void queryAll() {
        Connection conn = getConnection();
        try {
            String sql = "select * from student";
            ResultSet rs = DbUtil.query(sql);
            // 获取并操作结果集
            System.out.print("学号\t姓名\t\t年龄\t性别\t注册日期\n");
            while (rs.next()) {
                System.out.print(rs.getInt("id")+"\t\t");
                System.out.print(rs.getString("name")+"\t\t");
                System.out.print(rs.getInt("age")+"\t\t");
                System.out.print(rs.getString("sex")+"\t\t");
                System.out.print(rs.getDate("regtime")+"\n");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.close();
        }
    }

    /**
     * 新增学生
     */
    public static void insert() {
        Connection conn = getConnection();
        try {
            String sql = "insert into student(name, age, sex, regtime) values('小红', '28', '女', '2018-04-25')";
            int count = DbUtil.update(sql);
            System.out.println("向student表中插入了" + count + "条数据");
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.close();
        }
    }

    /**
     * 修改学生
     */
    public static void update() {
        Connection conn = getConnection();
        try {
            String sql = "update student set name='王五', age='30', sex='男', regtime='1997-01-01' where id=3";
            int count = DbUtil.update(sql);
            System.out.println("student表中更新了" + count + "条数据");
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.close();
        }
    }

    /**
     * 删除学生
     */
    public static void delete() {
        Connection conn = getConnection();
        try {
            String sql = "delete from student where id=3";
            int count = DbUtil.update(sql);
            System.out.println("student表中删除了" + count + "条数据");
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            DbUtil.close();
        }
    }
}
