package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.domain.Notice;
import ru.itmo.wp.service.NoticeService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class NoticesPage extends Page {
    private final NoticeService noticeService;

    public NoticesPage(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/notices")
    public String registerGet(Model model, HttpSession httpSession) {
        if (getUser(httpSession) == null) {
            return "redirect:/";
        }
        model.addAttribute("noticeForm", new Notice());
        return "NoticesPage";
    }

    @PostMapping("/notices")
    public String registerPost(@Valid @ModelAttribute("noticeForm") Notice noticeForm,
                               BindingResult bindingResult,
                               HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "NoticesPage";
        }

        if (getUser(httpSession) == null) {
            putMessage(httpSession, "There is no logged user");
            return "redirect:/enter";
        }

        noticeService.add(noticeForm);
        putMessage(httpSession, "Congrats, you have been notice");
        return "redirect:/";
    }
}
