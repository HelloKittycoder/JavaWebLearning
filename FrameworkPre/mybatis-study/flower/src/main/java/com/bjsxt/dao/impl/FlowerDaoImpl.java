package com.bjsxt.dao.impl;

import com.bjsxt.dao.FlowerDao;
import com.bjsxt.pojo.Flower;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by Administrator on 2019/3/16
 */
public class FlowerDaoImpl implements FlowerDao {
    @Override
    public List<Flower> selectAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Flower> flowerList = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8",
                    "root", "123456");
            ps = conn.prepareStatement("select * from flower");
            rs = ps.executeQuery();
            while (rs.next()) {
                flowerList.add(new Flower(rs.getInt(1), rs.getString(2),
                        rs.getDouble(3), rs.getString(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return flowerList;
    }

    @Override
    public int insertFlower(Flower flower) {
        Connection conn = null;
        PreparedStatement ps = null;
        int index = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8",
                    "root", "123456");
            ps = conn.prepareStatement("insert into flower values(default, ?, ?, ?)");
            ps.setObject(1, flower.getName());
            ps.setObject(2, flower.getPrice());
            ps.setObject(3, flower.getProduction());
            index = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return index;
    }
}
