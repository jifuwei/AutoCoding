package com.jifuwei.ac.web.config.dao.impl;

import com.jifuwei.ac.foundation.dao.IDaoImpl;
import com.jifuwei.ac.web.config.dao.ACConfigPackageFileDao;
import com.jifuwei.ac.web.config.data.po.ACConfigPackageFilePO;
import org.springframework.stereotype.Repository;

/**
 * 模板信息实现类
 * Created by JFW on 2016/11/2.
 */
@Repository("ACConfigPackageFileDaoImpl")
public class ACConfigPackageFileDaoImpl extends IDaoImpl<ACConfigPackageFilePO> implements ACConfigPackageFileDao {

    public ACConfigPackageFileDaoImpl() {
        super();
        this.tableName = "ac_config_package_file";
        this.tableKeys = new String[]{"package_id", "template_id"};
    }

    @Override
    protected String generateWhere(Object[] paramArrayOfObject) {
        return null;
    }
}
