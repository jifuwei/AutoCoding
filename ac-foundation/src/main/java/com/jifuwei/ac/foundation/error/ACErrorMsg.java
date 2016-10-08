package com.jifuwei.ac.foundation.error;

/**
 * 错误消息集合
 * Created by JFW on 2016/10/6.
 */
public enum ACErrorMsg {
    LOGIN_SUCCESS(40001, "登录成功"),
    USER_IS_NULL(40002, "用户不能为空"),
    PASSWORD_IS_NULL(40003, "密码不能为空"),
    PASS_CODE_IS_NULL(40004, "验证码不能为空"),
    PASS_CODE_ERROR(40005, "验证码不匹配"),
    USER_LOCKED(40006, "用户被锁定"),
    PASSWORD_ERROR(40007, "密码错误"),
    LOGIN_FAIL(40008, "登录系统失败，非法用户"),
    USER_INFO_ERROR(40009, "用户名或密码错误"),
    USER_ROLE_IS_NULL(40010, "登录失败，用户角色未授权"),
    LOGOUT_SUCCESS(40011, "登出成功"),
    LOGOUT_FAIL(40012, "登出系统失败"),
    PASSWORD_RESET(40013, "初始密码，需重置"),
    PASSWORD_EXPIRES(40014, "密码过期请重置密码"),
    PASSWORD_IS_USED(40015, "密码在过去三次已经被使用"),

    CALL_SUCCESS(40016, "操作成功"),
    ERROR_HTTP_REQUEST_IS_NOT_AVAILABLE(40017, "HTTP请求错误"),
    ERROR_REMOTE_SERVER_RETURN_ERROR(40018, "远程服务返回错误"),
    ERROR_RESOURCE_IS_NOT_READY(40019, "系统资源缺失"),
    ERROR_TECHNICAL_ERROR(40020, "技术处理失败"),
    ERROR_CONNECTION_TIMEOUT(40021, "连接超时"),
    ERROR_INVALID_AUTHORIZATION(40022, "无授权操作"),
    ERROR_NOT_SUPPORT_REMOTE_CALL(40023, "不支持远程系统调用"),
    ERROR_FUNCTION_NOT_SUPPORT(40024, "该功能点不支持"),
    ERROR_DATABASE_TECH_EXCEPTION(40025, "数据库操作异常"),
    ERROR_DUMPLICATE_PRIMARY_KEY(40026, "数据记录已经存在"),
    ERROR_RECORD_NOT_EXISTS(40027, "数据记录不存在"),
    ERROR_DATA_IS_MALFORM(40028, "数据格式不符合要求"),
    ERROR_ILLEGAL_ACCESS(40029, "非法用户访问"),
    ERROR_ILLEGAL_OPERATION(40030, "没有操作权限，请找管理员确认权限配置!");

    public int errcode;
    public String errmsg;

    ACErrorMsg(int errcode, String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
    }
}
