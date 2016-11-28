package com.autocoding.ac.web.meta.data;

import java.sql.Timestamp;

/**
 * 应用模块信息Data
 * Created by JFW on 2016/11/2.
 */
public class ACMetaModuleData {
    private Integer app_module_id;//应用模块编码
    private Integer app_id;//应用编码
    private String app_module_name;//应用模块名称
    private String app_module_remark;//应用模块备注
    private Timestamp create_time;//创建时间
    private String create_by;//创建者
    private Timestamp update_time;//更新时间
    private String update_by;//更新者

    public Integer getApp_module_id() {
        return app_module_id;
    }

    public void setApp_module_id(Integer app_module_id) {
        this.app_module_id = app_module_id;
    }

    public Integer getApp_id() {
        return app_id;
    }

    public void setApp_id(Integer app_id) {
        this.app_id = app_id;
    }

    public String getApp_module_name() {
        return app_module_name;
    }

    public void setApp_module_name(String app_module_name) {
        this.app_module_name = app_module_name;
    }

    public String getApp_module_remark() {
        return app_module_remark;
    }

    public void setApp_module_remark(String app_module_remark) {
        this.app_module_remark = app_module_remark;
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
