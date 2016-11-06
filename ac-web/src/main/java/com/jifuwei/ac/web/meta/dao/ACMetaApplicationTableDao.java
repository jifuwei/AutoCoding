package com.jifuwei.ac.web.meta.dao;

import com.jifuwei.ac.foundation.dao.IDao;
import com.jifuwei.ac.web.meta.data.po.ACMetaApplicationTablePO;

import java.util.List;

/**
 * 应用表信息Dao
 * Created by JFW on 2016/11/2.
 */
public interface ACMetaApplicationTableDao extends IDao<ACMetaApplicationTablePO> {
    List<ACMetaApplicationTablePO> getMeatApplicationTableDataByAppId(Integer app_id);
}
