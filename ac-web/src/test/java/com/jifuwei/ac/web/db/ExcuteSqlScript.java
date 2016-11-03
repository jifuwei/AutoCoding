package com.jifuwei.ac.web.db;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.SQLExec;
import org.apache.tools.ant.types.EnumeratedAttribute;

import java.io.File;

/**
 * 测试java执行sql文件共功能
 * Created by JFW on 2016/10/6.
 */
public class ExcuteSqlScript {
    public static void excuteSqlScript() {
        try {
            SQLExec sqlExec = new SQLExec();
            String driverClass = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://127.0.0.1/ac?useUnicode=true&characterEncoding=utf-8";
            String username = "root";
            String password = "123456";
            sqlExec.setDriver(driverClass);
            sqlExec.setUrl(url);
            sqlExec.setUserid(username);
            sqlExec.setPassword(password);
            sqlExec.setSrc(new File("F:\\IDEAWorkspace\\autocoding\\ac-web\\src\\test\\resources\\db_script\\ac.sql"));
            sqlExec.setOnerror((SQLExec.OnError) (EnumeratedAttribute.getInstance(SQLExec.OnError.class, "abort")));
            sqlExec.setPrint(true);
            sqlExec.setProject(new Project());
            sqlExec.execute();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    public static void main(String[] args) {
        ExcuteSqlScript.excuteSqlScript();
    }
}
