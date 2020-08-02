package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class UsersPage extends Page {
    private final UserService userService;

    public UsersPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/all")
    public String users(Model model, HttpSession httpSession) {
        model.addAttribute("users", userService.findAll());
        if (getUser(httpSession) != null) {
            model.addAttribute("hasUser", true);
        }
        return "UsersPage";
    }

    @PostMapping("/users/all")
    public String changeDisabled(@ModelAttribute("userId") String userId, @ModelAttribute("isDisabled") String disabled,
                                 HttpSession httpSession) {
        try {
            long id = Long.parseLong(userId);
            if (userService.findById(id) == null) {
                putMessage(httpSession, "No such user");
                return "redirect:/users/all";
            }
            userService.setDisabled(id, Boolean.parseBoolean(disabled));
        } catch (NumberFormatException ignored) {
            // No operations.
        }
        return "redirect:/users/all";
    }
}
