package com.autocoding.ac.web.config.service.impl;

import com.autocoding.ac.foundation.error.ACErrorMsg;
import com.autocoding.ac.foundation.exception.ACServiceException;
import com.autocoding.ac.web.config.dao.ACConfigTemplatePackageDao;
import com.autocoding.ac.web.config.data.po.ACConfigTemplatePackagePO;
import com.autocoding.ac.web.config.data.vo.ACConfigTemplatePackageVO;
import com.autocoding.ac.web.config.service.ACConfigTemplatePackageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 模板信息实现类
 * Created by JFW on 2016/11/2.
 */
@Service("ACConfigTemplatePackageServiceImpl")
public class ACConfigTemplatePackageServiceImpl implements ACConfigTemplatePackageService {

    @Resource(name = "ACConfigTemplatePackageDaoImpl")
    private ACConfigTemplatePackageDao dataDao = null;

    @Override
    public void save(ACConfigTemplatePackageVO vo) {
        ACConfigTemplatePackagePO po = dataDao.getSingle(vo.getPrimaryKeys());
        if (po != null) {
            throw new ACServiceException(ACErrorMsg.ERROR_DUMPLICATE_PRIMARY_KEY);
        }
        po = vo.toPO();
        po.setCreate_by("sys"); //TODO:加入系统登录功能后需转为登录人员信息
        po.setCreate_time(new Timestamp(new Date().getTime()));
        dataDao.save(po);
    }
}
