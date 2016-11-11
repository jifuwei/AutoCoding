package com.jifuwei.ac.web.common.error;

import com.jifuwei.ac.foundation.error.ACErrorMsg;

/**
 * 错误集合类
 * Created by JFW on 2016/10/16.
 */
public class ACWebErrorMsg extends ACErrorMsg {

    public static final ACWebErrorMsg ERROR_TMM_ILLEGAL_PARAMETER = new ACWebErrorMsg(40031, "模板引擎自定义函数非法参数错误");
    public static final ACWebErrorMsg ERROR_INIT_APP_DATABASE = new ACWebErrorMsg(40032, "初始化应用数据库脚本出错");
    public static final ACWebErrorMsg ERROR_DATABASE_IS_EXIST = new ACWebErrorMsg(40033, "数据库已存在");
    public static final ACWebErrorMsg ERROR_APP_DB_NOT_EXIST = new ACWebErrorMsg(40034, "应用数据库不存在");
    public static final ACWebErrorMsg ERROR_TEMPLATE_FILE = new ACWebErrorMsg(40035, "模板目录加载失败");
    public static final ACWebErrorMsg ERROR_TEMPLATE_RENDEX = new ACWebErrorMsg(40036, "模板渲染失败");
    public static final ACWebErrorMsg ERROR_TEMPLATE_project_init = new ACWebErrorMsg(40037, "初始化工程文件模板失败");

    public ACWebErrorMsg(int errcode, String errmsg) {
        super(errcode, errmsg);
    }
}
