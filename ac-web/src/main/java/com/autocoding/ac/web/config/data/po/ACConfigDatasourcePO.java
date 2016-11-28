package com.autocoding.ac.web.config.data.po;

import java.sql.Timestamp;

/**
 * 数据源配置Data
 * Created by JFW on 2016/11/3.
 */
public class ACConfigDatasourcePO {
    private Integer datasource_id;//数据源编码
    private String datasource_name;//数据源名称
    private String datasource_ip;//数据源ip
    private String datasource_port;//数据源端口
    private String db_name;//数据库名称
    private String db_connect_name;//数据库用户名
    private String db_connect_pwd;//数据库密码
    private String database_id;//数据库编码
    private Timestamp create_time;//创建时间
    private String create_by;//创建者
    private Timestamp update_time;//更新时间
    private String update_by;//更新者

    public Integer getDatasource_id() {
        return datasource_id;
    }

    public void setDatasource_id(Integer datasource_id) {
        this.datasource_id = datasource_id;
    }

    public String getDatasource_name() {
        return datasource_name;
    }

    public void setDatasource_name(String datasource_name) {
        this.datasource_name = datasource_name;
    }

    public String getDatasource_ip() {
        return datasource_ip;
    }

    public void setDatasource_ip(String datasource_ip) {
        this.datasource_ip = datasource_ip;
    }

    public String getDatasource_port() {
        return datasource_port;
    }

    public void setDatasource_port(String datasource_port) {
        this.datasource_port = datasource_port;
    }

    public String getDb_name() {
        return db_name;
    }

    public void setDb_name(String db_name) {
        this.db_name = db_name;
    }

    public String getDb_connect_name() {
        return db_connect_name;
    }

    public void setDb_connect_name(String db_connect_name) {
        this.db_connect_name = db_connect_name;
    }

    public String getDb_connect_pwd() {
        return db_connect_pwd;
    }

    public void setDb_connect_pwd(String db_connect_pwd) {
        this.db_connect_pwd = db_connect_pwd;
    }

    public String getDatabase_id() {
        return database_id;
    }

    public void setDatabase_id(String database_id) {
        this.database_id = database_id;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public Timestamp getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Timestamp update_time) {
        this.update_time = update_time;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }
}
