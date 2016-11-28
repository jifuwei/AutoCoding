package com.autocoding.ac.web.meta.data.vo;

import com.autocoding.ac.foundation.validation.EntityGroup;
import com.autocoding.ac.foundation.validation.PrimaryKeyGroup;
import com.autocoding.ac.web.meta.data.po.ACMetaApplicationPO;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 应用信息VO
 * Created by JFW on 2016/11/2.
 */
public class ACMetaApplicationVO {

    @NotEmpty(message = "{ACMetaApplicationVO.appId.NotEmpty}", groups = {PrimaryKeyGroup.class})
    private String app_id;//应用编码

    @NotEmpty(message = "{ACMetaApplicationVO.appEnName.NotEmpty}", groups = {EntityGroup.class})
    private String app_en_name;//系统英文名称

    @NotEmpty(message = "{ACMetaApplicationVO.appZhName.NotEmpty}", groups = {EntityGroup.class})
    private String app_zh_name;//应用中文名称

    @NotEmpty(message = "{ACMetaApplicationVO.groupId.NotEmpty}", groups = {EntityGroup.class})
    private String group_id;//组织标识

    @NotEmpty(message = "{ACMetaApplicationVO.appDbScript.NotEmpty}", groups = {EntityGroup.class})
    private String app_db_script;//数据库脚本文件

    @NotEmpty(message = "{ACMetaApplicationVO.packageId.NotEmpty}", groups = {EntityGroup.class})
    private String package_id;//模板套餐编码

    @NotEmpty(message = "{ACMetaApplicationVO.datasourceId.NotEmpty}", groups = {EntityGroup.class})
    private String datasource_id;//数据源编码

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
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

    public String getPackage_id() {
        return package_id;
    }

    public void setPackage_id(String package_id) {
        this.package_id = package_id;
    }

    public Object[] getPrimaryKeys() {
        return new Object[]{this.app_id};
    }

    public String getDatasource_id() {
        return datasource_id;
    }

    public void setDatasource_id(String datasource_id) {
        this.datasource_id = datasource_id;
    }

    public ACMetaApplicationPO toPO() {
        ACMetaApplicationPO po = new ACMetaApplicationPO();
        po.setApp_id(StringUtils.isBlank(this.app_id) ? null : Integer.valueOf(this.app_id));
        po.setApp_en_name(this.app_en_name);
        po.setApp_zh_name(this.app_zh_name);
        po.setGroup_id(this.group_id);
        po.setApp_db_script(this.app_db_script);
        po.setPackage_id(Integer.valueOf(this.package_id));
        po.setDatasource_id(Integer.valueOf(this.datasource_id));

        return po;
    }
}
