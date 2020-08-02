package ru.itmo.wp.servlet;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

public class MessageServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
        messagesList = new ArrayList<>();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, RuntimeException {
        String uri = request.getRequestURI();
        if ("/message/auth".equals(uri)) {
            String user = request.getParameter("user");
            if (user == null) {
                user = "";
            }
            request.getSession().setAttribute("user", user);
            String json = new Gson().toJson(user);
            sendJson(response, json);
        }
        if ("/message/findAll".equals(uri)) {
            String json = new Gson().toJson(messagesList);
            sendJson(response, json);
        }
        if ("/message/add".equals(uri)) {
            String user = (String) request.getSession().getAttribute("user");
            if (user == null) {
                throw new RuntimeException("Adding message without authorization");
            }
            String text = request.getParameter("text");
            if (text == null || "".equals(text)) {
                throw new RuntimeException("Adding empty text");
            }
            messagesList.add(new Message(user, text));
        }
    }

    private void sendJson(HttpServletResponse response, String json) throws IOException {
        response.setContentType("application/json");
        response.getWriter().print(json);
        response.getWriter().flush();
    }

    private class Message {
        private String user, text;

        Message(String user, String text) {
            this.user = user;
            this.text = text;
        }

        String getUser() {
            return user;
        }

        String getText() {
            return text;
        }
    }

    private ArrayList<Message> messagesList;
}
