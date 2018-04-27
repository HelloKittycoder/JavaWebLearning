package com.kittycoder.util;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.kittycoder.po.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by shucheng on 2018/4/25.
 */
@Repository
public class DaoImpl extends SqlMapClientDaoSupport implements Dao {

    @Autowired
    public void setSqlMapClientAutowire(SqlMapClient sqlMapClient) {
        super.setSqlMapClient(sqlMapClient);
    }

    @Override
    public List<Student> findAll() {
        return getSqlMapClientTemplate().queryForList("findAll");
    }

    @Override
    public Student findById(int id) {
        return (Student) getSqlMapClientTemplate().queryForObject("findById", id);
    }

    @Override
    public void insertStudent(Student student) {
        getSqlMapClientTemplate().insert("insertStudent", student);
    }

    @Override
    public void updateStudent(Student student) {
        getSqlMapClientTemplate().update("updateStudent", student);
    }

    @Override
    public void deleteStudent(int id) {
        getSqlMapClientTemplate().delete("deleteStudent", id);
    }
}
