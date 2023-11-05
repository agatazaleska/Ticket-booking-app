package com.example.demo.service.room;

import com.example.demo.dao.RoomRepository;
import com.example.demo.entity.Room;
import com.example.demo.entity.Screening;
import com.example.demo.exception.DataBaseException;
import com.example.demo.exception.ScreeningException;
import com.example.demo.service.room.RoomService;
import com.example.demo.service.screening.ScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {
    private RoomRepository roomRepository;
    private ScreeningService screeningService;

    @Autowired
    public RoomServiceImpl(
            RoomRepository theRoomRepository,
            ScreeningService theScreeningService) {
        roomRepository = theRoomRepository;
        screeningService = theScreeningService;
    }

    @Override
    public Optional<Room> findById(int roomId) {
        return roomRepository.findById(roomId);
    }

    @Override
    public Room findByScreeningId(int screeningId) {
        Optional<Screening> maybeScreening = screeningService.findById(screeningId);
        if (maybeScreening.isEmpty()) {
            throw new ScreeningException("Error. Screening not found.");
        }
        Optional<Room> maybeRoom = findById(maybeScreening.get().getRoomNumber());
        if (maybeRoom.isEmpty()) {
            throw new DataBaseException("Database error. Room for this " +
                                        "screening not found.");
        }
        return maybeRoom.get();
    }
}
