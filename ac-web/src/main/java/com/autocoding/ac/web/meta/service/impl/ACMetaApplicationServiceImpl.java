package com.autocoding.ac.web.meta.service.impl;

import com.autocoding.ac.foundation.error.ACErrorMsg;
import com.autocoding.ac.foundation.exception.ACServiceException;
import com.autocoding.ac.util.file.PropertiesUtil;
import com.autocoding.ac.util.string.CamelCaseUtil;
import com.autocoding.ac.web.common.error.ACWebErrorMsg;
import com.autocoding.ac.web.config.dao.ACConfigDatasourceDao;
import com.autocoding.ac.web.config.dao.ACConfigTemplateDao;
import com.autocoding.ac.web.config.data.ACConfigDatasourceData;
import com.autocoding.ac.web.config.data.po.ACConfigTemplatePO;
import com.autocoding.ac.web.freemark.method.DbColumn2JavaBeanTMM;
import com.autocoding.ac.web.freemark.method.DbColumnDataTypeTMM;
import com.autocoding.ac.web.freemark.method.DbColumnPo2VoTMM;
import com.autocoding.ac.web.freemark.method.DbColumnVo2PoTMM;
import com.autocoding.ac.web.meta.dao.ACDbMetaInfoDao;
import com.autocoding.ac.web.meta.dao.ACMetaApplicationDao;
import com.autocoding.ac.web.meta.dao.ACMetaApplicationTableDao;
import com.autocoding.ac.web.meta.dao.ACMetaModuleDao;
import com.autocoding.ac.web.meta.data.ACDbColumnMetaInfoData;
import com.autocoding.ac.web.meta.data.ACDbExportedKeyMetaInfoData;
import com.autocoding.ac.web.meta.data.ACDbPrimaryKeyMetaInfoData;
import com.autocoding.ac.web.meta.data.ACDbTableMetaInfoData;
import com.autocoding.ac.web.meta.data.po.ACMetaApplicationPO;
import com.autocoding.ac.web.meta.data.po.ACMetaApplicationTablePO;
import com.autocoding.ac.web.meta.data.po.ACMetaModulePO;
import com.autocoding.ac.web.meta.data.vo.ACMetaApplicationVO;
import com.autocoding.ac.web.meta.service.ACMetaApplicationService;
import com.autocoding.ac.web.meta.util.AntDbUtil;
import com.autocoding.ac.web.meta.util.DbMetaUtil;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.core.ParseException;
import freemarker.template.*;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Timestamp;
import java.util.*;

/**
 * 应用信息逻辑层实现类
 * Created by JFW on 2016/11/2.
 */
