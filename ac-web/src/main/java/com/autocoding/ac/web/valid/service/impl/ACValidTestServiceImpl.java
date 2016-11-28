package com.autocoding.ac.web.valid.service.impl;

import com.autocoding.ac.foundation.error.ACErrorMsg;
import com.autocoding.ac.foundation.exception.ACServiceException;
import com.autocoding.ac.web.valid.dao.ACValidTestDao;
import com.autocoding.ac.web.valid.data.po.ACValidTestPO;
import com.autocoding.ac.web.valid.data.vo.ACValidTestVO;
import com.autocoding.ac.web.valid.service.ACValidTestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 测试test接口实现类
 * Created by JFW on 2016/10/7.
 */
@Service("ACValidTestServiceImpl")
public class ACValidTestServiceImpl implements ACValidTestService {

    @Resource(name = "ACValidTestDaoImpl")
    private ACValidTestDao dataDao = null;

    @Override
    public void save(ACValidTestVO vo) {
        ACValidTestPO po = dataDao.getSingle(vo.getPrimaryKeys());
        if (po != null) {
            throw new ACServiceException(ACErrorMsg.ERROR_DUMPLICATE_PRIMARY_KEY);
        }
        po = vo.toPO();
        dataDao.save(po);
    }

    @Override
    public void delete(ACValidTestVO vo) {
        dataDao.delete(vo.getPrimaryKeys());
    }

    @Override
    public void update(ACValidTestVO vo) {
        ACValidTestPO po = dataDao.getSingle(vo.getPrimaryKeys());
        if (po == null) {
            throw new ACServiceException(ACErrorMsg.ERROR_RECORD_NOT_EXISTS);
        }
        po = vo.toPO();
        dataDao.update(po);
    }

    @Override
    public ACValidTestVO find(ACValidTestVO vo) {
        ACValidTestPO po = dataDao.getSingle(vo.getPrimaryKeys());
        if (po == null) {
            throw new ACServiceException(ACErrorMsg.ERROR_RECORD_NOT_EXISTS);
        }
        return po.toVO();
    }
}
