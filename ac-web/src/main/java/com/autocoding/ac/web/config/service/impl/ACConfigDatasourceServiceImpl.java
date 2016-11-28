package com.autocoding.ac.web.config.service.impl;

import com.autocoding.ac.foundation.error.ACErrorMsg;
import com.autocoding.ac.foundation.exception.ACServiceException;
import com.autocoding.ac.web.config.dao.ACConfigDatasourceDao;
import com.autocoding.ac.web.config.data.po.ACConfigDatasourcePO;
import com.autocoding.ac.web.config.data.vo.ACConfigDatasourceVO;
import com.autocoding.ac.web.config.service.ACConfigDatasourceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 模板文件信息实现类
 * Created by JFW on 2016/11/2.
 */
@Service("ACConfigDatasourceServiceImpl")
public class ACConfigDatasourceServiceImpl implements ACConfigDatasourceService {

    @Resource(name = "ACConfigDatasourceDaoImpl")
    private ACConfigDatasourceDao dataDao = null;

    @Override
    public void save(ACConfigDatasourceVO vo) {
        ACConfigDatasourcePO po = dataDao.getSingle(vo.getPrimaryKeys());
        if (po != null) {
            throw new ACServiceException(ACErrorMsg.ERROR_DUMPLICATE_PRIMARY_KEY);
        }
        po = vo.toPO();
        po.setCreate_by("sys");
        po.setCreate_time(new Timestamp(new Date().getTime()));
        dataDao.save(po);
    }
}
