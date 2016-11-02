package com.tellh.entity.model;

/**
 * Created by tlh on 2016/11/2.
 */
public class OrderModel {
    private int days;
    private String customerIdNum;
    private String roomNum;

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getCustomerIdNum() {
        return customerIdNum;
    }

    public void setCustomerIdNum(String customerIdNum) {
        this.customerIdNum = customerIdNum;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }
}
