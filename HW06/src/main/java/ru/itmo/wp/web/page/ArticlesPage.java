package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.ArticleService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/** @noinspection unused*/
public class ArticlesPage {
    private final ArticleService articleService = new ArticleService();

    private void action(HttpServletRequest request, Map<String, Object> view) {
        if (request.getSession().getAttribute("user") == null) {
            throw new RedirectException("/index");
        }
        view.put("articles", articleService.findAll());
        view.put("user", request.getSession().getAttribute("user"));
    }

    private void setHidden(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        long ownerId = articleService.find(Long.parseLong(request.getParameter("articleId"))).getUserId();
        if (((User)request.getSession().getAttribute("user")).getId() != ownerId) {
            throw new ValidationException("Bad owner");
        }
        articleService.setHidden(Long.parseLong(request.getParameter("articleId")),
                                 Boolean.parseBoolean(request.getParameter("hiddenValue")));
    }
}
