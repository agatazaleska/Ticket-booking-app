package com.example.demo.rest;

import com.example.demo.entity.Room;
import com.example.demo.service.room.RoomService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/room_api")
public class RoomRestController {
    private RoomService roomService;

    public RoomRestController(RoomService theRoomService) {
        roomService = theRoomService;
    }

    @GetMapping("/rooms/{screeningId}")
    public Room findByScreeningId(@PathVariable int screeningId) {
        return roomService.findByScreeningId(screeningId);
    }
}
