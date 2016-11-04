package com.tellh.service;

import com.tellh.dao.EmployeeDao;
import com.tellh.entity.Customer;
import com.tellh.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tlh on 2016/11/4.
 */
@Service
public class EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;

    public Employee login(String login, String password) {
        Employee employee = employeeDao.get(login);
        if (employee == null || employee.getPassword() == null || !employee.getPassword().equals(password))
            return null;
        return employee;
    }
}
