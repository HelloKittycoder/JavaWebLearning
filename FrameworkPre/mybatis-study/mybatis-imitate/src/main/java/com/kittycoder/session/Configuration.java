package com.kittycoder.session;

import com.kittycoder.mapping.MapStatement;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shucheng on 2019-9-20 下午 23:41
 */
public class Configuration {

    private String jdbcUrl;
    private String driverClass;
    private String username;
    private String password;

    private Map<String, MapStatement> mapStatements = new HashMap<>();

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, MapStatement> getMapStatements() {
        return mapStatements;
    }

    public void setMapStatements(Map<String, MapStatement> mapStatements) {
        this.mapStatements = mapStatements;
    }
}
