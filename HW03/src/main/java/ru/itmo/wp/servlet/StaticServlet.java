package ru.itmo.wp.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

public class StaticServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();
        String[] uriList = uri.split("[+]");
        boolean setContentType = false;
        for (String currentUri : uriList) {
            File file = new File(getServletContext().getRealPath(".") + "/../../src/main/webapp/static", currentUri);
            if (file.isFile()) {
                processFile(currentUri, file, response, !setContentType);
            } else {
                file = new File(getServletContext().getRealPath("/static"), currentUri);
                if (file.isFile()) {
                    processFile(currentUri, file, response, !setContentType);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    break;
                }
            }
            setContentType = true;
        }
    }

    private void processFile(String uri, File file, HttpServletResponse response, boolean needContentType) throws IOException {
        if (needContentType) {
            response.setContentType(getContentTypeFromName(uri));
        }
        OutputStream outputStream = response.getOutputStream();
        Files.copy(file.toPath(), outputStream);
        outputStream.flush();
    }

    private String getContentTypeFromName(String name) {
        name = name.toLowerCase();

        if (name.endsWith(".png")) {
            return "image/png";
        }

        if (name.endsWith(".jpg")) {
            return "image/jpeg";
        }

        if (name.endsWith(".html")) {
            return "text/html";
        }

        if (name.endsWith(".css")) {
            return "text/css";
        }

        if (name.endsWith(".js")) {
            return "application/javascript";
        }

        throw new IllegalArgumentException("Can't find content type for '" + name + "'.");
    }
}
