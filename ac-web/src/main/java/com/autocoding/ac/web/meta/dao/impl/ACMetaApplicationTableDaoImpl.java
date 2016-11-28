package com.autocoding.ac.web.meta.dao.impl;

import com.autocoding.ac.foundation.dao.IDaoImpl;
import com.autocoding.ac.web.meta.dao.ACMetaApplicationTableDao;
import com.autocoding.ac.web.meta.data.po.ACMetaApplicationTablePO;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public List<ACMetaApplicationTablePO> getMeatApplicationTableDataByAppId(Integer app_id) {
        Object[] args = {app_id};
        String sql = "SELECT * FROM ac_meta_application_table t WHERE t.app_id = ?";
        return defaultJdbcTemplate.query(sql, args, this.rm);
    }
}
