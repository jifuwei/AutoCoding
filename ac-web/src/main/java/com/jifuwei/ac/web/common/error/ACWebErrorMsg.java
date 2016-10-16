package com.jifuwei.ac.web.common.error;

import com.jifuwei.ac.foundation.error.ACErrorMsg;

/**
 * Created by JFW on 2016/10/16.
 */
public class ACWebErrorMsg extends ACErrorMsg {

    public static final ACWebErrorMsg ERROR_TMM_ILLEGAL_PARAMETER = new ACWebErrorMsg(40031, "模板引擎自定义函数非法参数错误");

    public ACWebErrorMsg(int errcode, String errmsg) {
        super(errcode, errmsg);
    }
}
