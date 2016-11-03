package com.jifuwei.ac.web.config.dao;

import com.jifuwei.ac.foundation.dao.IDao;
import com.jifuwei.ac.web.config.data.ACConfigDatasourceData;
import com.jifuwei.ac.web.config.data.po.ACConfigDatasourcePO;

/**
 * 数据源配置Dao
 * Created by JFW on 2016/11/2.
 */
public interface ACConfigDatasourceDao extends IDao<ACConfigDatasourcePO> {
    ACConfigDatasourceData findDatasourceAndDatabaseInfoByDatasourceId(String datasource_id);
}
