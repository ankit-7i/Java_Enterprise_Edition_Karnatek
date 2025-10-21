package com.jee.servlet.basic;

import java.io.PrintWriter;
import java.io.IOException;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.ServletException;

public class HelloServlet implements Servlet
{
    private ServletConfig config;

    @Override
    public void init(ServletConfig config)
    {
        this.config = config;
        System.out.println("init(config) executed");
    }

    @Override
    public void service(ServletRequest req, ServletResponse resp)
            throws ServletException, IOException
    {
        // Printing on server console
        System.out.println("service(req, resp) executed");

        // Printing on client browser
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<h2>Hi! This is Ankit </h2>");
        out.close();
    }

    @Override
    public void destroy()
    {
        System.out.println("destroy() executed");
    }

    @Override
    public ServletConfig getServletConfig()
    {
        System.out.println("getServletConfig() executed");
        return config;
    }

    @Override
    public String getServletInfo()
    {
        System.out.println("getServletInfo() executed");
        return "HelloServlet - implements Servlet interface";
    }
}
