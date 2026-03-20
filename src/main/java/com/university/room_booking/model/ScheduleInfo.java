package com.university.room_booking.model;

import java.time.LocalDate;

public record ScheduleInfo(
        Long id,
        String roomNumber,
        String teacherName,
        LocalDate lessonDate,
        int lessonNumber,
        String groupName
) {}