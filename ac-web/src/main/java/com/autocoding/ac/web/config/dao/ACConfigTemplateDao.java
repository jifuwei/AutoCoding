package com.autocoding.ac.web.config.dao;

import com.autocoding.ac.foundation.dao.IDao;
import com.autocoding.ac.web.config.data.po.ACConfigTemplatePO;

import java.util.List;

/**
 * 模板信息Dao
 * Created by JFW on 2016/11/2.
 */
public interface ACConfigTemplateDao extends IDao<ACConfigTemplatePO> {
    List<ACConfigTemplatePO> getConfigTemplateDataByPackageId(Integer package_id);
}
