package com.kittycoder;

import com.ibatis.common.jdbc.ScriptRunner;
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import javax.sql.DataSource;
import java.io.Reader;
import java.sql.Connection;
import java.util.Properties;

/**
 * Created by shucheng on 2020/8/9 11:44
 */
public class BaseSqlMapTest {

    protected static SqlMapClient sqlMap;

    protected static void initSqlMap(String configFile, Properties props) throws Exception {
        Reader reader = Resources.getResourceAsReader(configFile);
        sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader, props);
        reader.close();
    }

    protected static void initScript(String script) throws Exception {
        DataSource ds = sqlMap.getDataSource();
        Connection conn = ds.getConnection();
        Reader reader = Resources.getResourceAsReader(script);

        ScriptRunner runner = new ScriptRunner(conn, false, false);
        runner.setLogWriter(null);
        runner.setErrorLogWriter(null);

        runner.runScript(reader);
        conn.commit();
        reader.close();
    }
}
