package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.service.UserService;

@Controller
public class UserPage extends Page {
    private final UserService userService;

    public UserPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public String users(Model model, @PathVariable String id) {
        User user;
        try {
            user = userService.findById(Long.parseLong(id));
        } catch (Exception ignored) {
            user = null;
        }
        model.addAttribute("displaying_user", user);
        return "UserPage";
    }
}
