package com.jifuwei.ac.web.meta;

import com.alibaba.fastjson.JSON;
import com.jifuwei.ac.web.base.BaseTest;
import com.jifuwei.ac.web.meta.data.ACDbColumnMetaInfoData;
import com.jifuwei.ac.web.meta.data.ACDbTableMetaInfoData;
import com.jifuwei.ac.web.meta.data.po.ACDbMetaInfoPO;
import com.jifuwei.ac.web.meta.util.DbMetaUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * 测试DbMetaUtil工具类
 * Created by JFW on 2016/11/4.
 */
public class DbMetaUtilTest extends BaseTest {

    private DbMetaUtil dbMetaUtil = null;

    @Before
    public void before() {
        if (dbMetaUtil == null) {
            this.dbMetaUtil = new DbMetaUtil("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/autocoding?characterEncoding=utf8&amp;zeroDateTimeBehavior=convertToNull", "root", "123456");
        }
    }

    @Test
    public void findDbMetaInfoTest() {
        ACDbMetaInfoPO metaInfoPO = this.dbMetaUtil.findDbMetaInfo();
        System.out.println(JSON.toJSONString(metaInfoPO));
    }

    @Test
    public void findDbTablesMetaInfo() {
        List<ACDbTableMetaInfoData> tableMetaInfoDataList = this.dbMetaUtil.findDbTablesMetaInfo("autocoding", null, "%", new String[]{"TABLE"});
        System.out.println(JSON.toJSONString(tableMetaInfoDataList));
    }

    @Test
    public void findDbColumnsMetaInfo() {
        List<ACDbColumnMetaInfoData> columnMetaInfoDataList = this.dbMetaUtil.findDbColumnsMetaInfo("autocoding", null, "ac_valid_test", "%");
        System.out.println(JSON.toJSONString(columnMetaInfoDataList));
    }
}
