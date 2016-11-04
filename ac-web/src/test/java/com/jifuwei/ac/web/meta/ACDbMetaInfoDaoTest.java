package com.jifuwei.ac.web.meta;

import com.alibaba.fastjson.JSON;
import com.jifuwei.ac.web.base.BaseTest;
import com.jifuwei.ac.web.meta.dao.ACDbMetaInfoDao;
import com.jifuwei.ac.web.meta.data.ACDbColumnMetaInfoData;
import com.jifuwei.ac.web.meta.data.ACDbExportedKeyMetaInfoData;
import com.jifuwei.ac.web.meta.data.ACDbPrimaryKeyMetaInfoData;
import com.jifuwei.ac.web.meta.data.ACDbTableMetaInfoData;
import com.jifuwei.ac.web.meta.data.po.ACDbMetaInfoPO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 测试meta信息获取
 * Created by JFW on 2016/10/11.
 */
public class ACDbMetaInfoDaoTest extends BaseTest {

    @Autowired
    private ACDbMetaInfoDao dbMetaInfoDao = null;

    /**
     * 查询数据库元信息-测试
     */
    @Test
    public void findDbMetaInfoTest() {
        ACDbMetaInfoPO metaInfoPO = dbMetaInfoDao.findDbMetaInfo();
        System.out.println(JSON.toJSONString(metaInfoPO));
    }

    /**
     * 查询当前连接数据库表信息-测试
     */
    @Test
    public void findDbTablesMetaInfoTest() {
        String[] types = {"TABLE"};
        List<ACDbTableMetaInfoData> dbTableMetaInfoList = dbMetaInfoDao.findDbTablesMetaInfo("wxs", null, "%", types);
        System.out.println(JSON.toJSONString(dbTableMetaInfoList));
    }

    /**
     * 查询指定表列信息-测试
     */
    @Test
    public void findDbColumnsMetaInfoTest() {
        List<ACDbColumnMetaInfoData> dbColumnMetaInfoList = dbMetaInfoDao.findDbColumnsMetaInfo(null, null, "ac_valid_test", "%");
        System.out.println(JSON.toJSONString(dbColumnMetaInfoList));
    }

    /**
     * 查询指定表主键信息-测试
     */
    @Test
    public void findDbPrimaryKeysMetaInfoTest() {
        List<ACDbPrimaryKeyMetaInfoData> dbPrimaryKeyMetaInfoList = dbMetaInfoDao.findDbPrimaryKeysMetaInfo(null, null, "ac_valid_test");
        System.out.println(JSON.toJSONString(dbPrimaryKeyMetaInfoList));
    }

    /**
     * 查询指定表外键信息-测试
     */
    @Test
    public void findDbExportedKeysMetaInfoTest() {
        List<ACDbExportedKeyMetaInfoData> dbExportedKeyMetaInfoList = dbMetaInfoDao.findDbExportedKeysMetaInfo(null, null, "ac_valid_test");
        System.out.println(JSON.toJSONString(dbExportedKeyMetaInfoList));
    }

    /**
     * 查询导入脚本结构信息-测试
     */
    @Test
    public void findProjectModuleInfoTest() {
        List<ACDbColumnMetaInfoData> dbColumnMetaInfoList = null;
        List<ACDbPrimaryKeyMetaInfoData> dbPrimaryKeyMetaInfoList = null;
        List<ACDbExportedKeyMetaInfoData> dbExportedKeyMetaInfoList = null;
        String[] types = {"TABLE"};
        List<ACDbTableMetaInfoData> dbTableMetaInfoList = dbMetaInfoDao.findDbTablesMetaInfo("autocoding", null, "%", types);
        for (ACDbTableMetaInfoData table : dbTableMetaInfoList) {
            dbColumnMetaInfoList = dbMetaInfoDao.findDbColumnsMetaInfo("autocoding", null, table.getTableName(), "%");
            dbPrimaryKeyMetaInfoList = dbMetaInfoDao.findDbPrimaryKeysMetaInfo("autocoding", null, table.getTableName());
            dbExportedKeyMetaInfoList = dbMetaInfoDao.findDbExportedKeysMetaInfo("autocoding", null, table.getTableName());
            table.setColumnMetaInfoList(dbColumnMetaInfoList);
            table.setPrimaryKeyMetaInfoList(dbPrimaryKeyMetaInfoList);
            table.setExportedKeyMetaInfoList(dbExportedKeyMetaInfoList);
        }

        System.out.println(JSON.toJSONString(dbTableMetaInfoList));
    }
}
