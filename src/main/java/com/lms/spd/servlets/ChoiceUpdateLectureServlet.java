package com.lms.spd.servlets;


import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.services.LectureServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = {"/updateLecture"})
public class ChoiceUpdateLectureServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        this.process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        this.process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) {
        LectureServiceImpl service = new LectureServiceImpl();
        String[] numbers = request.getParameterValues("number");
        Lecture byID = service.getByID(Integer.parseInt(numbers[0]));

        request.setAttribute("lecture", byID);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/choiseupdate.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
