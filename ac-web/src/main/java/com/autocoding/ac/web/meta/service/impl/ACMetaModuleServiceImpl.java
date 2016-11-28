package com.autocoding.ac.web.meta.service.impl;

import com.autocoding.ac.foundation.error.ACErrorMsg;
import com.autocoding.ac.foundation.exception.ACServiceException;
import com.autocoding.ac.web.meta.dao.ACMetaModuleDao;
import com.autocoding.ac.web.meta.data.po.ACMetaModulePO;
import com.autocoding.ac.web.meta.data.vo.ACMetaModuleVO;
import com.autocoding.ac.web.meta.service.ACMetaModuleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 应用信息逻辑层实现类
 * Created by JFW on 2016/11/2.
 */
@Service("ACMetaModuleServiceImpl")
public class ACMetaModuleServiceImpl implements ACMetaModuleService {

    @Resource(name = "ACMetaModuleDaoImpl")
    private ACMetaModuleDao dataDao = null;

    @Override
    public void save(ACMetaModuleVO vo) {
        ACMetaModulePO po = dataDao.getSingle(vo.getPrimaryKeys());
        if (po != null) {
            throw new ACServiceException(ACErrorMsg.ERROR_DUMPLICATE_PRIMARY_KEY);
        }
        po = vo.toPO();
        po.setCreate_by("sys");
        po.setCreate_time(new Timestamp(new Date().getTime()));
        dataDao.save(po);
    }
}
