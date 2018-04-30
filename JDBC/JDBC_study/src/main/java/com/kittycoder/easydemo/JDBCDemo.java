package com.kittycoder.easydemo;

import java.sql.*;

/**
 * Created by shucheng on 2018/4/30.
 * JDBC操作数据库的基本步骤
 */
public class JDBCDemo {
    /**
     * 查询所有学生
     */
    public static void queryAll() {
        // 数据库驱动类的完整类名
        String driver = "com.mysql.jdbc.Driver";
        // 数据库连接地址
        String url = "jdbc:mysql://localhost:3306/guan?useUnicode=true&characterEncoding=UTF8";
        // 用户名
        String username = "root";
        // 密码
        String password = "123456";

        // 数据库连接对象
        Connection conn = null;
        // 数据库语句对象
        Statement stmt = null;
        // 数据库结果集对象
        ResultSet rs = null;
        try {
            // 1.加载数据库驱动（成功加载后，会将Driver类的实例注册到DriverManager类中）
            Class.forName(driver);
            // 2.获取数据库连接
            conn = DriverManager.getConnection(url, username, password);
            // 3.获取数据库语句对象
            stmt = conn.createStatement();
            // 4.定义操作的SQL语句
            String sql = "select * from student";
            // 5.执行数据库查询操作
            rs = stmt.executeQuery(sql);
            // 6.获取并操作结果集
            System.out.print("学号\t姓名\t\t年龄\t性别\t注册日期\n");
            while (rs.next()) {
                System.out.print(rs.getInt("id")+"\t\t");
                System.out.print(rs.getString("name")+"\t\t");
                System.out.print(rs.getInt("age")+"\t\t");
                System.out.print(rs.getString("sex")+"\t\t");
                System.out.print(rs.getDate("regtime")+"\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 7.关闭对象，回收数据库资源
            if (rs != null) { // 关闭结果集对象
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) { // 关闭数据库语句对象
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) { // 关闭数据库连接对象
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 新增学生
     */
    public static void insert() {
        // 数据库驱动类的完整类名
        String driver = "com.mysql.jdbc.Driver";
        // 数据库连接地址
        String url = "jdbc:mysql://localhost:3306/guan";
        // 用户名
        String username = "root";
        // 密码
        String password = "123456";

        // 数据库连接对象
        Connection conn = null;
        // 数据库语句对象
        Statement stmt = null;
        try {
            // 1.加载数据库驱动（成功加载后，会将Driver类的实例注册到DriverManager类中）
            Class.forName(driver);
            // 2.获取数据库连接
            conn = DriverManager.getConnection(url, username, password);
            // 3.获取数据库语句对象
            stmt = conn.createStatement();
            // 4.定义操作的SQL语句
            String sql = "insert into student(name, age, sex, regtime) values('小红', '28', '女', '2018-04-25')";
            // 5.执行数据库新增操作
            int count = stmt.executeUpdate(sql);
            System.out.println("向student表中插入了" + count + "条数据");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 7.关闭对象，回收数据库资源
            if (stmt != null) { // 关闭数据库语句对象
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) { // 关闭数据库连接对象
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 修改学生
     */
    public static void update() {
        // 数据库驱动类的完整类名
        String driver = "com.mysql.jdbc.Driver";
        // 数据库连接地址
        String url = "jdbc:mysql://localhost:3306/guan";
        // 用户名
        String username = "root";
        // 密码
        String password = "123456";

        // 数据库连接对象
        Connection conn = null;
        // 数据库语句对象
        Statement stmt = null;
        try {
            // 1.加载数据库驱动（成功加载后，会将Driver类的实例注册到DriverManager类中）
            Class.forName(driver);
            // 2.获取数据库连接
            conn = DriverManager.getConnection(url, username, password);
            // 3.获取数据库语句对象
            stmt = conn.createStatement();
            // 4.定义操作的SQL语句
            String sql = "update student set name='王五', age='30', sex='男', regtime='1997-01-01' where id=3";
            // 5.执行数据库更新操作
            int count = stmt.executeUpdate(sql);
            System.out.println("student表中更新了" + count + "条数据");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 7.关闭对象，回收数据库资源
            if (stmt != null) { // 关闭数据库语句对象
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) { // 关闭数据库连接对象
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 删除学生
     */
    public static void delete() {
        // 数据库驱动类的完整类名
        String driver = "com.mysql.jdbc.Driver";
        // 数据库连接地址
        String url = "jdbc:mysql://localhost:3306/guan";
        // 用户名
        String username = "root";
        // 密码
        String password = "123456";

        // 数据库连接对象
        Connection conn = null;
        // 数据库语句对象
        Statement stmt = null;
        try {
            // 1.加载数据库驱动（成功加载后，会将Driver类的实例注册到DriverManager类中）
            Class.forName(driver);
            // 2.获取数据库连接
            conn = DriverManager.getConnection(url, username, password);
            // 3.获取数据库语句对象
            stmt = conn.createStatement();
            // 4.定义操作的SQL语句
            String sql = "delete from student where id=3";
            // 5.执行数据库删除操作
            int count = stmt.executeUpdate(sql);
            System.out.println("student表中删除了" + count + "条数据");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 7.关闭对象，回收数据库资源
            if (stmt != null) { // 关闭数据库语句对象
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) { // 关闭数据库连接对象
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
