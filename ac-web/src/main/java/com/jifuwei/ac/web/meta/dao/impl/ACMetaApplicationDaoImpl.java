package com.jifuwei.ac.web.meta.dao.impl;

import com.jifuwei.ac.foundation.dao.IDaoImpl;
import com.jifuwei.ac.web.meta.dao.ACMetaApplicationDao;
import com.jifuwei.ac.web.meta.data.po.ACMetaApplicationPO;
import org.springframework.dao.EmptyResultDataAccessException;
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

    @Override
    public void createDatabase(String db_name) {
        String sql = "CREATE DATABASE " + db_name;//TODO:有sql注入危险，使用参数总是报语句错误，未能找到错误原因20161103
        defaultJdbcTemplate.update(sql);
    }

    @Override
    public boolean isExistDataBase(String db_name) {
        try {
            String queryDbName = null;
            Object[] args = {db_name};
            String sql = "SHOW DATABASES LIKE ?";
            queryDbName = defaultJdbcTemplate.queryForObject(sql, args, String.class);
            return db_name.equals(queryDbName) ? true : false;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}
