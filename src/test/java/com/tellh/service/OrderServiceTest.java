package com.tellh.service;

import com.tellh.entity.OrderMessage;
import com.tellh.entity.model.OrderModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by tlh on 2016/11/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:SpringAppContext.xml")
public class OrderServiceTest {
    @Autowired
    OrderService service;

    @Test
    public void orderRoom() throws Exception {
        OrderModel model = new OrderModel();
        model.setDays(1);
        model.setCustomerIdNum("110101198010016414");
        model.setRoomNum("101");
        OrderMessage orderMessage = service.orderRoom(model);
        System.out.println(orderMessage);
    }

    @Test
    public void paidDebt() throws Exception {
        service.paidDebt("101", "110101198010016414");
        System.out.println(service.checkOutRoom("101", "110101198010016414"));;
    }

    @Test
    public void checkOutRoom() throws Exception {
        System.out.println(service.checkOutRoom("101", "110101198010016414"));
    }

    @Test
    public void listAllOrdersExpire() throws Exception {
        System.out.println(service.listAllOrdersExpire());
    }

    @Test
    public void listAllOrdersValid() throws Exception {
        System.out.println(service.listAllOrdersValid());
    }

    @Test
    public void findAllExpireOrders() throws Exception {
        System.out.println(service.findAllExpireOrders());
    }

}