package com.lms.spd.servlets;

import com.lms.spd.services.LectureServiceImpl;
import com.lms.spd.utils.Util;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(urlPatterns = {"/dell"})
public class RemoveLectureServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        this.process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        this.process(request, response);
    }

    /*
       generate the page showing all the request parameters
     */
    private void process(HttpServletRequest request, HttpServletResponse response) {
        LectureServiceImpl service = new LectureServiceImpl();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/remove.jsp");
        String requestAtrName = "message";
        // Get the value of the request parameter
        int[] stringsNumberLecture = Util.getStringsNumberLecture(request.getParameter("number"));
        if (stringsNumberLecture.length > 0) {
            boolean resultByDellete = service.removeItems(stringsNumberLecture);
            if (resultByDellete) {
                request.setAttribute(requestAtrName,
                        "Lecture under ID No. " + Arrays.toString(stringsNumberLecture) + " removed from the database");
            } else {
                request.setAttribute(requestAtrName,
                        "Lecture under ID No. " + Arrays.toString(stringsNumberLecture) + " missing in the database");
            }
        } else {
            request.setAttribute(requestAtrName, "Incorrect input");
        }
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}

