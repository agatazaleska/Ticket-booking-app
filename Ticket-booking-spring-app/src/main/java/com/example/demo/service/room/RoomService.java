package com.example.demo.service.room;

import com.example.demo.entity.Room;

import java.util.Optional;

public interface RoomService {
    Optional<Room> findByNumber(int roomNumber);
}
