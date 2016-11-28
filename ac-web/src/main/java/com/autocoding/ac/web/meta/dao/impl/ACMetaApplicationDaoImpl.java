package com.autocoding.ac.web.meta.dao.impl;

import com.autocoding.ac.foundation.dao.IDaoImpl;
import com.autocoding.ac.web.meta.dao.ACMetaApplicationDao;
import com.autocoding.ac.web.meta.data.po.ACMetaApplicationPO;
import org.springframework.stereotype.Repository;

/**
 * 应用信息实现类
 * Created by JFW on 2016/11/2.
 */
@Repository("ACMetaApplicationDaoImpl")
public class ACMetaApplicationDaoImpl extends IDaoImpl<ACMetaApplicationPO> implements ACMetaApplicationDao {

    public ACMetaApplicationDaoImpl() {
        super();
        this.tableName = "ac_meta_application";
        this.tableKeys = new String[]{"app_id"};
    }

    @Override
    protected String generateWhere(Object[] paramArrayOfObject) {
        return null;
    }
}
