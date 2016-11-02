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
        po.setIs_available(Integer.valueOf(this.is_available));

        return po;
    }
}
