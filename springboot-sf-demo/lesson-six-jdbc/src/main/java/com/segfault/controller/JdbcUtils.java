package com.segfault.controller;


import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * JDBC连接测试
 */
public class JdbcUtils {

    /**
     * 通过Driver连接数据库
     *
     * @return
     * @throws Exception
     */
    public static Connection getConnectionFordDriver() throws Exception {
        Properties properties = new Properties();
        properties.load(
                JdbcUtils.class.getClassLoader()
                        .getResourceAsStream("application.properties"));
        //通过反射实现Driver接口（这里是mysql的实现）
        Driver driver = (Driver) Class.forName(properties.getProperty("spring.datasource.driver-class-name")).newInstance();
        Properties info = new Properties();
        info.setProperty("user", properties.getProperty("spring.datasource.username"));
        info.setProperty("password", properties.getProperty("spring.datasource.password"));
        //?useSSL=false,mysql5.x版本需要ssl校验
        Connection con = driver.connect(
                properties.getProperty("spring.datasource.url") + "?useSSL=false"
                , info);
        return con;
    }

    /**
     * 通过获取DriverManager
     * com.mysql.jdbc.JDBC4Connection
     *
     * @return
     * @throws Exception
     */
    public static Connection getConnectionForDriverManager() throws Exception {
        Properties properties = new Properties();
        properties.load(
                JdbcUtils.class.getClassLoader()
                        .getResourceAsStream("application.properties"));
        //往DriverManager注册Driver
        //可注册多个驱动
        Class.forName(properties.getProperty("spring.datasource.driver-class-name"));
        Connection con = DriverManager.getConnection(
                properties.getProperty("spring.datasource.url") + "?useSSL=false",
                properties.getProperty("spring.datasource.username"),
                properties.getProperty("spring.datasource.password"));
        return con;
    }

    public static void main(String[] args) throws Exception {
        Connection con = JdbcUtils.getConnectionForDriverManager();
        System.out.println(con);
    }
}
