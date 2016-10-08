package com.jifuwei.ac.web.valid.service;

import com.jifuwei.ac.web.valid.data.po.ACValidTestPO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 测试test接口
 * Created by JFW on 2016/10/7.
 */
@Service
public interface ACValidTestService {
    List<ACValidTestPO> getAll();
}
