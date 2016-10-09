package com.jifuwei.ac.web.valid.service.impl;

import com.jifuwei.ac.web.valid.dao.ACValidTestDao;
import com.jifuwei.ac.web.valid.data.po.ACValidTestPO;
import com.jifuwei.ac.web.valid.data.vo.ACValidTestVO;
import com.jifuwei.ac.web.valid.service.ACValidTestService;
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
    public void add(ACValidTestVO vo) {
        ACValidTestPO po = vo.toPO();
        dataDao.add(po);
    }
}
