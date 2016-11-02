package com.jifuwei.ac.web.config.dao.impl;

import com.jifuwei.ac.foundation.dao.IDaoImpl;
import com.jifuwei.ac.web.config.dao.ACConfigTemplateDao;
import com.jifuwei.ac.web.config.data.po.ACConfigTemplatePO;
import org.springframework.stereotype.Repository;

/**
 * 模板信息实现类
 * Created by JFW on 2016/11/2.
 */
@Repository("ACConfigTemplateDaoImpl")
public class ACConfigTemplateDaoImpl extends IDaoImpl<ACConfigTemplatePO> implements ACConfigTemplateDao {

    public ACConfigTemplateDaoImpl() {
        super();
        this.tableName = "ac_config_template";
        this.tableKeys = new String[]{"template_id"};
    }

    @Override
    protected String generateWhere(Object[] paramArrayOfObject) {
        return null;
    }
}
