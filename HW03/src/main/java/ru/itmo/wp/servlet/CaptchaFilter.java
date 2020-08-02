package ru.itmo.wp.servlet;

import ru.itmo.wp.util.ImageUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;

public class CaptchaFilter extends HttpFilter {
    @Override
    public void init() throws ServletException {
        super.init();
        rng = new Random();
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession curSession = request.getSession();
        Boolean captchaPassed = (Boolean) curSession.getAttribute("Captcha-Passed");
        if (captchaPassed != null && captchaPassed.equals(true)) {
            chain.doFilter(request, response);
        } else {
            if (curSession.getAttribute("Expected-Captcha") != null &&
                    curSession.getAttribute("Expected-Captcha").equals(request.getParameter("User-Captcha"))) {
                curSession.setAttribute("Captcha-Passed", true);
                response.sendRedirect(request.getRequestURI());
                return;
            }
            curSession.setAttribute("Captcha-Passed", false);
            int code = randomInt();
            curSession.setAttribute("Expected-Captcha", Integer.toString(code));
            String form = getCaptchaForm(code);
            DelayedHttpServletResponse delayedResponse = new DelayedHttpServletResponse(response);
            chain.doFilter(request, delayedResponse);
            response.getWriter().print(form);
            response.getWriter().flush();
        }
    }

    private int randomInt() {
        return rng.nextInt(900) + 100;
    }

    private String getCaptchaForm(Integer code) {
        String encodedImage = Base64.getEncoder().encodeToString(ImageUtils.toPng(code.toString()));
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "</head>" +
                "<body style=\"text-align: center; margin-top: 10%;\">" +
                "<img src=\"data:image/png;base64, "
                + encodedImage +
                "\"/>" +
                "<form method=\"get\" action=\"\">" +
                "<label for=\"User-Captcha\">Enter Captcha: </label>" +
                "<input name=\"User-Captcha\" id=\"User-Captcha\"/>" +
                "</form>" +
                "</body>" +
                "</html>";
    }

    private Random rng;
}
