package ru.itmo.wp.web.page;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.service.EventService;
import ru.itmo.wp.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public abstract class Page {
    final UserService userService = new UserService();
    final EventService eventService = new EventService();

    private HttpServletRequest request;
    private Map<String, Object> view;

    public void before(HttpServletRequest request, Map<String, Object> view) {
        this.request = request;
        this.view = view;
        putUser();
        putMessage();
    }

    public void after(HttpServletRequest request, Map<String, Object> view) {
        view.put("userCount", userService.findCount());
    }

    private void putUser() {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            view.put("user", user);
        }
    }

    void putMessage() {
        String message = (String) request.getSession().getAttribute("message");
        if (!Strings.isNullOrEmpty(message)) {
            view.put("message", message);
            request.getSession().removeAttribute("message");
        }
    }

    void setMessage(String message) {
        request.getSession().setAttribute("message", message);
    }

    void setUser(User user) {
        request.getSession().setAttribute("user", user);
    }

    User getUser() {
        return (User) request.getSession().getAttribute("user");
    }
}
