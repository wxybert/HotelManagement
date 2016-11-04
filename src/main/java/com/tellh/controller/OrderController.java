package com.tellh.controller;

import com.tellh.DatabaseException;
import com.tellh.entity.*;
import com.tellh.entity.model.CustomerWrapper;
import com.tellh.entity.model.OrderModel;
import com.tellh.service.OrderService;
import com.tellh.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

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

    @ResponseBody
    @RequestMapping("/check_out")
    public OrderMessage checkOutRoom(HttpServletRequest req) {
        Account account = AccountUtils.getUserFromSession(req);
        if (!(account instanceof Customer))
            return new OrderMessage("非顾客不能退房，请重新登录！", OrderMessage.Code.FAILED_CHECK_OUT_NOT_FOUND);
        return orderService.checkOutRoom(((Customer) account).getIdNum());
    }

    //以下接口仅提供给酒店工作人员访问
    @ResponseBody
    @RequestMapping("/check_out/{idNum}")
    public OrderMessage checkOutRoom(HttpServletRequest req, @PathVariable("idNum") String idNum) {
        Account account = AccountUtils.getUserFromSession(req);
        if (!(account instanceof Employee))
            return new OrderMessage("非酒店工作人员不能为其他顾客办理退房手续！", OrderMessage.Code.FAILED_CHECK_OUT_NOT_FOUND);
        return orderService.checkOutRoom(idNum);
    }

    @ResponseBody
    @RequestMapping("/paidDebt/check_out/{idNum}")
    public OrderMessage paidDebtAndCheckOut(HttpServletRequest req, @PathVariable("idNum") String idNum) {
        Account account = AccountUtils.getUserFromSession(req);
        if (!(account instanceof Employee))
            return new OrderMessage("请到酒店柜台办理退房手续！", OrderMessage.Code.FAILED_CHECK_OUT_NOT_FOUND);
        orderService.paidDebt(idNum);
        return orderService.checkOutRoom(idNum);
    }

    @ResponseBody
    @RequestMapping(value = "/valid", method = RequestMethod.GET)
    public List<Order> listAllOrdersValid(HttpServletRequest req) {
        Account account = AccountUtils.getUserFromSession(req);
        if (!(account instanceof Employee))
            return Collections.emptyList();
        return CustomerWrapper.wrapCustomerInOrders(orderService.listAllOrdersValid());
    }

    @ResponseBody
    @RequestMapping(value = "/expire", method = RequestMethod.GET)
    public List<Order> listAllOrdersExpire(HttpServletRequest req) {
        Account account = AccountUtils.getUserFromSession(req);
        if (!(account instanceof Employee))
            return Collections.emptyList();
        return CustomerWrapper.wrapCustomerInOrders(orderService.listAllOrdersExpire());
    }

    @ResponseBody
    @RequestMapping(value = "/check_expire", method = RequestMethod.GET)
    public List<Order> findAllExpireOrders(HttpServletRequest req) {
        Account account = AccountUtils.getUserFromSession(req);
        if (!(account instanceof Employee))
            return Collections.emptyList();
        return CustomerWrapper.wrapCustomerInOrders(orderService.findAllExpireOrders());
    }

    @ResponseBody
    @ExceptionHandler({DatabaseException.class})
    public OrderMessage handleException(DatabaseException e) {
        return new OrderMessage(e.getMessage(), OrderMessage.Code.FAILED_CHECK_IN);
    }

}
