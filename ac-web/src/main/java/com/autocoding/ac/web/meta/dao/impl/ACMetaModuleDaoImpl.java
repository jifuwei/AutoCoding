package com.autocoding.ac.web.meta.dao.impl;

import com.autocoding.ac.foundation.dao.IDaoImpl;
import com.autocoding.ac.web.meta.dao.ACMetaModuleDao;
import com.autocoding.ac.web.meta.data.po.ACMetaModulePO;
import org.springframework.stereotype.Repository;

/**
 * 应用信息实现类
 * Created by JFW on 2016/11/2.
 */
@Repository("ACMetaModuleDaoImpl")
public class ACMetaModuleDaoImpl extends IDaoImpl<ACMetaModulePO> implements ACMetaModuleDao {

    public ACMetaModuleDaoImpl() {
        super();
        this.tableName = "ac_meta_module";
        this.tableKeys = new String[]{"app_module_id"};
    }

    @Override
    protected String generateWhere(Object[] paramArrayOfObject) {
        return null;
    }
}
