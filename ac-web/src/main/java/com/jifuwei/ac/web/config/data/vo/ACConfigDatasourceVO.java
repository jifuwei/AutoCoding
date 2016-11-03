package com.jifuwei.ac.web.config.data.vo;

import com.jifuwei.ac.web.config.data.po.ACConfigDatasourcePO;
import org.apache.commons.lang3.StringUtils;

/**
 * 数据源配置Data
 * Created by JFW on 2016/11/3.
 */
public class ACConfigDatasourceVO {
    private String datasource_id;//数据源编码
    private String datasource_name;//数据源名称
    private String datasource_ip;//数据源ip
    private String datasource_port;//数据源端口
    private String db_name;//数据库名称
    private String db_connect_name;//数据库用户名
    private String db_connect_pwd;//数据库密码
    private String database_id;//数据库编码

    public String getDatasource_id() {
        return datasource_id;
    }

    public void setDatasource_id(String datasource_id) {
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

    public Object[] getPrimaryKeys() {
        return new Object[]{this.database_id};
    }

    public ACConfigDatasourcePO toPO() {
        ACConfigDatasourcePO po = new ACConfigDatasourcePO();
        po.setDatasource_id(StringUtils.isBlank(this.database_id) ? null : Integer.valueOf(this.database_id));
        po.setDatasource_name(this.datasource_name);
        po.setDatasource_ip(this.datasource_ip);
        po.setDatasource_port(this.datasource_port);
        po.setDb_name(this.db_name);
        po.setDb_connect_name(this.db_connect_name);
        po.setDb_connect_pwd(this.db_connect_pwd);
        po.setDatabase_id(this.database_id);

        return po;
    }
}
