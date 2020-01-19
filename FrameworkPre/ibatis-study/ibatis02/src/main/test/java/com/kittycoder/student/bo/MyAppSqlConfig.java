package com.kittycoder.student.bo;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by shucheng on 2020/1/19 17:13
 */
public class MyAppSqlConfig {

    public static final SqlMapClient sqlMap;
    static {
        try {
            String resource = "SqlMapConfig.xml";
            Reader reader = Resources.getResourceAsReader(resource);
            sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error" + e);
        }
    }

    public static SqlMapClient getSqlMapInstance() {
        return sqlMap;
    }

    public static void main(String[] args) throws SQLException {
        // System.out.println(getSqlMapInstance());
        SqlMapClient sqlMap = getSqlMapInstance();
        List list = sqlMap.queryForList("student.queryAll");
        System.out.println(list);

        /*Student s = new Student();
        s.setName("李四");
        s.setAge(20);
        sqlMap.insert("student.insertStudent", s);*/

        /*Student s = new Student(2, "王五", 30);
        sqlMap.update("student.updateStudent", s);*/

        // sqlMap.delete("student.deleteStudent",2);
    }
}
