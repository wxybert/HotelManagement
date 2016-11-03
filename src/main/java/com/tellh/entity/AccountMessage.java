package com.tellh.entity;

/**
 * Created by tlh on 2016/11/3.
 */
public class AccountMessage {
    public enum Code {
        SUCCESS_LOGIN,
        FAILED_LOGIN,
        SUCCESS_LOGOUT,
        SUCCESS_REGISTER,
        FAILED_REGISTER,
        NOT_FOUND
    }

    private String msg;
    private Code code;
    private Account account;

    public AccountMessage() {
    }

    public AccountMessage(String msg, Code code) {
        this.msg = msg;
        this.code = code;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public Code getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "AccountMessage{" +
                "msg='" + msg + '\'' +
                ", code=" + code.name() +
                '}';
    }
}
