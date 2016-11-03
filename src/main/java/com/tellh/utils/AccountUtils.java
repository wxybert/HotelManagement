package com.tellh.utils;

import com.tellh.entity.Account;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by tlh on 2016/11/3.
 */
public class AccountUtils {
    private static final String ACCOUNT_INFO = "ACCOUNT";

    public static void addAccountIntoSession(Account account, HttpServletRequest request) {
        request.getSession(true).setAttribute(ACCOUNT_INFO, account);
    }

    public static Account getUserFromSession(HttpServletRequest request) {
        return (Account) request.getSession(true).getAttribute(ACCOUNT_INFO);
    }

    public static void removeUserFromSession(HttpServletRequest request) {
        request.getSession(true).removeAttribute(ACCOUNT_INFO);
    }

}
