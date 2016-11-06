package com.jifuwei.ac.web.config.data.vo;

import com.jifuwei.ac.foundation.validation.EntityGroup;
import com.jifuwei.ac.foundation.validation.PrimaryKeyGroup;
import com.jifuwei.ac.web.config.data.po.ACConfigTemplatePO;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.DecimalMin;

/**
 * 模板信息VO
 * Created by JFW on 2016/11/2.
 */
public class ACConfigTemplateVO {

    @NotEmpty(message = "{ACConfigTemplateVO.templateId.NotEmpty}", groups = {PrimaryKeyGroup.class, EntityGroup.class})
    private String template_id; //模板编码
    private String template_desc; //模板描述

    @NotEmpty(message = "{ACConfigTemplateVO.templateVersion.NotEmpty}", groups = {EntityGroup.class})
    private String template_version; //模板版本:v2.3.67

    @DecimalMin(value = "1", message = "{ACConfigTemplateVO.templateType.DecimalMin}", groups = {EntityGroup.class})
    private String template_type; //模板类型：前端、control、dao

    private String template_file_name; //模板文件名称
    private String process_file_name; //输出文件名称
    private String process_path; //输出文件路径

    @NotEmpty(message = "{ACConfigTemplateVO.isAvailable.NotEmpty}", groups = {EntityGroup.class})
    private String is_available; //是否可用

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

    public String getTemplate_type() {
        return template_type;
    }

    public void setTemplate_type(String template_type) {
        this.template_type = template_type;
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

    public String getIs_available() {
        return is_available;
    }

    public void setIs_available(String is_available) {
        this.is_available = is_available;
    }

    public Object[] getPrimaryKeys() {
        return new Object[]{this.template_id};
    }

    public ACConfigTemplatePO toPO() {
        ACConfigTemplatePO po = new ACConfigTemplatePO();
        po.setTemplate_id(this.template_id);
        po.setTemplate_desc(this.template_desc);
        po.setTemplate_version(this.template_version);
        po.setTemplate_type(Integer.valueOf(this.template_type));
        po.setTemplate_file_name(this.template_file_name);
        po.setProcess_file_name(this.process_file_name);
        po.setProcess_path(this.process_path);
        po.setIs_available(Integer.valueOf(this.is_available));

        return po;
    }
}
