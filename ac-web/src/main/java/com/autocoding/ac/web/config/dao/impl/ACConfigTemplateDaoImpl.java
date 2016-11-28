package com.autocoding.ac.web.config.dao.impl;

import com.autocoding.ac.foundation.dao.IDaoImpl;
import com.autocoding.ac.web.config.dao.ACConfigTemplateDao;
import com.autocoding.ac.web.config.data.po.ACConfigTemplatePO;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public List<ACConfigTemplatePO> getConfigTemplateDataByPackageId(Integer package_id) {
        Object[] args = {package_id};
        String sql = "SELECT t2.* FROM ac_config_package_file t1 LEFT JOIN ac_config_template t2 ON t1.template_id = t2.template_id AND t1.template_version = t2.template_version WHERE t1.package_id = ?";
        return defaultJdbcTemplate.query(sql, args, this.rm);
    }
}
