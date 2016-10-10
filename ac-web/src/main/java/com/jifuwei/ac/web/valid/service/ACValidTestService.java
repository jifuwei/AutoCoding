package com.jifuwei.ac.web.valid.service;

import com.jifuwei.ac.web.valid.data.vo.ACValidTestVO;

/**
 * 测试test接口
 * Created by JFW on 2016/10/7.
 */
public interface ACValidTestService {
    void save(ACValidTestVO vo);

    void delete(ACValidTestVO vo);

    void update(ACValidTestVO vo);

    ACValidTestVO find(ACValidTestVO vo);
}
