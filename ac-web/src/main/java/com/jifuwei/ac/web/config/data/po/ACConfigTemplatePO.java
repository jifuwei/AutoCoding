package com.jifuwei.ac.web.config.data.po;

import java.sql.Timestamp;

/**
 * 模板信息PO
 * Created by JFW on 2016/11/2.
 */
public class ACConfigTemplatePO {
    private String template_id; //模板编码
    private String template_desc; //模板描述
    private String template_version; //模板版本:v2.3.67
    private Integer template_type; //模板类型：前端、control、dao
    private Integer is_available; //是否可用
    private Timestamp create_time; //创建时间
    private String create_by; //创建者
    private Timestamp update_time; //更新时间
    private String update_by; //更新者

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getTemplate_desc() {
        return template_desc;
    }

    public void setTemplate_desc(String template_desc) {
        this.template_desc = template_desc;
    }

    public String getTemplate_version() {
        return template_version;
    }

    public void setTemplate_version(String template_version) {
        this.template_version = template_version;
    }

    public Integer getTemplate_type() {
        return template_type;
    }

    public void setTemplate_type(Integer template_type) {
        this.template_type = template_type;
    }

    public Integer getIs_available() {
        return is_available;
    }

    public void setIs_available(Integer is_available) {
        this.is_available = is_available;
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
