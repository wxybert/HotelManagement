package com.tellh.dao;

import com.tellh.entity.Customer;
import com.tellh.entity.Order;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tlh on 2016/10/31.
 */
@Repository
public class CustomerDao extends BaseDao {
    public Customer getById(int id) {
        return getSession().get(Customer.class, id);
    }

    public Customer getByIdNum(String idNum) {
        return (Customer) getSession().getNamedQuery("ByIdNum")
                .setParameter("idNum", idNum)
                .uniqueResult();
    }

    public Customer getByIdNumWithOrders(String idNum) {
        Customer customer = (Customer) getSession().getNamedQuery("ByIdNum")
                .setParameter("idNum", idNum)
                .uniqueResult();
        Hibernate.initialize(customer.getOrders());
        return customer;
    }

    public boolean isVip(String idNum) {
        return getByIdNum(idNum).isVIP();
    }

    public void promote(String idNum) {
        Customer customer = getByIdNum(idNum);
        customer.setVIP(true);
        getSession().update(customer);
    }

    public void register(Customer customer) {
        getSession().save(customer);
    }

    public List<Order> listAllOrders(String idNum) {
        Hibernate.initialize(getByIdNum(idNum).getOrders());
        return getByIdNum(idNum).getOrders();
    }
}
