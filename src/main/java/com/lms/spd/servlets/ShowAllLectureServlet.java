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
import java.util.List;

@WebServlet(urlPatterns = {"/all"})
public class ShowAllLectureServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        this.process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        this.process(request, response);
    }


    protected void process(HttpServletRequest req, HttpServletResponse resp) {
        LectureServiceImpl service = new LectureServiceImpl();
        List<Lecture> lectures = service.getItems();
         RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/viewall.jsp");
        req.setAttribute("lectures",lectures);
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

}
