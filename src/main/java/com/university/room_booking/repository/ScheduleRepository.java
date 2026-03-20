package com.university.room_booking.repository;

import org.springframework.data.repository.query.Param;
import com.university.room_booking.model.Schedule;
import com.university.room_booking.model.ScheduleInfo;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.time.LocalDate;

public interface ScheduleRepository extends CrudRepository<Schedule, Long> {

    @Query("SELECT COUNT(*) FROM schedules WHERE room_id = :roomId " +
            "AND lesson_date = :lessonDate AND lesson_number = :lessonNumber")
    int countConflicts(@Param("roomId") Long roomId,
                       @Param("lessonDate") LocalDate lessonDate,
                       @Param("lessonNumber") int lessonNumber);

    @Query("""
        SELECT s.id, r.number AS room_number, t.full_name AS teacher_name,
               s.lesson_date, s.lesson_number, s.group_name
        FROM schedules s
        JOIN rooms r ON s.room_id = r.id
        JOIN teachers t ON s.teacher_id = t.id
        ORDER BY s.lesson_date, s.lesson_number
    """)
    List<ScheduleInfo> findAllScheduleInfo();

    @Query("""
        SELECT s.id, r.number AS room_number, t.full_name AS teacher_name,
               s.lesson_date, s.lesson_number, s.group_name
        FROM schedules s
        JOIN rooms r ON s.room_id = r.id
        JOIN teachers t ON s.teacher_id = t.id
        WHERE (:roomId IS NULL OR s.room_id = :roomId)
          AND (:lessonDate IS NULL OR s.lesson_date = :lessonDate)
          AND (:lessonNumber IS NULL OR s.lesson_number = :lessonNumber)
          AND (:groupName IS NULL OR :groupName = '' OR LOWER(s.group_name) LIKE LOWER(CONCAT('%', :groupName, '%')))
        ORDER BY s.lesson_date, s.lesson_number
    """)
    List<ScheduleInfo> findFilteredScheduleInfo(@Param("roomId") Long roomId,
                                                @Param("lessonDate") LocalDate lessonDate,
                                                @Param("lessonNumber") Integer lessonNumber,
                                                @Param("groupName") String groupName);
}