package com.autocoding.ac.web.config.data;

import java.sql.Timestamp;

/**
 * 套餐和模板关系表Data
 * Created by JFW on 2016/11/2.
 */
public class ACConfigPackageFileData {
    private Integer package_id;//套餐编码
    private String template_id;//模板编码
    private Timestamp create_time;//创建时间
    private String create_by;//创建者
    private Timestamp update_time;//更新时间
    private String update_by;//更新者

    public Integer getPackage_id() {
        return package_id;
    }

    public void setPackage_id(Integer package_id) {
        this.package_id = package_id;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
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
