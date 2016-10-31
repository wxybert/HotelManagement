package com.tellh.service;

import com.sun.net.httpserver.Authenticator;
import com.tellh.DatabaseException;
import com.tellh.dao.RoomDao;
import com.tellh.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tlh on 2016/10/31.
 */

@Service
public class RoomService {
    @Autowired
    private RoomDao dao;

    public Room getByRoomNumber(String roomNum) {
        return dao.getByRoomNumber(roomNum);
    }

    public Room getById(int id) {
        return dao.getById(id);
    }

    public List<Room> getByType(Room.Type type) {
        return dao.getByType(type);
    }

    public List<Room> listAllFreeRooms() {
        return dao.listAllFreeRooms();
    }

    public List<Room> listAllFreeRooms(int pageIndex, int pageSize) {
        return dao.listAllFreeRooms(pageIndex, pageSize);
    }

    public List<Room> listAllFreeRooms(Room.Type type) {
        return dao.listAllFreeRooms(type);
    }

    public List<Room> listAllFreeRooms(Room.Type type, int pageIndex, int pageSize) {
        return dao.listAllFreeRooms(type, pageIndex, pageSize);
    }

    public List<Room> listAll(int pageIndex, int pageSize) {
        return dao.listAll(pageIndex, pageSize);
    }

    public void checkIn(String roomNum) {
        dao.checkIn(roomNum);
        // TODO: 2016/10/31 插入订单记录
    }

    public void checkOut(String roomNum) {
        dao.checkOut(roomNum);
        // TODO: 2016/10/31 更新订单状态
    }
}