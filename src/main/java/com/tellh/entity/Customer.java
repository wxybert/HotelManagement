package com.tellh.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.tellh.filter.SimplePropertyFilter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by tlh on 2016/10/30.
 */
@NamedQueries({
        @NamedQuery(name = "ByIdNum", query = "SELECT c FROM Customer c WHERE idNum=:idNum"),
})
@Entity
@Table(name = "customer", schema = "hotel_management")
public class Customer extends Account {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "native")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "cid")
    private int id;
    //    @Column(nullable = false)
//    private String name;
    @Column(unique = true, nullable = false)
    private String idNum;
    //    @Column(nullable = false)
//    private String password;
    private boolean isVIP;
    @OneToMany(mappedBy = "customer")
    @OrderBy("deadline desc,dCheckIn desc")
    private List<Order> orders;

    public Order getValidOrder() {
        if (orders.isEmpty())
            return null;
        Order order = orders.get(0);
        if (order.getDeadline().before(new Date()))
            return null;
        return order;
    }

    public Customer() {
    }

    public Customer(String name, String idNum, String password) {
        this.name = name;
        this.idNum = idNum;
        this.password = password;
    }

    public boolean isVIP() {
        return isVIP;
    }

    public void setVIP(boolean VIP) {
        isVIP = VIP;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", idNum='" + idNum + '\'' +
                '}';
    }
}
