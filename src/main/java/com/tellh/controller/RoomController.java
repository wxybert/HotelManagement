package com.tellh.controller;

import com.tellh.entity.Room;
import com.tellh.entity.model.RoomFilterModel;
import com.tellh.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by tlh on 2016/11/3.
 */
//无需登录也可以访问的接口
@Controller
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @ResponseBody
    @RequestMapping(value = "/{roomNum}", method = RequestMethod.GET)
    public Room getByRoomNumber(@PathVariable("roomNum") String roomNum) {
        return roomService.getByRoomNumber(roomNum);
    }

    @ResponseBody
    @RequestMapping(value = "/byType", method = RequestMethod.GET)
    public List<Room> getByType(@RequestParam("type") Room.Type type) {
        return roomService.getByType(type);
    }

    @ResponseBody
    @RequestMapping(value = "/free", method = RequestMethod.GET)
    public List<Room> listAllFreeRooms(@RequestParam(defaultValue = "1") int pageIndex, @RequestParam(defaultValue = "10") int pageSize) {
        return roomService.listAllFreeRooms(pageIndex, pageSize);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public List<Room> filter(@RequestParam(defaultValue = "1") int pageIndex, @RequestParam(defaultValue = "10") int pageSize,
                             RoomFilterModel model) {
        return roomService.filter(model, pageIndex, pageSize);
    }


}
