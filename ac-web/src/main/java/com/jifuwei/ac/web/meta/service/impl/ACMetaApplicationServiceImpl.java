package com.jifuwei.ac.web.meta.service.impl;

import com.jifuwei.ac.foundation.error.ACErrorMsg;
import com.jifuwei.ac.foundation.exception.ACServiceException;
import com.jifuwei.ac.web.meta.dao.ACMetaApplicationDao;
import com.jifuwei.ac.web.meta.data.po.ACMetaApplicationPO;
import com.jifuwei.ac.web.meta.data.vo.ACMetaApplicationVO;
import com.jifuwei.ac.web.meta.service.ACMetaApplicationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 应用信息逻辑层实现类
 * Created by JFW on 2016/11/2.
 */
@Service("ACMetaApplicationServiceImpl")
public class ACMetaApplicationServiceImpl implements ACMetaApplicationService {

    @Resource(name = "ACMetaApplicationDaoImpl")
    private ACMetaApplicationDao dataDao = null;

    @Override
    public void save(ACMetaApplicationVO vo) {
        ACMetaApplicationPO po = dataDao.getSingle(vo.getPrimaryKeys());
        if (po != null) {
            throw new ACServiceException(ACErrorMsg.ERROR_DUMPLICATE_PRIMARY_KEY);
        }
        po = vo.toPO();
        po.setCreate_by("sys");
        po.setCreate_time(new Timestamp(new Date().getTime()));
        dataDao.save(po);
    }
}
