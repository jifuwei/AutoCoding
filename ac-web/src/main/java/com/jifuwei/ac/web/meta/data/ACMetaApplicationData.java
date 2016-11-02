package com.jifuwei.ac.web.meta.data;

import java.sql.Timestamp;

/**
 * 应用信息Data
 * Created by JFW on 2016/11/2.
 */
public class ACMetaApplicationData {
    private Integer app_id;//应用编码
    private String app_en_name;//系统英文名称
    private String app_zh_name;//应用中文名称
    private String group_id;//组织标识
    private String app_db_script;//数据库脚本文件
    private Integer package_id;//模板套餐编码
    private Timestamp create_time;//创建时间
    private String create_by;//创建者
    private Timestamp update_time;//更新时间
    private String update_by;//更新者

    public Integer getApp_id() {
        return app_id;
    }

    public void setApp_id(Integer app_id) {
        this.app_id = app_id;
    }

    public String getApp_en_name() {
        return app_en_name;
    }

    public void setApp_en_name(String app_en_name) {
        this.app_en_name = app_en_name;
    }

    public String getApp_zh_name() {
        return app_zh_name;
    }

    public void setApp_zh_name(String app_zh_name) {
        this.app_zh_name = app_zh_name;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getApp_db_script() {
        return app_db_script;
    }

    public void setApp_db_script(String app_db_script) {
        this.app_db_script = app_db_script;
    }

    public Integer getPackage_id() {
        return package_id;
    }

    public void setPackage_id(Integer package_id) {
        this.package_id = package_id;
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
