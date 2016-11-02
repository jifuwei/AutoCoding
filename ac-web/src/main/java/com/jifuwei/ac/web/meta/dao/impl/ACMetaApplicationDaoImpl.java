package com.jifuwei.ac.web.meta.dao.impl;

import com.jifuwei.ac.foundation.dao.IDaoImpl;
import com.jifuwei.ac.web.meta.dao.ACMetaApplicationDao;
import com.jifuwei.ac.web.meta.data.po.ACMetaApplicationPO;
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
