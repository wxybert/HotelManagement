package com.tellh.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by tlh on 2016/10/30.
 */
@NamedQueries({
        @NamedQuery(name = "ByRoomNumber", query = "SELECT r FROM Room r WHERE roomNo=:roomNum"),
        @NamedQuery(name = "ByType", query = "SELECT r FROM Room r WHERE room_type=:room_type"),
        @NamedQuery(name = "ListIsFree", query = "SELECT r FROM Room r WHERE isFree=true"),
        @NamedQuery(name = "ListIsFreeByType", query = "SELECT r FROM Room r WHERE isFree=true and room_type=:type")
})
@Entity
@Table(name = "room", schema = "hotel_management")
public class Room {
    public enum Type {
        ORDINARY_SUITE,//普通套房
        DELUXE_SUITE,//豪华套房
        ORDINARY_STANDARD,//普通标间
        DELUXE_STANDARD//豪华标间
    }

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "native")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "rid")
    private int id;
    @Column(name = "room_no", unique = true, updatable = false, length = 100)
    private String roomNo;
    private String pic_url;
    @Column(name = "is_free")
    private boolean isFree;
    private float price;
    private int size;
    @Column(name = "room_type")
    private Type type;
    @Column(name = "description")
    private String desc;
    @OneToMany(mappedBy = "room")
    @OrderBy("deadline desc")
    private List<Order> orders;

    public Room() {
    }

    public Room(String roomNo, String pic_url, boolean isFree, float price, int size, Type type, String desc) {
        this.roomNo = roomNo;
        this.pic_url = pic_url;
        this.isFree = isFree;
        this.price = price;
        this.size = size;
        this.type = type;
        this.desc = desc;
    }

    public Order getValidOrder() {
        if (orders.isEmpty())
            return null;
        Order order = orders.get(0);
        if (order.getDeadline().before(new Date()))
            return null;
        return order;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Room{" +
                "desc='" + desc + '\'' +
                ", id=" + id +
                ", roomNo='" + roomNo + '\'' +
                ", pic_url='" + pic_url + '\'' +
                ", isFree=" + isFree +
                ", price=" + price +
                ", size=" + size +
                ", type=" + type +
                '}';
    }
}
