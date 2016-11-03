package com.tellh.service;

import com.tellh.entity.Room;
import com.tellh.entity.model.RoomFilterModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by tlh on 2016/10/31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:HibernateApplicationContext.xml")
public class RoomServiceTest {
    @Autowired
    RoomService service;

    @Before
    public void before() {
        System.out.println("**************************");
    }

    @Test
    public void getByRoomNumber() throws Exception {
        Room room = service.getByRoomNumber("115");
        System.out.println(room);
    }

    @Test
    public void getById() throws Exception {
        System.out.println(service.getById(1));
    }

    @Test
    public void getByType() throws Exception {
        System.out.println(service.getByType(Room.Type.DELUXE_STANDARD));
    }

    @Test
    public void listAllFreeRooms() throws Exception {
        System.out.println(service.listAllFreeRooms());
    }

    @Test
    public void listAllFreeRooms1() throws Exception {
        int pageIndex = 2;//第二页
        int pageSize = 3;//每页条目数
        System.out.println(service.listAllFreeRooms(pageIndex, pageSize));
    }

    @Test
    public void listAllFreeRooms2() throws Exception {
        System.out.println(service.listAllFreeRooms(Room.Type.DELUXE_SUITE));
    }

    @Test
    public void listAllFreeRooms3() throws Exception {
        int pageIndex = 1;//第二页
        int pageSize = 5;//每页条目数
        System.out.println(service.listAllFreeRooms(Room.Type.DELUXE_SUITE, pageIndex, pageSize));
    }

    @Test
    public void listAll() throws Exception {
        int pageIndex = 2;//第二页
        int pageSize = 5;//每页条目数
        System.out.println(service.listAll(pageIndex, pageSize));
    }

    @Test
    public void filter() throws Exception {
        RoomFilterModel model = new RoomFilterModel();
        model.setSize(2);
        model.setType(Room.Type.DELUXE_STANDARD);
        model.setPrice(500);
        System.out.println(service.filter(model));
    }

}