package com.autocoding.ac.web.valid.dao.impl;

import com.autocoding.ac.foundation.dao.IDaoImpl;
import com.autocoding.ac.web.valid.dao.ACValidTestDao;
import com.autocoding.ac.web.valid.data.po.ACValidTestPO;
import org.springframework.stereotype.Repository;

/**
 * 测试dao接口实现类
 * Created by JFW on 2016/10/7.
 */
@Repository("ACValidTestDaoImpl")
public class ACValidTestDaoImpl extends IDaoImpl<ACValidTestPO> implements ACValidTestDao {

    public ACValidTestDaoImpl() {
        super();
        this.tableName = "ac_valid_test";
        this.tableKeys = new String[] {"ac_id"};
    }

    @Override
    protected String generateWhere(Object[] paramArrayOfObject) {
        return null;
    }
}
