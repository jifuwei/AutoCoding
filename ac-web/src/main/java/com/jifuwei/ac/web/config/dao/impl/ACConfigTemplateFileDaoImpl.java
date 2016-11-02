package com.jifuwei.ac.web.config.dao.impl;

import com.jifuwei.ac.foundation.dao.IDaoImpl;
import com.jifuwei.ac.web.config.dao.ACConfigTemplateFileDao;
import com.jifuwei.ac.web.config.data.po.ACConfigTemplateFilePO;
import org.springframework.stereotype.Repository;

/**
 * 模板文件信息实现类
 * Created by JFW on 2016/11/2.
 */
@Repository("ACConfigTemplateFileDaoImpl")
public class ACConfigTemplateFileDaoImpl extends IDaoImpl<ACConfigTemplateFilePO> implements ACConfigTemplateFileDao {

    public ACConfigTemplateFileDaoImpl() {
        super();
        this.tableName = "ac_config_template_file";
        this.tableKeys = new String[]{"template_file_id"};
    }

    @Override
    protected String generateWhere(Object[] paramArrayOfObject) {
        return null;
    }
}
