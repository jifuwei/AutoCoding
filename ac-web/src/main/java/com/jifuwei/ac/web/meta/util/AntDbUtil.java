package com.jifuwei.ac.web.meta.util;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.SQLExec;
import org.apache.tools.ant.types.EnumeratedAttribute;

import java.io.File;

/**
 * 使用ant处理数据库脚本文件
 * Created by JFW on 2016/10/13.
 */
public class AntDbUtil {

    private String driverClass;
    private String url;
    private String username;
    private String password;

    private SQLExec sqlExec = null;

    private static AntDbUtil instance = null;

    private AntDbUtil(String driverClass, String url, String username, String password) {
        this.driverClass = driverClass;
        this.url = url;
        this.username = username;
        this.password = password;

        // 初始化ant数据库连接
        sqlExec = new SQLExec();
        sqlExec.setDriver(driverClass);
        sqlExec.setUrl(url);
        sqlExec.setUserid(username);
        sqlExec.setPassword(password);
    }

    public static AntDbUtil getInstance(String driverClass, String url, String username, String password) {
        if (instance == null) {
            instance = new AntDbUtil(driverClass, url, username, password);
        }
        return instance;
    }

    public void excuteSqlScriptFile(File file) {
        sqlExec.setSrc(file);
        sqlExec.setOnerror((SQLExec.OnError) (EnumeratedAttribute.getInstance(SQLExec.OnError.class, "abort")));
        sqlExec.setPrint(true);
        sqlExec.setProject(new Project());
        sqlExec.execute();
    }

}
