package com.jifuwei.ac.web.valid.dao.impl;

import com.jifuwei.ac.foundation.dao.IDaoImpl;
import com.jifuwei.ac.web.valid.dao.ACValidTestDao;
import com.jifuwei.ac.web.valid.data.po.ACValidTestPO;
import org.springframework.stereotype.Repository;

/**
 * Created by JFW on 2016/10/7.
 */
@Repository("ACValidTestDaoImpl")
public class ACValidTestDaoImpl extends IDaoImpl<ACValidTestPO> implements ACValidTestDao {

    public ACValidTestDaoImpl() {
        super();
        this.tableName = "ac_valid_test";
        this.tableKeys = new String[] {"username"};
    }

    @Override
    protected String generateWhere(Object[] paramArrayOfObject) {
        return null;
    }
}
