package com.jifuwei.ac.web.meta.dao;

import com.jifuwei.ac.foundation.dao.IDao;
import com.jifuwei.ac.web.meta.data.ACDbColumnMetaInfoData;
import com.jifuwei.ac.web.meta.data.ACDbExportedKeyMetaInfoData;
import com.jifuwei.ac.web.meta.data.ACDbPrimaryKeyMetaInfoData;
import com.jifuwei.ac.web.meta.data.ACDbTableMetaInfoData;
import com.jifuwei.ac.web.meta.data.po.ACDbMetaInfoPO;

import java.util.List;

/**
 * 抓取数据库连接信息接口
 * Created by JFW on 2016/10/11.
 */
public interface ACDbMetaInfoDao extends IDao<ACDbMetaInfoPO> {
    ACDbMetaInfoPO findDbMetaInfo();

    List<ACDbTableMetaInfoData> findDbTablesMetaInfo(String catalog, String schema, String tableName, String[] types);

    List<ACDbColumnMetaInfoData> findDbColumnsMetaInfo(String catalog, String schama, String tablename, String columnNamePattern);

    List<ACDbPrimaryKeyMetaInfoData> findDbPrimaryKeysMetaInfo(String catalog, String schema, String tableName);

    List<ACDbExportedKeyMetaInfoData> findDbExportedKeysMetaInfo(String catalog, String schema, String tableName);
}
