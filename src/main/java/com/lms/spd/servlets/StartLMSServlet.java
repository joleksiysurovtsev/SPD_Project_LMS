package com.lms.spd.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StartLMSServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(StartLMSServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("src/main/webapp/web/display.jsp");
        requestDispatcher.forward(req, resp);
    }
}
