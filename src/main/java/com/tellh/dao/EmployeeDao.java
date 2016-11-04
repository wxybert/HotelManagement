package com.tellh.dao;

import com.tellh.entity.Employee;
import org.springframework.stereotype.Repository;

/**
 * Created by tlh on 2016/11/4.
 */
@Repository
public class EmployeeDao extends BaseDao {
    public Employee get(String login) {
        return (Employee) getSession().getNamedQuery("get")
                .setParameter("login", login)
                .uniqueResult();
    }
}
