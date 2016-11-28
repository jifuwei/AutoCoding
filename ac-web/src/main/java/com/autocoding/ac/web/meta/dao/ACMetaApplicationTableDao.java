package com.autocoding.ac.web.meta.dao;

import com.autocoding.ac.foundation.dao.IDao;
import com.autocoding.ac.web.meta.data.po.ACMetaApplicationTablePO;

import java.util.List;

/**
 * 应用表信息Dao
 * Created by JFW on 2016/11/2.
 */
public interface ACMetaApplicationTableDao extends IDao<ACMetaApplicationTablePO> {
    List<ACMetaApplicationTablePO> getMeatApplicationTableDataByAppId(Integer app_id);
}
