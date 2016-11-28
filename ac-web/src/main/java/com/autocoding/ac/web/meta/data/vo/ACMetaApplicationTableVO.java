package com.autocoding.ac.web.meta.data.vo;

import com.autocoding.ac.foundation.validation.EntityGroup;
import com.autocoding.ac.foundation.validation.PrimaryKeyGroup;
import com.autocoding.ac.web.meta.data.po.ACMetaApplicationTablePO;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 应用表信息VO
 * Created by JFW on 2016/11/2.
 */
public class ACMetaApplicationTableVO {

    @NotEmpty(message = "{ACMetaApplicationTableVO.appTableId.NotEmpty}", groups = {PrimaryKeyGroup.class})
    private String app_table_id;//应用表编码

    @NotEmpty(message = "{ACMetaApplicationTableVO.appId.NotEmpty}", groups = {EntityGroup.class})
    private String app_id;//应用编码

    @NotEmpty(message = "{ACMetaApplicationTableVO.appTableName.NotEmpty}", groups = {EntityGroup.class})
    private String app_table_name;//应用表名称
    private String app_table_remarks;//应用表备注

    public String getApp_table_id() {
        return app_table_id;
    }

    public void setApp_table_id(String app_table_id) {
        this.app_table_id = app_table_id;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
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

    public Object[] getPrimaryKeys() {
        return new Object[]{this.app_table_id};
    }

    public ACMetaApplicationTablePO toPO() {
        ACMetaApplicationTablePO po = new ACMetaApplicationTablePO();
        po.setApp_table_id(StringUtils.isBlank(this.app_table_id) ? null : Integer.valueOf(this.app_table_id));
        po.setApp_id(Integer.valueOf(this.app_id));
        po.setApp_table_name(this.app_table_name);
        po.setApp_table_remarks(this.app_table_remarks);

        return po;
    }
}
