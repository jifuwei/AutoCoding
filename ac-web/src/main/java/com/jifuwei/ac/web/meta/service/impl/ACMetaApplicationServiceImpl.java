package com.jifuwei.ac.web.meta.service.impl;

import com.jifuwei.ac.foundation.error.ACErrorMsg;
import com.jifuwei.ac.foundation.exception.ACServiceException;
import com.jifuwei.ac.web.common.error.ACWebErrorMsg;
import com.jifuwei.ac.web.config.dao.ACConfigDatasourceDao;
import com.jifuwei.ac.web.config.data.ACConfigDatasourceData;
import com.jifuwei.ac.web.meta.dao.ACMetaApplicationDao;
import com.jifuwei.ac.web.meta.data.po.ACMetaApplicationPO;
import com.jifuwei.ac.web.meta.data.vo.ACMetaApplicationVO;
import com.jifuwei.ac.web.meta.service.ACMetaApplicationService;
import com.jifuwei.ac.web.meta.util.AntDbUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 应用信息逻辑层实现类
 * Created by JFW on 2016/11/2.
 */
@Service("ACMetaApplicationServiceImpl")
public class ACMetaApplicationServiceImpl implements ACMetaApplicationService {
    private static final Logger logger = Logger.getLogger(ACMetaApplicationServiceImpl.class);

    @Resource(name = "ACMetaApplicationDaoImpl")
    private ACMetaApplicationDao dataDao = null;

    @Resource(name = "ACConfigDatasourceDaoImpl")
    private ACConfigDatasourceDao datasourceDao = null;

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

    /**
     * 初始化meta信息流程：
     * 1.依据脚本文件和数据源配置创建数据库
     * 2.依据创建的数据库拉取应用的表信息
     * 3.依据拉取的表信息读取模块等信息
     *
     * @param vo
     */
    @Override
    public void initAppMetaInfoFromDbScript(ACMetaApplicationVO vo) {
        //1.依据脚本文件和数据源配置创建数据库
        String appDbScript = vo.getApp_db_script();
        ACConfigDatasourceData datasourceData = datasourceDao.findDatasourceAndDatabaseInfoByDatasourceId(vo.getDatasource_id());
        initAppDatabase(datasourceData);
    }

    private void initAppDatabase(ACConfigDatasourceData datasourceData) {
        try {
            //建库
            if (dataDao.isExistDataBase(datasourceData.getDb_name())) {
                throw new ACServiceException(ACWebErrorMsg.ERROR_DATABASE_IS_EXIST);
            }
            dataDao.createDatabase(datasourceData.getDb_name());

            //执行脚本
            String driver = datasourceData.getDatabase_driver();
            //TODO:配置文件读取或放入数据库中配置
            String url = "jdbc:mysql://DB_IP:DB_PORT/DB_NAME?useUnicode=true&characterEncoding=utf-8".replace("DB_IP", datasourceData.getDatasource_ip()).replace("DB_PORT", datasourceData.getDatasource_port()).replace("DB_NAME", datasourceData.getDb_name());
            String username = datasourceData.getDb_connect_name();
            String pwd = datasourceData.getDb_connect_pwd();

            AntDbUtil.getInstance(driver, url, username, pwd).excuteSqlScriptFile(new File("F:\\IDEAWorkspace\\autocoding\\ac-web\\src\\test\\resources\\db_script\\ac.sql"));//TODO:文件路径从配置文件中读取
        } catch (Exception e) {
            logger.error(e);
            if (e instanceof ACServiceException) {
                throw (ACServiceException) e;
            }
            throw new ACServiceException(ACWebErrorMsg.ERROR_INIT_APP_DATABASE);
        }
    }
}
