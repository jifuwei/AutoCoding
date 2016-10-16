package com.jifuwei.ac.foundation.error;

import com.jifuwei.ac.foundation.data.ACResponseMsg;

/**
 * 错误消息集合
 * Created by JFW on 2016/10/6.
 */
public class ACErrorMsg {
    public static final ACErrorMsg CALL_SUCCESS = new ACErrorMsg(0, "操作成功");
    public static final ACErrorMsg LOGIN_SUCCESS = new ACErrorMsg(40001, "登录成功");
    public static final ACErrorMsg USER_IS_NULL = new ACErrorMsg(40002, "用户不能为空");
    public static final ACErrorMsg PASSWORD_IS_NULL = new ACErrorMsg(40003, "密码不能为空");
    public static final ACErrorMsg PASS_CODE_IS_NULL = new ACErrorMsg(40004, "验证码不能为空");
    public static final ACErrorMsg PASS_CODE_ERROR = new ACErrorMsg(40005, "验证码不匹配");
    public static final ACErrorMsg USER_LOCKED = new ACErrorMsg(40006, "用户被锁定");
    public static final ACErrorMsg PASSWORD_ERROR = new ACErrorMsg(40007, "密码错误");
    public static final ACErrorMsg LOGIN_FAIL = new ACErrorMsg(40008, "登录系统失败，非法用户");
    public static final ACErrorMsg USER_INFO_ERROR = new ACErrorMsg(40009, "用户名或密码错误");
    public static final ACErrorMsg USER_ROLE_IS_NULL = new ACErrorMsg(40010, "登录失败，用户角色未授权");
    public static final ACErrorMsg LOGOUT_SUCCESS = new ACErrorMsg(40011, "登出成功");
    public static final ACErrorMsg LOGOUT_FAIL = new ACErrorMsg(40012, "登出系统失败");
    public static final ACErrorMsg PASSWORD_RESET = new ACErrorMsg(40013, "初始密码，需重置");
    public static final ACErrorMsg PASSWORD_EXPIRES = new ACErrorMsg(40014, "密码过期请重置密码");
    public static final ACErrorMsg PASSWORD_IS_USED = new ACErrorMsg(40015, "密码在过去三次已经被使用");
    public static final ACErrorMsg ERROR_HTTP_REQUEST_IS_NOT_AVAILABLE = new ACErrorMsg(40017, "HTTP请求错误");
    public static final ACErrorMsg ERROR_REMOTE_SERVER_RETURN_ERROR = new ACErrorMsg(40018, "远程服务返回错误");
    public static final ACErrorMsg ERROR_RESOURCE_IS_NOT_READY = new ACErrorMsg(40019, "系统资源缺失");
    public static final ACErrorMsg ERROR_TECHNICAL_ERROR = new ACErrorMsg(40020, "技术处理失败");
    public static final ACErrorMsg ERROR_CONNECTION_TIMEOUT = new ACErrorMsg(40021, "连接超时");
    public static final ACErrorMsg ERROR_INVALID_AUTHORIZATION = new ACErrorMsg(40022, "无授权操作");
    public static final ACErrorMsg ERROR_NOT_SUPPORT_REMOTE_CALL = new ACErrorMsg(40023, "不支持远程系统调用");
    public static final ACErrorMsg ERROR_FUNCTION_NOT_SUPPORT = new ACErrorMsg(40024, "该功能点不支持");
    public static final ACErrorMsg ERROR_DATABASE_TECH_EXCEPTION = new ACErrorMsg(40025, "数据库操作异常");
    public static final ACErrorMsg ERROR_DUMPLICATE_PRIMARY_KEY = new ACErrorMsg(40026, "数据记录已经存在");
    public static final ACErrorMsg ERROR_RECORD_NOT_EXISTS = new ACErrorMsg(40027, "数据记录不存在");
    public static final ACErrorMsg ERROR_DATA_IS_MALFORM = new ACErrorMsg(40028, "数据格式不符合要求");
    public static final ACErrorMsg ERROR_ILLEGAL_ACCESS = new ACErrorMsg(40029, "非法用户访问");
    public static final ACErrorMsg ERROR_ILLEGAL_OPERATION = new ACErrorMsg(40030, "没有操作权限，请找管理员确认权限配置!");

    public int errcode;
    public String errmsg;

    public ACErrorMsg(int errcode, String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    public ACResponseMsg toResponseMsg() {
        ACResponseMsg acResponseMsg = new ACResponseMsg();
        acResponseMsg.errcode = this.errcode;
        acResponseMsg.errmsg = this.errmsg;
        return acResponseMsg;
    }
}
