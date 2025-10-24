package com.jee.servlet.basic;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.GenericServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;


@WebServlet("/fs")
public class PageServlet extends GenericServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        PrintWriter pw =res.getWriter();
        res.setContentType("text/html");
        String user_name = req.getParameter("uname");
        String user_password = req.getParameter("upwd");

        System.out.println("UserName : "+user_name);
        System.out.println("password : "+user_password);

    }
}
