package com.jifuwei.ac.web.freemark.method;

import com.jifuwei.ac.foundation.exception.ACServiceException;
import com.jifuwei.ac.web.common.error.ACWebErrorMsg;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.sql.Types;
import java.util.List;

/**
 * freemark自定义函数：VO到PO转换模板函数
 * Created by JFW on 2016/10/17.
 */
public class DbColumnPo2VoTMM implements TemplateMethodModelEx {

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
                return "String.valueOf(this." + args.get(0) + ")";
            case Types.BINARY:
                return "new String(this." + args.get(0) + ")";
            case Types.BIT:
                return "String.valueOf(this." + args.get(0) + ")";
            case Types.BLOB:
                return "new String(this." + args.get(0) + ")";
            case Types.CHAR:
                return "this." + args.get(0);
            case Types.CLOB:
                return "this." + args.get(0);
            case Types.DATE:
                return "String.valueOf(this." + args.get(0) + ")";
            case Types.DECIMAL:
                return "String.valueOf(this." + args.get(0) + ")";
            case Types.DOUBLE:
                return "String.valueOf(this." + args.get(0) + ")";
            case Types.FLOAT:
                return "String.valueOf(this." + args.get(0) + ")";
            case Types.INTEGER:
                return "String.valueOf(this." + args.get(0) + ")";
            case Types.JAVA_OBJECT:
                return "this." + args.get(0);
            case Types.LONGVARBINARY:
                return "new String(this." + args.get(0) + ")";
            case Types.LONGVARCHAR:
                return "this." + args.get(0);
            case Types.NUMERIC:
                return "String.valueOf(this." + args.get(0) + ")";
            case Types.OTHER:
                return "this." + args.get(0);
            case Types.REAL:
                return "String.valueOf(this." + args.get(0) + ")";
            case Types.SMALLINT:
                return "String.valueOf(this." + args.get(0) + ")";
            case Types.TIME:
                return "String.valueOf(this." + args.get(0) + ")";
            case Types.TIMESTAMP:
                return "String.valueOf(this." + args.get(0) + ")";
            case Types.TINYINT:
                return "String.valueOf(this." + args.get(0) + ")";
            case Types.VARBINARY:
                return "new String(this." + args.get(0) + ")";
            case Types.VARCHAR:
                return "this." + args.get(0);
            default:
                return null;
        }
    }
}
