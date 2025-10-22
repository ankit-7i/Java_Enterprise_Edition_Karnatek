package com.jee.servlet.basic;

import java.io.PrintWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.ServletException;

public class Date_and_TimeServlet implements Servlet {

    private ServletConfig config;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
        System.out.println("Date_and_TimeServlet initialized");
    }

    @Override
    public void service(ServletRequest req, ServletResponse resp)
            throws ServletException, IOException {
        System.out.println("service(req, resp) executed for Date_and_TimeServlet");

        // Set content type to HTML
        resp.setContentType("text/html");

        // Get current local date and time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy HH:mm:ss");

        String formattedDateTime = now.format(formatter);

        // Write response to browser
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Local Date and Time</title>");
        out.println("<meta http-equiv='refresh' content='1'>"); // Refresh every 1 second like a clock
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; background: #111; color: #0f0; text-align: center; margin-top: 15%; }");
        out.println("h2 { font-size: 2em; }");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>🕒 Current Local Date and Time:</h2>");
        out.println("<h2>" + formattedDateTime + "</h2>");
        out.println("</body>");
        out.println("</html>");

        out.close();
    }

    @Override
    public void destroy() {
        System.out.println("Date_and_TimeServlet destroyed");
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    @Override
    public String getServletInfo() {
        return "Date_and_TimeServlet - shows system date and time";
    }
}
