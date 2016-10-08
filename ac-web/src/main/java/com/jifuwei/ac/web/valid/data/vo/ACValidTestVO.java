package com.jifuwei.ac.web.valid.data.vo;

import javax.validation.constraints.NotNull;

/**
 * 测试VO
 * Created by JFW on 2016/10/6.
 */
public class ACValidTestVO {
    @NotNull(message = "{ACValidTestVO.username.notnull}")
    private String username;

    @NotNull(message = "{ACValidTestVO.password.notnull}")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
