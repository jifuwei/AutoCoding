package com.autocoding.ac.foundation.data;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * 用于web接口层统一格式化消息回复
 * Created by JFW on 2016/10/6.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ACResponseMsg {
    public int errcode;
    public String errmsg;
    public Object respData;

    public ACResponseMsg() {
        this.errcode = 0;
        this.errmsg = "OK";
    }
}
