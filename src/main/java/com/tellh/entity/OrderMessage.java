package com.tellh.entity;

/**
 * Created by tlh on 2016/11/2.
 */
public class OrderMessage {
    public enum Code {
        SUCCESS_Check_IN, FAILED_CHECK_IN,
        SUCCESS_Check_OUT, FAILED_CHECK_OUT_NOT_FOUND, FAILED_CHECK_OUT_DEBT
    }

    private Code code;
    private String msg;
    private float price;

    public OrderMessage() {
    }

    public OrderMessage(String msg, Code code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", price=" + price +
                '}';
    }
}
