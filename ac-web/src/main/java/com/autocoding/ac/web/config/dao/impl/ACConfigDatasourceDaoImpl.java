package com.autocoding.ac.web.config.dao.impl;

import com.autocoding.ac.foundation.dao.IDaoImpl;
import com.autocoding.ac.web.config.dao.ACConfigDatasourceDao;
import com.autocoding.ac.web.config.data.ACConfigDatasourceData;
import com.autocoding.ac.web.config.data.po.ACConfigDatasourcePO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

/**
 * 模板信息实现类
 * Created by JFW on 2016/11/3.
 */
@Repository("ACConfigDatasourceDaoImpl")
public class ACConfigDatasourceDaoImpl extends IDaoImpl<ACConfigDatasourcePO> implements ACConfigDatasourceDao {

    private final BeanPropertyRowMapper<ACConfigDatasourceData> dataRM = BeanPropertyRowMapper.newInstance(ACConfigDatasourceData.class);

    public ACConfigDatasourceDaoImpl() {
        super();
        this.tableName = "ac_config_datasource";
        this.tableKeys = new String[]{"datasource_id"};
    }

    @Override
    protected String generateWhere(Object[] paramArrayOfObject) {
        return null;
    }

    @Override
    public ACConfigDatasourceData findDatasourceAndDatabaseInfoByDatasourceId(Integer datasource_id) {
        Object[] args = {datasource_id};
        String sql = "SELECT cd1.*, cd2.database_desc, cd2.database_driver FROM ac_config_datasource cd1 LEFT JOIN ac_config_db cd2 ON cd1.database_id = cd2.database_id WHERE cd1.datasource_id = ?";
        return defaultJdbcTemplate.queryForObject(sql, args, dataRM);
    }
}
