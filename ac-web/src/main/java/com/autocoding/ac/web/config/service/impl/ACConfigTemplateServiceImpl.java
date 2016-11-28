package com.autocoding.ac.web.config.service.impl;

import com.autocoding.ac.foundation.error.ACErrorMsg;
import com.autocoding.ac.foundation.exception.ACServiceException;
import com.autocoding.ac.web.config.dao.ACConfigTemplateDao;
import com.autocoding.ac.web.config.data.po.ACConfigTemplatePO;
import com.autocoding.ac.web.config.data.vo.ACConfigTemplateVO;
import com.autocoding.ac.web.config.service.ACConfigTemplateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 模板信息实现类
 * Created by JFW on 2016/11/2.
 */
@Service("ACConfigTemplateServiceImpl")
public class ACConfigTemplateServiceImpl implements ACConfigTemplateService {

    @Resource(name = "ACConfigTemplateDaoImpl")
    private ACConfigTemplateDao dataDao = null;

    @Override
    public void save(ACConfigTemplateVO vo) {
        ACConfigTemplatePO po = dataDao.getSingle(vo.getPrimaryKeys());
        if (po != null) {
            throw new ACServiceException(ACErrorMsg.ERROR_DUMPLICATE_PRIMARY_KEY);
        }
        po = vo.toPO();
        po.setCreate_by("sys"); //TODO:加入系统登录功能后需转为登录人员信息
        po.setCreate_time(new Timestamp(new Date().getTime()));
        dataDao.save(po);
    }
}
