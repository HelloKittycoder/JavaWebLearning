package com.kittycoder.session;

import com.kittycoder.mapping.MapStatement;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.net.URL;
import java.util.*;

/**
 * Created by shucheng on 2019-9-21 上午 10:06
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration = new Configuration();
    public DefaultSqlSessionFactory() {
        loadJdbcConfig();
        loadMapStatement();
    }

    // 加载jdbc连接信息
    private void loadJdbcConfig() {
        Properties properties = new Properties();
        // InputStream inputStream = getClass().getResourceAsStream("/jdbc.properties");
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        configuration.setJdbcUrl(properties.get("jdbcUrl").toString());
        configuration.setDriverClass(properties.get("driverClass").toString());
        configuration.setUsername(properties.get("username").toString());
        configuration.setPassword(properties.get("password").toString());
    }

    // 读取xml中的sql
    private void loadMapStatement() {
        Map<String, MapStatement> mapStatements = configuration.getMapStatements();
        List<File> fileList = loadFilesByPkgname("com/kittycoder/mapper");
        // dom4j读取xml文件
        // https://blog.csdn.net/xyphf/article/details/78525244
        SAXReader reader = new SAXReader();
        for (File f : fileList) {
            try {
                Document doc = reader.read(f);
                Element root = doc.getRootElement();
                String namespace = root.attribute("namespace").getValue();

                Iterator<Element> it = root.elementIterator();
                while (it.hasNext()) {
                    Element e = it.next(); // 获取子元素
                    String sqlId = e.attribute("id").getValue();
                    String resultType = e.attribute("resultType").getValue();
                    String sql = e.getData().toString().trim();

                    MapStatement mapStatement = new MapStatement();
                    mapStatement.setNamespace(namespace);
                    mapStatement.setSqlId(sqlId);
                    mapStatement.setResultType(resultType);
                    mapStatement.setSqlStatement(sql);
                    mapStatements.put(namespace + "." + sqlId, mapStatement);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private List<File> loadFilesByPkgname(String packageName) {
        List<File> fileList = new ArrayList<>();
        try {
            // Enumeration<URL> resources = ClassLoader.getSystemClassLoader().getResources("com/kittycoder/mapper");
            Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(packageName);
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                File[] files = new File(url.getPath()).listFiles();
                for (File file : files) {
                    if (file.isFile() && file.getName().endsWith(".xml")) {
                        fileList.add(file);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileList;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
