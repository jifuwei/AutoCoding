package com.jifuwei.ac.web.config.data.po;

import java.sql.Timestamp;

/**
 * 模板文件信息PO
 * Created by JFW on 2016/11/2.
 */
public class ACConfigTemplateFilePO {
    private Integer template_file_id; //模板文件编码
    private String template_id; //模板编码
    private String template_version; //模板版本
    private String template_file_name; //模板文件名：actemplatecontroller.ftl
    private String process_file_name; //输出文件名：${}controller.java，${}为替换字符
    private String process_path; //输出相对路径：相对生成应用的根目录
    private Timestamp create_time; //创建时间
    private String create_by; //创建者
    private Timestamp update_time; //更新时间
    private String update_by; //更新者

    public Integer getTemplate_file_id() {
        return template_file_id;
    }

    public void setTemplate_file_id(Integer template_file_id) {
        this.template_file_id = template_file_id;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getTemplate_version() {
        return template_version;
    }

    public void setTemplate_version(String template_version) {
        this.template_version = template_version;
    }

    public String getTemplate_file_name() {
        return template_file_name;
    }

    public void setTemplate_file_name(String template_file_name) {
        this.template_file_name = template_file_name;
    }

    public String getProcess_file_name() {
        return process_file_name;
    }

    public void setProcess_file_name(String process_file_name) {
        this.process_file_name = process_file_name;
    }

    public String getProcess_path() {
        return process_path;
    }

    public void setProcess_path(String process_path) {
        this.process_path = process_path;
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
