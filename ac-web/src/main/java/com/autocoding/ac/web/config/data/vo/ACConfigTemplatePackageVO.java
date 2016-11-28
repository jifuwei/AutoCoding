package com.autocoding.ac.web.config.data.vo;

import com.autocoding.ac.web.config.data.po.ACConfigTemplatePackagePO;
import org.apache.commons.lang3.StringUtils;

/**
 * 模板套餐信息VO
 * Created by JFW on 2016/11/2.
 */
public class ACConfigTemplatePackageVO {
    private String package_id;//套餐编码
    private String package_name;//套餐名称
    private String package_desc;//套餐描述
    private String is_available;//是否可用

    public String getPackage_id() {
        return package_id;
    }

    public void setPackage_id(String package_id) {
        this.package_id = package_id;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String getPackage_desc() {
        return package_desc;
    }

    public void setPackage_desc(String package_desc) {
        this.package_desc = package_desc;
    }

    public String getIs_available() {
        return is_available;
    }

    public void setIs_available(String is_available) {
        this.is_available = is_available;
    }

    public Object[] getPrimaryKeys() {
        return new Object[]{this.package_id};
    }

    public ACConfigTemplatePackagePO toPO() {
        ACConfigTemplatePackagePO po = new ACConfigTemplatePackagePO();
        po.setPackage_id(StringUtils.isBlank(this.package_id) ? null : Integer.parseInt(this.package_id));
        po.setPackage_name(this.package_name);
        po.setPackage_desc(this.package_desc);
        po.setIs_available(Integer.valueOf(this.is_available));

        return po;
    }
}
