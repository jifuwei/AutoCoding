package com.jifuwei.ac.web.meta.dao.impl;

import com.jifuwei.ac.foundation.dao.IDaoImpl;
import com.jifuwei.ac.web.meta.dao.ACMetaApplicationTableDao;
import com.jifuwei.ac.web.meta.data.po.ACMetaApplicationTablePO;
import org.springframework.stereotype.Repository;

/**
 * 应用信息实现类
 * Created by JFW on 2016/11/2.
 */
@Repository("ACMetaApplicationTableDaoImpl")
public class ACMetaApplicationTableDaoImpl extends IDaoImpl<ACMetaApplicationTablePO> implements ACMetaApplicationTableDao {

    public ACMetaApplicationTableDaoImpl() {
        super();
        this.tableName = "ac_meta_application_table";
        this.tableKeys = new String[]{"app_table_id"};
    }

    @Override
    protected String generateWhere(Object[] paramArrayOfObject) {
        return null;
    }
}