@Service("ACMetaApplicationServiceImpl")
public class ACMetaApplicationServiceImpl implements ACMetaApplicationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ACMetaApplicationServiceImpl.class);

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
     *
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
        List<ACDbTableMetaInfoData> tableMetaInfoDataList = findProjectModuleInfo(datasourceData, dbMetaUtil);
        generateAppTemplateFiles(metaApplicationPO, configTemplateDataList, tableMetaInfoDataList);
    }

    /**
     * 查询数据库元数据信息
     *
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
     *
     * @param metaApplicationTablePO
     * @param configTemplateDataList
     * @param metaApplicationTablePOList
     */
    private void generateAppTemplateFiles(ACMetaApplicationPO metaApplicationPO, List<ACConfigTemplatePO> configTemplateDataList, List<ACDbTableMetaInfoData> tableMetaInfoDataList) {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_25);
        Properties prop = null;
        try {
            prop = PropertiesUtil.getProperties("config.properties");

            initFreemarkConfig(cfg, prop, metaApplicationPO);//初始化模板配置
            generateProjectBaseFiles(cfg, prop, metaApplicationPO);//渲染生成项目基础文件
            generateAppJavaFiles(cfg, configTemplateDataList, tableMetaInfoDataList, metaApplicationPO);//渲染生成java类文件
        } catch (IOException e) {
            LOGGER.error("found error", e);
            throw new ACServiceException(ACWebErrorMsg.ERROR_TEMPLATE_FILE);
        }
    }

    private void initFreemarkConfig(Configuration cfg, Properties prop, ACMetaApplicationPO metaApplicationPO) {
        try {
            // 依据配置文件加载相应的模板文件
            String projectType = (String) prop.get("app.config.project");
            FileTemplateLoader projectFTL = new FileTemplateLoader(new File("F:/IDEAWorkspace/autocoding/ac-web/src/main/resources/project-templates/" + projectType + "/temps/"));//TODO:加入配置文件内
            FileTemplateLoader javaFTL = new FileTemplateLoader(new File("F:/IDEAWorkspace/autocoding/ac-web/src/test/resources/templates/"));
            TemplateLoader[] templateLoaders = new TemplateLoader[]{projectFTL, javaFTL};
            MultiTemplateLoader mtl = new MultiTemplateLoader(templateLoaders);
            cfg.setTemplateLoader(mtl);
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);

            //设置全局共享资源
            String projectName = metaApplicationPO.getApp_en_name();
            String domainName = metaApplicationPO.getGroup_id();
            String projectNameUpperCase = metaApplicationPO.getApp_en_name().toUpperCase();
            cfg.setSharedVariable("projectName", projectName);
            cfg.setSharedVariable("projectZhName", metaApplicationPO.getApp_zh_name());
            cfg.setSharedVariable("domainName", domainName);
            cfg.setSharedVariable("projectNameUpperCase", projectNameUpperCase);
            cfg.setSharedVariable("dbColumn2JavaBeanTMM", new DbColumn2JavaBeanTMM());
            cfg.setSharedVariable("dbColumnVo2PoTMM", new DbColumnVo2PoTMM());
            cfg.setSharedVariable("dbColumnDataTypeTMM", new DbColumnDataTypeTMM());
            cfg.setSharedVariable("dbColumnPo2VoTMM", new DbColumnPo2VoTMM());
        } catch (IOException e) {
            LOGGER.error("found error", e);
            throw new ACServiceException(ACWebErrorMsg.ERROR_TEMPLATE_FILE);
        } catch (TemplateModelException e) {
            LOGGER.error("found error", e);
            throw new ACServiceException(ACWebErrorMsg.ERROR_TEMPLATE_FILE);
        }
    }

    private void generateProjectBaseFiles(Configuration cfg, Properties prop, ACMetaApplicationPO metaApplicationPO) {
        Template template = null;
        OutputStreamWriter out = null;
        File file = null;
        Map<String, Object> root = new HashMap<>();
        try {
            //拷贝工程必须文件
            String projectType = (String) prop.get("app.config.project");
            String targetFilePath = "F:/IDEAWorkspace/autocoding/ac-web/src/main/resources/project-templates/" + projectType + "/directory";
            FileUtils.copyDirectory(new File(targetFilePath), new File("G:/ac"));// 拷贝工程必备文件
            //配置生成工程文件
            ACConfigDatasourceData datasourceData = datasourceDao.findDatasourceAndDatabaseInfoByDatasourceId(metaApplicationPO.getDatasource_id());
            root.put("datasource", datasourceData);
            String[] projectTempPathArr = ((String) prop.get("app.config.project.maven.temps")).split(",");
            String[] projectFiles = getProjectFilesFromProp(projectTempPathArr);

            String outPath = null;
            String realName = null;
            for (int i = 0; i < projectTempPathArr.length; i++) {
                outPath = projectTempPathArr[i].substring(0, projectTempPathArr[i].lastIndexOf("/") + 1);
                realName = projectTempPathArr[i].substring(projectTempPathArr[i].lastIndexOf("/") + 1, projectTempPathArr[i].length());
                template = cfg.getTemplate(projectFiles[i].substring(0, projectFiles[i].lastIndexOf(".")) + ".ftl");
                file = new File("G:/ac" + outPath);//TODO:输出路径配置
                if (!file.exists()) {
                    file.mkdirs();
                }
                out = new OutputStreamWriter(new FileOutputStream("G:/ac" + outPath + realName), "UTF-8");//TODO:输出路径配置
                template.process(root, out);
            }

            out.flush();
        } catch (MalformedTemplateNameException e) {
            LOGGER.error("found error", e);
            throw new ACServiceException(ACWebErrorMsg.ERROR_TEMPLATE_project_init);
        } catch (ParseException e) {
            LOGGER.error("found error", e);
            throw new ACServiceException(ACWebErrorMsg.ERROR_TEMPLATE_project_init);
        } catch (TemplateNotFoundException e) {
            LOGGER.error("found error", e);
            throw new ACServiceException(ACWebErrorMsg.ERROR_TEMPLATE_project_init);
        } catch (IOException e) {
            LOGGER.error("found error", e);
            throw new ACServiceException(ACWebErrorMsg.ERROR_TEMPLATE_project_init);
        } catch (TemplateException e) {
            LOGGER.error("found error", e);
            throw new ACServiceException(ACWebErrorMsg.ERROR_TEMPLATE_project_init);
        }
    }

    private String[] getProjectFilesFromProp(String[] projectTempPathArr) {
        String[] projectTempsArr = new String[projectTempPathArr.length];
        int i = 0;
        for (String path : projectTempPathArr) {
            projectTempsArr[i++] = path.substring(path.lastIndexOf("/") + 1, path.length());
        }

        return projectTempsArr;
    }

    private void generateAppJavaFiles(Configuration cfg, List<ACConfigTemplatePO> configTemplateDataList, List<ACDbTableMetaInfoData> tableMetaInfoDataList, ACMetaApplicationPO metaApplicationPO) {
        try {
            Template template = null;
            Map<String, Object> root = null;
            OutputStreamWriter out = null;
            String appFileSavePath = "G:/ac";//TODO:配置文件配置路径
            String fileName = null;
            String directoryPath = null;
            File file = null;
            String moduleName = null;
            String domainPath = metaApplicationPO.getGroup_id().replace(".", "/");
            String appName = metaApplicationPO.getApp_en_name().toLowerCase();
            StringBuffer sb_zh = new StringBuffer("\n");
            StringBuffer sb_en = new StringBuffer("\n");
            for (ACDbTableMetaInfoData table : tableMetaInfoDataList) {
                //1.生成ORM映射java文件
                root = new HashMap<>();
                root.put("tableInfo", table);
                moduleName = table.getTableName().split("_")[1];
                for (ACConfigTemplatePO temp : configTemplateDataList) {
                    template = cfg.getTemplate(temp.getTemplate_file_name());
                    fileName = temp.getProcess_file_name().replace("${TABLE_TO_JAVA_FILE}", table.getJavaFileName());
                    directoryPath = appFileSavePath + temp.getProcess_path().replace("${DOMAIN_PATH}", domainPath).replace("${APP_NAME}", appName).replace("${MODULE_NAME}", moduleName);
                    file = new File(directoryPath);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    out = new OutputStreamWriter(new FileOutputStream(directoryPath + fileName), "UTF-8");//输出中文编码设置
                    template.process(root, out);
                }
                //2.生成对应的VO验证配置文件
                for (ACDbColumnMetaInfoData column : table.getColumnMetaInfoList()) {
                    if (column.getIsNullable() != "NO") {
                        sb_zh.append(metaApplicationPO.getApp_en_name().toUpperCase()).append(table.getModuleAndBusinessTableName()).append("VO.").append(column.getColumnName()).append(".NotEmpty = ");
                        sb_en.append(metaApplicationPO.getApp_en_name().toUpperCase()).append(table.getModuleAndBusinessTableName()).append("VO.").append(column.getColumnName()).append(".NotEmpty = ");
                        sb_zh.append(column.getColumnName()).append("字段不能为空\n");
                        sb_en.append(column.getColumnName()).append(" field cannot be empty\n");
                    }
                }
                sb_zh.append("\n");
                sb_en.append("\n");
            }
            FileUtils.writeStringToFile(new File(appFileSavePath + "/src/main/resources/valid-message/valid_message_zh_CN.properties"), sb_zh.toString(), "UTF-8", true);//TODO:写入配置文件
            FileUtils.writeStringToFile(new File(appFileSavePath + "/src/main/resources/valid-message/valid_message_en_US.properties"), sb_en.toString(), "UTF-8", true);

            out.flush();
        } catch (IOException e) {
            LOGGER.error("found error", e);
            throw new ACServiceException(ACWebErrorMsg.ERROR_TEMPLATE_FILE);
        } catch (TemplateException e) {
            LOGGER.error("found error", e);
            throw new ACServiceException(ACWebErrorMsg.ERROR_TEMPLATE_RENDEX);
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
     *
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
     *
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
            LOGGER.error("found error", e);
            if (e instanceof ACServiceException) {
                throw (ACServiceException) e;
            }
            throw new ACServiceException(ACWebErrorMsg.ERROR_INIT_APP_DATABASE);
        }
    }
}
