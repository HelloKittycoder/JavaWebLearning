package com.kittycoder;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by shucheng on 2020/2/10 12:53
 */
public class JdbcTemplateTest extends SpringBoot06DataJdbcApplicationTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void testQuery() throws Exception {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from department");
        System.out.println(list);
    }
}
