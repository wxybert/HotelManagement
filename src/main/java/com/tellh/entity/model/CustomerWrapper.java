package com.tellh.entity.model;

import com.tellh.entity.Customer;
import com.tellh.entity.Order;

import java.util.List;

/**
 * Created by tlh on 2016/11/4.
 */
public class CustomerWrapper extends Customer {
    CustomerWrapper(Customer customer) {
        setId(customer.getId());
        setVIP(customer.isVIP());
        setName(customer.getName());
        setIdNum(customer.getIdNum());
        setPassword(customer.getPassword());
        setPhone(customer.getPhone());
    }

    public static List<Order> wrapCustomerInOrders(List<Order> orders) {
        for (Order order : orders) {
            order.setCustomer(new CustomerWrapper(order.getCustomer()));
        }
        return orders;
    }
}
