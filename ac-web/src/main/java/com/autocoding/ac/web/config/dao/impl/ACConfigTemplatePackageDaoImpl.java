package com.autocoding.ac.web.config.dao.impl;

import com.autocoding.ac.foundation.dao.IDaoImpl;
import com.autocoding.ac.web.config.dao.ACConfigTemplatePackageDao;
import com.autocoding.ac.web.config.data.po.ACConfigTemplatePackagePO;
import org.springframework.stereotype.Repository;

/**
 * 模板套餐实现类
 * Created by JFW on 2016/11/2.
 */
@Repository("ACConfigTemplatePackageDaoImpl")
public class ACConfigTemplatePackageDaoImpl extends IDaoImpl<ACConfigTemplatePackagePO> implements ACConfigTemplatePackageDao {

    public ACConfigTemplatePackageDaoImpl() {
        super();
        this.tableName = "ac_config_template_package";
        this.tableKeys = new String[]{"package_id"};
    }

    @Override
    protected String generateWhere(Object[] paramArrayOfObject) {
        return null;
    }
}
