package com.tellh.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by tlh on 2016/10/31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:SpringAppContext.xml")
public class CustomerServiceTest {
    @Autowired
    private CustomerService service;

    @Test
    public void getByIdNum() throws Exception {
        assertTrue(service.getByIdNum("110101198010018233").getName().equals("张块"));
    }

    @Test
    public void promote() throws Exception {
        assertFalse(service.getByIdNum("110101198010018233").isVIP());
        service.promote("110101198010018233");
        assertTrue(service.getByIdNum("110101198010018233").isVIP());
    }

    @Test
    public void register() throws Exception {
        service.register("逗比哈哈哈", "110101198010018238", "123456");
        assertTrue(service.getByIdNum("110101198010018238").getName().equals("逗比哈哈哈"));
    }

    @Test
    public void login() throws Exception {
        assertTrue(service.login("110101198010018233", "123456") != null);
        assertTrue(service.login("110101198010018233", "abc123456") == null);
    }

    @Test
    public void listAllOrders() throws Exception {
        assertTrue(service.listAllOrders("110101198010018233").size() == 2);
    }

    @Test
    public void getByIdNumWithOrders() throws Exception {
        assertTrue(service.getByIdNumWithOrders("110101198010018233").getOrders().size() == 2);
    }

    @Test
    public void getRoomHasCheckIn() throws Exception {
        assertTrue(service.getRoomHasCheckIn("110101198010018233").getRoomNo().equals("100"));
    }

    @Test
    public void getRoomNeedToCheckOut() throws Exception {
        assertTrue(service.getRoomNeedToCheckOut("110101198010018233").getRoomNo().equals("100"));
        assertNull(service.getRoomNeedToCheckOut("110101198010016414"));
    }

}