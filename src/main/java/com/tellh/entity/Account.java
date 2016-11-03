package com.tellh.entity;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Created by tlh on 2016/11/3.
 */
@MappedSuperclass
public class Account {
    @Column(nullable = false)
    protected String name;
    @Column(nullable = false)
    @JSONField(serialize = false)
    protected String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
