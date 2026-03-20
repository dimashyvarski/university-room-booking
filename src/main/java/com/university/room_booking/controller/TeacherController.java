package com.university.room_booking.controller;

import com.university.room_booking.model.Teacher;
import com.university.room_booking.repository.TeacherRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TeacherController {

    private final TeacherRepository teacherRepository;

    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @GetMapping("/teachers")
    public String showTeachers(Model model) {
        model.addAttribute("teachers", teacherRepository.findAll());
        return "teachers";
    }

    @PostMapping("/teachers/add")
    public String addTeacher(Teacher teacher, RedirectAttributes redirectAttributes) {
        teacherRepository.save(teacher);
        redirectAttributes.addFlashAttribute("successMessage",
                "Нового викладача успішно додано до системи!");
        return "redirect:/teachers";
    }
}