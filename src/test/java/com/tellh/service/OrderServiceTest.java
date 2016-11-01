package com.tellh.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Created by tlh on 2016/11/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:HibernateApplicationContext.xml")
public class OrderServiceTest {
    @Autowired
    OrderService service;

    @Test
    public void getDateShouldCheckOut() throws Exception {
        Calendar calendar = service.getDateShouldCheckOut(2);
        System.out.println(new Timestamp(calendar.getTimeInMillis()));
    }

}