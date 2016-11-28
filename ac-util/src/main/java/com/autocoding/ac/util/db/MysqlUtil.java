package com.autocoding.ac.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * mysql数据库连接类，专用于打开表注释，其他数据库连接请使用数据源配置
 * Created by JFW on 2016/10/14.
 */
public class MysqlUtil {

    public static Connection getConnection() {
        Connection conn = null;
        try {
            //TODO:数据库配置需要写入配置文件中
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/autocoding?characterEncoding=utf8&amp;zeroDateTimeBehavior=convertToNull";
            Properties properties = new Properties();
            properties.setProperty("user", "root");
            properties.setProperty("password", "123456");
            properties.setProperty("remarks", "true"); //设置可以获取remarks信息
            properties.setProperty("useInformationSchema", "true");//设置可以获取tables remarks信息
            conn = DriverManager.getConnection(url, properties);
            return conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
