package com.autocoding.ac.web.config.service.impl;

import com.autocoding.ac.foundation.error.ACErrorMsg;
import com.autocoding.ac.foundation.exception.ACServiceException;
import com.autocoding.ac.web.config.dao.ACConfigTemplateFileDao;
import com.autocoding.ac.web.config.data.po.ACConfigTemplateFilePO;
import com.autocoding.ac.web.config.data.vo.ACConfigTemplateFileVO;
import com.autocoding.ac.web.config.service.ACConfigTemplateFileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 模板文件信息实现类
 * Created by JFW on 2016/11/2.
 */
@Service("ACConfigTemplateFileServiceImpl")
public class ACConfigTemplateFileServiceImpl implements ACConfigTemplateFileService {

    @Resource(name = "ACConfigTemplateFileDaoImpl")
    private ACConfigTemplateFileDao dataDao = null;

    @Override
    public void save(ACConfigTemplateFileVO vo) {
        ACConfigTemplateFilePO po = dataDao.getSingle(vo.getPrimaryKeys());
        if (po != null) {
            throw new ACServiceException(ACErrorMsg.ERROR_DUMPLICATE_PRIMARY_KEY);
        }
        po = vo.toPO();
        po.setCreate_by("sys");
        po.setCreate_time(new Timestamp(new Date().getTime()));
        dataDao.save(po);
    }
}
