package com.autocoding.ac.web.meta.data.vo;

import com.autocoding.ac.foundation.validation.EntityGroup;
import com.autocoding.ac.foundation.validation.PrimaryKeyGroup;
import com.autocoding.ac.web.meta.data.po.ACMetaModulePO;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 应用模块信息VO
 * Created by JFW on 2016/11/2.
 */
public class ACMetaModuleVO {

    @NotEmpty(message = "{ACMetaModuleVO.appModuleId.NotEmpty}", groups = {PrimaryKeyGroup.class})
    private String app_module_id;//应用模块编码

    @NotEmpty(message = "{ACMetaModuleVO.appId.NotEmpty}", groups = {EntityGroup.class})
    private String app_id;//应用编码

    @NotEmpty(message = "{ACMetaModuleVO.appModuleName.NotEmpty}", groups = {EntityGroup.class})
    private String app_module_name;//应用模块名称

    @NotEmpty(message = "{ACMetaModuleVO.appModuleRemark.NotEmpty}", groups = {EntityGroup.class})
    private String app_module_remark;//应用模块备注

    public String getApp_module_id() {
        return app_module_id;
    }

    public void setApp_module_id(String app_module_id) {
        this.app_module_id = app_module_id;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
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

    public Object[] getPrimaryKeys() {
        return new Object[]{this.app_module_id};
    }

    public ACMetaModulePO toPO() {
        ACMetaModulePO po = new ACMetaModulePO();
        po.setApp_module_id(StringUtils.isBlank(this.app_module_id) ? null : Integer.valueOf(this.app_module_id));
        po.setApp_id(Integer.valueOf(this.app_id));
        po.setApp_module_name(this.app_module_name);
        po.setApp_module_remark(this.app_module_remark);

        return po;
    }
}
