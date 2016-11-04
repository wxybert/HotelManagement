package com.tellh.dao;

import com.tellh.DatabaseException;
import com.tellh.entity.Order;
import com.tellh.entity.Room;
import com.tellh.entity.model.RoomFilterModel;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tlh on 2016/10/31.
 */
@Repository
public class RoomDao extends BaseDao {
    public Room getByRoomNumber(String roomNum) {
        return (Room) getSession().getNamedQuery("ByRoomNumber")
                .setParameter("roomNum", roomNum)
                .uniqueResult();
    }

    public Room getById(int id) {
        return getSession().get(Room.class, id);
    }

    public List<Room> getByType(Room.Type type) {
        return getSession().getNamedQuery("ByType")
                .setParameter("room_type", type.ordinal())
                .list();
    }

    public List<Room> listAllFreeRooms() {
        return getSession().getNamedQuery("ListIsFree")
                .list();
    }

    public List<Room> listAllNotFreeRooms() {
        return getSession().getNamedQuery("ListIsNotFree")
                .list();
    }

    //分頁
    public List<Room> listAllFreeRooms(int pageIndex, int pageSize) {
        return getSession().getNamedQuery("ListIsFree")
                .setFirstResult((pageIndex - 1) * pageSize)
                .setMaxResults(pageSize)
                .list();
    }

    public List<Room> listAllFreeRooms(Room.Type type) {
        return getSession().getNamedQuery("ListIsFreeByType")
                .setParameter("type", type.ordinal())
                .list();
    }

    public List<Room> listAllFreeRooms(Room.Type type, int pageIndex, int pageSize) {
        return getSession().getNamedQuery("ListIsFreeByType")
                .setParameter("type", type.ordinal())
                .setFirstResult((pageIndex - 1) * pageSize)
                .setMaxResults(pageSize)
                .list();
    }

    public List<Room> listAll(int pageIndex, int pageSize) {
        return getSession().createQuery("FROM Room", Room.class)
                .setFirstResult((pageIndex - 1) * pageSize)
                .setMaxResults(pageSize)
                .list();
    }

    public void checkOut(String roomNum) throws DatabaseException {
        Room room = getByRoomNumber(roomNum);
        if (room.isFree())
            throw new DatabaseException("这个房间是空的！不能办理退房。");
        room.setFree(true);
        getSession().save(room);
    }

    public void checkIn(String roomNum) throws DatabaseException {
        Room room = getByRoomNumber(roomNum);
        if (!room.isFree())
            throw new DatabaseException("这个房间已被预订！不能办理开房。");
        room.setFree(false);
        getSession().save(room);
    }

    public List<Order> listAllOrders(String roomNo) {
        return getByRoomNumber(roomNo).getOrders();
    }

    public List<Room> filter(RoomFilterModel model, int pageIndex, int pageSize) {
        Conjunction conjunction = Restrictions.conjunction();
        conjunction.add(Restrictions.eq("isFree", model.isFree()));
        if (model.getPrice() > 0)
            conjunction.add(Restrictions.le("price", model.getPrice()));
        if (model.getType() != null)
            conjunction.add(Restrictions.eq("type", model.getType()));
        if (model.getSize() != 0)
            conjunction.add(Restrictions.eq("size", model.getSize()));
        return getSession().createCriteria(Room.class)
                .setFirstResult((pageIndex - 1) * pageSize)
                .setMaxResults(pageSize)
                .add(conjunction).list();
    }
}
