package com.jifuwei.ac.web.freemark.method;

import com.jifuwei.ac.foundation.exception.ACServiceException;
import com.jifuwei.ac.web.common.error.ACWebErrorMsg;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.sql.Types;
import java.util.List;

/**
 * freemark自定义函数：获取数据库字段对应类型
 * Created by JFW on 2016/11/08.
 */
public class DbColumnDataTypeTMM implements TemplateMethodModelEx {

    /**
     * 规定该方法有且仅有一个参数
     * args.get(0):输出值类型，整形，即java.sql.Types对应的值
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
        switch (Integer.parseInt(String.valueOf(args.get(0)))) {
            case Types.BIGINT:
                return "BigInteger";
            case Types.BINARY:
                return "byte[]";
            case Types.BIT:
                return "Boolean";
            case Types.BLOB:
                return "byte[]";
            case Types.CHAR:
                return "String";
            case Types.CLOB:
                return "String";
            case Types.DATE:
                return "Date";
            case Types.DECIMAL:
                return "BigDecimal";
            case Types.DOUBLE:
                return "Double";
            case Types.FLOAT:
                return "Double";
            case Types.INTEGER:
                return "Integer";
            case Types.JAVA_OBJECT:
                return "String";
            case Types.LONGVARBINARY:
                return "byte[]";
            case Types.LONGVARCHAR:
                return "String";
            case Types.NUMERIC:
                return "BigDecimal";
            case Types.OTHER:
                return "String";
            case Types.REAL:
                return "Float";
            case Types.SMALLINT:
                return "Integer";
            case Types.TIME:
                return "Time";
            case Types.TIMESTAMP:
                return "Timestamp";
            case Types.TINYINT:
                return "Bute";
            case Types.VARBINARY:
                return "byte[]";
            case Types.VARCHAR:
                return "String";
            default:
                return null;
        }
    }
}
