package com.autocoding.ac.web.config.data.vo;

import com.autocoding.ac.foundation.validation.EntityGroup;
import com.autocoding.ac.foundation.validation.PrimaryKeyGroup;
import com.autocoding.ac.web.config.data.po.ACConfigTemplateFilePO;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 模板文件信息VO
 * Created by JFW on 2016/11/2.
 */
public class ACConfigTemplateFileVO {

    @NotEmpty(message = "{ACConfigTemplateFileVO.templateFileId.NotEmpty}", groups = {PrimaryKeyGroup.class})
    private String template_file_id; //模板文件编码

    @NotEmpty(message = "{ACConfigTemplateFileVO.templateId.NotEmpty}", groups = {EntityGroup.class})
    private String template_id; //模板编码

    @NotEmpty(message = "{ACConfigTemplateFileVO.templateVersion.NotEmpty}", groups = {EntityGroup.class})
    private String template_version; //模板版本

    @NotEmpty(message = "{ACConfigTemplateFileVO.templateFileName.NotEmpty}", groups = {EntityGroup.class})
    private String template_file_name; //模板文件名：actemplatecontroller.ftl

    @NotEmpty(message = "{ACConfigTemplateFileVO.processFileName.NotEmpty}", groups = {EntityGroup.class})
    private String process_file_name; //输出文件名：${}controller.java，${}为替换字符

    @NotEmpty(message = "{ACConfigTemplateFileVO.processPath.NotEmpty}", groups = {EntityGroup.class})
    private String process_path; //输出相对路径：相对生成应用的根目录

    public String getTemplate_file_id() {
        return template_file_id;
    }

    public void setTemplate_file_id(String template_file_id) {
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

    public Object[] getPrimaryKeys() {
        return new Object[]{this.template_file_id};
    }

    public ACConfigTemplateFilePO toPO() {
        ACConfigTemplateFilePO po = new ACConfigTemplateFilePO();
        po.setTemplate_file_id(StringUtils.isBlank(this.template_file_id) ? null : Integer.parseInt(this.template_file_id));
        po.setTemplate_id(this.template_id);
        po.setTemplate_version(this.template_version);
        po.setTemplate_file_name(this.template_file_name);
        po.setProcess_file_name(this.process_file_name);
        po.setProcess_path(this.process_path);

        return po;
    }
}
