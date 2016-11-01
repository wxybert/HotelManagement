package com.tellh.service;

import com.tellh.dao.CustomerDao;
import com.tellh.dao.OrderDao;
import com.tellh.dao.RoomDao;
import com.tellh.entity.Customer;
import com.tellh.entity.Order;
import com.tellh.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

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

    public boolean orderRoom(Order order, String customerIdNum, String roomNum) {
        int days = order.getDays();
        if (days == 0)
            return false;
        Customer customer = customerDao.getByIdNum(customerIdNum);
        //有房间未退，不能开房
        if (CustomerService.getRoomNeedToCheckOut(customer) != null)
            return false;
        roomDao.checkIn(roomNum);
        Room room = roomDao.getByRoomNumber(roomNum);
        order.setCustomer(customer);
        order.setRoom(room);
        //设置开房时间
        order.setdCheckIn(new Timestamp(System.currentTimeMillis()));
        order.setDeadline(new Timestamp(getDateShouldCheckOut(days).getTimeInMillis()));
        orderDao.insert(order);
        return true;
    }

    public boolean checkOutRoom(String roomNum, String idNum) {
        Room roomNeedToCheckOut = CustomerService.getRoomNeedToCheckOut(customerDao.getByIdNum(idNum));
        if (roomNeedToCheckOut == null || !roomNeedToCheckOut.getRoomNo().equals(roomNum)) return false;
        roomDao.checkOut(roomNum);
        return true;
    }

    public List<Order> listAllOrdersExpire() {
        return orderDao.listByState(Order.State.EXPIRE);
    }

    public List<Order> listAllOrdersValid() {
        return orderDao.listByState(Order.State.VALID);
    }

    public Calendar getDateShouldCheckOut(int days) {
        Calendar date = Calendar.getInstance();
        // reset hour, minutes, seconds and millis
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        date.add(Calendar.DAY_OF_MONTH, days);
        date.add(Calendar.HOUR_OF_DAY, 12);
        return date;
    }
}
