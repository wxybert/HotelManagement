package com.tellh.service;

import com.tellh.dao.CustomerDao;
import com.tellh.entity.Customer;
import com.tellh.entity.Order;
import com.tellh.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tlh on 2016/10/31.
 */
@Service
public class CustomerService {
    @Autowired
    private CustomerDao customerDao;

    public Customer getById(int id) {
        return customerDao.getById(id);
    }

    public Customer getByIdNum(String idNum) {
        return customerDao.getByIdNum(idNum);
    }

    public Customer getByIdNumWithOrders(String idNum) {
        return customerDao.getByIdNumWithOrders(idNum);
    }

    public boolean isVip(String idNum) {
        return customerDao.isVip(idNum);
    }

    public void promote(String idNum) {
        if (customerDao.isVip(idNum))
            return;
        customerDao.promote(idNum);
    }

    public void register(String name, String idNum, String password) {
        Customer customer = new Customer(name, idNum, password);
        customerDao.register(customer);
    }

    public Customer login(String idNum, String password) {
        Customer customer = customerDao.getByIdNumWithOrders(idNum);
        if (customer == null || !customer.getPassword().equals(password))
            return null;
        return customer;
    }

    public List<Order> listAllOrders(String idNum) {
        return customerDao.listAllOrders(idNum);
    }

    public Room getRoomHasCheckIn(String idNum) {
        Order validOrder = getByIdNum(idNum).getValidOrder();
        if (validOrder == null)
            return null;
        return validOrder.getRoom();
    }

    public Room getRoomNeedToCheckOut(String idNum) {
        List<Order> orders = getByIdNum(idNum).getOrders();
        if (orders.isEmpty())
            return null;
        Order order = orders.get(0);
        if (order.getState() == Order.State.CHECK_OUT)
            return null;
        return order.getRoom();
    }

    public static Room getRoomNeedToCheckOut(Customer customer) {
        List<Order> orders = customer.getOrders();
        if (orders.isEmpty())
            return null;
        Order order = orders.get(0);
        if (order.getState() == Order.State.CHECK_OUT)
            return null;
        return order.getRoom();
    }
}