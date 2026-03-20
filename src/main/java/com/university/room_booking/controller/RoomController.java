package com.university.room_booking.controller;

import com.university.room_booking.model.Room;
import com.university.room_booking.repository.RoomRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RoomController {

    private final RoomRepository roomRepository;

    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping("/rooms")
    public String showRooms(Model model) {
        model.addAttribute("rooms", roomRepository.findAll());
        return "rooms";
    }

    @PostMapping("/rooms/add")
    public String addRoom(Room room, RedirectAttributes redirectAttributes) {
        roomRepository.save(room);

        redirectAttributes.addFlashAttribute("successMessage",
                "Нову аудиторію успішно додано!");

        return "redirect:/rooms";
    }
}