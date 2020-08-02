package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.service.PostService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class PostPage extends Page {
    private final PostService postService;

    public PostPage(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post/{id}")
    public String post(Model model, @PathVariable String id) {
        Post post;
        try {
            post = postService.findById(Long.parseLong(id));
        } catch (Exception ignored) {
            post = null;
        }
        model.addAttribute("displayingPost", post);
        model.addAttribute("commentForm", new Comment());
        return "PostPage";
    }

    @PostMapping("/post/{id}")
    public String registerComment(@Valid @ModelAttribute("commentForm") Comment commentForm,
                               BindingResult bindingResult, Model model,
                               HttpSession httpSession, @PathVariable String id) {

        Post post;
        try {
            post = postService.findById(Long.parseLong(id));
        } catch (Exception ignored) {
            putMessage(httpSession, "Incorrect post id");
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("displayingPost", post);
            return "PostPage";
        }

        if (getUser(httpSession) == null) {
            putMessage(httpSession, "There is no logged user");
            return "redirect:/enter";
        }

        commentForm.setPost(post);
        postService.writeComment(post, commentForm, getUser(httpSession));
        return "redirect:/post/{id}";
    }
}