package com.autocoding.ac.web.freemark.method;

import com.autocoding.ac.web.meta.data.ACDbColumnMetaInfoData;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.sql.Types;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * freemark自定义函数：数据库字段需要导入的java类
 * Created by JFW on 2016/10/18.
 */
public class DbColumn2JavaImportTMM implements TemplateMethodModelEx {
    @Override
    public Object exec(List args) throws TemplateModelException {
        System.out.println(args.get(0));
        List<ACDbColumnMetaInfoData> columnMetaInfoList = (List<ACDbColumnMetaInfoData>) args.get(0);
        Set<String> columnImportList = new HashSet<>();
        String importStr = null;
        for (ACDbColumnMetaInfoData column : columnMetaInfoList) {
            importStr = sqlType2JavaTyp(column.getDataType());
            if (importStr != null) {
                columnImportList.add(importStr);
            }
        }
        return columnImportList;
    }

    /**
     * java.sql.Types值转换为java对应的类
     * @param sqlType
     * @return
     */
    private String sqlType2JavaTyp(int sqlType) {
        switch (sqlType) {
            case Types.BIGINT:
                return "import java.math.BigInteger;";
            case Types.BINARY:
                return null;
            case Types.BIT:
                return null;
            case Types.BLOB:
                return null;
            case Types.CHAR:
                return null;
            case Types.CLOB:
                return null;
            case Types.DATE:
                return "import java.sql.Date;";
            case Types.DECIMAL:
                return "import java.math.BigDecimal;";
            case Types.DOUBLE:
                return null;
            case Types.FLOAT:
                return null;
            case Types.INTEGER:
                return null;
            case Types.JAVA_OBJECT:
                return null;
            case Types.LONGVARBINARY:
                return null;
            case Types.LONGVARCHAR:
                return null;
            case Types.NUMERIC:
                return "import java.math.BigDecimal;";
            case Types.OTHER:
                return null;
            case Types.REAL:
                return null;
            case Types.SMALLINT:
                return null;
            case Types.TIME:
                return "import java.sql.Time;";
            case Types.TIMESTAMP:
                return "import java.sql.Timestamp;";
            case Types.TINYINT:
                return null;
            case Types.VARBINARY:
                return null;
            case Types.VARCHAR:
                return null;
            default:
                return null;
        }
    }
}
