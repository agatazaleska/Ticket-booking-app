package com.example.demo.service;

import com.example.demo.dao.RoomRepository;
import com.example.demo.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {
    private RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository theRoomRepository) {
        roomRepository = theRoomRepository;
    }

    @Override
    public Room findById(int roomId) {
        Optional<Room> result = roomRepository.findById(roomId);
        if (result.isEmpty()) {
            // custom exception?
            throw new RuntimeException();
        }
        else {
            return result.get();
        }
    }
}
