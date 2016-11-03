package com.tellh.entity.model;

import com.tellh.entity.Order;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by tlh on 2016/11/3.
 */
public class OrderForCustomerWrapper extends Order {
    public OrderForCustomerWrapper(Order order) {
        setId(order.getId());
        setState(order.getState());
        setDeadline(order.getDeadline());
        setdCheckIn(order.getdCheckIn());
        setRoom(order.getRoom());
    }

    public static List<Order> wrapList(Collection<Order> orders) {
        List<Order> orderws = new ArrayList<>();
        for (Order order : orders) {
            orderws.add(new OrderForCustomerWrapper(order));
        }
        return orderws;
    }
}
