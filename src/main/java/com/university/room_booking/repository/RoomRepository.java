package com.university.room_booking.repository;

import com.university.room_booking.model.Room;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RoomRepository extends CrudRepository<Room, Long> {

    @Query("SELECT * FROM rooms WHERE id NOT IN " +
            "(SELECT room_id FROM schedules WHERE lesson_date = :date AND lesson_number = :lessonNumber)")
    List<Room> findFreeRooms(@Param("date") LocalDate date,
                             @Param("lessonNumber") Integer lessonNumber);
}