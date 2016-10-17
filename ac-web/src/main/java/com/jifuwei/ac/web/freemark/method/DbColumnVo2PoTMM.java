package com.jifuwei.ac.web.freemark.method;

import com.jifuwei.ac.foundation.exception.ACServiceException;
import com.jifuwei.ac.web.common.error.ACWebErrorMsg;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.sql.Types;
import java.util.List;

/**
 * freemark自定义函数：PO到VO转换模板函数
 * Created by JFW on 2016/10/17.
 */
public class DbColumnVo2PoTMM implements TemplateMethodModelEx {

    /**
     * 规定该方法有且仅有两个参数
     * args.get(0):传入值，字符串类型，转换后的数据库表字段
     * args.get(1):输出值类型，整形，即java.sql.Types对应的值
     *
     * @param args 参数列表
     * @return
     * @throws TemplateModelException
     */
    @Override
    public Object exec(List args) throws TemplateModelException {
        // 参数校验
        if (args.size() != 2) {
            throw new ACServiceException(ACWebErrorMsg.ERROR_TMM_ILLEGAL_PARAMETER);
        }

        // 转换实现
        switch (Integer.parseInt(String.valueOf(args.get(1)))) {
            case Types.BIGINT:
                return "new BigInteger(this." + args.get(0) + ")";
            case Types.BINARY:
                return "this." + args.get(0) + ".getBytes(\"UTF8\")";
            case Types.BIT:
                return "Boolean.parseBoolean(this." + args.get(0) + ")";
            case Types.BLOB:
                return "this." + args.get(0) + ".getBytes(\"UTF8\")";
            case Types.CHAR:
                return "this." + args.get(0);
            case Types.CLOB:
                return "this." + args.get(0);
            case Types.DATE:
                return "Date.valueOf(this." + args.get(0) + ")";
            case Types.DECIMAL:
                return "new BigDecimal(this." + args.get(0) + ")";
            case Types.DOUBLE:
                return "Double.valueOf(this." + args.get(0) + ")";
            case Types.FLOAT:
                return "Double.valueOf(this." + args.get(0) + ")";
            case Types.INTEGER:
                return "Integer.valueOf(this." + args.get(0) + ")";
            case Types.JAVA_OBJECT:
                return "this." + args.get(0);
            case Types.LONGVARBINARY:
                return "this." + args.get(0) + ".getBytes(\"UTF8\")";
            case Types.LONGVARCHAR:
                return "this." + args.get(0);
            case Types.NUMERIC:
                return "new BigDecimal(this." + args.get(0) + ")";
            case Types.OTHER:
                return "this." + args.get(0);
            case Types.REAL:
                return "Float.valueOf(this." + args.get(0) + ")";
            case Types.SMALLINT:
                return "Integer.valueOf(this." + args.get(0) + ")";
            case Types.TIME:
                return "Time.valueOf(this." + args.get(0) + ")";
            case Types.TIMESTAMP:
                return "Timestamp.valueOf(this." + args.get(0) + ")";
            case Types.TINYINT:
                return "Bute.valueOf(this." + args.get(0) + ")";
            case Types.VARBINARY:
                return "this." + args.get(0) + ".getBytes(\"UTF8\")";
            case Types.VARCHAR:
                return "this." + args.get(0);
            default:
                return null;
        }
    }
}
