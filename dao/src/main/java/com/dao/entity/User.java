package com.dao.entity;

/**
 * @author pengqingsong
 * @date 26/10/2017
 * @desc 对应数据库中的user表
 */
public class User {

    private Long uid;

    private String userName;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
