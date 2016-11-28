package com.autocoding.ac.web.meta.data.po;

import java.sql.Timestamp;

/**
 * 应用表信息PO
 * Created by JFW on 2016/11/2.
 */
public class ACMetaApplicationTablePO {
    private Integer app_table_id;//应用表编码
    private Integer app_id;//应用编码
    private String app_table_name;//应用表名称
    private String app_table_remarks;//应用表备注
    private Timestamp create_time;//创建时间
    private String create_by;//创建者
    private Timestamp update_time;//更新时间
    private String update_by;//更新者

    public Integer getApp_table_id() {
        return app_table_id;
    }

    public void setApp_table_id(Integer app_table_id) {
        this.app_table_id = app_table_id;
    }

    public Integer getApp_id() {
        return app_id;
    }

    public void setApp_id(Integer app_id) {
        this.app_id = app_id;
    }

    public String getApp_table_name() {
        return app_table_name;
    }

    public void setApp_table_name(String app_table_name) {
        this.app_table_name = app_table_name;
    }

    public String getApp_table_remarks() {
        return app_table_remarks;
    }

    public void setApp_table_remarks(String app_table_remarks) {
        this.app_table_remarks = app_table_remarks;
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
