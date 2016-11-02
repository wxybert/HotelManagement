package com.tellh.dao;

import com.tellh.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tlh on 2016/10/31.
 */
@Repository
public class OrderDao extends BaseDao {
    public Order getById(int id) {
        return getSession().get(Order.class, id);
    }

    public List<Order> listByState(Order.State state) {
        return getSession().getNamedQuery("ByState")
                .setParameter("state", state)
                .list();
    }

    public void insert(Order order) {
        getSession().save(order);
    }


}
