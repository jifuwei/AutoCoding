package com.autocoding.ac.web.config.dao;

import com.autocoding.ac.foundation.dao.IDao;
import com.autocoding.ac.web.config.data.ACConfigDatasourceData;
import com.autocoding.ac.web.config.data.po.ACConfigDatasourcePO;

/**
 * 数据源配置Dao
 * Created by JFW on 2016/11/2.
 */
public interface ACConfigDatasourceDao extends IDao<ACConfigDatasourcePO> {
    ACConfigDatasourceData findDatasourceAndDatabaseInfoByDatasourceId(Integer datasource_id);
}
