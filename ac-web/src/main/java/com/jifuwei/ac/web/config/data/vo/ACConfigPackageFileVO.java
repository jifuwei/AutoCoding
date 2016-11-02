package com.jifuwei.ac.web.config.data.vo;

import com.jifuwei.ac.web.config.data.po.ACConfigPackageFilePO;
import org.apache.commons.lang3.StringUtils;

/**
 * 套餐和模板关系表VO
 * Created by JFW on 2016/11/2.
 */
public class ACConfigPackageFileVO {
    private String package_id;//套餐编码
    private String template_id;//模板编码

    public String getPackage_id() {
        return package_id;
    }

    public void setPackage_id(String package_id) {
        this.package_id = package_id;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public Object[] getPrimaryKeys() {
        return new Object[]{this.package_id, this.template_id};
    }

    public ACConfigPackageFilePO toPO() {
        ACConfigPackageFilePO po = new ACConfigPackageFilePO();
        po.setPackage_id(StringUtils.isBlank(this.package_id) ? null : Integer.valueOf(this.package_id));
        po.setTemplate_id(this.template_id);

        return po;
    }
}
