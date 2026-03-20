package com.university.room_booking.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.university.room_booking.model.Schedule;
import com.university.room_booking.repository.RoomRepository;
import com.university.room_booking.repository.ScheduleRepository;
import com.university.room_booking.repository.TeacherRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ScheduleController {

    private final ScheduleRepository scheduleRepository;
    private final RoomRepository roomRepository;
    private final TeacherRepository teacherRepository;

    public ScheduleController(ScheduleRepository scheduleRepository, RoomRepository roomRepository, TeacherRepository teacherRepository) {
        this.scheduleRepository = scheduleRepository;
        this.roomRepository = roomRepository;
        this.teacherRepository = teacherRepository;
    }

    @GetMapping("/schedule")
    public String showSchedule(Model model) {
        model.addAttribute("schedules", scheduleRepository.findAllScheduleInfo());
        model.addAttribute("rooms", roomRepository.findAll());
        model.addAttribute("teachers", teacherRepository.findAll());
        return "schedule";
    }

    @PostMapping("/schedule/book")
    public String bookRoom(Schedule schedule, RedirectAttributes redirectAttributes) {
        int conflicts = scheduleRepository.countConflicts(
                schedule.getRoomId(),
                schedule.getLessonDate(),
                schedule.getLessonNumber()
        );

        if (conflicts > 0) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Помилка! Ця аудиторія вже зайнята на обрану дату та пару.");
            return "redirect:/schedule";
        }

        scheduleRepository.save(schedule);
        redirectAttributes.addFlashAttribute("successMessage",
                "Аудиторію успішно заброньовано!");

        return "redirect:/schedule";
    }
    @PostMapping("/schedule/delete/{id}")
    public String deleteSchedule(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        scheduleRepository.deleteById(id);

        redirectAttributes.addFlashAttribute("successMessage",
                "Бронювання успішно скасовано!");
        return "redirect:/schedule";
    }
}