package com.kittycoder.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;

/**
 * Created by shucheng on 2018/4/30.
 */
public class DbUtil {

    private static Connection conn = null; // 数据库连接对象
    private static Statement stmt = null; // 数据库语句对象
    private static ResultSet rs = null; // 数据库结果集对象

    /**
     * 获取数据库连接
     * @param driver
     * @param url
     * @param username
     * @param password
     * @return
     */
    public static Connection getConnection(String driver, String url, String username, String password) {
        try {
            // 加载数据库驱动
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 执行查询语句，并返回结果集
     * @param sql
     * @return
     */
    public static ResultSet query(String sql) {
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * 执行更新（新增、修改、删除）操作，返回更新的行数
     * @param sql
     * @return
     */
    public static int update(String sql) {
        int count = 0; // 受影响的行数
        try {
            stmt = conn.createStatement();
            count = stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 关闭对象，回收数据库资源
     */
    public static void close() {
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

    /**
     * 从文件中读取sql，并执行批量更新（新增，修改，删除）操作
     * 用途：小数据量时，单元测试状态还原，数据库初始化
     * @param fileName
     *
     * 参见util/FileUtil文件
     */
    public static void updateBatchByFile(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                // System.out.println(tempString);
                // 如果不是查询语句，则执行更新操作
                if(tempString.toLowerCase().indexOf("select") == -1) { // 让indexOf方法不区分大小写
                    update(tempString);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        DbUtil.getConnection("com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/guan?useUnicode=true&characterEncoding=UTF8",
                "root", "123456");
        updateBatchByFile("src/main/resources/sql/init.sql");
        close();
    }
}
