package com.jifuwei.ac.web.meta.dao;

import com.jifuwei.ac.foundation.dao.IDao;
import com.jifuwei.ac.web.meta.data.po.*;

import java.util.List;

/**
 * 抓取数据库连接信息接口
 * Created by JFW on 2016/10/11.
 */
public interface ACDbMetaInfoDao extends IDao<ACDbMetaInfoPO> {
    ACDbMetaInfoPO findDbMetaInfo();

    List<ACDbTableMetaInfoPO> findDbTablesMetaInfo(String catalog, String schema, String tableName, String[] types);

    List<ACDbColumnMetaInfoPO> findDbColumnsMetaInfo(String catalog,String schama,String tablename,String columnNamePattern);

    List<ACDbPrimaryKeyMetaInfoPO> findDbPrimaryKeysMetaInfo(String catalog, String schema, String tableName);

    List<ACDbExportedKeyMetaInfoPO> findDbExportedKeysMetaInfo(String catalog, String schema, String tableName);
}
