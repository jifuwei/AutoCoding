package com.autocoding.ac.web.freemark.method;

import com.autocoding.ac.foundation.exception.ACServiceException;
import com.autocoding.ac.util.string.CamelCaseUtil;
import com.autocoding.ac.web.common.error.ACWebErrorMsg;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

import java.util.List;

/**
 * freemark自定义函数：数据库表字段到java bean的实体名称转换
 * Created by JFW on 2016/10/16.
 */
public class DbColumn2JavaBeanTMM implements TemplateMethodModelEx {

    private static final String FIELD = "FIELD";
    private static final String GET_SET = "GET_SET";

    /**
     * 规定该方法有且仅有两个参数
     * args.get(0):传入值，字符串类型，即数据库表字段信息，需严格按照autocoding表设计规范完成，具体参考开发文档
     * args.get(1):输出值类型，字符串类型，可选值：FIELD -> 转换为java bean中的字段，脱粉命名法；GET_SET -> 转换为字段生成get和set方法名
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

        //转换实现
        if (String.valueOf(args.get(1)).equals(FIELD)) {
            return CamelCaseUtil.toCamelCase(String.valueOf(args.get(0)));
        } else if (String.valueOf(args.get(1)).equals(GET_SET)) {
            return CamelCaseUtil.toCapitalizeCamelCase(String.valueOf(args.get(0)));
        }
        return "";
    }
}
