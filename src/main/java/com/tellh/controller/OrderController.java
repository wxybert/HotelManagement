package com.tellh.controller;

import com.tellh.entity.Account;
import com.tellh.entity.Customer;
import com.tellh.entity.Order;
import com.tellh.entity.OrderMessage;
import com.tellh.entity.model.OrderModel;
import com.tellh.service.OrderService;
import com.tellh.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by tlh on 2016/11/4.
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public OrderMessage orderRoom(OrderModel model, HttpServletRequest req) {
        Account account = AccountUtils.getUserFromSession(req);
        if (!(account instanceof Customer))
            return new OrderMessage("非顾客不能订房，请重新登录！", OrderMessage.Code.FAILED_CHECK_IN);
        model.setCustomerIdNum(((Customer) account).getIdNum());
        return orderService.orderRoom(model);
    }
}
