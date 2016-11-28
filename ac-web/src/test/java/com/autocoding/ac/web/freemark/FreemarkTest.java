package com.autocoding.ac.web.freemark;

import com.autocoding.ac.util.string.CamelCaseUtil;
import com.autocoding.ac.web.base.BaseTest;
import com.autocoding.ac.web.freemark.method.DbColumnVo2PoTMM;
import com.autocoding.ac.web.meta.dao.ACDbMetaInfoDao;
import com.autocoding.ac.web.meta.data.ACDbColumnMetaInfoData;
import com.autocoding.ac.web.meta.data.ACDbExportedKeyMetaInfoData;
import com.autocoding.ac.web.meta.data.ACDbPrimaryKeyMetaInfoData;
import com.autocoding.ac.web.meta.data.ACDbTableMetaInfoData;
import com.autocoding.ac.web.freemark.method.DbColumn2JavaBeanTMM;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模板引擎测试类
 * Created by JFW on 2016/10/14.
 */
public class FreemarkTest extends BaseTest {

    @Autowired
    private ACDbMetaInfoDao dbMetaInfoDao = null;

    @Test
    public void templateWriteTest() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_25);
        try {
//            cfg.setDirectoryForTemplateLoading(new File("F:\\IDEAWorkspace\\autocoding\\ac-web\\src\\test\\resources\\templates\\"));
            cfg.setClassForTemplateLoading(FreemarkTest.class, "/templates");
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);
            cfg.setSharedVariable("projectName", "ac.web");
            cfg.setSharedVariable("dbColumn2JavaBeanTMM", new DbColumn2JavaBeanTMM());
            cfg.setSharedVariable("dbColumnVo2PoTMM", new DbColumnVo2PoTMM());
            Template template = null;

            String domainName = "com.jifuwei";
            String projectName = "autocoding";
            String projectNameUpperCase = projectName.toUpperCase();
            List<ACDbTableMetaInfoData> tableMetaInfoDataList= findProjectModuleInfo();
            Map<String, Object> root = null;
            FileWriter out = null;
            String saveFilePath = "G:/src/";
            String[] fileName = {"ACTemplateController","ACValidTestService","ACValidTestServiceImpl","ACValidTestDao","ACValidTestDaoImpl","ACValidTestData","ACValidTestVO","ACValidTestPO"};
            String[] fileNameType = {"Controller","Service","ServiceImpl","Dao","DaoImpl","Data","VO","PO"};

            for (ACDbTableMetaInfoData table : tableMetaInfoDataList) {
                root = new HashMap<>();
                root.put("domainName", domainName);
//                root.put("projectName", projectName);
//                root.put("projectNameUpperCase", projectNameUpperCase);
//                root.put("projectName", "ac.web");
                root.put("projectNameUpperCase", "AC");
                root.put("tableInfo", table);

                //输出必须的类文件
//                for (String file : fileName) {
//                    template = cfg.getTemplate(file + ".ftl");
//                    out = new FileWriter(new File(saveFilePath + file + ".java"));
//                    template.process(root, out);
//                }

                for (int i = 0; i < fileName.length; i++) {
                    template = cfg.getTemplate(fileName[i] + ".ftl");
                    out = new FileWriter(new File(saveFilePath + "AC" + table.getModuleAndBusinessTableName() + fileNameType[i] + ".java"));
                    template.process(root, out);
                }

            }

            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询导入脚本结构信息-测试
     */
//    @Test
    public List<ACDbTableMetaInfoData> findProjectModuleInfo() {
        List<ACDbColumnMetaInfoData> dbColumnMetaInfoList = null;
        List<ACDbPrimaryKeyMetaInfoData> dbPrimaryKeyMetaInfoList = null;
        List<ACDbExportedKeyMetaInfoData> dbExportedKeyMetaInfoList = null;
        String[] types = {"TABLE"};
        List<ACDbTableMetaInfoData> dbTableMetaInfoList = dbMetaInfoDao.findDbTablesMetaInfo("autocoding", null, "%", types);
        for (ACDbTableMetaInfoData table : dbTableMetaInfoList) {
            dbColumnMetaInfoList = dbMetaInfoDao.findDbColumnsMetaInfo("autocoding", null, table.getTableName(), "%");
            dbPrimaryKeyMetaInfoList = dbMetaInfoDao.findDbPrimaryKeysMetaInfo("autocoding", null, table.getTableName());
            dbExportedKeyMetaInfoList = dbMetaInfoDao.findDbExportedKeysMetaInfo("autocoding", null, table.getTableName());
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
        System.out.println(tableName);
        String[] args = tableName.split("_");
        return args[2];
    }

    private String getModuleName(String tableName) {
        String[] args = tableName.split("_");
        return args[1];
    }
}
