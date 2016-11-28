package com.autocoding.ac.web.meta.service;

import com.autocoding.ac.web.meta.data.vo.ACMetaApplicationVO;

/**
 * 应用信息Service
 * Created by JFW on 2016/11/2.
 */
public interface ACMetaApplicationService {
    void save(ACMetaApplicationVO vo);

    void initAppMetaInfoFromDbScript(ACMetaApplicationVO vo);

    void generateAppTemplateFilesFromMetaInfo(ACMetaApplicationVO vo);
}
