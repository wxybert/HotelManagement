package com.tellh.entity;

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
public class Customer {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "native")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "cid")
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String idNum;
    @Column(nullable = false)
    private String password;
    private boolean isVIP;
    @OneToMany(mappedBy = "customer")
    @OrderBy("deadline desc")
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
