package com.example.demo.rest;

import com.example.demo.entity.Room;
import com.example.demo.exception.RoomException;
import com.example.demo.service.room.RoomService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/room_api")
public class RoomRestController {
    private RoomService roomService;

    public RoomRestController(RoomService theRoomService) {
        roomService = theRoomService;
    }

    @GetMapping("/rooms/{roomNumber}")
    public Room findByNumber(@PathVariable int roomNumber) {
        Optional<Room> maybeRoom = roomService.findByNumber(roomNumber);
        if (maybeRoom.isEmpty()) {
            throw new RoomException("Error. Room not found.");
        }
        return maybeRoom.get();
    }
}
