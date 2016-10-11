package com.jifuwei.ac.web.meta;

import com.alibaba.fastjson.JSON;
import com.jifuwei.ac.web.base.BaseTest;
import com.jifuwei.ac.web.meta.dao.ACDbMetaInfoDao;
import com.jifuwei.ac.web.meta.data.po.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 测试meta信息获取
 * Created by JFW on 2016/10/11.
 */
public class ACDbMetaInfoServiceTest extends BaseTest {

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
        String[] types = { "TABLE" };
        List<ACDbTableMetaInfoPO> dbTableMetaInfoList = dbMetaInfoDao.findDbTablesMetaInfo(null, null, "%", types);
        System.out.println(JSON.toJSONString(dbTableMetaInfoList));
    }

    /**
     * 查询指定表列信息-测试
     */
    @Test
    public void findDbColumnsMetaInfoTest() {
        List<ACDbColumnMetaInfoPO> dbColumnMetaInfoList = dbMetaInfoDao.findDbColumnsMetaInfo(null, null, "ac_valid_test","%");
        System.out.println(JSON.toJSONString(dbColumnMetaInfoList));
    }

    /**
     * 查询指定表主键信息-测试
     */
    @Test
    public void findDbPrimaryKeysMetaInfoTest() {
        List<ACDbPrimaryKeyMetaInfoPO> dbPrimaryKeyMetaInfoList = dbMetaInfoDao.findDbPrimaryKeysMetaInfo(null, null, "ac_valid_test");
        System.out.println(JSON.toJSONString(dbPrimaryKeyMetaInfoList));
    }

    /**
     * 查询指定表外键信息-测试
     */
    @Test
    public void findDbExportedKeysMetaInfoTest() {
        List<ACDbExportedKeyMetaInfoPO> dbPrimaryKeyMetaInfoList = dbMetaInfoDao.findDbExportedKeysMetaInfo(null, null, "ac_valid_test");
        System.out.println(JSON.toJSONString(dbPrimaryKeyMetaInfoList));
    }
}
