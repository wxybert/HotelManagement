package com.tellh.entity.model;

import com.tellh.entity.Room;

/**
 * Created by tlh on 2016/11/3.
 */
public class RoomFilterModel {
    private boolean isFree = true;
    private float price;
    private Room.Type type;
    private int size;

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Room.Type getType() {
        return type;
    }

    public void setType(Room.Type type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
