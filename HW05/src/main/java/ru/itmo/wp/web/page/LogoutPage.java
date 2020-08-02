package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;

/** @noinspection unused*/
public class LogoutPage extends Page {
    private void action(HttpServletRequest request) {
        eventService.addEvent(getUser().getId(), Event.Type.LOGOUT);
        request.getSession().removeAttribute("user");

        setMessage("Good bye. Hope to see you soon!");
        //request.getSession().setAttribute("message", "Good bye. Hope to see you soon!");
        throw new RedirectException("/index");
    }
}
