package com.autocoding.ac.web.meta.util;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.SQLExec;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * 使用ant处理数据库脚本文件
 * Created by JFW on 2016/10/13.
 */
public class AntDbUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(AntDbUtil.class);

    /**
     * ant工具执行数据库脚本文件
     * @param driverClass
     * @param url
     * @param username
     * @param password
     * @param file
     */
    public static void excuteSqlScriptFile(String driverClass, String url, String username, String password, File file) {
        LOGGER.info("driverClass: " + driverClass);
        LOGGER.info("url: " + url);
        LOGGER.info("username: " + username);
        LOGGER.info("password: " + password);
        // 初始化ant数据库连接
        SQLExec sqlExec = new SQLExec();
        sqlExec.setDriver(driverClass);
        sqlExec.setUrl(url);
        sqlExec.setUserid(username);
        sqlExec.setPassword(password);
        sqlExec.setEncoding("UTF8");
        sqlExec.setSrc(file);
        sqlExec.setOnerror((SQLExec.OnError) (EnumeratedAttribute.getInstance(SQLExec.OnError.class, "abort")));
        sqlExec.setPrint(true);
        sqlExec.setProject(new Project());
        sqlExec.execute();
    }

}
