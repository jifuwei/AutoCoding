package com.jifuwei.ac.web.freemark.method;

import com.jifuwei.ac.foundation.exception.ACServiceException;
import com.jifuwei.ac.web.common.error.ACWebErrorMsg;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.sql.Types;
import java.util.List;

/**
 * freemark自定义函数：数据库表字段到java bean需要导入的类
 * Created by JFW on 2016/10/16.
 */
public class DbColumn2JavaImportTMM implements TemplateMethodModelEx {

    /**
     * 规定该方法有且仅有一个参数
     * args.get(0):传入值，java.sql.Types类型值，由此判断需要引入的java类信息
     *
     * @param args 参数列表
     * @return
     * @throws TemplateModelException
     */
    @Override
    public Object exec(List args) throws TemplateModelException {
        // 参数校验
        if (args.size() != 1) {
            throw new ACServiceException(ACWebErrorMsg.ERROR_TMM_ILLEGAL_PARAMETER);
        }

        // 转换实现
        System.out.println(args.get(0));
        switch (Integer.parseInt(String.valueOf(args.get(0)))) {
            case Types.BIGINT:
                return null;
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
                return "import java.math.BigDecimal";
            case Types.OTHER:
                return null;
            case Types.REAL:
                return null;
            case Types.SMALLINT:
                return null;
            case Types.TIME:
                return "import java.sql.Time";
            case Types.TIMESTAMP:
                return "import java.sql.Timestamp";
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
