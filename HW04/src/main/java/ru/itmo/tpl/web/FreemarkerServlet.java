package ru.itmo.tpl.web;

import freemarker.template.*;
import ru.itmo.tpl.util.DataUtil;
import ru.itmo.tpl.util.DebugUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class FreemarkerServlet extends HttpServlet {
    private Configuration freemarkerConfiguration;

    @Override
    public void init() throws ServletException {
        super.init();

        freemarkerConfiguration = new Configuration(Configuration.VERSION_2_3_29);

        File freemarkerDirectory = DebugUtil.getFile(getServletContext(), "WEB-INF/templates");
        try {
            freemarkerConfiguration.setDirectoryForTemplateLoading(freemarkerDirectory);
        } catch (IOException e) {
            throw new ServletException("Unable to configure freemarker configuration:"
                    + " freemarkerConfiguration.setDirectoryForTemplateLoading(freemarkerDirectory) failed"
                    + " [freemarkerDirectory=" + freemarkerDirectory + "].", e);
        }

        freemarkerConfiguration.setDefaultEncoding(StandardCharsets.UTF_8.name());
        freemarkerConfiguration.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        freemarkerConfiguration.setLogTemplateExceptions(false);
        freemarkerConfiguration.setWrapUncheckedExceptions(true);
        freemarkerConfiguration.setFallbackOnNullLoopVariable(false);
        freemarkerConfiguration.setNumberFormat("computer");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        String uri = request.getRequestURI();
        if (uri.matches("[/]*")) {
            response.sendRedirect("/index");
            response.setStatus(307);
            return;
        }

        Template template;
        try {
            template = freemarkerConfiguration.getTemplate(
                    URLDecoder.decode(uri, StandardCharsets.UTF_8.name()) + ".ftlh");
        } catch (TemplateNotFoundException ignored) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            template = freemarkerConfiguration.getTemplate("404.ftl");
        }

        response.setContentType("text/html");
        Map<String, Object> data = new HashMap<>();
        data.put("cur_uri", uri);
        putData(request, data);

        try {
            template.process(data, response.getWriter());
        } catch (TemplateException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }

    private void putData(HttpServletRequest request, Map<String, Object> data) {
        for (Map.Entry<String, String[]> e : request.getParameterMap().entrySet()) {
            if (e.getValue() != null && e.getValue().length == 1) {
                Object value = e.getValue()[0];
                if (e.getKey().endsWith("_id")) {
                    try {
                        value = Long.parseLong(e.getValue()[0]);
                    } catch (Exception ignored) {}
                }
                data.put(e.getKey(), value);
            }
        }
        DataUtil.putData(data);
    }
}
