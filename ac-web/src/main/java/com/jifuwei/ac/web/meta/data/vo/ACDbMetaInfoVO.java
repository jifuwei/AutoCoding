package com.jifuwei.ac.web.meta.data.vo;

/**
 * 数据库信息实体Data
 * Created by JFW on 2016/10/11.
 */
public class ACDbMetaInfoVO {
    private String userName;					//用户
    private String url;							//数据局连接url
    private String databaseProductName;			//数据库产品名称
    private String databaseProductVersion;		//数据库产品版本
    private String databaseDriverName;			//数据库驱动名称
    private String databaseDriverVersion;		//数据库驱动版本

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDatabaseProductName() {
        return databaseProductName;
    }

    public void setDatabaseProductName(String databaseProductName) {
        this.databaseProductName = databaseProductName;
    }

    public String getDatabaseProductVersion() {
        return databaseProductVersion;
    }

    public void setDatabaseProductVersion(String databaseProductVersion) {
        this.databaseProductVersion = databaseProductVersion;
    }

    public String getDatabaseDriverName() {
        return databaseDriverName;
    }

    public void setDatabaseDriverName(String databaseDriverName) {
        this.databaseDriverName = databaseDriverName;
    }

    public String getDatabaseDriverVersion() {
        return databaseDriverVersion;
    }

    public void setDatabaseDriverVersion(String databaseDriverVersion) {
        this.databaseDriverVersion = databaseDriverVersion;
    }
}
