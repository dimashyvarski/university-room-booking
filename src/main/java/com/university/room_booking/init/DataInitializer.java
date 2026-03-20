package com.university.room_booking.init;

import com.university.room_booking.model.Room;
import com.university.room_booking.model.Teacher;
import com.university.room_booking.repository.RoomRepository;
import com.university.room_booking.repository.TeacherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoomRepository roomRepository;
    private final TeacherRepository teacherRepository;

    public DataInitializer(RoomRepository roomRepository, TeacherRepository teacherRepository) {
        this.roomRepository = roomRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (roomRepository.count() == 0) {

            Room room1 = new Room();
            room1.setNumber("47");
            room1.setCapacity(30);
            room1.setType("Лекційна");
            roomRepository.save(room1);

            Room room2 = new Room();
            room2.setNumber("25");
            room2.setCapacity(15);
            room2.setType("Комп'ютерний клас");
            roomRepository.save(room2);

            System.out.println("Аудиторії успішно додані в базу!");
        }

        if (teacherRepository.count() == 0) {

            Teacher t1 = new Teacher();
            t1.setFullName("Коваленко Андрій Сергійович");
            t1.setDepartment("Кафедра компютерних систем");
            t1.setEmail("kovalenko@university.edu");
            teacherRepository.save(t1);

            Teacher t2 = new Teacher();
            t2.setFullName("Мельник Олена Ігорівна");
            t2.setDepartment("Кафедра електроніки");
            t2.setEmail("melnik@university.edu");
            teacherRepository.save(t2);

            System.out.println("Викладачі успішно додані в базу!");
        }
    }
}