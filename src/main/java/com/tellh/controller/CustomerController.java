package com.tellh.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.tellh.entity.*;
import com.tellh.service.CustomerService;
import com.tellh.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by tlh on 2016/11/3.
 */
@Controller
public class CustomerController {
    static {
        //关闭循环引用
        JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.DisableCircularReferenceDetect.getMask();
    }

    @Autowired
    private CustomerService customerService;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public AccountMessage login(String idNum, String password, HttpServletRequest request) {
        Customer customer = customerService.login(idNum, password);
        AccountMessage message = new AccountMessage();
        if (customer != null) {
            message.setCode(AccountMessage.Code.SUCCESS_LOGIN);
            message.setMsg("登录成功");
            message.setAccount(customer);
            AccountUtils.addAccountIntoSession(customer, request);
        } else {
            message.setCode(AccountMessage.Code.FAILED_LOGIN);
            message.setMsg("登录失败");
        }
        return message;
    }

    @ResponseBody
    @RequestMapping(value = "/logout")
    public AccountMessage logout(HttpServletRequest request) {
        AccountUtils.removeUserFromSession(request);
        return new AccountMessage("成功登出", AccountMessage.Code.SUCCESS_LOGOUT);
    }


    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public AccountMessage register(@RequestParam("name") String name, String idNum, String password) {
        AccountMessage message = new AccountMessage();
        try {
            customerService.register(name, idNum, password);
            message.setMsg("注册成功");
            message.setCode(AccountMessage.Code.SUCCESS_REGISTER);
        } catch (Exception e) {
            message.setMsg("注册失败");
            message.setCode(AccountMessage.Code.FAILED_REGISTER);
        } finally {
            return message;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/{userId}/orders", method = RequestMethod.GET)
    public List<Order> listAllOrders(@PathVariable("userId") String idNum, HttpServletRequest request) {
        Account account = AccountUtils.getUserFromSession(request);
        if (account instanceof Employee || ((Customer) account).getIdNum().equals(idNum))
            return customerService.listAllOrders(idNum);
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/{userId}/room/check_in", method = RequestMethod.GET)
    public Room getRoomHasCheckIn(@PathVariable("userId") String idNum, HttpServletRequest request) {
        Account account = AccountUtils.getUserFromSession(request);
        if (account instanceof Employee || ((Customer) account).getIdNum().equals(idNum))
            return customerService.getRoomHasCheckIn(idNum);
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/{userId}/room/need_check_out", method = RequestMethod.GET)
    public Room getRoomNeedToCheckOut(@PathVariable("userId") String idNum, HttpServletRequest request) {
        Account account = AccountUtils.getUserFromSession(request);
        if (account instanceof Employee || ((Customer) account).getIdNum().equals(idNum))
            return customerService.getRoomNeedToCheckOut(idNum);
        return null;
    }

    @ResponseBody
    @ExceptionHandler({NullPointerException.class})
    public AccountMessage handleException(Exception e) {
        e.printStackTrace();
        return new AccountMessage("请求的用户还没注册", AccountMessage.Code.NOT_FOUND);
    }
}
