package com.tellh.service;

import com.tellh.dao.CustomerDao;
import com.tellh.dao.OrderDao;
import com.tellh.dao.RoomDao;
import com.tellh.entity.Customer;
import com.tellh.entity.Order;
import com.tellh.entity.OrderMessage;
import com.tellh.entity.Room;
import com.tellh.entity.model.OrderModel;
import com.tellh.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tlh on 2016/11/1.
 */
@Service
public class OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private RoomDao roomDao;

    public OrderMessage orderRoom(OrderModel model) {
        int days = model.getDays();
        if (days == 0) {
            OrderMessage message = new OrderMessage();
            message.setCode(OrderMessage.Code.FAILED_CHECK_IN);
            message.setMsg("您要订几天呢？");
            return message;
        }
        Customer customer = customerDao.getByIdNum(model.getCustomerIdNum());
        //有房间未退，不能开房
        if (CustomerService.getOrderNeedToCheckOut(customer) != null) {
            OrderMessage message = new OrderMessage();
            message.setCode(OrderMessage.Code.FAILED_CHECK_IN);
            message.setMsg("有房间未退，不能开房。");
            return message;
        }
        roomDao.checkIn(model.getRoomNum());
        Room room = roomDao.getByRoomNumber(model.getRoomNum());
        Order order = new Order();
        order.setCustomer(customer);
        order.setRoom(room);
        //设置开房起始时间
        order.setdCheckIn(new Timestamp(System.currentTimeMillis()));
        order.setDeadline(new Timestamp(DateUtils.getDateShouldCheckOut(days).getTimeInMillis()));
        orderDao.insert(order);
        OrderMessage message = new OrderMessage();
        message.setCode(OrderMessage.Code.SUCCESS_Check_IN);
        message.setMsg("订房成功！");
        return message;
    }

    //清算
    private float liquidate(String roomNum, String idNum) {
        Order orderNeedToCheckOut = CustomerService.getOrderNeedToCheckOut(customerDao.getByIdNum(idNum));
        if (orderNeedToCheckOut == null || !orderNeedToCheckOut.getRoom().getRoomNo().equals(roomNum))
            return 0;
        long time = System.currentTimeMillis() - orderNeedToCheckOut.getDeadline().getTime();
        if (time < 0)
            return 0;
        int discrepantDays = DateUtils.getDiscrepantDays(time);
        if (discrepantDays > 0)
            return orderNeedToCheckOut.getRoom().getPrice() * discrepantDays;
        return orderNeedToCheckOut.getRoom().getPrice();
    }

    //支付余账
    public void paidDebt(String roomNum, String idNum) {
        Order orderNeedToCheckOut = CustomerService.getOrderNeedToCheckOut(customerDao.getByIdNum(idNum));
        if (orderNeedToCheckOut == null || !orderNeedToCheckOut.getRoom().getRoomNo().equals(roomNum) ||
                orderNeedToCheckOut.getState() != Order.State.EXPIRE)
            return;
        orderNeedToCheckOut.setState(Order.State.PAID);
    }

    public OrderMessage checkOutRoom(String roomNum, String idNum) {
        Order orderNeedToCheckOut = CustomerService.getOrderNeedToCheckOut(customerDao.getByIdNum(idNum));
        if (orderNeedToCheckOut == null || !orderNeedToCheckOut.getRoom().getRoomNo().equals(roomNum)) {
            OrderMessage message = new OrderMessage();
            message.setCode(OrderMessage.Code.FAILED_CHECK_OUT_WRONG_MATCH);
            message.setMsg("身份证号与所退房间不符！");
            return message;
        }
        float money;
        if (orderNeedToCheckOut.getState() == Order.State.EXPIRE) {
            money = liquidate(roomNum, idNum);
            OrderMessage message = new OrderMessage();
            message.setCode(OrderMessage.Code.FAILED_CHECK_OUT_DEBT);
            message.setMsg("有欠款未付清！");
            message.setPrice(money);
            return message;
        }
        roomDao.checkOut(roomNum);
        //更新订单信息
        orderNeedToCheckOut.setState(Order.State.CHECK_OUT);
        return new OrderMessage("退房成功，期待您的再次光临！", OrderMessage.Code.SUCCESS_Check_OUT);
    }

    public List<Order> listAllOrdersExpire() {
        return orderDao.listByState(Order.State.EXPIRE);
    }

    public List<Order> listAllOrdersValid() {
        return orderDao.listByState(Order.State.VALID);
    }

    public List<Order> findAllExpireOrders() {
        List<Order> result = new ArrayList<>();
        result.addAll(listAllOrdersExpire());
//        result.addAll(listAllOrdersValid().stream().filter(order -> order.getState() == Order.State.EXPIRE).collect(Collectors.toList()));
        for (Order order : listAllOrdersValid()) {
            if (order.getState() == Order.State.EXPIRE)
                result.add(order);
        }
        return result;
    }
}
