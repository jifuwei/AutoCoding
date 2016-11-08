package com.jifuwei.ac.web.meta.service.impl;

import com.jifuwei.ac.foundation.error.ACErrorMsg;
import com.jifuwei.ac.foundation.exception.ACServiceException;
import com.jifuwei.ac.util.string.CamelCaseUtil;
import com.jifuwei.ac.web.common.error.ACWebErrorMsg;
import com.jifuwei.ac.web.config.dao.ACConfigDatasourceDao;
import com.jifuwei.ac.web.config.dao.ACConfigTemplateDao;
import com.jifuwei.ac.web.config.data.ACConfigDatasourceData;
import com.jifuwei.ac.web.config.data.po.ACConfigTemplatePO;
import com.jifuwei.ac.web.freemark.method.DbColumn2JavaBeanTMM;
import com.jifuwei.ac.web.freemark.method.DbColumnDataTypeTMM;
import com.jifuwei.ac.web.freemark.method.DbColumnPo2VoTMM;
import com.jifuwei.ac.web.freemark.method.DbColumnVo2PoTMM;
import com.jifuwei.ac.web.meta.dao.ACDbMetaInfoDao;
import com.jifuwei.ac.web.meta.dao.ACMetaApplicationDao;
import com.jifuwei.ac.web.meta.dao.ACMetaApplicationTableDao;
import com.jifuwei.ac.web.meta.dao.ACMetaModuleDao;
import com.jifuwei.ac.web.meta.data.ACDbColumnMetaInfoData;
import com.jifuwei.ac.web.meta.data.ACDbExportedKeyMetaInfoData;
import com.jifuwei.ac.web.meta.data.ACDbPrimaryKeyMetaInfoData;
import com.jifuwei.ac.web.meta.data.ACDbTableMetaInfoData;
import com.jifuwei.ac.web.meta.data.po.ACMetaApplicationPO;
import com.jifuwei.ac.web.meta.data.po.ACMetaApplicationTablePO;
import com.jifuwei.ac.web.meta.data.po.ACMetaModulePO;
import com.jifuwei.ac.web.meta.data.vo.ACMetaApplicationVO;
import com.jifuwei.ac.web.meta.service.ACMetaApplicationService;
import com.jifuwei.ac.web.meta.util.AntDbUtil;
import com.jifuwei.ac.web.meta.util.DbMetaUtil;
import freemarker.template.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.sql.Timestamp;
import java.util.*;

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

    @Resource(name = "ACDbMetaInfoDaoImpl")
    private ACDbMetaInfoDao metaInfoDao = null;

    @Resource(name = "ACMetaApplicationTableDaoImpl")
    private ACMetaApplicationTableDao metaApplicationTableDao = null;

    @Resource(name = "ACMetaModuleDaoImpl")
    private ACMetaModuleDao metaModuleDao = null;

    @Resource(name = "ACConfigTemplateDaoImpl")
    private ACConfigTemplateDao configTemplateDao = null;

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
        ACMetaApplicationPO po = dataDao.getSingle(vo.getPrimaryKeys());
        if (po == null) {
            throw new ACServiceException(ACWebErrorMsg.ERROR_APP_DB_NOT_EXIST);
        }
        ACConfigDatasourceData datasourceData = datasourceDao.findDatasourceAndDatabaseInfoByDatasourceId(po.getDatasource_id());
        DbMetaUtil dbMetaUtil = getDbMetaUtil(datasourceData);

        initAppDatabase(datasourceData, dbMetaUtil);//依据脚本文件和数据源配置创建数据库
        List<ACMetaApplicationTablePO> metaApplicationTablePOList = initAppDbTables(po, datasourceData, dbMetaUtil);//依据创建的数据库拉取应用的表信息
        initAppModules(po, metaApplicationTablePOList);
    }

    /**
     * 获取数据库连接
     * @param datasourceData
     * @return
     */
    private DbMetaUtil getDbMetaUtil(ACConfigDatasourceData datasourceData) {
        //TODO:配置文件读取或放入数据库中配置
        String driverClass = datasourceData.getDatabase_driver();
        String url = "jdbc:mysql://DB_IP:DB_PORT/mysql?useUnicode=true&characterEncoding=utf-8".replace("DB_IP", datasourceData.getDatasource_ip()).replace("DB_PORT", datasourceData.getDatasource_port());
        String username = datasourceData.getDb_connect_name();
        String pwd = datasourceData.getDb_connect_pwd();
        return new DbMetaUtil(driverClass, url, username, pwd);
    }

    @Override
    public void generateAppTemplateFilesFromMetaInfo(ACMetaApplicationVO vo) {
        ACMetaApplicationPO metaApplicationPO = dataDao.getSingle(vo.getPrimaryKeys());
        if (metaApplicationPO == null) {
            throw new ACServiceException(ACWebErrorMsg.ERROR_APP_DB_NOT_EXIST);
        }
        ACConfigDatasourceData datasourceData = datasourceDao.findDatasourceAndDatabaseInfoByDatasourceId(metaApplicationPO.getDatasource_id());
        DbMetaUtil dbMetaUtil = getDbMetaUtil(datasourceData);

        List<ACConfigTemplatePO> configTemplateDataList = configTemplateDao.getConfigTemplateDataByPackageId(metaApplicationPO.getPackage_id());
        List<ACDbTableMetaInfoData> tableMetaInfoDataList= findProjectModuleInfo(datasourceData, dbMetaUtil);
        generateAppTemplateFiles(metaApplicationPO, configTemplateDataList, tableMetaInfoDataList);
    }

    /**
     * 查询数据库元数据信息
     * @param datasourceData
     * @param dbMetaUtil
     * @return
     */
    private List<ACDbTableMetaInfoData> findProjectModuleInfo(ACConfigDatasourceData datasourceData, DbMetaUtil dbMetaUtil) {
        List<ACDbColumnMetaInfoData> dbColumnMetaInfoList = null;
        List<ACDbPrimaryKeyMetaInfoData> dbPrimaryKeyMetaInfoList = null;
        List<ACDbExportedKeyMetaInfoData> dbExportedKeyMetaInfoList = null;
        List<ACDbTableMetaInfoData> dbTableMetaInfoList = dbMetaUtil.findDbTablesMetaInfo(datasourceData.getDb_name(), null, "%", new String[]{"TABLE"});
        for (ACDbTableMetaInfoData table : dbTableMetaInfoList) {
            dbColumnMetaInfoList = dbMetaUtil.findDbColumnsMetaInfo(datasourceData.getDb_name(), null, table.getTableName(), "%");
            dbPrimaryKeyMetaInfoList = dbMetaUtil.findDbPrimaryKeysMetaInfo(datasourceData.getDb_name(), null, table.getTableName());
            dbExportedKeyMetaInfoList = dbMetaUtil.findDbExportedKeysMetaInfo(datasourceData.getDb_name(), null, table.getTableName());
            table.setColumnMetaInfoList(dbColumnMetaInfoList);
            table.setPrimaryKeyMetaInfoList(dbPrimaryKeyMetaInfoList);
            table.setExportedKeyMetaInfoList(dbExportedKeyMetaInfoList);

            table.setModuleName(getModuleName(table.getTableName()));
            table.setBusinessTablename(getBusinessTableName(table.getTableName()));
            table.setModuleAndBusinessTableName(getModuleAndBusinessTableName(table.getTableName()));
        }

        return dbTableMetaInfoList;
    }

    private String getModuleAndBusinessTableName(String tableName) {
        return CamelCaseUtil.toCamelCase(tableName.substring(tableName.indexOf("_"), tableName.length()));
    }

    private String getBusinessTableName(String tableName) {
        String str = getModuleAndBusinessTableName(tableName);
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    private String getModuleName(String tableName) {
        return tableName.split("_")[1];
    }

    /**
     * 生成模板文件
     * @param metaApplicationTablePO
     * @param configTemplateDataList
     * @param metaApplicationTablePOList
     */
    private void generateAppTemplateFiles(ACMetaApplicationPO metaApplicationPO, List<ACConfigTemplatePO> configTemplateDataList, List<ACDbTableMetaInfoData> tableMetaInfoDataList) {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_25);
        try {
            cfg.setDirectoryForTemplateLoading(new File("F:\\IDEAWorkspace\\autocoding\\ac-web\\src\\test\\resources\\templates\\"));//TODO:模板文件路径从配置文件读取
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);

            //设置全局共享资源
            String projectName = metaApplicationPO.getApp_en_name();
            String domainName = metaApplicationPO.getGroup_id();
            String projectNameUpperCase = metaApplicationPO.getApp_en_name().toUpperCase();
            cfg.setSharedVariable("projectName", projectName);
            cfg.setSharedVariable("domainName", domainName);
            cfg.setSharedVariable("projectNameUpperCase", projectNameUpperCase);
            cfg.setSharedVariable("dbColumn2JavaBeanTMM", new DbColumn2JavaBeanTMM());
            cfg.setSharedVariable("dbColumnVo2PoTMM", new DbColumnVo2PoTMM());
            cfg.setSharedVariable("dbColumnDataTypeTMM", new DbColumnDataTypeTMM());
            cfg.setSharedVariable("dbColumnPo2VoTMM", new DbColumnPo2VoTMM());

            Template template = null;
            Map<String, Object> root = null;
            OutputStreamWriter out = null;
            String appFileSavePath = "G:/ac";//TODO:配置文件配置路径
            String fileName = null;
            String directoryPath = null;
            File file = null;
            for (ACDbTableMetaInfoData table : tableMetaInfoDataList) {
                root = new HashMap<>();
                root.put("tableInfo", table);
                for (ACConfigTemplatePO temp : configTemplateDataList) {
                    template = cfg.getTemplate(temp.getTemplate_file_name());
                    fileName = temp.getProcess_file_name().replace("${}", table.getJavaFileName());
                    directoryPath = appFileSavePath + temp.getProcess_path();
                    file = new File(directoryPath);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    out = new OutputStreamWriter(new FileOutputStream(directoryPath + fileName),"UTF-8");//输出中文编码设置
                    template.process(root, out);
                }
            }

            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateModelException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    private void initAppModules(ACMetaApplicationPO po, List<ACMetaApplicationTablePO> metaApplicationTablePOList) {
        if (metaApplicationTablePOList == null || metaApplicationTablePOList.size() == 0) {
            return;
        }

        Set<String> moduleInfoSet = new HashSet<>();
        String moduleName = null;
        String moduleRemarks = null;
        for (ACMetaApplicationTablePO metaApplicationTablePO : metaApplicationTablePOList) {
            moduleName = metaApplicationTablePO.getApp_table_name().split("_")[1];//遵循表命名规则
            moduleRemarks = metaApplicationTablePO.getApp_table_remarks().split("/")[0];
            moduleInfoSet.add(moduleName + "," + moduleRemarks);
        }

        List<ACMetaModulePO> metaModulePOList = new ArrayList<>();
        ACMetaModulePO metaModulePO = null;
        Timestamp now = new Timestamp(new Date().getTime());
        for (String moduleInfo : moduleInfoSet) {
            metaModulePO = new ACMetaModulePO();
            moduleName = moduleInfo.split(",")[0];
            moduleRemarks = moduleInfo.split(",")[1];
            metaModulePO.setApp_id(po.getApp_id());
            metaModulePO.setApp_module_name(moduleName);
            metaModulePO.setApp_module_remark(moduleRemarks);
            metaModulePO.setCreate_time(now);
            metaModulePO.setCreate_by("sys");//TODO:写入登录用户

            metaModulePOList.add(metaModulePO);
        }

        if (metaModulePOList != null) {
            metaModuleDao.batchAdd(metaModulePOList);
        }
    }

    /**
     * 初始化应用表信息
     * @param po
     * @param datasourceData
     * @param dbMetaUtil
     * @return
     */
    private List<ACMetaApplicationTablePO> initAppDbTables(ACMetaApplicationPO po, ACConfigDatasourceData datasourceData, DbMetaUtil dbMetaUtil) {
        List<ACDbTableMetaInfoData> tableMetaInfoDataList = dbMetaUtil.findDbTablesMetaInfo(datasourceData.getDb_name(), null, "%", new String[]{"TABLE"});
        List<ACMetaApplicationTablePO> metaApplicationTablePOList = new ArrayList<>();
        ACMetaApplicationTablePO metaApplicationTablePO = null;
        Timestamp now = new Timestamp(new Date().getTime());
        for (ACDbTableMetaInfoData tableMetaInfoData : tableMetaInfoDataList) {
            metaApplicationTablePO = new ACMetaApplicationTablePO();
            metaApplicationTablePO.setApp_id(po.getApp_id());
            metaApplicationTablePO.setApp_table_name(tableMetaInfoData.getTableName());
            metaApplicationTablePO.setApp_table_remarks(tableMetaInfoData.getRemarks());
            metaApplicationTablePO.setCreate_time(now);
            metaApplicationTablePO.setCreate_by("sys");//TODO:写入登录用户

            metaApplicationTablePOList.add(metaApplicationTablePO);
        }

        if (metaApplicationTablePOList.size() > 0) {
            metaApplicationTableDao.batchAdd(metaApplicationTablePOList);
            return metaApplicationTablePOList;
        }
        return null;
    }

    /**
     * 初始化应用脚本
     * @param datasourceData
     * @param dbMetaUtil
     */
    private void initAppDatabase(ACConfigDatasourceData datasourceData, DbMetaUtil dbMetaUtil) {
        try {
            //建库
            if (dbMetaUtil.isExistDataBase(datasourceData.getDb_name())) {
                throw new ACServiceException(ACWebErrorMsg.ERROR_DATABASE_IS_EXIST);
            }
            dbMetaUtil.createDatabase(datasourceData.getDb_name());

            //执行脚本
            String driver = datasourceData.getDatabase_driver();
            //TODO:配置文件读取或放入数据库中配置
            String url = "jdbc:mysql://DB_IP:DB_PORT/DB_NAME?useUnicode=true&characterEncoding=utf-8".replace("DB_IP", datasourceData.getDatasource_ip()).replace("DB_PORT", datasourceData.getDatasource_port()).replace("DB_NAME", datasourceData.getDb_name());
            String username = datasourceData.getDb_connect_name();
            String pwd = datasourceData.getDb_connect_pwd();

            AntDbUtil.excuteSqlScriptFile(driver, url, username, pwd, new File("F:\\IDEAWorkspace\\autocoding\\ac-web\\src\\test\\resources\\db_script\\ac.sql"));//TODO:文件路径从配置文件中读取
        } catch (Exception e) {
            logger.error(e);
            if (e instanceof ACServiceException) {
                throw (ACServiceException) e;
            }
            throw new ACServiceException(ACWebErrorMsg.ERROR_INIT_APP_DATABASE);
        }
    }
}
