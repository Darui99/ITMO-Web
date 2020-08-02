package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/** @noinspection unused*/
public class EnterPage extends Page {
    private void action() {
        // No operations.
    }

    private void enter(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        String loginOrEmail = request.getParameter("loginOrEmail");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("passwordConfirmation");

        User user = userService.validateAndFindByLoginOrEmailAndPassword(loginOrEmail, password);
        setUser(user);
        //request.getSession().setAttribute("user", user);
        setMessage("Hello, " + user.getLogin());
        //request.getSession().setAttribute("message", "Hello, " + user.getLogin());
        eventService.addEvent(user.getId(), Event.Type.ENTER);

        throw new RedirectException("/index");
    }
}
