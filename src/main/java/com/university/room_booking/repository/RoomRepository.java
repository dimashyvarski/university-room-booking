package com.university.room_booking.repository;

import com.university.room_booking.model.Room;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<Room, Long> {
}